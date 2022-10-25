package com.orion.labreservationapp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "servers")
@Data
public class Server {
    @Id
    Long id;

    String serverName;
    String serverLocation;
    String serverIp;
    String serialNumber;
    String serverType;
    Boolean isHost;
}
