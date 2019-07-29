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

/**
 * This class is used for handle events on Picture page
 */
@Controller
@SessionAttributes(value = "loggedUser")
public class PictureController {
    /**
     * PictureDAOImpl instance
     */
    private PictureDAOImpl pictureDAO;

    /**
     * Setter for PictureDAOImpl instance
     *
     * @param pictureDAO PictureDAOImpl instance
     */
    public void setPictureDAO(PictureDAOImpl pictureDAO) {
        this.pictureDAO = pictureDAO;
    }

    /**
     * Prints Picture page
     *
     * @param pictureId id of the picture
     * @param model
     * @return name of a page to render
     */
    @RequestMapping(value = "/picture", method = RequestMethod.GET)
    public String goToPicture(@RequestParam(value = "id") long pictureId, ModelMap model) {
        Picture pic;
        try {
            pic = pictureDAO.getPictureById(pictureId);
            model.put("comments", pictureDAO.getCommentsForPicture(pictureId));
            model.put("likes", pictureDAO.countLikes(pictureId));
        } catch (SQLException e) {
            // TODO: 28.07.2019 add logger
            return "redirect:/errorPage";
        }
        if (pic == null) return "redirect:/errorPage"; // TODO: 7/29/2019 check this case. It may never happen
        model.put("picture", pic);
        return "picture";
    }

    /**
     * Adds comment to picture and reloads the page. If user is not logged in, then it redirects to Signin page
     *
     * @param comment   comment left by user
     * @param pictureId id of a picture the comment belongs to
     * @param model
     * @return name of a page to render
     */
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public String doComment(@RequestParam(value = "comment") String comment, @RequestParam(value = "pictureId") long pictureId, ModelMap model) {
        //User user = (User) model.get("loggedUser"); // TODO: 7/29/2019 check the refactor
        if (!model.containsKey("loggedUser")) return "redirect:/signin";
        try {
            pictureDAO.addCommentToPicture(pictureId, ((User) model.get("loggedUser")).getLogin(), comment);
        } catch (SQLException e) {
            // TODO: 28.07.2019 add logger
            return "redirect:/errorPage";
        }
        return "redirect:/picture?id=" + pictureId;
    }

    /**
     * Adds like to picture and reloads the page. If user is not logged in, then it redirects to Sign in page
     *
     * @param pictureId id of a picture
     * @param model
     * @return name of a page to render
     */
    @RequestMapping(value = "/likeIt", method = RequestMethod.POST)
    public String likeThePicture(@RequestParam(value = "pictureId") long pictureId, ModelMap model) {
        //User user = (User) model.get("loggedUser"); // TODO: 7/29/2019 check the refactor
        if (!model.containsKey("loggedUser")) return "redirect:/signin";
        try {
            pictureDAO.likeThePicture(pictureId, ((User) model.get("loggedUser")).getLogin());
        } catch (SQLException e) {
            // TODO: 28.07.2019 add logger
            return "redirect:/errorPage";
        }
        return "redirect:/picture?id=" + pictureId;
    }
}
