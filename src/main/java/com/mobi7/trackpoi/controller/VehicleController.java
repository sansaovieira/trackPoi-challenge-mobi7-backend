package com.mobi7.trackpoi.controller;

import com.mobi7.trackpoi.model.Position;
import com.mobi7.trackpoi.service.PositionIngestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin
@Tag(name = "Vehicles", description = "Endpoints for managing vehicle positions and license plates")
public class VehicleController {

	private final PositionIngestService ingest;

	public VehicleController(PositionIngestService ingest) {
		this.ingest = ingest;
	}

	@GetMapping("/plates")
	@Operation(summary = "List all plates")
	public List<String> plates() {
		return ingest.listPlates();
	}

	@GetMapping("/positions")
	@Operation(summary = "List all plates", description = "Filter positions by license plate and date")
	public List<Position> positions(@Parameter(description = "Vehicle license plate number") @RequestParam String plate,
			@Parameter(description = "Date in format yyyy-MM-dd") @RequestParam String date) {
		LocalDate d = LocalDate.parse(date);
		return ingest.fetchPersistAndReturn(plate, d);
	}
}
