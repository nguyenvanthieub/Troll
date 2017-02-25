package com.haynhanh.troll.model.item;

import java.util.List;

/**
 * Created by thieumao on 2/25/17.
 */

public class ItemDetail {

    private Integer id;
    private String title;
    private String url;
    private List<ItemPart> parts = null;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ItemPart> getParts() {
        return parts;
    }

    public void setParts(List<ItemPart> parts) {
        this.parts = parts;
    }
}
