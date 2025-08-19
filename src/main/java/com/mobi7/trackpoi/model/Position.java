package com.mobi7.trackpoi.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "position")
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Boolean ignition;
	private Double latitude;
	private Double longitude;
	private String plate;
	private Double speed;

	private Instant timestamp;

	public Position() {
	}

	public Position(String plate, Instant timestamp, double latitude, double longitude, double speed,
			boolean ignition) {
		this.plate = plate;
		this.timestamp = timestamp;
		this.latitude = latitude;
		this.longitude = longitude;
		this.speed = speed;
		this.ignition = ignition;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIgnition() {
		return ignition;
	}

	public void setIgnition(Boolean ignition) {
		this.ignition = ignition;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
}
