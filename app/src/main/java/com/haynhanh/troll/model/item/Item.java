package com.haynhanh.troll.model.item;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thieumao on 2/25/17.
 */

public class Item implements Serializable {

    private Integer status;
    private String message;
    private List<ItemDetail> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ItemDetail> getData() {
        return data;
    }

    public void setData(List<ItemDetail> data) {
        this.data = data;
    }

}
