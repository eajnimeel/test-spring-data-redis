package com.example.springdataredis.service;

import com.example.springdataredis.model.Person;
import com.example.springdataredis.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private TestRepository repository;

    @Autowired

    public PersonServiceImpl(TestRepository repository) {
        this.repository = repository;
    }

    public Optional<Person> findPerson(String id) {
        return repository.findById(id);
    }

    public Iterable<Person> findAllPerson(List<String> ids) {
        return repository.findAllById(ids);
    }

    public Iterable<Person> findAllPerson() {
        return repository.findAll();
    }

    public void savePerson(Person person) {
        Person save = repository.save(person);
    }

    public void saveAllPerson(List<Person> person) {
        repository.saveAll(person);
    }

    public void deletePerson(Person person) {
        repository.delete(person);
    }

    public void deletePerson(List<Person> personList) {
        repository.deleteAll(personList);
    }

    public void deleteAllPerson() {
        repository.deleteAll();
    }

    public boolean existsPerson(Person person) {
        return repository.existsById(person.getId());
    }

    public long countAllPerson() {
        return repository.count();
    }
}
