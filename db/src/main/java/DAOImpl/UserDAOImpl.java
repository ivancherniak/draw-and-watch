package DAOImpl;

import DAO.Statements;
import DAO.UserDAO;
import model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
	private JdbcTemplate jdbcTemplate;

	public UserDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

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
		while (resultSet.next()) {
			list.add(new User(
					resultSet.getString(1),
					resultSet.getString(2),
					resultSet.getString(3)
			));
		}
		return list;
	}
}
