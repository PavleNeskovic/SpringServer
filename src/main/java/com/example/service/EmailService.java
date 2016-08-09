package com.example.service;

import java.util.concurrent.Future;

import com.example.model.Greetings;

public interface EmailService {
	Boolean send(Greetings greeting);
	
	void sendAsync(Greetings greeting);
	
	Future<Boolean> sendAsyncWithResult(Greetings greeting);
}
