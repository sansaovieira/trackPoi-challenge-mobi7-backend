package com.mobi7.trackpoi.controller;

import com.mobi7.trackpoi.model.DwellEvent;
import com.mobi7.trackpoi.model.Poi;
import com.mobi7.trackpoi.model.Position;
import com.mobi7.trackpoi.service.DwellEngineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dwell-engine")
@Tag(name = "Dwell Engine", description = "Endpoints for calculating dwell events from positions and POIs")
public class DwellEngineController {

	private final DwellEngineService dwellEngineService;

	@Autowired
	public DwellEngineController(DwellEngineService dwellEngineService) {
		this.dwellEngineService = dwellEngineService;
	}

	@PostMapping("/calculate")
	@Operation(summary = "Calculates dwell events", description = "Receives a list of positions and POIs and returns the calculated dwell events")
	public List<DwellEvent> calculateDwellEvents(
			@Parameter(description = "Object containing positions and POIs") @RequestBody DwellRequest request) {
		return dwellEngineService.calculateDwellEvents(request.getPositions(), request.getPois());
	}

	public static class DwellRequest {
		private List<Position> positions;
		private List<Poi> pois;

		public List<Position> getPositions() {
			return positions;
		}

		public void setPositions(List<Position> positions) {
			this.positions = positions;
		}

		public List<Poi> getPois() {
			return pois;
		}

		public void setPois(List<Poi> pois) {
			this.pois = pois;
		}
	}
}
