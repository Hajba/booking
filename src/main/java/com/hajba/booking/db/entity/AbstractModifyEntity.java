package com.hajba.booking.db.entity;

import com.hajba.booking.db.entity.util.YesNoConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public class AbstractModifyEntity<ID extends Serializable> extends AbstractEntity<ID>{
    @Column(name = "create_date")
    @CreationTimestamp
    private Instant createDate;

    @Column(name = "active")
    @Convert(converter = YesNoConverter.class)
    private boolean active = true;
}
