package com.hajba.booking.service.journey;

import com.hajba.booking.db.entity.JourneyEntity;
import com.hajba.booking.db.repo.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionalJourneyService {

    @Autowired
    private JourneyRepository journeyRepository;

    @Transactional
    public Long createJourney(final JourneyEntity entity){
        return journeyRepository.create(entity);
    }
}