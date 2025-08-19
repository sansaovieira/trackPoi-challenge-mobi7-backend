package com.mobi7.trackpoi.service;

import com.mobi7.trackpoi.external.ApiPositionProvider;
import com.mobi7.trackpoi.external.LocalPositionProvider;
import com.mobi7.trackpoi.model.Position;
import com.mobi7.trackpoi.repository.PositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PositionIngestService {

	private static final Logger log = LoggerFactory.getLogger(PositionIngestService.class);

	private final PositionRepository repository;
	private final ApiPositionProvider apiProvider;
	private final LocalPositionProvider localProvider;

	public PositionIngestService(PositionRepository repository, ApiPositionProvider apiProvider,
			LocalPositionProvider localProvider) {
		this.repository = repository;
		this.apiProvider = apiProvider;
		this.localProvider = localProvider;
	}

	public List<Position> savePositions(List<Position> positions) {
		return repository.saveAll(positions);
	}

	public List<String> listPlates() {
		return repository.findAll().stream().map(Position::getPlate).filter(Objects::nonNull).distinct().sorted()
				.collect(Collectors.toList());
	}

	public List<Position> getPersistedPositions(String plate, LocalDate date) {
		Instant start = date.atStartOfDay(ZoneOffset.UTC).toInstant();
		Instant end = date.atTime(23, 59, 59).atZone(ZoneOffset.UTC).toInstant();
		return repository.findByPlateAndTimestampBetween(plate, start, end);
	}

	public List<Position> fetchPersistAndReturn(String plate, LocalDate date) {
		List<Position> fromApi = apiProvider.getPositions(plate, date);
		List<Position> source = fromApi.isEmpty() ? localProvider.getPositions(plate, date) : fromApi;

		if (source.isEmpty()) {
			log.info("No positions found for plate={} date={}", plate, date);
			return Collections.emptyList();
		}

		repository.saveAll(source);

		return getPersistedPositions(plate, date);
	}
}
