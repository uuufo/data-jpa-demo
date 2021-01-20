package com.example.accessingdatajpa.repository;

import java.util.List;

import com.example.accessingdatajpa.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByLastName(String lastName);

    Employee findById(long id);
}