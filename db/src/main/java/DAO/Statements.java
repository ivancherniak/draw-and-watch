package DAO;

/**
 * This class contains strings to pass the argument in prepared statements
 */
public class Statements {
    public static final String GET_MAIN_DATA_OF_ALL_USERS = "SELECT login, name FROM users";
    public static final String GET_USER_BY_LOGIN = "SELECT login, name FROM users WHERE login = ?";
    public static final String INSERT_NEW_USER = "INSERT INTO users values(?, ?, ?)";
    public static final String GET_FAVOURITE_PROFILES_BY_USER_LOGIN = "SELECT u.login, u.name FROM favourite_profiles fp JOIN users u ON u.login = fp.likes WHERE fp.login = ?";
    public static final String GET_MAIN_USER_DATA_BY_LOGIN = "SELECT name FROM users WHERE login = ?";
    public static final String ADD_PROFILE_TO_FAVOURITES = "INSERT INTO favourite_profiles VALUES(?, ?)";
    public static final String DELETE_PROFILE_FROM_FAVOURITES = "DELETE FROM favourite_profiles WHERE login = ? AND likes = ?";
    public static final String GET_PICTURE_BY_ID = "SELECT picture_id, painted_by, (SELECT name FROM users WHERE login = painted_by), painted_when, content FROM pictures WHERE picture_id = ?";
    public static final String SAVE_PICTURE = "INSERT INTO pictures VALUES (id_generator.NEXTVAL, ?, TO_DATE(?, 'dd-MM-yyyy HH:MI:SS'), ?)";
    public static final String GET_MAIN_PICTURE_DATA_BY_LOGIN = "SELECT picture_id, content FROM pictures WHERE painted_by = ?";
    public static final String ADD_COMMENT_TO_PICTURE = "INSERT INTO comments VALUES(id_generator.NEXTVAL, ?, ?, ?, ?)";
    public static final String GET_LAST_COMMENT_FOR_PICTURE = "SELECT comment_id FROM comments WHERE connect_by_isleaf = 1 START WITH picture_id = ? CONNECT BY PRIOR comment_id = parent_id";
    public static final String GET_COMMENTS_FOR_PICTURE = "SELECT c.login, (SELECT name FROM users WHERE login = c.login), c.comment_data FROM comments c START WITH parent_id IS NULL AND picture_id = ? CONNECT BY PRIOR comment_id = parent_id";
    public static final String IS_PICTURE_LIKED = "SELECT 1 FROM picture_likes WHERE picture_id = ? AND login = ?";
    public static final String REMOVE_LIKE = "DELETE FROM picture_likes WHERE picture_id = ? AND login = ?";
    public static final String ADD_LIKE = "INSERT INTO picture_likes VALUES(?, ?)";
    public static final String COUNT_LIKES = "SELECT count(*) FROM picture_likes WHERE picture_id = ?";
    public static final String IS_PROFILE_IN_FAVOURITES = "SELECT count(*) FROM favourite_profiles WHERE login = ? AND likes = ?";
    public static final String GET_USER_DATA_BY_LOGIN = "SELECT name, password FROM users WHERE login = ?";
}
