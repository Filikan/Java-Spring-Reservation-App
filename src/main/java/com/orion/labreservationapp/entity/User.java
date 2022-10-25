package com.orion.labreservationapp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    Long id;

    String firstName;
    String lastName;
    String email;
    String password;


}
