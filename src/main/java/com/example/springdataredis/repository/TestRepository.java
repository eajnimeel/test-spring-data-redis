package com.example.springdataredis.repository;

import com.example.springdataredis.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Person, String> {
}
