package com.mobi7.trackpoi.controller;

import com.mobi7.trackpoi.model.Poi;
import com.mobi7.trackpoi.service.PoiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pois")
@CrossOrigin
public class PoiController {

	private final PoiService service;

	public PoiController(PoiService service) {
		this.service = service;
	}

	@GetMapping
	public List<Poi> list() {
		return service.list();
	}

	@PostMapping
	public ResponseEntity<Poi> create(@RequestBody Poi poi) {
		return ResponseEntity.ok(service.create(poi));
	}
}
