package com.dep_assessment.jenkinsdemo.service;

import com.dep_assessment.jenkinsdemo.dto.EntityForCrudDto;
import com.dep_assessment.jenkinsdemo.entity.EntityForCrud;
import com.dep_assessment.jenkinsdemo.repository.JenkinsDemoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JenkinsDemoService {

    @Autowired
    JenkinsDemoRepository jenkinsDemoRepository;

    @Autowired
    ObjectMapper objectMapper;

    //Basic Crud operations performed here


    public EntityForCrud saveNewEntity(EntityForCrudDto entityForCrudDto) {
        EntityForCrud entity = objectMapper.convertValue(entityForCrudDto, EntityForCrud.class);
        return jenkinsDemoRepository.save(entity);
    }

    public EntityForCrud updateEntity(EntityForCrudDto entityForCrudDto) throws BadRequestException {
        Optional<EntityForCrud> entityOptional = jenkinsDemoRepository.findById(entityForCrudDto.getId());
        if(entityOptional.isPresent()){
            EntityForCrud entity = entityOptional.get();
            entity.setFirstName(entityForCrudDto.getFirstName());
            entity.setLastName(entityForCrudDto.getLastName());
            entity.setAge(entityForCrudDto.getAge());
            entity.setEmail(entityForCrudDto.getEmail());
            return jenkinsDemoRepository.save(entity);
        }
        else{
            throw new BadRequestException("No Such data found");
        }
    }


    public EntityForCrud getById(String id) throws BadRequestException {
        Optional<EntityForCrud> entityOptional = jenkinsDemoRepository.findById(id);
        if(entityOptional.isPresent()){
            return entityOptional.get();
        }
        else{
            throw new BadRequestException("No Such data found");
        }
    }

    public List<EntityForCrud> getAllEntities(){
        return jenkinsDemoRepository.findAll();
    }

    public void deleteEntity(String id) throws BadRequestException {
        Optional<EntityForCrud> entityForCrudOptional = jenkinsDemoRepository.findById(id);
        if(entityForCrudOptional.isPresent()){
            jenkinsDemoRepository.deleteById(id);
        }
        else{
            throw new BadRequestException("No Such data found");
        }
    }

}
