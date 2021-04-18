package com.hajba.booking.service.journey;

import com.hajba.booking.model.dto.Journey;

import java.time.LocalDate;
import java.util.Collection;

public interface JourneyService {
    Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) throws Exception;
}
