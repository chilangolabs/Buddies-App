package com.chilangolabs.buddis.Entitys;

import org.json.JSONObject;

/**
 * Created by gorro on 21/06/15.
 */
public class ItemListHome {

    JSONObject json;

    public ItemListHome(JSONObject json) {
        this.json = json;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }
}
