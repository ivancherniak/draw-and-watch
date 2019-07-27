package DAOImpl;

import DAO.Statements;
import DAO.UserDAO;
import model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.ModelMap;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends BaseDAO implements UserDAO {
	@Override
	public List<User> getAllUsers() throws SQLException {
		return parseUsers(jdbcTemplate
				.getDataSource()
				.getConnection()
				.prepareStatement(Statements.GET_ALL_USERS)
				.executeQuery());
	}

	private List<User> parseUsers(ResultSet resultSet) throws SQLException {
		List<User> list = new ArrayList<>();
		while (resultSet.next()) {
			list.add(new User(
					resultSet.getString(2),
					resultSet.getString(1),
					null, null
			));
		}
		return list;
	}
	@Override
	public boolean registerNewUser(User user, ModelMap model) throws SQLException {
		if (isUniqueLogin(user, model)) {
			try {
				statement = jdbcTemplate.getDataSource().getConnection().prepareStatement(Statements.INSERT_NEW_USER);
				statement.setString(1, user.getLogin());
				statement.setString(2, user.getName());
				statement.setString(3, user.getPassword());
				return statement.execute();
			} finally {
				//if (statement != null) statement.close();
			}
		}
		return false;
	}

	private boolean isUniqueLogin(User user, ModelMap model) throws SQLException {
		try {
			statement = jdbcTemplate.getDataSource().getConnection().prepareStatement(Statements.GET_USER_BY_LOGIN);
			statement.setString(1, user.getLogin());
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				model.addAttribute("invalidLogin", "This login is already in use");
				return false;
			}
			return true;
		} finally {
			//if (statement != null) statement.close();
			//if (resultSet != null) resultSet.close();
		}
	}
	@Override
	public boolean isUserExists(User user, ModelMap model) throws SQLException {
		statement = jdbcTemplate.getDataSource().getConnection().prepareStatement(Statements.SELECT_USER);
		statement.setString(1, user.getLogin());
		statement.setString(2, user.getPassword());
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			user.setName(resultSet.getString(2));
			return true;
		} else {
			model.addAttribute("invalidUser", "Incorrect login or password");
			return false;
		}
	}

	@Override
	public List<User> getFavouriteProfiles(User user) throws SQLException {
		statement = jdbcTemplate.getDataSource().getConnection().prepareStatement(Statements.GET_FAVOURITE_PROFILES_BY_USER_LOGIN);
		statement.setString(1, user.getLogin());
		return parseUsers(statement.executeQuery());
	}

	@Override
	public User getUserProfile(String login) throws SQLException {
		statement = jdbcTemplate.getDataSource().getConnection().prepareStatement(Statements.GET_USER_DATA_BY_LOGIN);
		statement.setString(1, login);
		List<User> list = parseUsers(statement.executeQuery());
		return list == null || list.size() == 0 ? null : list.get(0);
	}

	@Override
	public void addProfileToFavourites(String login, String likes) throws SQLException {
		statement = jdbcTemplate.getDataSource().getConnection().prepareStatement(Statements.ADD_PROFILE_TO_FAVOURITES);
		statement.setString(1, login);
		statement.setString(2, likes);
		statement.execute();
	}

	@Override
	public void deleteProfileFromFavourites(String login, String likes) throws SQLException {
		statement = jdbcTemplate.getDataSource().getConnection().prepareStatement(Statements.DELETE_PROFILE_FROM_FAVOURITES);
		statement.setString(1, login);
		statement.setString(2, likes);
		statement.execute();
	}
}
