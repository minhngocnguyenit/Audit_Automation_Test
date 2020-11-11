package com.sm.models;

public class ABCOTD {
    private String id;
    private String name;

    public ABCOTD() {

    }

    public ABCOTD(String name) {
        this.name = name;
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
}
