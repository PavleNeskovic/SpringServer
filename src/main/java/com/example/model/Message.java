package com.example.model;


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
	
	private String line_text;
	
	@ManyToOne
	@JoinColumn(name="user")
	public User user;
	
	protected Message(){}

	public Message(String line_text) {
		super();
		this.line_text = line_text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLine_text() {
		return line_text;
	}

	public void setLine_text(String line_text) {
		this.line_text = line_text;
	}

//	public int getUser() {
//		return user;
//	}
//
//	public void setUser(int user) {
//		this.user = user;
//	}
//	
//	@ManyToOne
//    @JoinColumn(name = "id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
