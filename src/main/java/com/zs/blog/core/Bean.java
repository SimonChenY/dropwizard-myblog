package com.zs.blog.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by luckyzhou on 3/17/16.
 */
@XmlRootElement
public class Bean {
    @JsonProperty
    private String value;

    public Bean() {
    }

    public Bean(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
