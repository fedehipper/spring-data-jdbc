package com.demo.jdbc.domain;

import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

public class Student {

    @Id
    private Long id;

    private String name;
    
    @MappedCollection(idColumn = "student_id")
    private Set<Registration> registrations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Set<Registration> registrations) {
        this.registrations = registrations;
    }

}
