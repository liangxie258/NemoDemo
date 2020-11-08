package com.nemo.demo.basequickadpter;

import com.chad.library.adapter.base.entity.SectionEntity;

public class GroupData extends SectionEntity<String> {

    public GroupData(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public GroupData(String s) {
        super(s);
    }
}
