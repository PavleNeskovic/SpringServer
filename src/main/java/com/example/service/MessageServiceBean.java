package com.example.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Message;
import com.example.model.User;
import com.example.repository.MessageRepository;

@Service
public class MessageServiceBean {
	
	@Autowired 
	private MessageRepository messageRepository;
	
	public Collection<Message> getAll() {


        Collection<Message> greetings = messageRepository.findAll();

        return greetings;
	}
	
	public Message create(Message greeting) {

    	if (greeting.getId() != null) {
    		//fails
			return null;
		}
    	
        Message savedGreeting = messageRepository.save(greeting);
	//	Triggering rollback
//        if (savedGreeting.getId() == 4L) {
//			throw new RuntimeException("Roll me back");
//		}
        return savedGreeting;
    }
	
}
