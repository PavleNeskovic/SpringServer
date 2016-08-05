package com.example.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Greetings;
import com.example.repository.GreetingRepository;

@Service
@Transactional(
		propagation = Propagation.SUPPORTS,
		readOnly = true)
public class GreetingServiceBean implements GreetingService {

	@Autowired
	private GreetingRepository greetingsRepository;
   
    @Override
    public Collection<Greetings> findAll() {

        Collection<Greetings> greetings = greetingsRepository.findAll();

        return greetings;
    }

    @Override
    @Cacheable(value = "greetings",
    		   key = "#id")
    public Greetings findOne(Long id) {
    	
        Greetings greeting = greetingsRepository.findOne(id);

        return greeting;
    }

    @Override
    @Transactional(
    		propagation = Propagation.REQUIRED,
    		readOnly = false
    		)
    @CachePut(value = "greetings",
 		   key = "#result.id")
    public Greetings create(Greetings greeting) {

    	if (greeting.getId() != null) {
    		//fails
			return null;
		}
    	
        Greetings savedGreeting = greetingsRepository.save(greeting);
	//	Triggering rollback
//        if (savedGreeting.getId() == 4L) {
//			throw new RuntimeException("Roll me back");
//		}
        return savedGreeting;
    }

    @Override
    @Transactional(
    		propagation = Propagation.REQUIRED,
    		readOnly = false
    		)
    @CachePut(value = "greetings",
	   key = "#greetings.id")
    public Greetings update(Greetings greeting) {

    	Greetings greetingPersisted = findOne(greeting.getId());
    	
    	if (greetingPersisted.getId() == null) {
    		//fails
			return null;
		}
    	
    	
        Greetings updatedGreeting = greetingsRepository.save(greeting);

        return updatedGreeting;
    }

    @Override
    @Transactional(
    		propagation = Propagation.REQUIRED,
    		readOnly = false
    		)
    @CacheEvict(value = "greetings",
	   key = "#id")
    public void delete(Long id) {

        greetingsRepository.delete(id);

}

    @Override
    @CacheEvict(value = "greetings", allEntries = true)
    public void evictCache(){
    	
    }
}
