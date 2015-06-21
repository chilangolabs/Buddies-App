package com.chilangolabs.buddis.Entitys;

/**
 * Created by gorro on 21/06/15.
 */
public class ItemProfesionals {

    String name, id;
    String image;

    public ItemProfesionals(String name, String id, String image) {
        this.name = name;
        this.id = id;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
