package application.controller;

import DAOImpl.UserDAOImpl;
import application.model.InputValidator;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
@SessionAttributes(value = "loggedUser")
public class RegistrationController {
	private UserDAOImpl userDAO;
	private InputValidator validator;

	public void setValidator(InputValidator validator) {
		this.validator = validator;
	}

	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	@ModelAttribute("Money")
	public User getLoggedUser() {
		return null; //or however you create a default
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String goToRegistration(ModelMap model) {
		//model.put("loggedUser", null);
		//model.addAttribute("user", new User());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user, ModelMap model) {
		if (!model.containsKey("loggedUser")) model.put("loggedUser", null);
		try {
			if (validator.isValidRegistrationData(user, model) && !userDAO.registerNewUser(user, model)) {
				model.put("loggedUser", user);
				return "redirect:/home";
			}
		} catch (SQLException e) {
			model.addAttribute("SQLError", "Error while trying to register user. <br>Please try again");
			// TODO: 20.07.2019 add logger
		}
		return "registration";
	}
}
