package com.cxyz.commons.widget.sideview;

/**
 * Created by Administrator on 2018/11/20.
 */

public class SortModel {

    private String name;   //显示的数据
    private String sortLetters;  //显示数据拼音的首字母

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}