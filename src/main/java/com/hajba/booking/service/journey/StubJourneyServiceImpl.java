package com.hajba.booking.service.journey;

import com.hajba.booking.dto.Journey;
import com.hajba.booking.service.journey.JourneyService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

public class StubJourneyServiceImpl implements JourneyService {

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        return Collections.emptyList();
    }
}
