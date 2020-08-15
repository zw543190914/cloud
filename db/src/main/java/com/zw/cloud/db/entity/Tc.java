package com.zw.cloud.db.entity;

import java.io.Serializable;

public class Tc implements Serializable {
    private Integer id;

    private Integer first;

    private Integer second;

    private Integer third;

    private Integer four;

    private Integer five;

    private Integer blueFirst;

    private Integer blueSecond;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public Integer getThird() {
        return third;
    }

    public void setThird(Integer third) {
        this.third = third;
    }

    public Integer getFour() {
        return four;
    }

    public void setFour(Integer four) {
        this.four = four;
    }

    public Integer getFive() {
        return five;
    }

    public void setFive(Integer five) {
        this.five = five;
    }

    public Integer getBlueFirst() {
        return blueFirst;
    }

    public void setBlueFirst(Integer blueFirst) {
        this.blueFirst = blueFirst;
    }

    public Integer getBlueSecond() {
        return blueSecond;
    }

    public void setBlueSecond(Integer blueSecond) {
        this.blueSecond = blueSecond;
    }
}