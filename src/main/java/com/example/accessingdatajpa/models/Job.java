package com.example.accessingdatajpa.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "job_generator")
    private Long id;
    private String name;
    private String location;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    Set<Employee> employees = new HashSet<>();

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

    public void addEmployee(Employee employee) {
        employees.add(employee);
        employee.setJob(this);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
