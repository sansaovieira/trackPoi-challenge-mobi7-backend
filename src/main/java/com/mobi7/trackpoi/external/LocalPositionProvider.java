package com.mobi7.trackpoi.external;

import com.mobi7.trackpoi.model.Position;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class LocalPositionProvider implements PositionProvider {

	@Override
	public List<Position> getPositions(String plate, LocalDate date) {
		List<Position> list = new ArrayList<>();

		if (!"TESTE001".equalsIgnoreCase(plate) || !LocalDate.of(2018, 12, 16).equals(date)) {
			return list;
		}

		ZoneOffset offset = ZoneOffset.UTC;

		list.add(new Position("TESTE001", ZonedDateTime.of(2018, 12, 16, 2, 13, 40, 0, offset).toInstant(), -25.3648873,
				-51.4699206, 0, false));
		list.add(new Position("TESTE001", ZonedDateTime.of(2018, 12, 16, 3, 13, 46, 0, offset).toInstant(), -25.3648760,
				-51.4699118, 0, false));
		list.add(new Position("TESTE001", ZonedDateTime.of(2018, 12, 16, 4, 13, 52, 0, offset).toInstant(), -25.3648998,
				-51.4698986, 0, false));
		list.add(new Position("TESTE001", ZonedDateTime.of(2018, 12, 16, 5, 13, 58, 0, offset).toInstant(), -25.3649248,
				-51.4699233, 0, false));
		list.add(new Position("TESTE001", ZonedDateTime.of(2018, 12, 16, 6, 14, 2, 0, offset).toInstant(), -25.3649000,
				-51.4699351, 0, false));
		list.add(new Position("TESTE001", ZonedDateTime.of(2018, 12, 16, 7, 14, 8, 0, offset).toInstant(), -25.3649381,
				-51.4699348, 0, false));
		list.add(new Position("TESTE001", ZonedDateTime.of(2018, 12, 16, 8, 14, 14, 0, offset).toInstant(), -25.3649295,
				-51.4699223, 0, false));
		list.add(new Position("TESTE001", ZonedDateTime.of(2018, 12, 16, 9, 14, 20, 0, offset).toInstant(), -25.3649108,
				-51.4699310, 0, false));

		return list;
	}
}
