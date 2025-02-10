package com.udemy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pokemon_table")
public class Pokemon {

    @Id
    @Column(name = "id_pokemon")
    private Integer indexPokedex;
    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private Integer remaing;

    public Integer getIndexPokedex() {
        return indexPokedex;
    }

    public void setIndexPokedex(Integer indexPokedex) {
        this.indexPokedex = indexPokedex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice(){
        return price;
    }

    public void setPrice(Double price){
        this.price = price;
    }
}
