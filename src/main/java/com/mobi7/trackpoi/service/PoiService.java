package com.mobi7.trackpoi.service;

import com.mobi7.trackpoi.model.Poi;
import com.mobi7.trackpoi.repository.PoiRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoiService {

	private final PoiRepository repository;

	public PoiService(PoiRepository repository) {
		this.repository = repository;
	}

	public List<Poi> list() {
		return repository.findAll();
	}

	public Poi create(Poi poi) {
		return repository.save(poi);
	}
}
