package application.controller;

import DAOImpl.UserDAOImpl;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
public class HomeController {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@RequestMapping(value = {"/home", "", "/"}, method = RequestMethod.GET)
	public String printHello(ModelMap model) {
		//model.addAttribute("users", userDAO.getAllUsers());
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
