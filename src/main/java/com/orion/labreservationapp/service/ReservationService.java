package com.orion.labreservationapp.service;

import com.orion.labreservationapp.entity.Reservation;
import com.orion.labreservationapp.entity.Server;
import com.orion.labreservationapp.entity.User;
import com.orion.labreservationapp.repos.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private UserService userService;
    private ServerService serverService;

    public ReservationService(ReservationRepository reservationRepository,
                              UserService userService,
                              ServerService serverService){
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.serverService = serverService;
    }

    //Check out here later.
    /*public List<Reservation> geetAllReservations() {
        return reservationRepository.findAll();
    }*/

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getOneReservationById(Long reservationId){
        return reservationRepository.findById(reservationId).orElse(null);
    }
    public Reservation createOneReservation(Reservation newReservation) {
        return reservationRepository.save(newReservation);
    }

    public Reservation updateOneReservationById(Long reservationId,
                                                Reservation newReservation) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isPresent())
        {
            Reservation toUpdate = reservation.get();
            toUpdate.setUser(newReservation.getUser());
            toUpdate.setServer(newReservation.getServer());
            /*toUpdate.setReservationDate((Date) updateReservation.getReservationDate());*/
            reservationRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOneReservationById(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }
}