package com.people.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private int age;
    @Setter
    @Getter
    private String country;
    @Setter
    @Getter
    private String email;

    public Person() {}

    public Person(String name, int age, String country, String email) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.email = email;
    }

}
