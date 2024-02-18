package com.people.demo.service;

import com.people.demo.model.Person;
import com.people.demo.repository.PeopleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {

    private final PeopleRepository repository;

    public PeopleService(PeopleRepository repository) {
        this.repository = repository;
    }

    public List<Person> getEveryone() {
        return repository.findAll();
    }

    public Person getPerson(Long id) {
        return repository.getReferenceById(id);
    }

    public Person createPerson(Person person) {
        return repository.save(person);
    }

    public ResponseEntity<Person> replacePerson(Person newPerson, Long id) {
        Person person = repository.getReferenceById(id);
        person.setName(newPerson.getName());
        person.setAge(newPerson.getAge());
        person.setEmail(newPerson.getEmail());
        person.setCountry(newPerson.getCountry());
        final Person updatedPerson = repository.save(person);
        return ResponseEntity.ok(updatedPerson);
    }

    public void deletePerson(Long id) {
        repository.deleteById(id);
    }
}
