package com.orion.labreservationapp.model;

public class ServerDO {
    private String server_name;
    private String server_ip;
    private String server_location;
    private String serial_number;
    private String server_type;
    private Boolean is_host;
    private int id;

    public ServerDO(String server_name, String server_ip, String server_location, String serial_number,
            String server_type, Boolean is_host, int id) {
        this.server_name = server_name;
        this.server_ip = server_ip;
        this.server_location = server_location;
        this.serial_number = serial_number;
        this.server_type = server_type;
        this.is_host = is_host;
        this.id = id;
    }

    public ServerDO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public String getServer_ip() {
        return server_ip;
    }

    public void setServer_ip(String server_ip) {
        this.server_ip = server_ip;
    }

    public String getServer_location() {
        return server_location;
    }

    public void setServer_location(String server_location) {
        this.server_location = server_location;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getServer_type() {
        return server_type;
    }

    public void setServer_type(String server_type) {
        this.server_type = server_type;
    }

    public Boolean getIs_host() {
        return is_host;
    }

    public void setIs_host(Boolean is_host) {
        this.is_host = is_host;
    }

    @Override
    public String toString() {
        return "ServerDO{" +
                "id=" + id +
                ", server_name='" + server_name + '\'' +
                ", server_ip='" + server_ip + '\'' +
                ", server_location='" + server_location + '\'' +
                ", serial_number='" + serial_number + '\'' +
                ", server_type='" + server_type + '\'' +
                ", is_host=" + is_host +
                '}';
    }
}
