package com.example.springdataredis.service;

import com.example.springdataredis.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Optional<Person> findPerson(String id);

    Iterable<Person> findAllPerson(List<String> ids);

    Iterable<Person> findAllPerson();

    void savePerson(Person person);

    void saveAllPerson(List<Person> person);

    void deletePerson(Person person);

    void deletePerson(List<Person> personList);

    void deleteAllPerson();

    boolean existsPerson(Person person);

    long countAllPerson();
}
