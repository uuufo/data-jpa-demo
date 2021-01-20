package com.example.accessingdatajpa.controllers;

import com.example.accessingdatajpa.models.Employee;
import com.example.accessingdatajpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> listEmployees(@RequestParam(value = "lastname", required = false, defaultValue = "") String lastName){
        if (lastName.equals("")) {
            return employeeRepository.findAll();
        } else {
            return new ArrayList<>(employeeRepository.findByLastName(lastName));
        }
    }
}
