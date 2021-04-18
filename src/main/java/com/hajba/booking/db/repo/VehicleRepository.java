package com.hajba.booking.db.repo;

import com.hajba.booking.db.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleRepository extends CommonRepository<VehicleEntity, Long> {

    public VehicleRepository() {
        super(VehicleEntity.class);
    }

}