package com.orion.labreservationapp.service;

import com.orion.labreservationapp.DBC.TransactionManager;
import com.orion.labreservationapp.log.Log;
import com.orion.labreservationapp.model.UserDO;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserServiceIF{

    private final String SQL_GET_USERS = "SELECT * FROM users";
    private final String SQL_CREATE_USER = "INSERT INTO users (user_name, first_name, last_name, email, department, password, id) VALUES (?,?,?,?,?,?,?)";
    private final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    private final String SQL_DELETE_USERS = "DELETE FROM users";
    private final String SQL_UPDATE_USER = "UPDATE users SET user_name = ?, first_name = ?, last_name = ?, email = ?, department = ?, password = ? WHERE id = ?";


    @Override
    public int getUserCount() throws SQLException {
        Connection connection = TransactionManager.getConnection();
        int count = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select count(*) from users;");
        while (resultSet.next()) {
            count = resultSet.getInt("count");
        }
        resultSet.close();
        statement.close();
        connection.close();
        return count;
    }

    public int getLastUserId() throws SQLException {
        Connection connection = TransactionManager.getConnection();
        int lastId = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = (SELECT MAX(id) FROM users)");
        while (resultSet.next()) {
            lastId = resultSet.getInt(1);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return lastId;
    }

    @Override
    public List<UserDO> getUser() throws SQLException {
        try {
            Connection connection = TransactionManager.getConnection();
            List<UserDO> users = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_USERS);
            while (resultSet.next()) {
                UserDO user = new UserDO();
                user.setId(resultSet.getInt("id"));
                user.setUser_name(resultSet.getString("user_name"));
                user.setFirst_name(resultSet.getString("first_name"));
                user.setLast_name(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setDepartment(resultSet.getString("department"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
            resultSet.close();
            statement.close();
            connection.close();
            return users;
        } catch (SQLException e) {
            Log.logger(Log.LogConstant.TAG_ERROR, e.getMessage());
            throw e;
        }
    }

    @Override
    public void createUser(String user_name, String first_name, String last_name, String email, String department, String password, int id) throws SQLException {
        id = getLastUserId() + 1;
        try {

            Connection connection = TransactionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER);
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, first_name);
            preparedStatement.setString(3, last_name);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, department);
            preparedStatement.setBoolean(6, true);
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Log.logger(Log.LogConstant.TAG_ERROR, e.getMessage());
            throw e;
        }

    }

    @Override
    public int updateUser(UserDO user) throws SQLException {
        try {
            Connection connection = TransactionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1, user.getUser_name());
            statement.setString(2, user.getFirst_name());
            statement.setString(3, user.getLast_name());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getDepartment());
            statement.setString(6, user.getPassword());
            statement.setInt(7, user.getId());
            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return result;
        } catch (SQLException e) {
            Log.logger(Log.LogConstant.TAG_ERROR, e.getMessage());
            throw e;
        }
    }

    @Override
    public int deleteUserByID(UserDO user) throws SQLException {
        try {
            Connection connection = TransactionManager.getConnection();
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            connection.close();
            return user.getId();
        } catch (SQLException e) {
            Log.logger(Log.LogConstant.TAG_ERROR, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<UserDO> deleteUser() throws SQLException {
        try {
            Connection connection = TransactionManager.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL_DELETE_USERS);
            connection.commit();
            statement.close();
            connection.close();
            return getUser();
        } catch (SQLException e) {
            Log.logger(Log.LogConstant.TAG_ERROR, e.getMessage());
            throw e;
        }
    }
}
