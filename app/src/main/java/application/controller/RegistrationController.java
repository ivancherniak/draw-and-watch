package application.controller;

import DAOImpl.UserDAOImpl;
import application.model.InputValidator;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class RegistrationController {
	private UserDAOImpl userDAO;
	private InputValidator validator;

	public void setValidator(InputValidator validator) {
		this.validator = validator;
	}

	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("loggedUser") User user, ModelMap model, HttpServletRequest request) {
		if (validator.isValidRegistrationData(user, model)) {
			try {
				if (!userDAO.registerNewUser(user, model)) {
					model.put("loggedUser", user);
					return "home";
				}
			} catch (SQLException e) {
				model.addAttribute("SQLError", "Error while trying to register user. <br>Please try again");
				// TODO: 20.07.2019 add logger
			}
		}
		return "registration";
	}
}
