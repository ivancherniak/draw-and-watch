package DAO;

public class Statements {
	public static final String GET_ALL_USERS = "SELECT login, name FROM users";
	public static final String GET_USER_BY_LOGIN = "SELECT login, name FROM users WHERE login = ?";
	public static final String INSERT_NEW_USER = "INSERT INTO users values(?, ?, ?)";
	public static final String SELECT_USER = "SELECT login, name FROM users WHERE login = ? and password = ?";
	public static final String GET_FAVOURITE_PROFILES_BY_USER_LOGIN = "SELECT u.login, u.name FROM favourite_profiles fp JOIN users u ON u.login = fp.likes WHERE fp.login = ?";
	public static final String GET_USER_DATA_BY_LOGIN = "SELECT login, name FROM users WHERE login = ?";
	public static final String ADD_PROFILE_TO_FAVOURITES = "INSERT INTO favourite_profiles VALUES(?, ?)";
	public static final String DELETE_PROFILE_FROM_FAVOURITES = "DELETE FROM favourite_profiles WHERE login = ? and likes = ?";
}
