package com.dep_assessment.jenkinsdemo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(value = "JenkinsDemo")
public class EntityForCrud {

    @Id
    private String id;
    private String firstName;
    private String lastName;

    private int age;

    private String email;
}
