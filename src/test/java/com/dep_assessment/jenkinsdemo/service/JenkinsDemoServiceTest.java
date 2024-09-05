package com.dep_assessment.jenkinsdemo.service;

import com.dep_assessment.jenkinsdemo.dto.EntityForCrudDto;
import com.dep_assessment.jenkinsdemo.entity.EntityForCrud;
import com.dep_assessment.jenkinsdemo.repository.JenkinsDemoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class JenkinsDemoServiceTest {

    @Mock
    private JenkinsDemoRepository jenkinsDemoRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private JenkinsDemoService jenkinsDemoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveNewEntity() {
        EntityForCrudDto dto = new EntityForCrudDto();
        dto.setId("12345");
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setAge(30);
        dto.setEmail("john.doe@example.com");

        EntityForCrud entity = new EntityForCrud();
        entity.setId("12345");
        entity.setFirstName("John");
        entity.setLastName("Doe");
        entity.setAge(30);
        entity.setEmail("john.doe@example.com");

        when(objectMapper.convertValue(dto, EntityForCrud.class)).thenReturn(entity);
        when(jenkinsDemoRepository.save(any(EntityForCrud.class))).thenReturn(entity);

        EntityForCrud savedEntity = jenkinsDemoService.saveNewEntity(dto);

        assertNotNull(savedEntity);
        assertEquals("12345", savedEntity.getId());
        assertEquals("John", savedEntity.getFirstName());
        verify(jenkinsDemoRepository, times(1)).save(any(EntityForCrud.class));
    }

    @Test
    void testUpdateEntity() throws BadRequestException {
        EntityForCrudDto dto = new EntityForCrudDto();
        dto.setId("12345");
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setAge(30);
        dto.setEmail("john.doe@example.com");

        EntityForCrud existingEntity = new EntityForCrud();
        existingEntity.setId("12345");
        existingEntity.setFirstName("OldName");
        existingEntity.setLastName("OldLastName");
        existingEntity.setAge(25);
        existingEntity.setEmail("old.email@example.com");

        when(jenkinsDemoRepository.findById(anyString())).thenReturn(Optional.of(existingEntity));
        when(jenkinsDemoRepository.save(any(EntityForCrud.class))).thenReturn(existingEntity);

        EntityForCrud updatedEntity = jenkinsDemoService.updateEntity(dto);

        assertNotNull(updatedEntity);
        assertEquals("John", updatedEntity.getFirstName());
        verify(jenkinsDemoRepository, times(1)).save(any(EntityForCrud.class));
    }

    @Test
    void testGetById() throws BadRequestException {
        EntityForCrud entity = new EntityForCrud();
        entity.setId("12345");
        entity.setFirstName("John");
        entity.setLastName("Doe");
        entity.setAge(30);
        entity.setEmail("john.doe@example.com");

        when(jenkinsDemoRepository.findById(anyString())).thenReturn(Optional.of(entity));

        EntityForCrud foundEntity = jenkinsDemoService.getById("12345");

        assertNotNull(foundEntity);
        assertEquals("John", foundEntity.getFirstName());
        verify(jenkinsDemoRepository, times(1)).findById(anyString());
    }

    @Test
    void testGetByIdNotFound() {
        when(jenkinsDemoRepository.findById(anyString())).thenReturn(Optional.empty());

        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
            jenkinsDemoService.getById("12345");
        });

        assertEquals("No Such data found", thrown.getMessage());
    }

    @Test
    void testGetAllEntities() {
        EntityForCrud entity = new EntityForCrud();
        entity.setId("12345");
        entity.setFirstName("John");
        entity.setLastName("Doe");
        entity.setAge(30);
        entity.setEmail("john.doe@example.com");

        when(jenkinsDemoRepository.findAll()).thenReturn(Collections.singletonList(entity));

        List<EntityForCrud> entities = jenkinsDemoService.getAllEntities();

        assertNotNull(entities);
        assertEquals(1, entities.size());
        assertEquals("John", entities.get(0).getFirstName());
        verify(jenkinsDemoRepository, times(1)).findAll();
    }

    @Test
    void testDeleteEntity() throws BadRequestException {
        when(jenkinsDemoRepository.findById(anyString())).thenReturn(Optional.of(new EntityForCrud()));

        jenkinsDemoService.deleteEntity("12345");

        verify(jenkinsDemoRepository, times(1)).deleteById(anyString());
    }

    @Test
    void testDeleteEntityNotFound() {
        when(jenkinsDemoRepository.findById(anyString())).thenReturn(Optional.empty());

        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
            jenkinsDemoService.deleteEntity("12345");
        });

        assertEquals("No Such data found", thrown.getMessage());
    }
}
