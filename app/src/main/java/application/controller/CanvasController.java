package application.controller;

import DAOImpl.PictureDAOImpl;
import model.Picture;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;

@Controller
@SessionAttributes(value = "loggedUser")
public class CanvasController {
	private PictureDAOImpl pictureDAO;

	public PictureDAOImpl getPictureDAO() {
		return pictureDAO;
	}

	public void setPictureDAO(PictureDAOImpl pictureDAO) {
		this.pictureDAO = pictureDAO;
	}

	@RequestMapping(value = "/canvas", method = RequestMethod.GET)
	public String goToCanvas(ModelMap model) {
		return model.containsKey("loggedUser") ? "canvas" : "redirect:/signin";
	}

	@RequestMapping(value = "/savefile", method = RequestMethod.POST)
	public String saveImage(HttpServletRequest request, ModelMap model) {
		try {
			pictureDAO.savePicture(((User) model.get("loggedUser")).getLogin(), new Date().getTime(), request.getParameter("imageBase64Value"));
		} catch (SQLException e) {
			e.printStackTrace();
			//TODO add logger
		}
		return "canvas";
	}
}
