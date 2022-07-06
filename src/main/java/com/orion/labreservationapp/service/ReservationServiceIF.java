package com.orion.labreservationapp.service;

import com.orion.labreservationapp.model.ReservationDO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ReservationServiceIF {
    public void createReservation(int user_id, int server_id, String date, Boolean status, String reason) throws SQLException;
    public List<ReservationDO> getReservation() throws SQLException;
    public int updateReservation(ReservationDO reservation) throws SQLException;
    public int deleteReservation(ReservationDO reservation) throws SQLException;
}
