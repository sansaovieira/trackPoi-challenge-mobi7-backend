package com.mobi7.trackpoi.external;

import com.mobi7.trackpoi.model.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class ApiPositionProvider implements PositionProvider {

	private static final Logger log = LoggerFactory.getLogger(ApiPositionProvider.class);
	private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

	private final RestTemplate rest;

	public ApiPositionProvider() {
	
		SimpleClientHttpRequestFactory f = new SimpleClientHttpRequestFactory();
		f.setConnectTimeout(3000);
		f.setReadTimeout(3000);
		this.rest = new RestTemplate(f);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Position> getPositions(String plate, LocalDate date) {
		String url = String.format("https://challenge-backend.mobi7.io/posicao?placa=%s&data=%s", plate,
				DATE_FMT.format(date));
		log.info("Consuming Mobi7 URL: {}", url);

		try {
			List<Map<String, Object>> response = rest.getForObject(url, List.class);
			if (response == null || response.isEmpty()) {
				return Collections.emptyList();
			}

			List<Position> out = new ArrayList<>();
			for (Map<String, Object> row : response) {
				String p = (String) row.get("placa");
				String tsStr = String.valueOf(row.get("data")); 
				double lat = ((Number) row.get("latitude")).doubleValue();
				double lon = ((Number) row.get("longitude")).doubleValue();
				double speed = ((Number) row.get("velocidade")).doubleValue();
				boolean ignition = (Boolean) row.get("ignicao");

				out.add(new Position(p, OffsetDateTime.parse(tsStr).toInstant(), lat, lon, speed, ignition));
			}
			return out;
		} catch (Exception ex) {
			log.error("Failed to get positions from Mobi7: {}", ex.getMessage());
			return Collections.emptyList();
		}
	}
}
