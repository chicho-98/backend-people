package com.people.demo.service;

import com.people.demo.exception.ResourceNotFoundException;
import com.people.demo.model.Person;
import com.people.demo.repository.PeopleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {

    private final PeopleRepository repository;

    public PeopleService(PeopleRepository repository) {
        this.repository = repository;
    }

    public List<Person> getEveryone() {
        return repository.findAll();
    }

    public Optional<Person> getPerson(Long id) {
        Optional<Person> person = repository.findById(id);
        if (person.isPresent()) {
            return person;
        }
        throw new ResourceNotFoundException("No such person with id " + id);
    }

    public Person createPerson(Person person) {
        return repository.save(person);
    }

    public Person replacePerson(Person newPerson, Long id) {
        Person person = repository.getReferenceById(id);
        person.setName(newPerson.getName());
        person.setAge(newPerson.getAge());
        person.setEmail(newPerson.getEmail());
        person.setCountry(newPerson.getCountry());
        return repository.save(person);
    }

    public void deletePerson(Long id) {
        repository.deleteById(id);
    }
}
