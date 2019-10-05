package services;

/**
 * This class is used for contain data from registration form of Registration page
 */
public class UserRegistrationModel extends UserLoginModel {
    /**
     * User's name
     */
    private String name;
    /**
     * User's repeat password
     */
    private String repeatPassword;

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
     * Getter for repeat password
     *
     * @return user's repeat password
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Setter for repeat password
     *
     * @param repeatPassword user's repeat password
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public String toString() {
        return "UserRegistrationModel{" +
                "name='" + name + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
