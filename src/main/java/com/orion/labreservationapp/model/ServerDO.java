package com.orion.labreservationapp.model;

public class ServerDO {
    private int id;
    private String serverName;
    private String serverIP;
    private String serverLocation;
    private String serialNumber;
    private String serverType;
    private Boolean isHost;


    public ServerDO(String serverName, String serverIP, String serverLocation, String serialNumber, String serverType, Boolean isHost) {
        this.serverName = serverName;
        this.serverIP = serverIP;
        this.serverLocation = serverLocation;
        this.serialNumber = serialNumber;
        this.serverType = serverType;
        this.isHost = isHost;
    }
    public ServerDO() {
    }
    public int getId() {
        return id;
    }
    public String getServerName() {
        return serverName;
    }
    public String getServerIP() {
        return serverIP;
    }
    public String getServerLocation() {
        return serverLocation;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public String getServerType() {
        return serverType;
    }
    public Boolean getIsHost() {
        return isHost;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }
    public void setServerLocation(String serverLocation) {
        this.serverLocation = serverLocation;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public void setServerType(String serverType) {
        this.serverType = serverType;
    }
    public void setIsHost(Boolean isHost) {
        this.isHost = isHost;
    }
}

