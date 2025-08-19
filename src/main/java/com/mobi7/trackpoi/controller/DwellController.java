package com.mobi7.trackpoi.controller;

import com.mobi7.trackpoi.model.DwellEvent;
import com.mobi7.trackpoi.repository.DwellEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dwells")
public class DwellController {

	private final DwellEventRepository dwellEventRepository;

	@Autowired
	public DwellController(DwellEventRepository dwellEventRepository) {
		this.dwellEventRepository = dwellEventRepository;
	}

	@GetMapping
	public List<DwellEvent> getAllDwellEvents() {
		return dwellEventRepository.findAll();
	}

	@GetMapping("/plate/{plate}")
	public List<DwellEvent> getDwellEventsByPlate(@PathVariable String plate) {
		return dwellEventRepository.findByPlate(plate);
	}

	@PostMapping
	public DwellEvent createDwellEvent(@RequestBody DwellEvent dwellEvent) {
		return dwellEventRepository.save(dwellEvent);
	}
}
