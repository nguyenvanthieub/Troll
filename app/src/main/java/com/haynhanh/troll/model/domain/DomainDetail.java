package com.haynhanh.troll.model.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by peter on 2/19/17.
 */

public class DomainDetail {

    private Integer id;
    private String name;
    private String domain;
    private String logo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
