package application.controller;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
public class CanvasController {
	@RequestMapping(value = "/canvas", method = RequestMethod.GET)
	public String goToCanvas() {

		return "canvas";
	}
}
