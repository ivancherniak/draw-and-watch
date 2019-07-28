package DAOImpl;

import DAO.Statements;
import DAO.UserDAO;
import model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.ModelMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends BaseDAO implements UserDAO {
	private Connection connection;

	private void getConnection() throws SQLException {
		connection = jdbcTemplate.getDataSource().getConnection();
	}

	@Override
	public List<User> getAllUsers() throws SQLException {
		if (connection == null || connection.isClosed()) getConnection();
		try {
			return parseUsers(connection
					.prepareStatement(Statements.GET_ALL_USERS)
					.executeQuery());
		} finally {
			connection.close();
		}
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
		if (connection == null || connection.isClosed()) getConnection();
		if (isUniqueLogin(user, model)) {
			try {
				statement = connection.prepareStatement(Statements.INSERT_NEW_USER);
				statement.setString(1, user.getLogin());
				statement.setString(2, user.getName());
				statement.setString(3, user.getPassword());
				return statement.execute();
			} finally {
				//if (statement != null) statement.close();
				connection.close();
			}
		}
		return false;
	}

	private boolean isUniqueLogin(User user, ModelMap model) throws SQLException {
		if (connection == null || connection.isClosed()) getConnection();
		try {
			statement = connection.prepareStatement(Statements.GET_USER_BY_LOGIN);
			statement.setString(1, user.getLogin());
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				model.addAttribute("invalidLogin", "This login is already in use");
				return false;
			}
			return true;
		} finally {
			connection.close();
			//if (statement != null) statement.close();
			//if (resultSet != null) resultSet.close();
		}
	}
	@Override
	public boolean isUserExists(User user, ModelMap model) throws SQLException {
		if (connection == null || connection.isClosed()) getConnection();
		try {
			statement = connection.prepareStatement(Statements.SELECT_USER);
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
		} finally {
			connection.close();
		}
	}

	@Override
	public List<User> getFavouriteProfiles(User user) throws SQLException {
		if (connection == null || connection.isClosed()) getConnection();
		try {
			statement = connection.prepareStatement(Statements.GET_FAVOURITE_PROFILES_BY_USER_LOGIN);
			statement.setString(1, user.getLogin());
			return parseUsers(statement.executeQuery());
		} finally {
			connection.close();
		}
	}

	@Override
	public User getUserProfile(String login) throws SQLException {
		if (connection == null || connection.isClosed()) getConnection();
		try {
			statement = connection.prepareStatement(Statements.GET_USER_DATA_BY_LOGIN);
			statement.setString(1, login);
			List<User> list = parseUsers(statement.executeQuery());
			return list == null || list.size() == 0 ? null : list.get(0);
		} finally {
			connection.close();
		}
	}

	@Override
	public void addProfileToFavourites(String login, String likes) throws SQLException {
		if (connection == null || connection.isClosed()) getConnection();
		try {
			statement = connection.prepareStatement(Statements.ADD_PROFILE_TO_FAVOURITES);
			statement.setString(1, login);
			statement.setString(2, likes);
			statement.execute();
		} finally {
			connection.close();
		}
	}

	@Override
	public void deleteProfileFromFavourites(String login, String likes) throws SQLException {
		if (connection == null || connection.isClosed()) getConnection();
		try {
			statement = connection.prepareStatement(Statements.DELETE_PROFILE_FROM_FAVOURITES);
			statement.setString(1, login);
			statement.setString(2, likes);
			statement.execute();
			jdbcTemplate.getDataSource().getConnection().close();
		} finally {
			connection.close();
		}
	}
}
