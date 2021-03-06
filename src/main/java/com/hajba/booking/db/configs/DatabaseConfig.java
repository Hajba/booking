package com.hajba.booking.db.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("database.properties")
@EnableTransactionManagement
public class DatabaseConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource(){
        HikariConfig config = new HikariConfig();
        config.setUsername(environment.getProperty("database.username"));
        config.setPassword(environment.getProperty("database.password"));
        config.setJdbcUrl(environment.getProperty("database.url"));
        config.addDataSourceProperty("databaseName", environment.getProperty("database.name"));
        config.setDataSourceClassName(PGSimpleDataSource.class.getName());
        config.setMinimumIdle(30);
        config.setMaximumPoolSize(150);
        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean emf(DataSource dataSource){ // DataSource dataSource(){} @Bean used here
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.hajba.booking.db.entity");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties properties = new Properties();
        properties.put("hibernate.dialect", PostgreSQL10Dialect.class.getName());
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.show_sql", "true");
        properties.put("javax.persistence.query.timeout", environment.getProperty("database.query.timeout"));
        emf.setJpaProperties(properties);
        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
}
