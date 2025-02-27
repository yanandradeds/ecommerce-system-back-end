package com.udemy.repository;

import com.udemy.model.CardModel;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<CardModel, Integer> {

}