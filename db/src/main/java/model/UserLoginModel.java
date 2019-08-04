package model;

/**
 * This class is used for contain data from login form of Signin page
 */
public class UserLoginModel {
    /**
     * User's login
     */
    protected String login;
    /**
     * User's password
     */
    protected String password;

    /**
     * Getter for login
     *
     * @return user's login
     */
    public String getLogin() {
        return login;
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
     * Setter for login
     *
     * @param login user's login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Setter for user's password
     *
     * @param password user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginModel{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
