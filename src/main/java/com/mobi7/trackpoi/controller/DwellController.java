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
@RequestMapping("/api/dwells")
@Tag(name = "Dwell Events", description = "Gerencia dwell events persistidos")
public class DwellController {

	private final DwellEventRepository dwellEventRepository;

	@Autowired
	public DwellController(DwellEventRepository dwellEventRepository) {
		this.dwellEventRepository = dwellEventRepository;
	}

	@GetMapping
	@Operation(summary = "Lista todos os dwell events")
	public List<DwellEvent> getAllDwellEvents() {
		return dwellEventRepository.findAll();
	}

	@GetMapping("/plate/{plate}")
	@Operation(summary = "Lista dwell events por placa", description = "Filtra dwell events pelo número da placa do veículo")
	public List<DwellEvent> getDwellEventsByPlate(
			@Parameter(description = "Número da placa do veículo") @PathVariable String plate) {
		return dwellEventRepository.findByPlate(plate);
	}

	@PostMapping
	@Operation(summary = "Cria um dwell event", description = "Persiste um dwell event no banco")
	public DwellEvent createDwellEvent(
			@Parameter(description = "Objeto DwellEvent a ser criado") @RequestBody DwellEvent dwellEvent) {
		return dwellEventRepository.save(dwellEvent);
	}
}
