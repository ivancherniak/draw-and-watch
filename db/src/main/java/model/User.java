package model;

import java.util.Optional;

// TODO: 7/29/2019 replace optional with base types

/**
 * This class represents user instances
 */
public class User {
    /**
     * Name of the user
     */
    private Optional<String> name;
    /**
     * User's login
     */
    private Optional<String> login; // TODO: 7/29/2019 should be final
    /**
     * User's password
     */
    private Optional<String> password; // TODO: 7/29/2019 should be final
    /**
     * User's repeat password
     */
    private Optional<String> repeatPassword; // TODO: 7/29/2019 should be final

    /**
     * This constructor creates instance of User
     *
     * @param name           user's name
     * @param login          user's login
     * @param password       user's password
     * @param repeatPassword user's repeat password
     */
    public User(String name, String login, String password, String repeatPassword) {
        this.name = Optional.ofNullable(name);
        this.login = Optional.ofNullable(login);
        this.password = Optional.ofNullable(password);
        this.repeatPassword = Optional.ofNullable(repeatPassword);
    }

    /**
     * Getter for login
     *
     * @return user's login
     */
    public String getLogin() {
        return login.orElseGet(String::new);
    }

    /**
     * Getter for name
     *
     * @return user's name
     */
    public String getName() {
        return name.orElseGet(String::new);
    }

    /**
     * Getter for password
     *
     * @return user's password
     */
    public String getPassword() {
        return password.orElseGet(String::new);
    }

    /**
     * Setter for name
     *
     * @param name user's name
     */
    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }
}
