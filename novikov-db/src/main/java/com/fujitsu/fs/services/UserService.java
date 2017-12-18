package com.fujitsu.fs.services;

import java.sql.SQLException;

public interface UserService {

    boolean isRegistered(String username, String password);

    void save(String username, String password);
}
