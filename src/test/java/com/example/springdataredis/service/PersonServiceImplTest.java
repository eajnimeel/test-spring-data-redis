package com.example.springdataredis.service;

import com.example.springdataredis.model.Person;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceImplTest {

    @Autowired
    private PersonService personService;

    Person person;

    @Before
    public void init() {
        personService.deleteAllPerson();
        person = new Person();
        person.setId("1");
        person.setAge(40);
        person.setFirstName("Minjae");
        person.setLastName("Lee");
    }

    @Test
    public void findPerson() {
        personService.savePerson(person);
        Optional<Person> person = personService.findPerson("1");
        Person resultPerson = person.get();
        assertThat(resultPerson).isInstanceOf(Person.class);
        assertThat(resultPerson.getId()).isEqualTo(this.person.getId());
    }

    @Test
    public void findAllPerson() {

    }

    @Test
    public void findAllPerson1() {
    }

    @Test
    public void savePerson() {
    }

    @Test
    public void saveAllPerson() {
    }

    @Test
    public void deletePerson() {
    }

    @Test
    public void deletePerson1() {
    }

    @Test
    public void deleteAllPerson() {
    }

    @Test
    public void existsPerson() {
    }

    @Test
    public void countAllPerson() {
    }
}
