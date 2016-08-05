package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Greetings;

@Repository
public interface GreetingRepository extends JpaRepository<Greetings, Long> {

}
