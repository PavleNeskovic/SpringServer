package com.example.web.api;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import com.example.dto.UserDto;
import com.example.model.Message;
import com.example.model.User;
import com.example.service.EmailService;
import com.example.service.GreetingService;
import com.example.service.MessageServiceBean;
import com.example.service.UserServiceBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsControler {

	@Autowired
	private UserServiceBean userService;
	
	@Autowired
	private MessageServiceBean messageService;
	
    @Autowired
    private EmailService emailService;
   
	 private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(
            value = "/api/greetings",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<User>> getGreetings() {

        Collection<User> greetings = userService.findAll();

        return new ResponseEntity<Collection<User>>(greetings,
                HttpStatus.OK);
    }
    
    
    @RequestMapping(
            value = "/api/greetings/message",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Message>> getMessage() {

        Collection<Message> greetings = messageService.getAll();

        return new ResponseEntity<Collection<Message>>(greetings,
                HttpStatus.OK);
    }
    
    @RequestMapping(
    		value = "/api/greetings/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getGreeting(
    		@PathVariable("id") String id){
    	User greeting = userService.findOne(id);
    	if (greeting == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
    	return new ResponseEntity<User>(greeting, HttpStatus.OK);
    }
    
    @RequestMapping(
    		value = "/api/greetings",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createGreeting(
    		@RequestBody UserDto greeting){
    	UserDto savedGreeting = userService.create(greeting);
    	return new ResponseEntity<UserDto>(savedGreeting, HttpStatus.CREATED);
    }
    
    @RequestMapping(
    		value = "/api/greetings/message",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> createMessage(
    		@RequestBody Message greeting){
    	Message savedGreeting = messageService.create(greeting);
    	return new ResponseEntity<Message>(savedGreeting, HttpStatus.CREATED);
    }
    
    
    @RequestMapping(
            value = "/api/greetings/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateGreeting(
            @RequestBody User greeting) {
        User updatedGreeting = userService.update(greeting);
        if (updatedGreeting == null) {
            return new ResponseEntity<User>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<User>(updatedGreeting, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/greetings/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteGreeting(
            @PathVariable("id") String id) {

        userService.delete(id);
       

        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
}

    /**
     * Web service endpoint to fetch a single Greeting entity by primary key
     * identifier and send it as an email.
     * 
     * If found, the Greeting is returned as JSON with HTTP status 200 and sent
     * via Email.
     * 
     * If not found, the service returns an empty response body with HTTP status
     * 404.
     * 
     * @param id A Long URL path variable containing the Greeting primary key
     *        identifier.
     * @param waitForAsyncResult A boolean indicating if the web service should
     *        wait for the asynchronous email transmission.
     * @return A ResponseEntity containing a single Greeting object, if found,
     *         and a HTTP status code as described in the method comment.
     */
//    @RequestMapping(
//            value = "/api/greetings/{id}/send",
//            method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> sendGreeting(@PathVariable("id") Long id,
//            @RequestParam(
//                    value = "wait",
//                    defaultValue = "false") boolean waitForAsyncResult) {
//
//        logger.info("> sendGreeting id:{}", id);
//
//        User greeting = null;
//
//        try {
//            greeting = userService.findOne(id);
//            if (greeting == null) {
//                logger.info("< sendGreeting id:{}", id);
//                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//            }
//
//            if (waitForAsyncResult) {
//                Future<Boolean> asyncResponse = emailService
//                        .sendAsyncWithResult(greeting);
//                boolean emailSent = asyncResponse.get();
//                logger.info("- greeting email sent? {}", emailSent);
//            } else {
//                emailService.sendAsync(greeting);
//            }
//        } catch (Exception e) {
//            logger.error("A problem occurred sending the Greeting.", e);
//            return new ResponseEntity<User>(
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        logger.info("< sendGreeting id:{}", id);
//        return new ResponseEntity<User>(greeting, HttpStatus.OK);
//}
    
}

