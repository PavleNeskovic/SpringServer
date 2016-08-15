package com.example.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
//@Table(name = "message")
public class Message {
	
	@Id
	@GeneratedValue
	public Long id;
	
	private String text;
	
	@ManyToOne(cascade=CascadeType.ALL)
	public User sender;
	
	@ManyToOne(cascade=CascadeType.ALL)
	public User recever;

	public Message() {
		super();
	}

	public Message(Long id, String text, User sender, User recever) {
		super();
		this.id = id;
		this.text = text;
		this.sender = sender;
		this.recever = recever;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecever() {
		return recever;
	}

	public void setRecever(User recever) {
		this.recever = recever;
	}
	
	
	
	
}
