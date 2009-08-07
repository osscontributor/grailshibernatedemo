package com.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Person {
    
    private String firstName;
    private String lastName;
    private Integer age;
    
    @Id
    @GeneratedValue
    private Long id;
    private Long version;
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public Long getId() {
        return id;
    }
    
    public Long getVersion() {
        return version;
    }
    
    public void setFirstName(String s) {
        firstName = s;
    }
    
    public void setLastName(String s) {
        lastName = s;
    }
    
    public void setAge(Integer i) {
        age = i;
    }
    
    public void setId(Long l) {
        id = l;
    }
    
    public void setVersion(Long l) {
        version = l;
    }
}