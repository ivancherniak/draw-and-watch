package application.controller;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {
	@RequestMapping(value = "/logged", method = RequestMethod.POST)
	public String submit(@ModelAttribute("user") User user, BindingResult result, ModelMap model) {
		model.put("user", user);
		return "redirect:/home";
	}
}
