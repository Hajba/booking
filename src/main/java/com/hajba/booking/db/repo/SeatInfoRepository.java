package com.hajba.booking.db.repo;

import com.hajba.booking.db.entity.SeatInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public class SeatInfoRepository extends CommonRepository<SeatInfoEntity, Long> {
    public SeatInfoRepository() {
        super(SeatInfoEntity.class);
    }
}
