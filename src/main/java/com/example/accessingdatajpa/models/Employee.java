package com.example.accessingdatajpa.models;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "employee_generator")
    private Long id;
    private String firstName;
    private String lastName;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    Job job;

    protected Employee() {}

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
//
//    public Job getJob() {
//        return job;
//    }

    public void setJob(Job job) {
        if (this.job != null) {
            this.job.removeEmployee(this);
        }
        this.job = job;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}