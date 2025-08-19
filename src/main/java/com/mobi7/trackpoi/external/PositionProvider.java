package com.mobi7.trackpoi.external;

import com.mobi7.trackpoi.model.Position;

import java.time.LocalDate;
import java.util.List;

public interface PositionProvider {
	List<Position> getPositions(String plate, LocalDate date);
}
