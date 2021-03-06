package com.hajba.booking.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "seat_info")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
public class SeatInfoEntity extends AbstractEntity<Long>{

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleEntity vehicle;

    @ManyToOne
    @JoinColumn(name = "journey_id", nullable = false)
    private JourneyEntity journey;

    // Кол-во свободных мест
    @Column(name = "free_seats")
    private Integer freeSeats;

}

