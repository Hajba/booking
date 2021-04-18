package com.hajba.booking.config;

import com.hajba.booking.db.repo.DataSource;
import com.hajba.booking.service.journey.DbJourneyServiceImpl;
import com.hajba.booking.service.journey.InMemoryJourneyServiceImpl;
import com.hajba.booking.service.journey.JourneyService;
import com.hajba.booking.service.journey.StubJourneyServiceImpl;
import com.hajba.booking.service.ticket.TicketClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.hajba.booking"})
@PropertySource({"application.properties"})
public class RootConfig {

    @Bean
    public DataSource getDataSource() throws Exception {
        return new DataSource();
    }


    public TicketClient getTicketClient() throws Exception {
        return new TicketClient();
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
