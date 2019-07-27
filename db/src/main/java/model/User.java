package model;

import org.springframework.ui.ModelMap;

import java.util.Optional;

public class User {
	private Optional<String> name;
	private Optional<String> login;
	private Optional<String> password;
	private Optional<String> repeatPassword;

	//public User(String login, String password) {

	//}

	public User(String name, String login, String password, String repeatPassword) {
		this.name = Optional.ofNullable(name);
		this.login = Optional.ofNullable(login);
		this.password = Optional.ofNullable(password);
		this.repeatPassword = Optional.ofNullable(repeatPassword);
	}

	//public User(String login, String name) {
	//	this.name = Optional.ofNullable(name);
	//	this.login = Optional.ofNullable(login);
	//	this.password = Optional.ofNullable(null);
	//	this.repeatPassword = Optional.ofNullable(null);
	//}

	public String getLogin() {
		return login.orElseGet(String::new);
	}

	public String getName() {
		return name.orElseGet(String::new);
	}

	public String getPassword() {
		return password.orElseGet(String::new);
	}

	public String getRepeatPassword() {
		return repeatPassword.orElseGet(String::new);
	}

	public void setName(String name) {
		this.name = Optional.ofNullable(name);
	}
}
