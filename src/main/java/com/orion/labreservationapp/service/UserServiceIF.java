package com.orion.labreservationapp.service;

import com.orion.labreservationapp.model.UserDO;

import java.sql.SQLException;
import java.util.List;

public interface UserServiceIF {
    public int getUserCount() throws SQLException;

    public List<UserDO> getUser() throws SQLException;

    public void createUser(String user_name, String first_name, String last_name, String email,
                           String department, String password, int id) throws SQLException;

    public int updateUser(UserDO user) throws SQLException;

    public int deleteUserByID(UserDO user) throws SQLException;

    public List<UserDO> deleteUser() throws SQLException;
}
