package com.hajba.booking.config;

import com.hajba.booking.repo.DataSource;
import com.hajba.booking.service.journey.DbJourneyServiceImpl;
import com.hajba.booking.service.journey.InMemoryJourneyServiceImpl;
import com.hajba.booking.service.journey.JourneyService;
import com.hajba.booking.service.journey.StubJourneyServiceImpl;
import com.hajba.booking.service.ticket.TicketClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {


    @Bean
    public DataSource getDataSource() throws Exception {
        return new DataSource();
    }

    @Bean
    public TicketClient getTicketClient() throws Exception {
        return new TicketClient(getDbJourneyService());
    }

    @Bean("dbJourneyService")
    public JourneyService getDbJourneyService() throws Exception {
        return new DbJourneyServiceImpl(getDataSource());
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
