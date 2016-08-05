package com.example.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Greetings {

	@Id
	@GeneratedValue
	private Long id;
	
	private String text;
	
	public Greetings() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setText(String text) {
		this.text = text;
	}


}
