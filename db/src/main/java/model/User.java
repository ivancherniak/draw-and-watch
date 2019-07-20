package model;

import org.springframework.ui.ModelMap;

import java.util.Optional;

public class User {
	private Optional<String> name;
	private Optional<String> login;
	private Optional<String> password;
	private Optional<String> repeatPassword;

	public User(String name, String login, String password, String repeatPassword) {
		this.name = Optional.ofNullable(name);
		this.login = Optional.ofNullable(login);
		this.password = Optional.ofNullable(password);
		this.repeatPassword = Optional.ofNullable(repeatPassword);
	}

	public String getLogin() {
		return login.orElseGet(String::new);
	}

	public String getName() {
		return name.orElseGet(String::new);
	}

	public String getPassword() {
		return password.orElseGet(String::new);
	}

	public boolean isValid(ModelMap model) {
		if (name.orElseGet(String::new).length() == 0) {
			model.addAttribute("invalidName", "Name should not be empty");
			return false;
		}
		if (login.orElseGet(String::new).length() == 0) {
			model.addAttribute("invalidLogin", "Login should not be empty");
			return false;
		}
		if (password.orElseGet(String::new).length() == 0) {
			model.addAttribute("invalidPassword", "Login should not be empty");
			return false;
		}
		if (!password.get().equals(repeatPassword.orElseGet(String::new))) {
			model.addAttribute("passwordsDontMatch", "Passwords should be equal");
			return false;
		}
		return true;
	}
}
