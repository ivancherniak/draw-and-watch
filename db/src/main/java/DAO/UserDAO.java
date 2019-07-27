package DAO;

import model.User;
import org.springframework.ui.ModelMap;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
	List<User> getAllUsers() throws SQLException;
	boolean registerNewUser(User user, ModelMap model) throws SQLException;
	boolean isUserExists(User user, ModelMap model) throws SQLException;
	List<User> getFavouriteProfiles(User user) throws SQLException;
}
