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

	public List<User> getAllUsers() {
		List<User> list = new ArrayList<>();
		try {
			PreparedStatement statement = jdbcTemplate.getDataSource().getConnection().prepareStatement(Statements.GET_ALL_USERS);
			list = parseUser(statement.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private List<User> parseUser(ResultSet resultSet) throws SQLException {
		List<User> list = new ArrayList<>();
		/*while (resultSet.next()) {
			list.add(new User(
					resultSet.getString(1),
					resultSet.getString(2),
					resultSet.getString(3)
			));
		}*/
		return list;
	}

	public boolean registerNewUser(User user, ModelMap model) throws SQLException {
		if (isUniqueLogin(user, model)) {
			try {
				statement = jdbcTemplate.getDataSource().getConnection().prepareStatement(Statements.INSERT_NEW_USER);
				statement.setString(1, user.getLogin());
				statement.setString(2, user.getName());
				statement.setString(3, user.getPassword());
				return statement.execute();
			} finally {
				if (statement != null) statement.close();
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
			if (statement != null) statement.close();
			if (resultSet != null) resultSet.close();
		}
	}
}
