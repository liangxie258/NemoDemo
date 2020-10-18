package com.gldcircle.emoji.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gldcircle.emoji.EmojiManager;
import com.gldcircle.emoji.EmojiconHandler;
import com.gldcircle.emoji.ImageSpanData;
import com.gldcircle.emoji.R;
import com.gldcircle.emoji.data.JsonData;
import com.gldcircle.emoji.util.BitmapUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.File;
import java.util.LinkedList;
import java.util.List;


@SuppressLint("AppCompatCustomView")
public class EmojiEdittext extends EditText {

    private static final String TAG = "EmojiEdittext";

    private View view = null;
    private int mEmojiconSize;
    private int mEmojiconAlignment;
    private int mEmojiconTextSize;
    private boolean mUseSystemDefault = false;

    public EmojiEdittext(Context context) {
        super(context);
        initView(null);
    }

    public EmojiEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public EmojiEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        mEmojiconSize = (int) getTextSize();
        mEmojiconTextSize = (int) getTextSize();
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Emojicon);
            mEmojiconSize = (int) a.getDimension(R.styleable.Emojicon_emojiconSize, getTextSize());
            mEmojiconAlignment = a.getInt(R.styleable.Emojicon_emojiconAlignment, DynamicDrawableSpan.ALIGN_BASELINE);
            mUseSystemDefault = a.getBoolean(R.styleable.Emojicon_emojiconUseSystemDefault, false);
            a.recycle();
        }
        setText(getText());
        addTextChangedListener(textWatcher);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (view != null)
                view.setEnabled(!editable.toString().isEmpty());//输入完不为空内容才可以点击
            int start = getSelectionStart();
            int end = getSelectionEnd();
            removeTextChangedListener(this);//取消监听
            updateText();
//            CharSequence cs = EmojiManager.parse(editable.toString(), getTextSize());
//            setText(cs, TextView.BufferType.SPANNABLE);
            setSelection(start, end);
            addTextChangedListener(this);
        }
    };

    public void setEmojiconSize(int pixels) {
        mEmojiconSize = pixels;
        updateText();
    }

    /**
     * 将原有的私有方法updateText改成public方法，方便子类集成改写
     */
    public void updateText() {
        EmojiconHandler.addEmojis(getContext(), getText(), mEmojiconSize, mEmojiconAlignment, mEmojiconTextSize, mUseSystemDefault);
    }

    public void setUseSystemDefault(boolean useSystemDefault) {
        mUseSystemDefault = useSystemDefault;
    }

    /**
     * 未输入的时候让其他控件不能点击
     */
    public void isEnable(View view) {
        this.view = view;
    }

    /**
     * 插入图片
     *
     * @param filePath
     */
    public void insertImage(String filePath) {
        File imageFile = new File(filePath);
        if (!imageFile.exists()) {
            Log.e(TAG, "image file does not exist");
            return;
        }
        //取得原bitmap
        Bitmap resourceBitmap = BitmapUtil.decodeSampledBitmapFromFilePath(filePath, dip2px(getContext(), 80));
        //活的正常显示的bitmap
        Bitmap rotateBitmap = BitmapUtil.rotateBitmap(filePath, resourceBitmap);
        //取得需要显示的Bitmap
        Drawable drawable = new BitmapDrawable(getContext().getResources(), rotateBitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        //删除光标括起来的区域
        removeSelectedContent();
        //构建ImageSpan
        ImageSpanData imageSpanData = new ImageSpanData(drawable, filePath);
        insertImageSpan(imageSpanData);
    }

    /**
     * 插入imageSpan
     */
    void insertImageSpan(ImageSpanData imageSpanData) {
        if (imageSpanData == null) {
            return;
        }

        // 只有正常编辑器插入的ImageSpan才在前面都插入一空行，否则从草稿插入的不再另外加一空行
        if (!isCursorInFirstIndexOfLine()) {
            //插入回车符
            insertStringIntoEditText("\n", getSelectionStart());
        }


        //将bitmap插入到editText中
        SpannableString imageSpannableString = new SpannableString(ImageSpanData.IMAGE_SPAN_PLACEHOLDER);
        imageSpannableString.setSpan(imageSpanData, 0, ImageSpanData.IMAGE_SPAN_PLACEHOLDER.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        insertStringIntoEditText(imageSpannableString, getSelectionStart());

        //在imageSpan后面都插入一空行
        insertStringIntoEditText("\n", getSelectionStart());
    }

    void insertStringIntoEditText(CharSequence content, int pos) {
        Editable editable = getEditableText();//获得文本内容
        if (pos < 0 || pos >= editable.length()) {
            editable.append(content);
            setSelection(editable.length());
        } else {
            editable.insert(pos, content);
            setSelection(pos + content.length());
        }
    }

    /**
     * 在插入blockImage之前，先删除被光标选中的区域
     */
    private void removeSelectedContent() {
        Editable editable = getEditableText();
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();

        if (selectionStart >= selectionEnd) {
            return;
        }

        editable.delete(selectionStart, selectionEnd);
    }

    /**
     * 判断光标是否处于行首
     */
    private boolean isCursorInFirstIndexOfLine() {
        int cursor = getSelectionStart();
        if (cursor < 0 || length() <= 0) {
            return true;
        }

        Editable editable = getEditableText();
        return cursor == 0 || editable.charAt(cursor - 1) == '\n';
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        Log.i(TAG, "dxValue:" + (dpValue * scale));
        return (int) (dpValue * scale);
    }

    /**
     * 将富文本信息解析成json
     *
     * @return
     */
    public String getJsonText() {
        int index = getSelectionStart();
        if (index < 0 || length() <= 0) {
            return null;
        }
        Editable editable = getText();
        //取得所有图片的span
        ImageSpanData[] imageSpans = editable.getSpans(0, editable.length(), ImageSpanData.class);
        List<JsonData> jsonDataList = new LinkedList<JsonData>();
        //解析过程
        for (int i = 0; i < imageSpans.length; i++) {
            ImageSpanData currentImageSpanData = imageSpans[i];
            if (i == 0) {//如果是第一个imagespan，则读取之前的内容
                int startImageIndex = editable.getSpanStart(currentImageSpanData);
                CharSequence charSequence = editable.subSequence(0, startImageIndex);
                addTextJson(jsonDataList, charSequence);
            } else {
                ImageSpanData beforeImageSpanData = imageSpans[i - 1];
                //上一个imagespan的结束位置
                int beforeImageIndex = editable.getSpanEnd(beforeImageSpanData);
                int startImageIndex = editable.getSpanStart(currentImageSpanData);
                CharSequence charSequence = editable.subSequence(beforeImageIndex, startImageIndex);
                addTextJson(jsonDataList, charSequence);
            }
            //插入图片json
            JsonData imageJsonData = new JsonData(JsonData.IMAGE_TYPE, currentImageSpanData.getValue());
            jsonDataList.add(imageJsonData);
            if (i == imageSpans.length - 1) {//如果是最后一个imageSpan
                int endImageIndex = editable.getSpanEnd(currentImageSpanData);
                CharSequence charSequence = editable.subSequence(endImageIndex, editable.length());
                addTextJson(jsonDataList, charSequence);
            }
        }
        return new Gson().toJson(jsonDataList);
    }

    public void parseJsonText(String json) {
        List<JsonData> jsonDataList = new Gson().fromJson(json, new TypeToken<List<JsonData>>() {
        }.getType());
        if (jsonDataList != null && jsonDataList.size() > 0) {
            for (JsonData jsonData : jsonDataList) {
                Editable editable = getText();
                int cursor = getSelectionStart();
                if (cursor < 0) {
                    cursor = 0;
                }
                if (jsonData.getType() == JsonData.TEXT_TYPE) {
                    editable.insert(cursor, jsonData.getValue());
                } else if (jsonData.getType() == JsonData.IMAGE_TYPE) {
                    insertImage(jsonData.getValue());
                }
                setSelection(editable.length());
            }
        }
    }

    /**
     * 添加文本的jsonData
     */
    private void addTextJson(List<JsonData> jsonDataList, CharSequence charSequence) {
        String textJson = removeEnter(charSequence);
        if (textJson != null && textJson.length() > 0) {
            JsonData textJsonData = new JsonData(JsonData.TEXT_TYPE, textJson);
            jsonDataList.add(textJsonData);
        }
    }

    //去除文本
    private String removeEnter(CharSequence charSequence) {
        if (charSequence != null && charSequence.length() > 0) {
            String text = charSequence.toString();
            if (text.contains("\n")) {//如果包含回车符
                if (text.startsWith("\n")) {//如果第一个字符是回车符，则去除,如果包含回车符说明字符串长度至少为1
                    if (text.length() > 1) {
                        text = text.substring(1, text.length());
                        if (text.endsWith("\n")) {
                            if (text.length() > 1) {
                                text = text.substring(0, text.length() - 1);
                                return text;
                            } else {
                                return null;
                            }
                        } else {
                            return text;
                        }
                    } else {
                        return null;
                    }
                } else {
                    if (text.endsWith("\n")) {
                        if (text.length() > 1) {
                            text = text.substring(0, text.length() - 1);
                            return text;
                        } else {
                            return null;
                        }
                    } else {
                        return text;
                    }
                }
            } else {
                return text;
            }
        }
        return null;
    }

}
