package application.controller;

import DAOImpl.PictureDAOImpl;
import model.Picture;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLException;

@Controller
@SessionAttributes(value = "loggedUser")
public class PictureController {
	private PictureDAOImpl pictureDAO;

	public void setPictureDAO(PictureDAOImpl pictureDAO) {
		this.pictureDAO = pictureDAO;
	}

	@RequestMapping(value = "/picture", method = RequestMethod.GET)
	public String goToPicture(@RequestParam(value="id") long pictureId, ModelMap model) {
		Picture pic;
		try {
			pic = pictureDAO.getPictureById(pictureId);
			model.put("comments", pictureDAO.getCommentsForPicture(pictureId));
			model.put("likes", pictureDAO.countLikes(pictureId));
		} catch (SQLException e) {
			// TODO: 28.07.2019 add logger
			return "redirect:/errorPage";
		}
		if (pic == null) return "redirect:/errorPage";
		model.put("picture", pic);
		return "picture";
	}

	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public String doComment(@RequestParam(value = "comment") String comment, @RequestParam(value = "pictureId") long pictureId, ModelMap model) {
		User user = (User) model.get("loggedUser");
		if (user == null) return "redirect:/signin";
		try {
			pictureDAO.addCommentToPicture(pictureId, user.getLogin(), comment);
		} catch (SQLException e) {
			// TODO: 28.07.2019 add logger
			return "redirect:/errorPage";
		}
		return "redirect:/picture?id=" + pictureId;
	}

	@RequestMapping(value = "/likeIt", method = RequestMethod.POST)
	public String likeThePicture(@RequestParam(value = "pictureId") long pictureId, ModelMap model) {
		User user = (User) model.get("loggedUser");
		if (user == null) return "redirect:/signin";
		try {
			pictureDAO.likeThePicture(pictureId, user.getLogin());
		} catch (SQLException e) {
			// TODO: 28.07.2019 add logger
			return "redirect:/errorPage";
		}
		return "redirect:/picture?id=" + pictureId;
	}
}
