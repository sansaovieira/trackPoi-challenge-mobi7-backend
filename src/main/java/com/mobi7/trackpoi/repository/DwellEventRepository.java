package com.mobi7.trackpoi.repository;

import com.mobi7.trackpoi.model.DwellEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DwellEventRepository extends JpaRepository<DwellEvent, Long> {

	List<DwellEvent> findByPlate(String plate);

	List<DwellEvent> findByPlateAndStartTimeBetween(String plate, java.time.LocalDateTime start,
			java.time.LocalDateTime end);
}
