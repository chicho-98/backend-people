package com.people.demo.controller;

import com.people.demo.model.Person;
import com.people.demo.service.PeopleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PeopleController {
    private final PeopleService service;

    public PeopleController(PeopleService service) {
        this.service = service;
    }

    @GetMapping("/people")
    public List<Person> getEveryone() {
        return service.getEveryone();
    }

    @GetMapping("/people/{id}")
    public Person getPerson(@PathVariable Long id) {
        return service.getPerson(id);
    }

    @PostMapping("/people")
    public Person createPerson(@RequestBody Person person) {
        return service.createPerson(person);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        return service.replacePerson(newPerson, id);
    }

    @DeleteMapping("/people/{id}")
    public void deletePerson(@PathVariable Long id) {
        service.deletePerson(id);
    }
}
