package application.controller;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
@SessionAttributes(value = "loggedUser")
public class CanvasController {
	@RequestMapping(value = "/canvas", method = RequestMethod.GET)
	public String goToCanvas(ModelMap model) {
		return model.containsKey("loggedUser") ? "canvas" : "redirect:/signin";
	}

	@RequestMapping(value = "/savefile", method = RequestMethod.POST)
	public String saveImage(HttpServletRequest request, ModelMap model) {
		String imgBase64 = request.getParameter("imageBase64Value");
		model.addAttribute("imgBase64", imgBase64);
		return "jdbc-test";
	}
}
