package com.orion.labreservationapp.responses;

import com.orion.labreservationapp.entity.Reservation;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class ReservationResponse {
    Long id;
    Long userId;
    Long serverId;
    @Temporal(TemporalType.DATE)
    Date reservationDate;

    public ReservationResponse(Reservation entity){ // for mapping. => mapper
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.serverId = entity.getServer().getId();
        this.reservationDate = entity.getReservationDate();
    }

}