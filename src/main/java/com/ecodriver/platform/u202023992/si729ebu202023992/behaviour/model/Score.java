package com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="driver_id", nullable = false)
    private Long driverId;

    @Column(name="value", nullable = false)
    private float value;

    @Column(name="registered_at", nullable = false)
    private Date registeredAt;
}
