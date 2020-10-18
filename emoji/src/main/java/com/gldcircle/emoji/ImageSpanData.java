package com.gldcircle.emoji;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;

public class ImageSpanData extends ImageSpan {

    private String value;//图片的路径
    public static final String IMAGE_SPAN_PLACEHOLDER = "[image]";

    public ImageSpanData(@NonNull Drawable drawable, String filePath) {
        super(drawable);
        this.value = filePath;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
