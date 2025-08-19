package com.mobi7.trackpoi.unidade;

import com.mobi7.trackpoi.external.ApiPositionProvider;
import com.mobi7.trackpoi.external.LocalPositionProvider;
import com.mobi7.trackpoi.model.Position;
import com.mobi7.trackpoi.repository.PositionRepository;
import com.mobi7.trackpoi.service.PositionIngestService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PositionIngestServiceTest {

	private PositionRepository repo;
	private ApiPositionProvider api;
	private LocalPositionProvider local;
	private PositionIngestService service;

	@BeforeEach
	void setup() {
		repo = mock(PositionRepository.class);
		api = mock(ApiPositionProvider.class);
		local = mock(LocalPositionProvider.class);
		service = new PositionIngestService(repo, api, local);
	}

	@Test
	void fetchUsesApiWhenAvailable() {
		LocalDate date = LocalDate.of(2018, 12, 16);
		List<Position> apiData = Arrays
				.asList(new Position("TESTE001", Instant.parse("2018-12-16T02:13:40Z"), -25.36, -51.46, 0, false));

		when(api.getPositions("TESTE001", date)).thenReturn(apiData);
		when(repo.saveAll(apiData)).thenReturn(apiData);
		when(repo.findByPlateAndTimestampBetween(eq("TESTE001"), any(Instant.class), any(Instant.class)))
				.thenReturn(apiData);

		List<Position> result = service.fetchPersistAndReturn("TESTE001", date);

		assertEquals(1, result.size());
		verify(api, times(1)).getPositions("TESTE001", date);
		verify(local, never()).getPositions(anyString(), any());
	}

	@Test
	void fetchFallsBackToLocalWhenApiEmpty() {
		LocalDate date = LocalDate.of(2018, 12, 16);
		when(api.getPositions("TESTE001", date)).thenReturn(Collections.emptyList());

		List<Position> localData = Arrays
				.asList(new Position("TESTE001", Instant.parse("2018-12-16T03:13:46Z"), -25.36, -51.46, 0, false));
		when(local.getPositions("TESTE001", date)).thenReturn(localData);
		when(repo.saveAll(localData)).thenReturn(localData);
		when(repo.findByPlateAndTimestampBetween(eq("TESTE001"), any(Instant.class), any(Instant.class)))
				.thenReturn(localData);

		List<Position> result = service.fetchPersistAndReturn("TESTE001", date);

		assertEquals(1, result.size());
		verify(api, times(1)).getPositions("TESTE001", date);
		verify(local, times(1)).getPositions("TESTE001", date);
	}
}
