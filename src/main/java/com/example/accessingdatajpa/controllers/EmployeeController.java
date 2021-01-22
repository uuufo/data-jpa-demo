package com.example.accessingdatajpa.controllers;

import com.example.accessingdatajpa.exceptions.EmployeeException;
import com.example.accessingdatajpa.exceptions.JobException;
import com.example.accessingdatajpa.models.Employee;
import com.example.accessingdatajpa.repository.EmployeeRepository;
import com.example.accessingdatajpa.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> listEmployees(@RequestParam(value = "lastname", required = false) String lastName) {
        return employeeService.getEmployees(lastName);
    }

    @GetMapping("/{id}")
    public Employee findEmployee(@PathVariable long id) throws EmployeeException {
        return employeeService.getEmployee(id);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) throws EmployeeException, JobException {
        return employeeService.persistEmployee(employee);
    }

    @PutMapping
    public Employee pubEmployee(@RequestBody Employee employee) throws EmployeeException {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) throws EmployeeException {
        return employeeService.removeEmployee(id);
    }

}
