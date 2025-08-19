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
@RequestMapping("/api/vehicles")
@CrossOrigin
@Tag(name = "Veículos", description = "Endpoints para gestão de posições e placas de veículos")
public class VehicleController {

	private final PositionIngestService ingest;

	public VehicleController(PositionIngestService ingest) {
		this.ingest = ingest;
	}

	@GetMapping("/plates")
	@Operation(summary = "Lista todas as placas")
	public List<String> plates() {
		return ingest.listPlates();
	}

	@GetMapping("/positions")
	@Operation(summary = "Lista posições de um veículo", description = "Filtra posições por placa e data")
	public List<Position> positions(@Parameter(description = "Número da placa do veículo") @RequestParam String plate,
			@Parameter(description = "Data no formato yyyy-MM-dd") @RequestParam String date) {
		LocalDate d = LocalDate.parse(date);
		return ingest.fetchPersistAndReturn(plate, d);
	}
}
