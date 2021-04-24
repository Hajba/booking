package com.hajba.booking.db.repo;

import com.hajba.booking.db.entity.StopEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class StopRepository extends CommonRepository<StopEntity, Long> {

    public StopRepository() {
        super(StopEntity.class);
    }

    @Override
    public void remove(StopEntity entity) {
        StopEntity stop = findById(entity.getId()).get();
        stop.removeAllJourneys();
        getEntityManager().remove(stop.getStopAddInfo());
        super.remove(stop);
    }
}
