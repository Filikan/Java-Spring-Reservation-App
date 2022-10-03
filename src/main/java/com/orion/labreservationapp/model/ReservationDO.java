package com.orion.labreservationapp.model;

public class ReservationDO {
    private int id;
    private String server_id;
    private String user_id;
    private String start_date;
    private String end_date;
    private Boolean status;
    private String reason;

    public ReservationDO(int id, String server_id, String user_id, String date, Boolean status, String reason) {
        this.id = id;
        this.server_id = server_id;
        this.user_id = user_id;
        this.start_date = start_date;
        this.end_date = end_date;
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String date) {
        this.end_date = end_date;
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
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
