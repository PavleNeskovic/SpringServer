package com.example.web.api;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.example.model.Greetings;
import com.example.service.GreetingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsControler {

	@Autowired
	private GreetingService greetingService;
   

    @RequestMapping(
            value = "/api/greetings",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Greetings>> getGreetings() {

        Collection<Greetings> greetings = greetingService.findAll();

        return new ResponseEntity<Collection<Greetings>>(greetings,
                HttpStatus.OK);
    }
    
    @RequestMapping(
    		value = "/api/greetings/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greetings> getGreeting(
    		@PathVariable("id") Long id){
    	Greetings greeting = greetingService.findOne(id);
    	if (greeting == null) {
			return new ResponseEntity<Greetings>(HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<Greetings>(greeting, HttpStatus.OK);
    }
    
    @RequestMapping(
    		value = "/api/greetings",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greetings> createGreeting(
    		@RequestBody Greetings greeting){
    	Greetings savedGreeting = greetingService.create(greeting);
    	return new ResponseEntity<Greetings>(savedGreeting, HttpStatus.CREATED);
    }
    
    @RequestMapping(
            value = "/api/greetings/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greetings> updateGreeting(
            @RequestBody Greetings greeting) {
        Greetings updatedGreeting = greetingService.update(greeting);
        if (updatedGreeting == null) {
            return new ResponseEntity<Greetings>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Greetings>(updatedGreeting, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/greetings/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Greetings> deleteGreeting(
            @PathVariable("id") Long id) {

        greetingService.delete(id);
       

        return new ResponseEntity<Greetings>(HttpStatus.NO_CONTENT);
}

}

