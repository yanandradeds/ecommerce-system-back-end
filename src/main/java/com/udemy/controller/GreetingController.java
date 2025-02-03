package com.udemy.controller;

import com.udemy.model.Pokemon;
import com.udemy.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon")
public class GreetingController {

    @Autowired
    private PokemonService service;


    @RequestMapping(value = "/{index}", method = RequestMethod.GET)
    public Pokemon searchPokemon(@PathVariable(value = "index")Integer index){
        return service.findByIndex(index);
    }
}
