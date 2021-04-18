package com.hajba.booking.service.stop;

import com.hajba.booking.db.entity.StopEntity;
import com.hajba.booking.db.repo.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional; //TODO vokh check if need spring @Transactional

@Service
public class TransactionalStopService {

    @Autowired
    private StopRepository stopRepository;

    public StopEntity createOrUpdate(StopEntity stopEntity){
        return stopRepository.createOrUpdate(stopEntity);
    }
}
