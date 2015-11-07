package com.rpl.olympic.register.controller;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.rpl.olympic.register.controller.model.ViewModel;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserView implements ViewModel {
	private static final long	serialVersionUID	= -5525946398058897481L;
	private Long				id;
	private String				name;
	private String				email;
	private String				nickname;

	public UserView() {
	}

	public UserView(Long id, String name, String email, String nickname) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.nickname = nickname;
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
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

}
