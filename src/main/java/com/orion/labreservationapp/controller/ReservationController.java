package com.orion.labreservationapp.controller;

import com.orion.labreservationapp.entity.Reservation;
import com.orion.labreservationapp.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    //Check this again.
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PostMapping
    public Reservation createOneReservation(@RequestBody Reservation newReservation) {
        return reservationService.createOneReservation(newReservation);
    }

    @GetMapping("/{reservationId}")
    public Reservation getOneReservationById(@PathVariable Long reservationId) {
        return reservationService.getOneReservationById(reservationId);
    }

    @PutMapping("/{reservationId}")
    public Reservation updateOneReservation(@PathVariable Long reservationId,
                                            @RequestBody Reservation newReservation) {
        return reservationService.updateOneReservationById(reservationId,newReservation);
    }

    @DeleteMapping("/{reservationId}")
    public void deleteOneReservation(@PathVariable Long reservationId) {
        reservationService.deleteOneReservationById(reservationId);
    }


}