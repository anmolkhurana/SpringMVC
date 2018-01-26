package com.web.login;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	public boolean isValidateUser(String user, String password) {
		return user.equalsIgnoreCase("hello") && password.equals("hello");
	}
}
