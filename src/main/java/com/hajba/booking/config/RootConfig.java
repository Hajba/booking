package com.hajba.booking.config;


import com.hajba.booking.service.old.journey.InMemoryJourneyServiceImpl;
import com.hajba.booking.service.journey.JourneyService;
import com.hajba.booking.service.old.journey.StubJourneyServiceImpl;
import com.hajba.booking.service.ticket.TicketClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.hajba.booking"})
@PropertySource({"application.properties"})
public class RootConfig {


    public TicketClient getTicketClient() throws Exception {
        return new TicketClient();
    }

    @Bean("inMemoryJourneyService")
    public JourneyService getMemoryJourneyService(){
        return new InMemoryJourneyServiceImpl();
    }

    @Bean("stubJourneyService")
    public JourneyService stubJourneyService(){
        return new StubJourneyServiceImpl();
    }
}
