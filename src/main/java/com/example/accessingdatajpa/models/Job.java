package com.example.accessingdatajpa.models;

import javax.persistence.*;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "job_generator")
    private Long id;
    private String name;
    private String location;

    protected Job() {
    }

    public Job(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return String.format(
                "Job[id=%d, name='%s', location='%s']",
                id, name, location);
    }
}
