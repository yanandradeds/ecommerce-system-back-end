package com.udemy.service;

import com.udemy.dto.CardDTO;
import com.udemy.exceptions.ResourceNotFoundException;
import com.udemy.model.Card;
import com.udemy.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RepositoryService {

    @Autowired
    private ApplicationRepository repository;

    public void createCard(Card data){
        repository.save(data);
    }

    public Card findByIdCard(Integer id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found resources"));
    }

    public ArrayList<Card> findAllCards(){
        ArrayList<Card> cards = new ArrayList<>();
        repository.findAll().forEach(cards::add);

        return cards;
    }

    public void updateCard(CardDTO data) {
        Card toUpdate = repository.findById(data.getId()).orElseThrow(() -> new ResourceNotFoundException("Not found resources"));
        toUpdate.setName(data.getName());
        toUpdate.setPrice(data.getPrice());
        toUpdate.setQuantity(data.getQuantity());
        repository.save(toUpdate);
    }

    public void deleteRowCard(Integer id){
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found any card for delete"));
        repository.deleteById(id);
    }



}
