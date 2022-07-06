package com.orion.labreservationapp.model;

import java.util.Date;

public class ReservationDO {
    private int id;
    private String server_id;
    private String user_id;
    private String  date;
    private Boolean status;
    private String reason;

    public ReservationDO(int id, String server_id, String user_id, String  date, Boolean status, String reason) {
        this.id = id;
        this.server_id = server_id;
        this.user_id = user_id;
        this.date = date;
        this.status = status;
        this.reason = reason;
    }
    public ReservationDO() {

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getServer_id() {
        return server_id;
    }
    public void setServer_id(String server_id) {
        this.server_id = server_id;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String  getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    @Override
    public String toString() {
        return "ReservationDO{" +
                "id=" + id +
                ", server_id='" + server_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}

