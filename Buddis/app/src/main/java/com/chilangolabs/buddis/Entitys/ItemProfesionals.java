package com.chilangolabs.buddis.Entitys;

import org.json.JSONObject;

/**
 * Created by gorro on 21/06/15.
 */
public class ItemProfesionals {

    String name, id;
    String image;
    JSONObject json;

    public ItemProfesionals(String name, String id, String image, JSONObject json) {
        this.name = name;
        this.id = id;
        this.image = image;
        this.json = json;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
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
