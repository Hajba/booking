import com.hajba.booking.db.entity.*;
import com.hajba.booking.db.entity.enums.DirectionType;
import com.hajba.booking.service.ticket.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.Instant;
import java.time.LocalDate;

public class BookingApplication {
    public static void main(String[] args) throws Exception {
        final ApplicationContext context = new ClassPathXmlApplicationContext("common-beans.xml");
        System.out.println("after init");

        TicketClient ticketClient = context.getBean(TicketClient.class);

        VehicleEntity trainVehicle = buildVehicle("Интерсити");
        trainVehicle = ticketClient.createOrUpdateVechicle(trainVehicle);

        JourneyEntity journey = buildJourney("Odessa", "Kiev", Instant.now(), Instant.now().plusSeconds(1000));
        journey.addStop(buildStop("Одесса-Главная", "Одесса", LocalDate.parse("1980-12-03"), "", 1D, 2D));
        journey.addStop(buildStop("Подольск", "Подольск", LocalDate.parse("1980-12-03"), "", 1D, 2D));
        journey.addStop(buildStop("Вапнярка", "Вапнярка", LocalDate.parse("1980-12-03"), "", 5D, 2D));
        journey.addStop(buildStop("Жмеринка", "Жмеринка", LocalDate.parse("1980-12-03"), "", 7D, 2D));
        journey.addStop(buildStop("Винница", "Винница", LocalDate.parse("1980-12-03"), "", 7D, 2D));
        journey.addStop(buildStop("Киев-Пасс.", "Киев", LocalDate.parse("1980-12-03"), "", 1D, 2D));

        journey.setVehicle(trainVehicle);
        journey = ticketClient.createOrUpdateJourney(journey);

        trainVehicle.addSeatInfo(buildSeatInfo(journey, trainVehicle, 120));
        trainVehicle = ticketClient.createOrUpdateVechicle(trainVehicle);

        trainVehicle.getSeatInfos().get(0).setFreeSeats(100);
        trainVehicle = ticketClient.createOrUpdateVechicle(trainVehicle);


        trainVehicle.getSeatInfos().forEach(ticketClient::removeSeatInfo);

        ticketClient.removeVehicle(trainVehicle);

        StopEntity stop = journey.getStops().get(0);
        ticketClient.removeStop(stop);

        ticketClient.removeJourneyById(journey.getId());

    }

    private static JourneyEntity buildJourney(final String from, final String to,
                                              final Instant dateFrom, final Instant dateTo){
        final JourneyEntity journey = new JourneyEntity();
        journey.setStationFrom(from);
        journey.setStationTo(to);
        journey.setDateFrom(dateFrom);
        journey.setDateTo(dateTo);
        journey.setDirection(DirectionType.TO);
        journey.setActive(true);
        return journey;
    }

    private static StopEntity buildStop(final String name, final String cityName,
                                        final LocalDate buildDate, final String description,
                                        final Double lat, final Double lon){
        final StopAddInfoEntity stopAddInfo = new StopAddInfoEntity();
        stopAddInfo.setLatitude(lat);
        stopAddInfo.setLongitude(lon);

        final CommonInfo commonInfo = new CommonInfo();
        commonInfo.setName(name);
        commonInfo.setCityName(cityName);
        commonInfo.setBuildDate(buildDate);
        commonInfo.setDescription(description);

        final StopEntity stop = new StopEntity();
        stop.addAddInfo(stopAddInfo);
        stop.setCommonInfo(commonInfo);
        return stop;
    }

    private static VehicleEntity buildVehicle(final String name){
        final VehicleEntity vehicle = new VehicleEntity();
        vehicle.setName(name);
        return vehicle;
    }

    private static SeatInfoEntity buildSeatInfo(final JourneyEntity journey,
                                                final VehicleEntity vehicle, final Integer freeSeats){
        final SeatInfoEntity seatInfo = new SeatInfoEntity();
        seatInfo.setJourney(journey);
        seatInfo.setVehicle(vehicle);
        seatInfo.setFreeSeats(freeSeats);
        return seatInfo;
    }
}
