package com.danyalvarez.conversor.numericalsystems.classes;

/**
 * Created by daniel on 06/03/14.
 */
public class ResultItem {

    private String title;
    private String value;

    public ResultItem() {
        this.title = "";
        this.value = "";
    }

    public ResultItem(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
