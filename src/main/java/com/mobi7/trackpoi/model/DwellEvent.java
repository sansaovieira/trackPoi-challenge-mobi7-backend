package com.mobi7.trackpoi.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "dwell_events")
public class DwellEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String plate;

	@Column(name = "poi_name")
	private String poiName;

	private Instant startTime;
	private Instant endTime;
	private long durationMinutes;

	@Column(name = "poi_id")
	private Long poiId;

	public DwellEvent() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getPoiName() {
		return poiName;
	}

	public void setPoiName(String poiName) {
		this.poiName = poiName;
	}

	public Instant getStartTime() {
		return startTime;
	}

	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}

	public Instant getEndTime() {
		return endTime;
	}

	public void setEndTime(Instant endTime) {
		this.endTime = endTime;
	}

	public long getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(long durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public Long getPoiId() {
		return poiId;
	}

	public void setPoiId(Long poiId) {
		this.poiId = poiId;
	}
}
