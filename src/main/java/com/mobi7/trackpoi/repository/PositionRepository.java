package com.mobi7.trackpoi.repository;

import com.mobi7.trackpoi.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
	List<Position> findByPlateAndTimestampBetween(String plate, Instant start, Instant end);

	List<Position> findByPlate(String plate);
}
