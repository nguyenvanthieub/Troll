package com.haynhanh.troll.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thieumao on 2/25/17.
 */

public class Item {

    private Integer status;
    private String message;
    private List<ItemDetail> data = null;
    private Map<String, Object> additionalProperties = new HashMap<>();

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

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
