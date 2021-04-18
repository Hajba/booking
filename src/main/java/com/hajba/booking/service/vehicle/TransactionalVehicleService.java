package com.hajba.booking.service.vehicle;

import com.hajba.booking.db.entity.VehicleEntity;
import com.hajba.booking.db.repo.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionalVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public VehicleEntity createOrUpdate(VehicleEntity vehicle){
        return vehicleRepository.createOrUpdate(vehicle);
    }

}
