package application.controller;

import DAOImpl.UserDAOImpl;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class RegistrationController {
	UserDAOImpl userDAO;

	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute User user, ModelMap model) {
		if (user.isValid(model)) {
			try {
				userDAO.registerNewUser(user, model);
				return "home";
			} catch (SQLException e) {
				model.addAttribute("SQLError", "Error while trying to register user. Please try again");
				// TODO: 20.07.2019 add logger
			}
		}
		return "registration";
	}
}
