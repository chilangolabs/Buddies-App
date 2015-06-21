package com.chilangolabs.buddis.Entitys;

/**
 * Created by gorro on 21/06/15.
 */
public class ItemCard {

    String img, title;
    int imgInt;

    public ItemCard(int imgInt, String title) {
        this.imgInt = imgInt;
        this.title = title;
    }

    public ItemCard(String img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImgInt() {
        return imgInt;
    }

    public void setImgInt(int imgInt) {
        this.imgInt = imgInt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
