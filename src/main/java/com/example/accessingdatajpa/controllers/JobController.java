package com.example.accessingdatajpa.controllers;

import com.example.accessingdatajpa.exceptions.JobException;
import com.example.accessingdatajpa.models.Job;
import com.example.accessingdatajpa.repository.JobRepository;
import com.example.accessingdatajpa.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobRepository jobRepository;

    @GetMapping
    public List<Job> listJobs(@RequestParam(value = "location", required = false) Optional<String> location) {
        return jobService.getJobs(location);
    }

    @GetMapping("/{id}")
    public Job findJob(@PathVariable long id) throws JobException {
        return jobService.getJob(id).get();
    }

    @PostMapping(consumes = "application/json")
    public Job postJob(@RequestBody Job job) throws JobException {
        return jobService.persistJob(job);
    }

    @PutMapping
    public Job putJob(@RequestBody Job job) throws JobException {
        return jobService.updateJob(job);
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        return jobService.removeJob(id);
    }
}
