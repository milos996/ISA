package com.example.ISAums.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
public abstract class BaseEntity implements Persistable<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Type(type = "uuid-char")
    private UUID id;

    @NotNull
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @NotNull
    @CreationTimestamp
    @Column(name = "time_created")
    private Date timeCreated;

    @Column(name = "time_updated")
    @UpdateTimestamp
    private Date timeUpdated;

    @PrePersist
    private void prePersist() {
        this.timeCreated = new Date();
        this.timeUpdated = new Date();
        this.isDeleted = false;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public UUID getId() {
        return id;
    }
}
