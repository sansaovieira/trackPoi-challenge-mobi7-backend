package com.mobi7.trackpoi.controller;

import com.mobi7.trackpoi.model.Poi;
import com.mobi7.trackpoi.service.PoiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pois")
@CrossOrigin
@Tag(name = "POIs", description = "Manage points of interest (POIs)")
public class PoiController {

	private final PoiService service;

	public PoiController(PoiService service) {
		this.service = service;
	}

	@GetMapping
	@Operation(summary = "List all POIs")
	public List<Poi> list() {
		return service.list();
	}

	@PostMapping
	@Operation(summary = "Creates a new POI")
	public ResponseEntity<Poi> create(@RequestBody Poi poi) {
		return ResponseEntity.ok(service.create(poi));
	}
}
