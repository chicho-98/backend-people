package com.people.demo.service;

import com.people.demo.model.Person;
import com.people.demo.repository.PeopleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PeopleServiceTest {

    @Mock
    private PeopleRepository peopleRepository;
    AutoCloseable autoCloseable;
    private PeopleService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable =  MockitoAnnotations.openMocks(this);
        underTest = new PeopleService(peopleRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testGetEveryone() {
        // When
        underTest.getEveryone();

        // Then
        verify(peopleRepository).findAll();
    }

    @Test
    void testGetPersonById() {
        // Arrange
        Long id = 1L;
        Person mockPerson = Mockito.mock(Person.class);
        when(peopleRepository.findById(id)).thenReturn(Optional.of(mockPerson));

        // Act
        Optional<Person> result = underTest.getPerson(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(result.get(), mockPerson);
    }

    @Test
    void testCreatePerson() {
        // Given
        Person person = new Person("Juan", 28, "ARG", "juan@mail.com");

        // When
        underTest.createPerson(person);

        // Then
        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
        verify(peopleRepository).save(personArgumentCaptor.capture());

        Person capturedPerson = personArgumentCaptor.getValue();
        assertEquals(capturedPerson, person);
    }

    @Test
    void testEditPerson() {
        // Arrange
        Long id = 1L;
        Person savedPerson = new Person(id, "Carlos", 42, "ARG", "carlos@mail.com");
        Person editedPerson = new Person(id, "Roberto", 40, "ARG", "roberto@mail.com");
        when(peopleRepository.getReferenceById(id)).thenReturn(savedPerson);

        // Act
        underTest.replacePerson(editedPerson, id);

        // Assert
        ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        verify(peopleRepository).getReferenceById(longArgumentCaptor.capture());
        verify(peopleRepository).save(personArgumentCaptor.capture());

        Person capturedPerson = personArgumentCaptor.getValue();
        Long capturedId = longArgumentCaptor.getValue();

        assertEquals(capturedId, id);
        assertEquals(capturedPerson, editedPerson);
    }

    @Test
    void testDeletePerson() {
        // Given
        Long id = 3L;

        // When
        underTest.deletePerson(id);

        // Then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(peopleRepository).deleteById(longArgumentCaptor.capture());

        Long capturedId = longArgumentCaptor.getValue();
        assertEquals(capturedId, id);
    }
}