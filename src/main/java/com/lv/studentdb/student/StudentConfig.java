package com.lv.studentdb.student;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner (StudentRepository repository) {
        return args -> {
            Student lalindra = new Student(
                "Lalindra",
                "lalindra@gmail.com",
                40
            );

            Student alex = new Student(
                "Alex",
                "alex@gmail.com",
                50
            );

            repository.saveAll(
                List.of(lalindra, alex)
            );

        };
    }

}
