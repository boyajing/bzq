package com.nantian.daoExtend;

public class Sequence {
    private String id;

    private Integer nextval;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getNextval() {
        return nextval;
    }

    public void setNextval(Integer nextval) {
        this.nextval = nextval;
    }
}