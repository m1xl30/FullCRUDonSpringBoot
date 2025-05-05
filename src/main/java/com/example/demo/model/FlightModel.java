package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="flights")
public class FlightModel {

    @Id
    @Column(name="id")
    private Long id;
    @Column(name="flightnumber")
    private String flightNumber;
    @Column(name="destination")
    private String destination;

}
