package com.sunchs.store.framework.bean;

import java.util.List;

public class SelectChildData {

    private Integer id;
    private Integer pid;
    private String title;
    private boolean isSelected;
    private List<SelectChildData> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public List<SelectChildData> getChildren() {
        return children;
    }

    public void setChildren(List<SelectChildData> children) {
        this.children = children;
    }
}
