package application.controller;

import DAOImpl.UserDAOImpl;
import model.User;
import model.UserRegistrationModel;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLException;

/**
 * This class is used for handle events on Registration page
 */
@Controller
@SessionAttributes(value = "loggedUser")
public class RegistrationController {
	/**
	 * Logger object
	 */
	private static final Logger logger = Logger.getLogger(RegistrationController.class);
	/**
	 * UserDAOImpl instance
	 */
	private UserDAOImpl userDAO;

	/**
	 * Setter for UserDAOImpl instance
	 *
	 * @param userDAO
	 */
	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * Prints Registration page
	 *
	 * @return name of a page to render
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String goToRegistration() {
		return "registration";
	}

	/**
	 * Registers new user. If registration is successful it redirects to home page and add user to session. Otherwise it reloads Registration page and shows error message
	 *
	 * @param user  user trying to register
	 * @param model
	 * @return name of a page to render
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") UserRegistrationModel user, ModelMap model) {
		try {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			if (userDAO.registerNewUser(user, model)) {
				model.put("loggedUser", new User(
						user.getLogin(),
						user.getName(),
						user.getPassword()));
				logger.info("User " + user.getLogin() + " has been registered");
				return "redirect:/home";
			}
		} catch (SQLException e) {
			model.addAttribute("SQLError", "Error while trying to register user. <br>Please try again");
			logger.error("Error while trying to register user: " + user, e);
		}
		return "registration";
	}
}
