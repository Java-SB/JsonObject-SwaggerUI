package com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.repository;

import com.ecodriver.platform.u202023992.si729ebu202023992.behaviour.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByDriverId(Long driverId);
}
