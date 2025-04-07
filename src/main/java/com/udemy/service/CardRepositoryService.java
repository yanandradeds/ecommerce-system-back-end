package com.udemy.service;

import com.udemy.dto.CardDTO;
import com.udemy.exceptions.ResourceNotFoundException;
import com.udemy.model.Card;
import com.udemy.repository.ApplicationRepository;
import com.udemy.util.CustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CardRepositoryService {

    @Autowired
    private ApplicationRepository repository;

    public void createCard(Card data){
        repository.save(data);
    }

    public Card findByIdCard(Integer id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found resources"));
    }

    public Page<CardDTO> findAllCards(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page,limit);
        return repository.findAll(pageable).map(CustomUtils.CustomModelMapper::mapping);
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
