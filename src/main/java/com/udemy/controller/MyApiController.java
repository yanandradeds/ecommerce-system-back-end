package com.udemy.controller;


import com.udemy.repository.dto.CardDTO;
import com.udemy.util.CustomUtils;
import com.udemy.vo.PokemonVO;
import com.udemy.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = { "/pokemon", "/pokemon/"})
public class MyApiController {

    @Autowired
    private PokemonService service;
    private final Logger logger = Logger.getLogger(MyApiController.class.getName());

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<PokemonVO> findAll(){
        logger.info("Searching all data from pokemon table");
        return CustomUtils.CustomModelMapper.mappingList(service.findAllPokemon());
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public PokemonVO createCardDatabase(@RequestBody PokemonVO actual){
        logger.info("A criar uma nova inserção na tabela de pokemons");
        CardDTO entity = CustomUtils.CustomModelMapper.mapping(actual);
        return CustomUtils.CustomModelMapper.mapping(service.createRowPokemon(entity));
    }

    @GetMapping(value = "/{index}")
    public PokemonVO findById(@PathVariable Integer index){
        logger.info("finding card by id");
        return CustomUtils.CustomModelMapper.mapping(service.findByIndexPokemon(index));
    }
}
