package com.fujitsu.fs.dao;

import java.sql.SQLException;

public interface UserDao {

    boolean isRegistered(String username, String password);

    void save(String username, String password);
}
