package com.dep_assessment.jenkinsdemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityForCrudDto {

    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
}
