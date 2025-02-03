package com.udemy.service;


import com.udemy.model.Pokemon;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class PokemonService {

    public PokemonService() {
    }

    private final Logger logger = Logger.getLogger(PokemonService.class.getName());
    public Pokemon findByIndex(Integer index) {
        logger.info("o Mock foi selecionado");

        return new Pokemon(1,"Charmander");
    }
}
