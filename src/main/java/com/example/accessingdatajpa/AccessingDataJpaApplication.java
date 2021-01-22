package com.example.accessingdatajpa;

import com.example.accessingdatajpa.models.Employee;
import com.example.accessingdatajpa.models.Job;
import com.example.accessingdatajpa.repository.EmployeeRepository;
import com.example.accessingdatajpa.repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class);
    }

    @Bean
    public CommandLineRunner demo(JobRepository jRepository, EmployeeRepository eRepository) {
        return (args) -> {

            Employee jared = new Employee("Jared", "Larsen");
            Employee jen = new Employee("Jen", "Parker");
//			Employee jj = new Employee("JJ", "Parker");
//			Employee bob = new Employee("Bob", "Larsen");
//
            Job fisherman = new Job("Fisherman", "Florida");
            Job plumber = new Job("Plumber", "Boise");
//			Job welder = new Job("Welder", "Los Angeles");
//			Job hunter = new Job("Hunter", "Alaska");

            jared.setJob(fisherman);
//			fisherman.addEmployee(jen);
//
//			plumber.addEmployee(jj);
//			plumber.addEmployee(bob);
//
//			welder.addEmployee(bob);
//			hunter.addEmployee(jared);
//
//			fisherman.addEmployee(jared);
//
//			jRepository.save(fisherman);
            jRepository.save(plumber);
//			jRepository.save(welder);
//			jRepository.save(hunter);
//
            eRepository.save(jared);
            eRepository.save(jen);
//			eRepository.save(jj);
//			eRepository.save(bob);
        };
    }
}