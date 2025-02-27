package com.udemy.service;

import com.udemy.dto.CardDTO;
import com.udemy.exceptions.ResourceNotFoundException;
import com.udemy.model.CardModel;
import com.udemy.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RepositoryService {

    @Autowired
    private ApplicationRepository repository;

    public void createCard(CardModel data){
        repository.save(data);
    }

    public CardModel findByIdCard(Integer id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found resources"));
    }

    public ArrayList<CardModel> findAllCards(){
        ArrayList<CardModel> cards = new ArrayList<>();
        repository.findAll().forEach(cards::add);

        return cards;
    }

    public void updateCard(CardDTO data) {
        CardModel toUpdate = repository.findById(data.getId()).orElseThrow(() -> new ResourceNotFoundException("Not found resources"));
        toUpdate.setName(data.getName());
        toUpdate.setPrice(data.getPrice());
        toUpdate.setQuantity(data.getQuantity());
        repository.save(toUpdate);
    }

    public void deleteRowCard(Integer index){
        repository.deleteById(index);
    }



}
