package com.example.model;

import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
//@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	public Long id;
	

	public String handle;
	
	
	

	protected User() {
		super();
	}

	public User(String handle) {
		super();
		this.handle = handle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
//	@OneToMany(targetEntity=Message.class, mappedBy = "user")
//	public Set<Message> messages;

//	public Long getIdUser() {
//		return idUser;
//	}
//
//	public void setIdUser(Long idUser) {
//		this.idUser = idUser;
//	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}
//	
//	
//    public Set<Message> getMessages() {
//        return messages;
//    }
//	
//	public void setMessages(Set<Message> messages) {
//		this.messages = messages;
//	}
//	
	
	
}
