package com.orion.labreservationapp.service;

import com.orion.labreservationapp.DBC.TransactionManager;
import com.orion.labreservationapp.log.Log;
import com.orion.labreservationapp.model.ReservationDO;
import com.orion.labreservationapp.model.ServerDO;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationServiceIF {
private final String SQL_CREATE_RESERVATION = "INSERT INTO reservation (user_id, server_id, date, status, reason) VALUES (?,?,?,?,?)";
private final String SQL_GET_RESERVATIONS = "SELECT * FROM reservation";
private final String SQL_UPDATE_RESERVATION = "UPDATE reservation SET user_id = ?, server_id = ?, date = ?, status = ?, reason = ? WHERE id = ?";
private final String SQL_DELETE_RESERVATION = "DELETE FROM reservation WHERE id = ?";


    public void createReservation(int user_id, int server_id, String date, Boolean status, String reason) throws SQLException {
    try {
        Connection connection = TransactionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_RESERVATION);
        preparedStatement.setInt(1, user_id);
        preparedStatement.setInt(2, server_id);
        preparedStatement.setDate(3, stringToDate(date));
        preparedStatement.setBoolean(4, status);
        preparedStatement.setString(5, reason);
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
    } catch (SQLException e) {
        Log.logger(Log.LogConstant.TAG_ERROR, e.getMessage());
    }

    }
    public static Date stringToDate(String date) {
        java.util.Date date1 = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
        return sqlDate;
    }
    public int updateReservation(ReservationDO reservation) throws SQLException {
        try {
            Connection connection = TransactionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_RESERVATION);
            statement.setString(1, reservation.getServer_id());
            statement.setString(2, reservation.getUser_id());
            statement.setString(3, reservation.getDate());
            statement.setBoolean(4, reservation.getStatus());
            statement.setString(5, reservation.getReason());
            statement.setInt(6, reservation.getId());
            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return result;
        }
        catch (SQLException e) {
            Log.logger(Log.LogConstant.TAG_ERROR, e.getMessage());
            throw e;
        }
    }


public int deleteReservation(ReservationDO reservation) throws SQLException {
    try {
        Connection connection = TransactionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(SQL_DELETE_RESERVATION);
        statement.setInt(1, reservation.getId());
        int result = statement.executeUpdate();
        statement.close();
        connection.close();
        return result;
    }
    catch (SQLException e) {
        Log.logger(Log.LogConstant.TAG_ERROR, e.getMessage());
        throw e;
    }
}


   public List <ReservationDO> getReservation() throws SQLException {
        try {
            Connection connection = TransactionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_RESERVATIONS);
            List <ReservationDO> reservationList = new ArrayList<>();
            while (resultSet.next()) {
                ReservationDO reservation = new ReservationDO();
                reservation.setServer_id(resultSet.getString("server_id"));
                reservation.setUser_id(resultSet.getString("user_id"));
                reservation.setDate(resultSet.getString("date"));
                reservation.setStatus(resultSet.getBoolean("status"));
                reservation.setReason(resultSet.getString("reason"));
                reservationList.add(reservation);
            }
            resultSet.close();
            statement.close();
            connection.close();
            return reservationList;
        }
        catch (SQLException e) {
            Log.logger(Log.LogConstant.TAG_ERROR, e.getMessage());
            throw e;
        }
    }
}