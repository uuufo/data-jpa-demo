package com.example.accessingdatajpa.services;

import com.example.accessingdatajpa.exceptions.EmployeeException;
import com.example.accessingdatajpa.exceptions.JobException;
import com.example.accessingdatajpa.models.Employee;
import com.example.accessingdatajpa.models.Job;
import com.example.accessingdatajpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    JobService jobService;

    public Employee getEmployee(long id) throws EmployeeException {
        validateEmployeeId(id);
        return employeeRepository.findById(id);
    }

    public List<Employee> getEmployees(String lastName) {
        if (lastName == null) {
            return employeeRepository.findAll();
        } else {
            return new ArrayList<>(employeeRepository.findByLastName(lastName));
        }
    }

    public Employee persistEmployee(Employee employee) throws EmployeeException, JobException {
        validateEmployee(employee);
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployeesByJobId(Long id) {
        List<Employee> list = employeeRepository.findByJobId(id);
        return employeeRepository.findByJobId(id);
    }

    public Employee updateEmployee(Employee employee) throws EmployeeException {
        validateEmployeeId(employee.getId());
        validateEmployee(employee);
        return employeeRepository.save(employee);
    }

    public String removeEmployee(Long id) throws EmployeeException {
        validateEmployeeId(id);
        employeeRepository.deleteById(id);
        return ("employee.id (" + id + ") successfully fired.");
    }

    public void validateEmployee(Employee employee) throws EmployeeException {
        if (employee.getFirstName() == null) {
            throw new EmployeeException("employee.firstName is required.");
        } else if (employee.getLastName() == null) {
            throw new EmployeeException("employee.lastName is required.");
        } else if (employee.getJob() != null) {
            jobService.validateJob(employee.getJob());
            if (jobService.existsById(employee.getJob().getId())) {
                Optional<Job> job = jobService.getJob(employee.getJob().getId());
                employee.setJob(job.get());
            }
        }
    }

    public void validateEmployeeId(Long id) throws EmployeeException {
        if (id == null) {
            throw new EmployeeException("employee.id required.");
        } else if (!employeeRepository.existsById(id)) {
            throw new EmployeeException("employee.id (" + id + ") does not exist.");
        }
    }
}
