package DAO;

public class Statements {
	public static final String GET_ALL_USERS = "SELECT object_id, name FROM objects WHERE object_type_id = 101";
	public static final String GET_USER_BY_LOGIN = "SELECT login, name FROM users WHERE login = ?";
	public static final String INSERT_NEW_USER = "INSERT INTO users values(?, ?, ?)";
	public static final String SELECT_USER = "SELECT login, name FROM users WHERE login = ? and password = ?";
}
