package model;

/**
 * This class represents user instances
 */
public class User {
    /**
     * Name of the user
     */
    private String name;
    /**
     * User's login
     */
    private final String login;
    /**
     * User's password
     */
    private final String password;
    /**
     * User's repeat password
     */
    private final String repeatPassword;

    /**
     * This constructor creates instance of User
     *
     * @param name           user's name
     * @param login          user's login
     * @param password       user's password
     * @param repeatPassword user's repeat password
     */
    public User(String name, String login, String password, String repeatPassword) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    /**
     * Getter for login
     *
     * @return user's login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Getter for name
     *
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for password
     *
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for name
     *
     * @param name user's name
     */
    public void setName(String name) {
        this.name = name;
    }
}
