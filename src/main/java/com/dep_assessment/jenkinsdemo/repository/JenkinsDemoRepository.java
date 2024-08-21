package com.dep_assessment.jenkinsdemo.repository;

import com.dep_assessment.jenkinsdemo.entity.EntityForCrud;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JenkinsDemoRepository extends MongoRepository<EntityForCrud, String> {


}
