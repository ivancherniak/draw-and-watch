package application.controller;

import DAOImpl.PictureDAOImpl;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.sql.SQLException;

/**
 * This class is used for handle events on Canvas page
 */
@Controller
@SessionAttributes(value = "loggedUser")
public class CanvasController {
    /**
     * PictureDAOImpl instance
     */
    private PictureDAOImpl pictureDAO;

    /**
     * Getter for PictureDAOImpl instance
     *
     * @return instance of PictureDAOImpl
     */
    public PictureDAOImpl getPictureDAO() {
        return pictureDAO;
    }

    /**
     * Setter for PictureDAOImpl instance
     *
     * @param pictureDAO PictureDAOImpl instance
     */
    public void setPictureDAO(PictureDAOImpl pictureDAO) {
        this.pictureDAO = pictureDAO;
    }

    /**
     * Prints Canvas page. If a user is not logged in, then this method redirects to Signin page
     *
     * @param model
     * @return name of a page to render
     */
    @RequestMapping(value = "/canvas", method = RequestMethod.GET)
    public String goToCanvas(ModelMap model) {
        return model.containsKey("loggedUser") ? "canvas" : "redirect:/signin";
    }

    /**
     * Saves painted image when "Save" button is been clicked. This method redirects to Profile page of logged user
     *
     * @param request request to server
     * @param model
     * @return name of a page to render
     */
    @RequestMapping(value = "/savefile", method = RequestMethod.POST)
    public String saveImage(HttpServletRequest request, ModelMap model) {
        User user = (User) model.get("loggedUser");
        try {
            pictureDAO.savePicture(user.getLogin(), new Date().getTime(), request.getParameter("imageBase64Value"));
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO add logger
        }
        return "redirect:/profile?login=" + user.getLogin();
    }
}
