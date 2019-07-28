package application.model;
/*
import model.User;
import org.springframework.ui.ModelMap;

public class InputValidator {
	public boolean isValidLoginData(User user, ModelMap model) {
		if (user.getLogin().length() == 0) {
			model.addAttribute("invalidLogin", "Login should not be empty");
			return false;
		}
		if (user.getPassword().length() == 0) {
			model.addAttribute("invalidPassword", "Password should not be empty");
			return false;
		}
		return true;
	}

	public boolean isValidRegistrationData(User user, ModelMap model) {
		if (!isValidLoginData(user, model)) return false;
		if (user.getName().length() == 0) {
			model.addAttribute("invalidName", "Name should not be empty");
			return false;
		}
		if (!user.getPassword().equals(user.getRepeatPassword())) {
			model.addAttribute("passwordsDontMatch", "Passwords should be equal");
			return false;
		}
		return true;
	}
}*/
