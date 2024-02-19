package com.burgas.practice2.server.user;

import java.util.List;
import java.util.Vector;

/**
 * The type User list.
 */
public class UserList {

    private List<User>userList;


    public UserList() {
        userList = new Vector<>();
        userList.add(new User("client", "client"));
    }

    public List<User> getUserList() {
        return userList;
    }


    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
