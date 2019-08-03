package application.controller;

import DAOImpl.UserDAOImpl;
import model.User;
import model.UserLoginModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.sql.SQLException;

/**
 * This class is used for handle events on Login page
 */
@Controller
@SessionAttributes(value = "loggedUser")
public class LoginController {
	/**
	 * UserDAOImpl instance
	 */
	private UserDAOImpl userDAO;

	/**
	 * Setter for UserDAOImpl instance
	 *
	 * @param userDAO UserDAOImpl instance
	 */
	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * Add logged in user to session and redirects to Home page. If errors occur then it stays on the same page
	 *
	 * @param user  data from login form
	 * @param model
	 * @return name of a page to render
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String doLogin(@ModelAttribute("user") UserLoginModel user, ModelMap model) {
		try {
			User loggedUser;
			if ((loggedUser = userDAO.getUserByLoginAndPassword(user, model)) != null) {
				model.put("loggedUser", loggedUser);
				return "redirect:/home";
			}
		} catch (SQLException e) {
			model.addAttribute("SQLError", "Error while trying to register user. <br>Please try again");
			// TODO: 21.07.2019 add logger
		}
		return "signin";
	}

	/**
	 * Deletes logged user from session and redirects to Home page
	 *
	 * @param status session status
	 * @return name of a page to render
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String doLogout(SessionStatus status, ModelMap model) {
		status.setComplete();
		model.put("loggedUser", null);
		return "redirect:/home";
	}
}
