package com.orion.labreservationapp.requests;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class ReservationUpdateRequest {

    @Temporal(TemporalType.DATE)
    Date reservationDate;
}