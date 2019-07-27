package application.controller;

import DAOImpl.UserDAOImpl;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.sql.SQLException;

@Controller
@SessionAttributes(value = {"loggedUser", "errorMsg"})
public class ProfileController {
	private UserDAOImpl userDAO;

	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String goToProfile(@RequestParam(value="login") String login, ModelMap model) {
		User user = (User) model.get("loggedUser");

		if (login == null || login.length() == 0) {
			if (user == null) return "redirect:/errorPage";
			login = ((User) model.get("loggedUser")).getLogin();
		}
		try {
			model.put("userProfile", userDAO.getUserProfile(login));
			model.put("isProfileInFavourites", user == null ? false : userDAO.getFavouriteProfiles(user).size() != 0);
		} catch (SQLException e) {
			// TODO: 27.07.2019 add logger
			return "redirect:/errorPage";
		}
		return "profile";
	}

	@RequestMapping(value = "/addToFavourites", method = RequestMethod.GET)
	public String addToFavourites(@RequestParam(value="login") String login, ModelMap model) {
		User user = (User) model.get("loggedUser");
		if (user == null) return "redirect:/signin";
		if (login == null || login.length() == 0) return "redirect:/errorPage";
		try {
			model.put("userProfile", userDAO.getUserProfile(login));
			model.put("isProfileInFavourites", userDAO.getFavouriteProfiles((User) model.get("loggedUser")).size() != 0);
			userDAO.addProfileToFavourites(((User) model.get("loggedUser")).getLogin(), login);
		} catch (SQLException e) {
			// TODO: 27.07.2019 add logger
			return "redirect:/errorPage";
		}
		return "redirect:/profile?login=" + login;
	}

	@RequestMapping(value = "/deleteFromFavourites", method = RequestMethod.GET)
	public String deleteFromFavourites(@RequestParam(value="login") String login, ModelMap model) {
		User user = (User) model.get("loggedUser");
		if (user == null) return "redirect:/signin";
		if (login == null || login.length() == 0) return "redirect:/errorPage";
		try {
			model.put("userProfile", userDAO.getUserProfile(login));
			model.put("isProfileInFavourites", userDAO.getFavouriteProfiles((User) model.get("loggedUser")).size() != 0);
			userDAO.deleteProfileFromFavourites(((User) model.get("loggedUser")).getLogin(), login);
		} catch (SQLException e) {
			// TODO: 27.07.2019 add logger
			return "redirect:/errorPage";
		}
		return "redirect:/profile?login=" + login;
	}
}
