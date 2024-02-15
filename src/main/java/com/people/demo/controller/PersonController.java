package com.people.demo.controller;

import com.people.demo.PeopleRepository;
import com.people.demo.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PersonController {

    private final PeopleRepository repository;

    PersonController(PeopleRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/people")
    public List<Person> getEverybody() {
        return repository.findAll();
    }

    @GetMapping("/people/{id}")
    public Optional<Person> getPerson(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/people")
    public Person createPerson(@RequestBody Person person) {
        return repository.save(person);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable Long id) {
        Optional<Person> personOptional = repository.findById(id);
        if (personOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        person.setId(id);
        repository.save(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/people/{id}")
    public void deletePerson(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
