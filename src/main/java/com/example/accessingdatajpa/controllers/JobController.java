package com.example.accessingdatajpa.controllers;

import com.example.accessingdatajpa.exceptions.JobException;
import com.example.accessingdatajpa.models.Job;
import com.example.accessingdatajpa.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/jobs")
public class JobController {

        @Autowired
        private JobRepository jobRepository;

        @GetMapping
        public List<Job> listJobs(){
                return jobRepository.findAll();
        }

        @GetMapping("/{id}")
        public Job findJob(@PathVariable long id) throws JobException {
                Optional<Job> job = jobRepository.findById(id);
                if (job.isPresent()) {
                        return job.get();
                } else {
                        throw new JobException("Couldn't find Job with ID: " + id);
                }
        }
}
