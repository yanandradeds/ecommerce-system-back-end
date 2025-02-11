package com.udemy.service;

import com.udemy.exceptions.ResourceNotFoundException;
import com.udemy.repository.dto.CardDTO;
import com.udemy.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PokemonService {

    @Autowired
    private ApplicationRepository repository;

    public CardDTO createRowPokemon(CardDTO data){
       return repository.save(data);
    }

    public CardDTO findByIndexPokemon(Integer index){
        return repository.findById(index).orElseThrow(() -> new ResourceNotFoundException("Not found resources"));
    }

    public ArrayList<CardDTO> findAllPokemon(){
        ArrayList<CardDTO> cards = new ArrayList<>();
        repository.findAll().forEach(cards::add);

        return cards;
    }

    public void updateCard(CardDTO data) {
        CardDTO toUpdate = repository.findById(data.getId()).orElseThrow(() -> new ResourceNotFoundException("Not found resources"));
        toUpdate.setName(data.getName());
        repository.save(toUpdate);
    }

    public void deleteRowCard(Integer index){
        repository.deleteById(index);
    }



}
