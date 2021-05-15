package com.hajba.booking.service.stop;

import com.hajba.booking.db.entity.StopEntity;
import com.hajba.booking.db.repo.StopRepository;
import com.hajba.booking.service.AbstractTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalStopService extends AbstractTransactionalService<StopEntity, Long> {

    private final StopRepository stopRepository;

    @Autowired
    public TransactionalStopService(StopRepository stopRepository) {
        super(stopRepository);
        this.stopRepository = stopRepository;
    }

    public StopEntity createOrUpdate(StopEntity stopEntity){
        return stopRepository.createOrUpdate(stopEntity);
    }

    @Transactional
    public void remove(StopEntity stop) {
        stopRepository.remove(stop);
    }
}
