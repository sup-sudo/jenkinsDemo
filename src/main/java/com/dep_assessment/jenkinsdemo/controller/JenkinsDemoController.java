package com.dep_assessment.jenkinsdemo.controller;

import com.dep_assessment.jenkinsdemo.dto.EntityForCrudDto;
import com.dep_assessment.jenkinsdemo.entity.EntityForCrud;
import com.dep_assessment.jenkinsdemo.service.JenkinsDemoService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jenkins-demo")
public class JenkinsDemoController {

    @Autowired
    JenkinsDemoService jenkinsDemoService;


    @PostMapping()
    public EntityForCrud saveNewEntity(@RequestBody EntityForCrudDto entityForCrudDto){
        return jenkinsDemoService.saveNewEntity(entityForCrudDto);
    }

    @GetMapping("/list")
    public List<EntityForCrud> getAllEntities(){
        return jenkinsDemoService.getAllEntities();
    }

    @GetMapping()
    public EntityForCrud getEntityById(@RequestParam(name = "id") String id) throws BadRequestException {
        return jenkinsDemoService.getById(id);
    }

    @PutMapping
    public EntityForCrud updateEntity(@RequestBody EntityForCrudDto entityForCrudDto) throws BadRequestException {
        return jenkinsDemoService.updateEntity(entityForCrudDto);
    }

    @DeleteMapping
    public void deleteEntity(@RequestParam(name = "id") String id) throws BadRequestException {
        jenkinsDemoService.deleteEntity(id);
    }

}
