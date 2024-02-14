package com.people.demo.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter
    private String name;
    @Getter
    private int age;
    @Getter
    private String country;
    @Getter
    private String email;

    public Person() {}

    public Person(String name, int age, String country, String email) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }
}
