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
@RequestMapping("/api/dwell-engine")
@Tag(name = "Dwell Engine", description = "Endpoints para cálculo de dwell events a partir de posições e POIs")
public class DwellEngineController {

	private final DwellEngineService dwellEngineService;

	@Autowired
	public DwellEngineController(DwellEngineService dwellEngineService) {
		this.dwellEngineService = dwellEngineService;
	}

	@PostMapping("/calculate")
	@Operation(summary = "Calcula dwell events", description = "Recebe uma lista de posições e POIs e retorna os dwell events calculados")
	public List<DwellEvent> calculateDwellEvents(
			@Parameter(description = "Objeto contendo posições e POIs") @RequestBody DwellRequest request) {
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
