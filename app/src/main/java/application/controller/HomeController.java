package application.controller;

import DAOImpl.UserDAOImpl;
import model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLException;

@Controller
@SessionAttributes(value = "loggedUser")
public class HomeController {

	private JdbcTemplate jdbcTemplate;
	private UserDAOImpl userDAO;

	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@RequestMapping(value = {"/home", "", "/"}, method = RequestMethod.GET)
	public String printHello(ModelMap model) {
		try {
			model.put("allUsers", userDAO.getAllUsers());
			if (model.get("loggedUser") != null) {
				model.put("favourites", userDAO.getFavouriteProfiles((User) model.get("loggedUser")));
			}
		} catch (SQLException e) {
			// TODO: 27.07.2019 add logger
			model.put("SQLError", "Error while trying to get all users");
		}
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String printTest(ModelMap model) {
		model.addAttribute("msg", "hello test");
		//model.addAttribute("user", new User());
		return "signin";
	}



	@RequestMapping(value = "/jdbc", method = RequestMethod.GET)
	public String jdbcTest(ModelMap model) {
		try {
			model.addAttribute("result", jdbcTemplate.getDataSource().getConnection().getSchema());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "jdbc-test";
	}
}
