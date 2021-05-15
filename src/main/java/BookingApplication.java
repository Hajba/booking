import com.hajba.booking.config.RootConfig;
import com.hajba.booking.db.entity.*;
import com.hajba.booking.db.entity.enums.DirectionType;
import com.hajba.booking.service.QueryType;
import com.hajba.booking.service.ticket.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.Instant;
import java.time.LocalDate;

public class BookingApplication {
    public static void main(String[] args) throws Exception {
        final ApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
        TicketClient ticketClient = context.getBean(TicketClient.class);

        // Создадим транспортное средство поезд
        VehicleEntity trainVehicle = buildVehicle("Интерсити");
        trainVehicle = ticketClient.createOrUpdateVechicle(trainVehicle);

        // Создадим маршрут с остановками
        JourneyEntity journey = buildJourney("Odessa", "Kiev", Instant.now(), Instant.now().plusSeconds(1000));
        journey.addStop(buildStop("Одесса-Главная", "Одесса", LocalDate.parse("1980-12-03"), "", 1D, 2D));
        journey.addStop(buildStop("Подольск", "Подольск", LocalDate.parse("1980-12-03"), "", 1D, 2D));
        journey.addStop(buildStop("Вапнярка", "Вапнярка", LocalDate.parse("1980-12-03"), "", 5D, 2D));
        journey.addStop(buildStop("Жмеринка", "Жмеринка", LocalDate.parse("1980-12-03"), "", 7D, 2D));
        journey.addStop(buildStop("Винница", "Винница", LocalDate.parse("1980-12-03"), "", 7D, 2D));
        journey.addStop(buildStop("Киев-Пасс.", "Киев", LocalDate.parse("1980-12-03"), "", 1D, 2D));

        // укажем транспорт -> поезд
        journey.setVehicle(trainVehicle);
        journey = ticketClient.createOrUpdateJourney(journey);

        // Укажем кол-во свободных мест для поезда
        trainVehicle.addSeatInfo(buildSeatInfo(journey, trainVehicle, 120));
        trainVehicle = ticketClient.createOrUpdateVechicle(trainVehicle);

        // уменьшим кол-во мест в поезде
        trainVehicle.getSeatInfos().get(0).setFreeSeats(100);
        trainVehicle = ticketClient.createOrUpdateVechicle(trainVehicle);

        System.out.println("\nfindAllByName");
        System.out.println(ticketClient.findAllByName("Интерсити"));

        System.out.println("Поиск journey с сортировкой по id");
        System.out.println("\nQueryType.HQL");
        System.out.println(ticketClient.findAllJourneys(QueryType.HQL, 1, 5, "id", true));
        System.out.println("\nQueryType.NATIVE");
        System.out.println(ticketClient.findAllJourneys(QueryType.NATIVE, 2, 5, "id", true));
        System.out.println("\nQueryType.CRITERIA");
        System.out.println(ticketClient.findAllJourneys(QueryType.CRITERIA, 3, 5, "id", true));

        System.out.println("Поиск vehicle с сортировкой по id");
        System.out.println("\nQueryType.HQL");
        System.out.println(ticketClient.findAllVehicles(QueryType.HQL, 1, 10, "id", true));
        System.out.println("\nQueryType.NATIVE");
        System.out.println(ticketClient.findAllVehicles(QueryType.NATIVE, 2, 10, "id", true));
        System.out.println("\nQueryType.CRITERIA");
        System.out.println(ticketClient.findAllVehicles(QueryType.CRITERIA, 3, 10, "id", true));

        System.out.println("Поиск stop с сортировкой по active");
        System.out.println("\nQueryType.HQL");
        System.out.println(ticketClient.findAllStops(QueryType.HQL, 1, 4, "active", true));
        System.out.println("\nQueryType.NATIVE");
        System.out.println(ticketClient.findAllStops(QueryType.NATIVE, 2, 4, "active", true));
        System.out.println("\nQueryType.CRITERIA");
        System.out.println(ticketClient.findAllStops(QueryType.CRITERIA, 3, 4, "active", true));

        System.out.println("\nПолучение списка транспортных средств с наименьшим количеством свободных мест");
        System.out.println(ticketClient.findVehicleWithMinFreeSeats());

        System.out.println("\nПолучение списка транспортных средств с наибольшим  количеством свободных мест");
        System.out.println(ticketClient.findVehicleWithMaxFreeSeats());

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
