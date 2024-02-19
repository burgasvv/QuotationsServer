package com.burgas.practice2.server.user;

import java.util.Objects;

/**
 * The type User class for storing logins and passwords.
 */
public class User {

    private String login;
    private String password;

    /**
     * Instantiates a new User.
     *
     * @param login the login
     * @param password the password
     */
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        User user = (User) object;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return STR."User{login='\{login}\{'\''}, password='\{password}\{'\''}\{'}'}";
    }
}
