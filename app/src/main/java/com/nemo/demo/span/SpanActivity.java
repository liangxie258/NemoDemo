package com.nemo.demo.span;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.gldcircle.emoji.widget.EmojiBoard;
import com.gldcircle.emoji.widget.EmojiEdittext;
import com.nemo.demo.Constants;
import com.nemo.demo.R;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpanActivity extends AppCompatActivity implements TextWatcher {


    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.btn7)
    Button btn7;
    @BindView(R.id.btn8)
    Button btn8;
    @BindView(R.id.emojiBoard)
    EmojiBoard emojiBoard;
    @BindView(R.id.emojiEdittext)
    EmojiEdittext emojiEdittext;
    @BindView(R.id.btn9)
    Button btn9;
    @BindView(R.id.btn10)
    Button btn10;
    @BindView(R.id.btn11)
    Button btn11;
    @BindView(R.id.btn12)
    Button btn12;

    private int REQUEST_CODE = 0x101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_span);
        ButterKnife.bind(this);

        etContent.addTextChangedListener(this);
        etContent.setMovementMethod(LinkMovementMethod.getInstance());

        emojiBoard.setItemClickListener(new EmojiBoard.OnEmojiItemClickListener() {
            @Override
            public void onClick(String code) {
                if (code.equals("/DEL")) {//点击了删除图标
                    emojiEdittext.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                } else {//插入表情
                    emojiEdittext.getText().insert(emojiEdittext.getSelectionStart(), code);
                }
            }
        });

//        Uri uri=new Uri(new File(""));
    }


    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn10, R.id.btn11, R.id.btn12, R.id.btn13})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
//                CharSequence charSequence = etContent.getText();
//                Spannable spannable = Spannable.Factory.getInstance().newSpannable(charSequence);
//                Drawable smallIcon = getResources().getDrawable(R.mipmap.small);
//                smallIcon.setBounds(0, 0, 50, 50);
//                ImageSpan imageSpan = new ImageSpan(smallIcon);
//                spannable.setSpan(imageSpan, 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                etContent.setText(spannable, TextView.BufferType.EDITABLE);

                Spannable spannable1 = etContent.getText();
                Drawable smallIcon = getResources().getDrawable(R.mipmap.small);
                smallIcon.setBounds(0, 0, 50, 50);
                ImageSpan imageSpan = new ImageSpan(smallIcon);
                spannable1.setSpan(imageSpan, 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            case R.id.btn2:
                Editable editable = etContent.getText();
                editable.setSpan(new URLSpan("http://wwww.baidu.com"), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            case R.id.btn3://插入内容
                Editable editable1 = etContent.getText();
//                editable1.insert(etContent.getSelectionStart(),"[image]");
                editable1.insert(0, "[image]");
                break;
            case R.id.btn4:
                int selectionStart = etContent.getSelectionStart();
                Log.i(Constants.TAG, "光标位置:" + selectionStart);
                break;
            case R.id.btn5://替换
                //-------------替换原则，必须有一个字符与Span对应，setspan(0,0)不会生效

//                CharSequence charSequence = tvContent.getText();
//                Spannable spannable = Spannable.Factory.getInstance().newSpannable(charSequence);
//                Drawable smallIcon = getResources().getDrawable(R.mipmap.small);
//                smallIcon.setBounds(0, 0, 50, 50);
//                ImageSpan imageSpan = new ImageSpan(smallIcon);
//                spannable.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                tvContent.setText(spannable, TextView.BufferType.EDITABLE);

//                tvContent.setSpannableFactory();

                //影响段落的span
//                spannable.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, spannable.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//                tvContent.setText(spannable);

                /**
                 * 影响段落的Span不一定要指定\n换行符的位置，只要指定换行符间隔出的段落的某部分内容，则整个段落都会生生效
                 */
//                spannable.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
//                tvContent.setText(spannable);

                /*
                  插入表情
                 */
//                SpannableString spannableString = new SpannableString("fsdfasfsdffdsafdsfdsfasfdsfsdfasfsafsafasfsafsda");
//                Drawable smallIcon = getResources().getDrawable(R.mipmap.small);
////                smallIcon.setBounds(0, 0, smallIcon.getIntrinsicWidth(), smallIcon.getIntrinsicHeight());
//                smallIcon.setBounds(0, 0, 50, 50);
//                ImageSpan imageSpan = new ImageSpan(smallIcon);
//                spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                tvContent.setText(spannableString);
//                SpannedString spannedString=new SpannedString("");
//                spannedString.
                break;
            case R.id.btn6:
                int length = emojiEdittext.getText().toString().length();
                Log.i(Constants.TAG, "length：" + length);
                break;
            case R.id.btn7:
                String etContentStr = etContent.getText().toString();
                char[] chars = etContentStr.toCharArray();
                Log.i(Constants.TAG, "    length:" + chars.length + "   chars" + chars);
                break;
            case R.id.btn8://显示edittext内容
                CharSequence edContent = etContent.getText();
                String content = new String(edContent.toString());
                tvContent.setText(content);

                break;
            case R.id.btn9:
                //单选
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(true)  //设置是否单选
                        .canPreview(true) //是否可以预览图片，默认为true
                        .start(this, REQUEST_CODE); // 打开相册
                break;
            case R.id.btn10://清空
                emojiEdittext.setText("");
                break;
            case R.id.btn11:
                edContent = emojiEdittext.getText();
                content = new String(edContent.toString());
                tvContent.setText(content);
                break;
            case R.id.btn12://生成json
                String jsonText = emojiEdittext.getJsonText();
                tvContent.setText(jsonText);
                break;
            case R.id.btn13://导入json
                jsonText = tvContent.getText().toString();
                emojiEdittext.parseJsonText(jsonText);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.i(Constants.TAG, "beforeTextChanged:   s" + s + "    start:" + start + "    count" + count + "after:" + after);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.i(Constants.TAG, "onTextChanged:   s" + s + "    start:" + start + "    count" + count);
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.i(Constants.TAG, "onTextChanged:   s" + s);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelector.SELECT_RESULT);

            emojiEdittext.insertImage(images.get(0));

//            /**
//             * 是否是来自于相机拍照的图片，
//             * 只有本次调用相机拍出来的照片，返回时才为true。
//             * 当为true时，图片返回的结果有且只有一张图片。
//             */
//            boolean isCameraImage = data.getBooleanExtra(ImageSelector.IS_CAMERA_IMAGE, false);
        }
    }


}
