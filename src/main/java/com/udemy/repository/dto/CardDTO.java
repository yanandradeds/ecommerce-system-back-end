package com.udemy.repository.dto;


import jakarta.persistence.*;


@Entity(name = "pokemon_table")
public class CardDTO {

    @Id
    @Column(name = "id_card_pokemon")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Integer remains = 0;


    @Column(nullable = false)
    Double price = 0d;


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

    public Integer getRemains() {
        return remains;
    }

    public void setRemains(Integer remains) {
        this.remains = remains;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
