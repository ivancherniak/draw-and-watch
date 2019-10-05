package model;

import services.SimpleUser;

/**
 * This class contains the whole information about users
 */
public class User extends SimpleUser {
    /**
     * User's password
     */
    private String password;

    /**
     * This constructor creates instance of User
     *
     * @param name     user's name
     * @param login    user's login
     * @param password user's password
     */
    public User(String login, String name, String password) {
        super(login, name);
        this.password = password;
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
     * Setter for password
     *
     * @param password user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
