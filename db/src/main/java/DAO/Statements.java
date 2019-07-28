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
	public static final String GET_PICTURE_BY_ID = "SELECT * FROM pictures WHERE picture_id = ?";
	public static final String SAVE_PICTURE = "INSERT INTO pictures VALUES (id_generator.NEXTVAL, ?, TO_DATE(?, 'dd-MM-yyyy HH:MI:SS'), ?)";
	public static final String GET_PICTURE_BY_USER = "SELECT * FROM pictures WHERE painted_by = ?";
	public static final String GET_USER_FAVORITES_PICTURES = "SELECT * from pictures WHERE picture_id IN (SELECT * FROM picture_likes WHERE login = ?)";
	public static final String DELETE_PICTURE = "DELETE FROM pictures WHERE picture_id = ?";
	public static final String ADD_COMMENT_TO_PICTURE = "INSERT INTO comments VALUES(id_generator.NEXTVAL, ?, ?, ?, ?)";
	public static final String GET_LAST_COMMENT_FOR_PICTURE = "SELECT comment_id FROM comments WHERE connect_by_isleaf = 1 START WITH picture_id = ? CONNECT BY PRIOR comment_id = parent_id";
	public static final String GET_COMMENTS_FOR_PICTURE = "SELECT c.comment_id, c.picture_id, u.login, u.name, c.comment_data FROM comments c JOIN users u ON u.login = c.login WHERE c.picture_id = ?";
}
