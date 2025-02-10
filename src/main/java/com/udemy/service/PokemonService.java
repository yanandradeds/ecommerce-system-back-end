package com.udemy.service;

import com.udemy.exceptions.ResourceNotFoundException;
import com.udemy.model.Pokemon;
import com.udemy.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PokemonService {

    @Autowired
    private ApplicationRepository repository;

    public Pokemon createRowPokemon(Pokemon data){
       return repository.save(data);
    }

    public Pokemon findByIndexPokemon(Integer index){
        return repository.findById(index).orElseThrow(() -> new ResourceNotFoundException("Not found resources"));
    }

    public ArrayList<Pokemon> findAllPokemon(){
        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
        repository.findAll().forEach(pokemons::add);

        return pokemons;
    }

    public void updatePokemon(Pokemon data) {
        Pokemon toUpdate = repository.findById(data.getIndexPokedex()).orElseThrow(() -> new ResourceNotFoundException("Not found resources"));
        toUpdate.setName(data.getName());
        repository.save(toUpdate);
    }

    public void deleteRowPokemon(Integer index){
        repository.deleteById(index);
    }



}
