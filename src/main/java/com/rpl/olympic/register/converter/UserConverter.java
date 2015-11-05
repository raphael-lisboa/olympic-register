package com.rpl.olympic.register.converter;

import com.rpl.olympic.register.controller.UserView;
import com.rpl.olympic.register.model.User;

public class UserConverter {

	public static User convert(UserView userView) {
		return new User(userView.getId(), userView.getName(), userView.getEmail(), userView.getNickname());
	}

	public static UserView convert(User user) {
		return new UserView(user.getId(), user.getName(), user.getEmail(), user.getNickname());
	}

}
