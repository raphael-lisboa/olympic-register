package com.rpl.olympic.register.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "USER")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

	private static final long	serialVersionUID	= 4233210723284454557L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long				id;
	@NotBlank
	private String				name;
	@NotBlank
	private String				email;
	private String				nickname;

	public User() {
	}

	public User(Long id, String name, String email, String nickname) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.nickname = nickname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, email=%s, nickname=%s]", id, name, email, nickname);
	}

}
