package com.fujitsu.fs.dao;

import com.fujitsu.fs.utils.DbWrapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UserDaoImpl implements UserDao {

    private static UserDao userDao;

    private final Logger userLogger = Logger.getLogger(UserDaoImpl.class);

    private UserDaoImpl() {

    }

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public boolean isRegistered(String username, String password) {
        Connection connection = null;
        try {
            connection = DbWrapper.getConnection();
        } catch (Exception ex) {
            userLogger.error("Couldn't get connection for user registration checking");
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        String sql = null;

        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ? ");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, getHash(password));
            } catch (Exception e) {
                userLogger.error("Couldn't create statement for user registration checking");
            }


            if(preparedStatement != null) {
                try {
                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        return true;

                    }
                } catch (Exception e) {
                    userLogger.error("Couldn't execute query for registration checking");
                }
            }

            try{
                connection.close();
            }catch(Exception e) {
                userLogger.error("Couldn't close connection");
            }

        }

        return false;

    }

    public void save(String username, String password){
        Connection connection = null;
        try {
            connection = DbWrapper.getConnection();
        } catch (Exception ex) {
            userLogger.error("Couldn't get connection to save user");
        }


        if (connection != null) {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement("INSERT INTO Users (username,password) VALUES(?,?)");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, getHash(password));
                preparedStatement.execute();
            } catch (SQLException ex) {
                userLogger.error("Couldn't create statement to save user");
            }

            try {
                connection.close();
            } catch (SQLException e) {
                userLogger.error("Couldn't close connection");
            }

            DbWrapper.putback(connection);


        }


    }

    private String getHash(String password) {
        return Arrays.toString(DigestUtils.md5(password));
    }


}
