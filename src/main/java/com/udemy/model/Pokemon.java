package com.udemy.model;

public class Pokemon {

    private final String name;
    private final Integer indexPokedex;

    public Pokemon( Integer indexPokedex, String name) {
        this.indexPokedex = indexPokedex;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIndexPokedex() {
        return indexPokedex;
    }
}
