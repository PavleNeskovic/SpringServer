package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.User;
import com.example.model.User;
import com.example.repository.GreetingRepository;
import com.example.repository.UserRepository;

@Service
@Transactional(
		propagation = Propagation.SUPPORTS,
		readOnly = true)
public class UserServiceBean {

	@Autowired
	private UserRepository userRepository;
   
    
    public Collection<User> findAll() {

        Collection<User> greetings = userRepository.findAll();

        return greetings;
    }


    @Cacheable(value = "greetings",
    		   key = "#id")
    public User findOne(Long id) {
    	
        User greeting = userRepository.findOne(id);

        return greeting;
    }


    @Transactional(
    		propagation = Propagation.REQUIRED,
    		readOnly = false
    		)
    @CachePut(value = "greetings",
 		   key = "#result.id")
    public User create(User greeting) {

    	if (greeting.getId() != null) {
    		//fails
			return null;
		}
    	
        User savedGreeting = userRepository.save(greeting);
	//	Triggering rollback
//        if (savedGreeting.getId() == 4L) {
//			throw new RuntimeException("Roll me back");
//		}
        return savedGreeting;
    }


    @Transactional(
    		propagation = Propagation.REQUIRED,
    		readOnly = false
    		)
    @CachePut(value = "greetings",
	   key = "#greetings.id")
    public User update(User greeting) {

    	User greetingPersisted = findOne( greeting.getId());
    	
    	if (greetingPersisted.getId() == null) {
    		//fails
			return null;
		}
    	
    	
        User updatedGreeting = userRepository.save(greeting);

        return updatedGreeting;
    }


    @Transactional(
    		propagation = Propagation.REQUIRED,
    		readOnly = false
    		)
    @CacheEvict(value = "greetings",
	   key = "#id")
    public void delete(Long id) {

        userRepository.delete(id);

}

    @CacheEvict(value = "greetings", allEntries = true)
    public void evictCache(){
    	
    }
}
