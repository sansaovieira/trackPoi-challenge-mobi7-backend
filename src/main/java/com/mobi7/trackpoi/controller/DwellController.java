package com.mobi7.trackpoi.controller;

import com.mobi7.trackpoi.model.DwellEvent;
import com.mobi7.trackpoi.repository.DwellEventRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dwells")
@Tag(name = "Dwell Events", description = "Manages persisted dwell events")
public class DwellController {

	private final DwellEventRepository dwellEventRepository;

	@Autowired
	public DwellController(DwellEventRepository dwellEventRepository) {
		this.dwellEventRepository = dwellEventRepository;
	}

	@GetMapping
	@Operation(summary = "List all dwell events")
	public List<DwellEvent> getAllDwellEvents() {
		return dwellEventRepository.findAll();
	}

	@GetMapping("/plate/{plate}")
	@Operation(summary = "List dwell events by plate", description = "Filter dwell events by vehicle license plate number")
	public List<DwellEvent> getDwellEventsByPlate(
			@Parameter(description = "Vehicle license plate number") @PathVariable String plate) {
		return dwellEventRepository.findByPlate(plate);
	}

	@PostMapping
	@Operation(summary = "Creates a dwell event", description = "Persists a dwell event in the database")
	public DwellEvent createDwellEvent(
			@Parameter(description = "DwellEvent object to be created") @RequestBody DwellEvent dwellEvent) {
		return dwellEventRepository.save(dwellEvent);
	}
}
