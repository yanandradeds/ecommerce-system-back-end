package com.udemy.repository;

import com.udemy.repository.dto.CardDTO;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<CardDTO, Integer> {


}
