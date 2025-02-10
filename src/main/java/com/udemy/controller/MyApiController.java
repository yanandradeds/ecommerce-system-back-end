package com.udemy.controller;


import com.udemy.repository.ApplicationRepository;
import com.udemy.model.Pokemon;
import com.udemy.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.logging.Logger;

@RestController
@RequestMapping("/pokemon")
public class MyApiController {

    @Autowired
    private PokemonService service;
    private final Logger logger = Logger.getLogger(MyApiController.class.getName());

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Pokemon> findAll(){
        logger.info("Searching all data from pokemon table");
        return service.findAllPokemon();
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Pokemon createPokemonDatabase(@RequestBody Pokemon actual){
        logger.info("A criar uma nova inserção na tabela de pokemons");
        Pokemon entity = new Pokemon();
        entity.setIndexPokedex(actual.getIndexPokedex());
        entity.setName(actual.getName());

        return service.createRowPokemon(entity);
    }

    @GetMapping(value = "/{index}")
    public Pokemon returnByIndex(@PathVariable Integer index){
        return service.findByIndexPokemon(index);
    }
}
