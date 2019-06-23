package application.controller;

import application.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@RequestMapping(value = {"/home", "", "/"}, method = RequestMethod.GET)
	public String printHello(ModelMap model) {
		User user = (User) model.get("user");
		if (user == null || user.getLogin() == null)
		model.addAttribute("msg", "hello worldwww");
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String printTest(ModelMap model) {
		model.addAttribute("msg", "hello test");
		model.addAttribute("user", new User());
		return "signin";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String goToRegistration(ModelMap model) {
		model.addAttribute("msg", "hello test");
		model.addAttribute("user", new User());
		return "registration";
	}
}
