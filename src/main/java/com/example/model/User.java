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
import javax.persistence.UniqueConstraint;

import org.springframework.util.StringUtils;

import com.example.dto.UserDto;

@Entity
//@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	public String mail;

	public User() {
		super();
	}
	
	public User(UserDto userDto){
		this.mail = userDto.getMail();
	}

	public User(String mail) {
		super();
		this.mail = mail;
	}


	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	//create representation
	public UserDto transferToDto() {
		UserDto dto = new UserDto();
		dto.setMail(getMail());
		return dto;
	}
	
	public void updateFromDto(UserDto dto) {
		if (StringUtils.isEmpty(dto.getMail())) {
			this.setMail(dto.getMail());
		}

}

	
	
}
