package com.example.service;

import java.util.Collection;

import com.example.model.Greetings;


public interface GreetingService {

    Collection<Greetings> findAll();

    Greetings findOne(Long id);

    Greetings create(Greetings greeting);

    Greetings update(Greetings greeting);

    void delete(Long id);
    
    void evictCache();

}