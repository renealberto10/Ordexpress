package com.pumpkinapplabs.ordexpress.data.model;

public class FavoriteModelClass {
    Integer image;
    String title;
    boolean isSelected = true;

    public FavoriteModelClass(Integer image, String title) {
        this.image = image;
        this.title = title;
        this.isSelected = isSelected;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
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
}
