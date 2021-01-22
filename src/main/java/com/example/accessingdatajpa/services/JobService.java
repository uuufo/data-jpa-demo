package com.example.accessingdatajpa.services;

import com.example.accessingdatajpa.exceptions.JobException;
import com.example.accessingdatajpa.models.Employee;
import com.example.accessingdatajpa.models.Job;
import com.example.accessingdatajpa.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    EmployeeService employeeService;

    public Job persistJob(Job job) throws JobException {
        validateJob(job);
        return jobRepository.save(job);
    }

    public Job updateJob(Job job) {
        validateJobId(job.getId());
        validateJob(job);
        return jobRepository.save(job);
    }

    public String removeJob(Long id) {
        validateJobId(id);
        if (checkConstraints(id)) {
            jobRepository.deleteById(id);
            return ("job.id (" + id + ") deleted successfully.");
        } else {
            throw new JobException("cannot delete job, has constraints (employee(s) still assigned)", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateJob(Job job) {
        if (job.getLocation() == null) {
            throw new JobException("job.location is required.", HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (job.getName() == null) {
            throw new JobException("job.name is required.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public boolean existsById(Long id) {
        return jobRepository.existsById(id);
    }

    public void validateJobId(Long id) {
        if (id == null) {
            throw new JobException("job.id required.", HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (!jobRepository.existsById(id)) {
            throw new JobException("job.id (" + id + ") does not exist.", HttpStatus.BAD_REQUEST);
        }
    }

    public boolean checkConstraints(Long id) {
        List<Employee> list = employeeService.getEmployeesByJobId(id);
        return list.size() == 0;
    }

    public Optional<Job> getJob(long id) throws JobException {
        validateJobId(id);
        return jobRepository.findById(id);
    }

    public List<Job> getJobs(Optional<String> location) {
        if (location.isPresent()) {
            return jobRepository.findByLocation(location.get());
        } else {
            return jobRepository.findAll();
        }
    }
}
