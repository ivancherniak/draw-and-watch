package application.controller;

import DAOImpl.UserDAOImpl;
import application.model.InputValidator;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.sql.SQLException;


@Controller
@SessionAttributes(value = "loggedUser")
public class LoginController {
	private UserDAOImpl userDAO;
	private InputValidator validator;

	public void setValidator(InputValidator validator) {
		this.validator = validator;
	}

	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String doLogin(@ModelAttribute("user") User user, ModelMap model) {
		try {
			if (validator.isValidLoginData(user, model) && userDAO.isUserExists(user, model)) {
				model.put("loggedUser", user);
				return "redirect:/home";
			}
		} catch (SQLException e) {
			model.addAttribute("SQLError", "Error while trying to register user. <br>Please try again");
			// TODO: 21.07.2019 add logger
		}
		return "signin";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String doLogout(SessionStatus status) {
		status.setComplete(); 
		return "redirect:/home";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String goToSignin() {
		return "signin";
	}
}
