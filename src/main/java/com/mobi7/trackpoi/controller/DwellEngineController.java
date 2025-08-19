package com.mobi7.trackpoi.controller;

import com.mobi7.trackpoi.model.DwellEvent;
import com.mobi7.trackpoi.model.Poi;
import com.mobi7.trackpoi.model.Position;
import com.mobi7.trackpoi.service.DwellEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dwell-engine")
public class DwellEngineController {

	private final DwellEngineService dwellEngineService;

	@Autowired
	public DwellEngineController(DwellEngineService dwellEngineService) {
		this.dwellEngineService = dwellEngineService;
	}

	@PostMapping("/calculate")
	public List<DwellEvent> calculateDwellEvents(@RequestBody DwellRequest request) {
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
