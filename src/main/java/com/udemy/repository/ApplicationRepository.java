package com.udemy.repository;

import com.udemy.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Card, Integer> {

}