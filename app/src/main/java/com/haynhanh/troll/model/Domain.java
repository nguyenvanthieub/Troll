package com.haynhanh.troll.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by peter on 2/19/17.
 */

public class Domain {

    private Integer status;
    private String message;
    private List<DomainDetail> data = null;
    private Map<String, Object> additionalProperties = new HashMap<>();

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

    public List<DomainDetail> getData() {
        return data;
    }

    public void setData(List<DomainDetail> data) {
        this.data = data;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
