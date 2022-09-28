package com.orion.labreservationapp.service;

import com.orion.labreservationapp.DBC.TransactionManager;
import com.orion.labreservationapp.log.Log;
import com.orion.labreservationapp.model.ReservationDO;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationServiceIF {
    private final String SQL_CREATE_RESERVATION = "INSERT INTO reservation (user_id, server_id, start_date, end_date, status, reason) VALUES (?,?,?,?,?,?)";
    private final String SQL_GET_RESERVATIONS = "SELECT * FROM reservation";
    private final String SQL_UPDATE_RESERVATION = "UPDATE reservation SET user_id = ?, server_id = ?, start_date = ?, end_date = ?, status = ?, reason = ? WHERE id = ?";
    private final String SQL_DELETE_RESERVATION = "DELETE FROM reservation WHERE id = ?";

    public ReservationServiceImpl() {
    }

    public void createReservation(int user_id, int server_id, String start_date, String end_date, Boolean status,
            String reason) throws SQLException {
        try {
            Connection connection = TransactionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_RESERVATION);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, server_id);
            try {
                preparedStatement.setDate(3, new java.sql.Date(stringToDate(start_date).getTime()));
                preparedStatement.setDate(4, new java.sql.Date(stringToDate(end_date).getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            preparedStatement.setBoolean(5, status);
            preparedStatement.setString(6, reason);
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            Log.logger(Log.LogConstant.TAG_ERROR, e.getMessage());
        }
    }

    public static Date stringToDate(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = dateFormat.parse(date);
        java.util.Date utilDate = date1;
        return utilDate;
    }

    // I wrote this function for checking dates
    public boolean getDatesBetween(LocalDate startDate, LocalDate endDate) throws SQLException {
        List<ReservationDO> reservationList = getReservation();
        ReservationDO end_Date = reservationList.get(2);
        ReservationDO start_Date = reservationList.get(3);

        // this part gets dates between input dates
        List<LocalDate> dateList = new ArrayList<LocalDate>();
        dateList = startDate.datesUntil(endDate).collect(Collectors.toList());

        for (int i = 0; i < dateList.size(); i++) {
            if (dateList.get(i).toString() == start_Date.toString()
                    || dateList.get(i).toString() == end_Date.toString()) {
                return true;
            }
        }
        return false;
    }

    public int updateReservation(ReservationDO reservation) throws SQLException {
        try {
            Connection connection = TransactionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_RESERVATION);
            statement.setString(1, reservation.getServer_id());
            statement.setString(2, reservation.getUser_id());
            statement.setString(3, reservation.getStart_date());
            statement.setString(4, reservation.getEnd_date());
            statement.setBoolean(5, reservation.getStatus());
            statement.setString(6, reservation.getReason());
            statement.setInt(8, reservation.getId());
            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return result;
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            Log.logger(Log.LogConstant.TAG_ERROR, e.getMessage());
            throw e;
        }
    }

    public List<ReservationDO> getReservation() throws SQLException {
        try {
            Connection connection = TransactionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_RESERVATIONS);
            List<ReservationDO> reservationList = new ArrayList<>();
            while (resultSet.next()) {
                ReservationDO reservation = new ReservationDO();
                reservation.setServer_id(resultSet.getString("server_id"));
                reservation.setUser_id(resultSet.getString("user_id"));
                reservation.setStart_date(resultSet.getString("start_date"));
                reservation.setEnd_date(resultSet.getString("end_date"));
                reservation.setStatus(resultSet.getBoolean("status"));
                reservation.setReason(resultSet.getString("reason"));
                reservationList.add(reservation);
            }
            resultSet.close();
            statement.close();
            connection.close();
            return reservationList;
        } catch (SQLException e) {
            Log.logger(Log.LogConstant.TAG_ERROR, e.getMessage());
            throw e;
        }
    }
}