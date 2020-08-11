package com.sunchs.store.framework.bean;

import java.util.List;

public class TitleValueChildrenData {

    private Integer id;
    private String title;
    private Double value;
    private List<TitleValueData> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public List<TitleValueData> getChildren() {
        return children;
    }

    public void setChildren(List<TitleValueData> children) {
        this.children = children;
    }
}
