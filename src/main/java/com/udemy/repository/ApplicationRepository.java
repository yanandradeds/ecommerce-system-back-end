package com.udemy.repository;

import com.udemy.model.Pokemon;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Pokemon, Integer> {


}
