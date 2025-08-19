package com.mobi7.trackpoi.controller;

import com.mobi7.trackpoi.model.Position;
import com.mobi7.trackpoi.service.PositionIngestService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin
public class VehicleController {

	private final PositionIngestService ingest;

	public VehicleController(PositionIngestService ingest) {
		this.ingest = ingest;
	}

	@GetMapping("/plates")
	public List<String> plates() {
		return ingest.listPlates();
	}

	@GetMapping("/positions")
	public List<Position> positions(@RequestParam String plate, @RequestParam String date) {
		LocalDate d = LocalDate.parse(date);
		return ingest.fetchPersistAndReturn(plate, d);
	}
}
