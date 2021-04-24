package com.hajba.booking.service.ticket;


import com.hajba.booking.db.entity.JourneyEntity;
import com.hajba.booking.db.entity.SeatInfoEntity;
import com.hajba.booking.db.entity.StopEntity;
import com.hajba.booking.db.entity.VehicleEntity;
import com.hajba.booking.model.dto.Journey;
import com.hajba.booking.service.journey.JourneyService;
import com.hajba.booking.service.journey.TransactionalJourneyService;
import com.hajba.booking.service.seatInfo.TransactionalSeatInfoService;
import com.hajba.booking.service.stop.TransactionalStopService;
import com.hajba.booking.service.vehicle.TransactionalVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class TicketClient {

    @Autowired
    private List<JourneyService> journeyServices;

    @Autowired
    private TransactionalJourneyService journeyService;

    @Autowired
    private Environment environment;

    @Autowired
    private TransactionalStopService stopService;

    @Autowired
    private TransactionalSeatInfoService seatInfoService;

    @Autowired
    private TransactionalVehicleService vehicleService;


    @Value("${datasource.url}")
    private String url;

    public TicketClient(){
    }

    public JourneyEntity createOrUpdateJourney(JourneyEntity journey) {
        return journeyService.createOrUpdate(journey);
    }

    public Optional<JourneyEntity> findJourneyById(Long id, boolean withDependencies){
        return id == null ? Optional.empty() : journeyService.findById(id, withDependencies);
    }

    public StopEntity createOrUpdateStop(StopEntity stopEntity){
        return stopService.createOrUpdate(stopEntity);
    }

    public SeatInfoEntity createOrUpdateSeatInfo(SeatInfoEntity seatInfo) {
        return seatInfoService.createOrUpdate(seatInfo);
    }

    public VehicleEntity createOrUpdateVechicle(VehicleEntity vehicle) {
        return vehicleService.createOrUpdate(vehicle);
    }


    public void removeJourney(JourneyEntity journey) {
        journeyService.remove(journey);
    }

    public void removeJourneyById(Long journeyId) {
        journeyService.removeById(journeyId);
    }

    public void removeVehicle(VehicleEntity vehicleEntity){
        vehicleService.remove(vehicleEntity);
    }

    public void removeVehicleById(Long id){
        vehicleService.removeById(id);
    }

    public void removeSeatInfo(SeatInfoEntity seatInfo){
        seatInfoService.remove(seatInfo);
    }

    public void removeSeatInfoById(Long id){
        seatInfoService.removeById(id);
    }

    public void removeStop(StopEntity stop) {
        stopService.remove(stop);
    }
}
