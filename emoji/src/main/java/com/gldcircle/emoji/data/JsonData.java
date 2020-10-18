package com.gldcircle.emoji.data;

public class JsonData {

    public static final int TEXT_TYPE = 1;
    public static final int IMAGE_TYPE = 2;

    private int type;//1文本  2图片
    private String value;//文本和图片的内容

    public JsonData(int type, String value) {
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
