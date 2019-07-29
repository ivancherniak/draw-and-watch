package DAOImpl;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
