package com.hajba.booking.db.repo;

import com.hajba.booking.db.entity.JourneyEntity;
import com.hajba.booking.db.entity.VehicleEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JourneyRepository extends CommonRepository<JourneyEntity, Long> {

    public JourneyRepository() {
        super(JourneyEntity.class);
    }

    @Override
    public JourneyEntity createOrUpdate(JourneyEntity entity) {
        final VehicleEntity vehicle = entity.getVehicle();
        if (Objects.nonNull(vehicle)){
            if (!entityManager.contains(vehicle)){
                entity.setVehicle(entityManager.merge(vehicle));
            }
        }
        return super.createOrUpdate(entity);
    }
}