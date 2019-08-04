package application.controller;

import DAOImpl.CommentDAOImpl;
import DAOImpl.PictureDAOImpl;
import model.User;
import org.apache.log4j.Logger;
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
     * Logger object
     */
    private static final Logger logger = Logger.getLogger(PictureController.class);
    /**
     * PictureDAOImpl instance
     */
    private PictureDAOImpl pictureDAO;

    /**
     * CommentDAOImpl instance
     */
    private CommentDAOImpl commentDAO;

    /**
     * Setter for PictureDAOImpl instance
     *
     * @param pictureDAO PictureDAOImpl instance
     */
    public void setPictureDAO(PictureDAOImpl pictureDAO) {
        this.pictureDAO = pictureDAO;
    }

    /**
     * Getter for pictureDAO
     *
     * @return PictureDAOImpl instance
     */
    public PictureDAOImpl getPictureDAO() {
        return pictureDAO;
    }

    /**
     * Getter for commentDAO
     *
     * @return CommentDAOImpl instance
     */
    public CommentDAOImpl getCommentDAO() {
        return commentDAO;
    }

    /**
     * Setter for commentDAO
     *
     * @param commentDAO CommentDAOImpl instance
     */
    public void setCommentDAO(CommentDAOImpl commentDAO) {
        this.commentDAO = commentDAO;
    }

    /**
     * Prints Picture page
     *
     * @param id    id of the picture
     * @param model
     * @return name of a page to render
     */
    @RequestMapping(value = "/picture", method = RequestMethod.GET)
    public String goToPicture(@RequestParam(value = "id") long id, ModelMap model) {
        try {
            model.put("pictureModel", pictureDAO.getPictureModelById(id));
        } catch (SQLException e) {
            logger.error("Error while trying to get data for Picture page", e);
            return "redirect:/errorPage";
        }
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
        if (!model.containsKey("loggedUser"))  {
            logger.info("Unregistered user tries to leave a comment");
            return "redirect:/signin";
        }
        try {
            commentDAO.addCommentToPicture(pictureId, ((User) model.get("loggedUser")).getLogin(), comment);
        } catch (SQLException e) {
            logger.error("Error while trying to add comment to picture", e);
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
        if (!model.containsKey("loggedUser")) {
            logger.info("Unregistered user tries to add like");
            return "redirect:/signin";
        }
        try {
            pictureDAO.likeThePicture(pictureId, ((User) model.get("loggedUser")).getLogin());
        } catch (SQLException e) {
            logger.error("Error while trying to add like to picture", e);
            return "redirect:/errorPage";
        }
        return "redirect:/picture?id=" + pictureId;
    }
}
