package com.example.accessingdatajpa.repository;

import com.example.accessingdatajpa.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByLocation(String location);

    Job findByName(String name);

}
