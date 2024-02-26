package com.people.demo.controller;

import com.people.demo.model.Person;
import com.people.demo.service.PeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PeopleController {
    private final PeopleService service;

    public PeopleController(PeopleService service) {
        this.service = service;
    }

    @GetMapping("/person")
    public List<Person> getEveryone() {
        return service.getEveryone();
    }

    @GetMapping("/person/{id}")
    public Optional<Person> getPerson(@PathVariable Long id) {
        return service.getPerson(id);
    }

    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person person) {
        return service.createPerson(person);
    }

    @PutMapping("/person/{id}")
    public Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        return service.replacePerson(newPerson, id);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable Long id) {
        service.deletePerson(id);
    }
}
