package com.dep_assessment.jenkinsdemo.controller;

import com.dep_assessment.jenkinsdemo.dto.EntityForCrudDto;
import com.dep_assessment.jenkinsdemo.entity.EntityForCrud;
import com.dep_assessment.jenkinsdemo.service.JenkinsDemoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class JenkinsDemoControllerTest {

    @Mock
    private JenkinsDemoService jenkinsDemoService;

    @InjectMocks
    private JenkinsDemoController jenkinsDemoController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jenkinsDemoController).build();
    }

    @Test
    public void testSaveNewEntity() throws Exception {
        EntityForCrudDto entityDto = new EntityForCrudDto();
        // Set fields in entityDto as needed

        EntityForCrud savedEntity = new EntityForCrud();
        // Set fields in savedEntity

        when(jenkinsDemoService.saveNewEntity(any(EntityForCrudDto.class))).thenReturn(savedEntity);

        mockMvc.perform(post("/api/v1/jenkins-demo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"12345\","
                                + "\"firstName\": \"John\","
                                + "\"lastName\": \"Doe\","
                                + "\"age\": 30,"
                                + "\"email\": \"john.doe@example.com\"}"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetAllEntities() throws Exception {
        List<EntityForCrud> entities = Arrays.asList(new EntityForCrud(), new EntityForCrud());
        when(jenkinsDemoService.getAllEntities()).thenReturn(entities);

        mockMvc.perform(get("/api/v1/jenkins-demo/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetEntityById() throws Exception {
        EntityForCrud entity = new EntityForCrud();
        entity.setId("1");

        when(jenkinsDemoService.getById(eq("1"))).thenReturn(entity);

        mockMvc.perform(get("/api/v1/jenkins-demo?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void testUpdateEntity() throws Exception {
        EntityForCrudDto entityDto = new EntityForCrudDto();
        // Set fields in entityDto as needed

        EntityForCrud updatedEntity = new EntityForCrud();
        // Set fields in updatedEntity

        when(jenkinsDemoService.updateEntity(any(EntityForCrudDto.class))).thenReturn(updatedEntity);

        mockMvc.perform(put("/api/v1/jenkins-demo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"field1\": \"updatedValue\" }")) // replace with actual fields
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteEntity() throws Exception {
        mockMvc.perform(delete("/api/v1/jenkins-demo?id=1"))
                .andExpect(status().isOk());
    }
}
