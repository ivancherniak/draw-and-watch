package application.controller;

import DAOImpl.UserDAOImpl;
import model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLException;

/**
 * This class is used for handle events on Home page
 */
@Controller
@SessionAttributes(value = "loggedUser")
public class HomeController {
    /**
     * JdbcTemplate instance
     */
    private JdbcTemplate jdbcTemplate;
    /**
     * UserDAOImpl instance
     */
    private UserDAOImpl userDAO;

    /**
     * Setter for UserDAOImpl instance
     *
     * @param userDAO UserDAOImpl instance
     */
    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Setter for JdbcTemplate instance
     *
     * @param jdbcTemplate JdbcTemplate instance
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Prints Home page
     *
     * @param model
     * @return name of a page to render
     */
    @RequestMapping(value = {"/home", "", "/"}, method = RequestMethod.GET)
    public String printHello(ModelMap model) {
        try {
            model.put("allUsers", userDAO.getAllUsers());
            if (model.containsKey("loggedUser")) {
                model.put("favourites", userDAO.getFavouriteProfiles((User) model.get("loggedUser")));
            }
        } catch (SQLException e) {
            // TODO: 27.07.2019 add logger
            model.put("SQLError", "Error while trying to get all users");
        }
        return "home";
    }

    // TODO: 7/29/2019 add javadoc after refactor
    // TODO: 7/29/2019 move this method to LoginController
    @RequestMapping(value = {"/login", "signin"}, method = RequestMethod.GET)
    public String printTest() { // TODO: 7/29/2019 rename the method
        return "signin"; // TODO: 7/29/2019 replace signin with redirect:/signin
    }


    // TODO: 7/29/2019 DELETE THIS METHOD
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
