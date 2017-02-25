package com.haynhanh.troll.model.item;

/**
 * Created by thieumao on 2/26/17.
 */

public class ItemView {

    private String name;
    private String image;

    public ItemView() {
    }

    public ItemView(String name, String image) {
        this.name = name;
        this.image = image;
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
