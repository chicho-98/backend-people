package com.people.demo.repository;

import com.people.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PeopleRepository extends JpaRepository<Person, Long> {
}
