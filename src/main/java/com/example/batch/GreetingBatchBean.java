package com.example.batch;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.model.Greetings;
import com.example.service.GreetingService;

@Component
public class GreetingBatchBean {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GreetingService greetingService;
	
//	@Scheduled(
//			cron = "0,30 * * * * *")
	public void cronJob() {
		logger.info("> cronJob");
		
		Collection<Greetings> greetings = greetingService.findAll();
		logger.info("There are {} greetings in the data store",
				greetings.size());
		
		
		logger.info("< cronJob");
		
	}
	

}
