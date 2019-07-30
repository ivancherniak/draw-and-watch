package DAOImpl;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class contains common fields for all DAO implementations
 */
public abstract class BaseDAO {
    /**
     * JdbcTemplate instance
     */
    protected JdbcTemplate jdbcTemplate;
    /**
     * PreparedStatement instance
     */
    protected PreparedStatement statement;
    /**
     * ResultSet instance
     */
    protected ResultSet resultSet;

    /**
     * Connection instance
     */
    protected Connection connection;

    /**
     * Getter for jdbcTemplate
     *
     * @return JdbcTemplate instance
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * Setter for jdbcTemplate
     *
     * @param jdbcTemplate JdbcTemplate instance
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Initiates connection if it closed or equals null
     *
     * @throws SQLException when connection failed
     */
    protected void getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = jdbcTemplate.getDataSource().getConnection();
        }
    }

    /**
     * Closes resultSet, statement and connection if they not equal null
     *
     * @throws SQLException when failed to close
     */
    protected void closeAll() throws SQLException {
        if (resultSet != null) resultSet.close();
        if (statement != null) statement.close();
        if (connection != null) connection.close();
    }
}
