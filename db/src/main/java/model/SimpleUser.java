package model;

/**
 * This class is used when there is no need to store the whole information about user
 */
public class SimpleUser {
    /**
     * User's login
     */
    protected String login;
    /**
     * User's name
     */
    protected String name;

    /**
     * Getter for login
     *
     * @return user's login
     */
    public String getLogin() {
        return login;
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
     * Getter for name
     *
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     *
     * @param name user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This constructor creates instance of SimpleUser
     * @param login user's login
     * @param name user's name
     */
    public SimpleUser(String login, String name) {
        this.login = login;
        this.name = name;
    }
}
