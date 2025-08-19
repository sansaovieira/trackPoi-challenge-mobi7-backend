package com.mobi7.trackpoi.repository;

import com.mobi7.trackpoi.model.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoiRepository extends JpaRepository<Poi, Long> {
	Poi findByName(String name);
}
