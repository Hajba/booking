package com.hajba.booking.db.repo;

import com.hajba.booking.db.entity.JourneyEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JourneyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Long create(JourneyEntity journeyEntity){
        entityManager.persist(journeyEntity);
        return journeyEntity.getId();
    }
}