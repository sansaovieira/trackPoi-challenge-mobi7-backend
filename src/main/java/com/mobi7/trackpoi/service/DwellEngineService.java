package com.mobi7.trackpoi.service;

import com.mobi7.trackpoi.model.DwellEvent;
import com.mobi7.trackpoi.model.Poi;
import com.mobi7.trackpoi.model.Position;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class DwellEngineService {

	private static final double SPEED_THRESHOLD_STOPPED = 5.0;

	public List<DwellEvent> calculateDwellEvents(List<Position> positions, List<Poi> pois) {
		List<DwellEvent> dwellEvents = new ArrayList<>();

		for (Poi poi : pois) {
			DwellEvent currentDwell = null;

			for (Position position : positions) {
				if (isInsidePoi(position, poi) && position.getSpeed() <= SPEED_THRESHOLD_STOPPED) {

					if (currentDwell == null) {
						currentDwell = new DwellEvent();
						currentDwell.setPlate(position.getPlate());
						currentDwell.setPoiName(poi.getName());
						currentDwell.setPoiId(poi.getId());
						currentDwell.setStartTime(position.getTimestamp());
					}

					currentDwell.setEndTime(position.getTimestamp());

				} else {
					if (currentDwell != null) {
						long durationMinutes = Duration.between(currentDwell.getStartTime(), currentDwell.getEndTime())
								.toMinutes();
						currentDwell.setDurationMinutes(durationMinutes);

						dwellEvents.add(currentDwell);
						currentDwell = null;
					}
				}
			}

			if (currentDwell != null) {
				long durationMinutes = Duration.between(currentDwell.getStartTime(), currentDwell.getEndTime())
						.toMinutes();
				currentDwell.setDurationMinutes(durationMinutes);
				dwellEvents.add(currentDwell);
			}
		}

		return dwellEvents;
	}

	private boolean isInsidePoi(Position position, Poi poi) {
		double distance = haversine(position.getLatitude(), position.getLongitude(), poi.getLatitude(),
				poi.getLongitude());
		return distance <= poi.getRadius();
	}

	private double haversine(double lat1, double lon1, double lat2, double lon2) {
		final int EARTH_RADIUS = 6371000;
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return EARTH_RADIUS * c;
	}
}
