/*
 * Copyright (c) 2024 Ulrich Cech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Ulrich Cech <ulrich@ulrichcech.de>, 2024
 */
package de.ulrichcech.mbcc.server.platform.persistence;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.UUID;


/**
 * The superclass for any persistent object. It contains some helper methods and
 * has already the ID-, created- and lastUpdate-handling for prePersist/preUpdate lifecycle.</br>
 * In addition, validation is prepared and can be programmatically omitted, if necessary
 *
 * @author Ulrich Cech
 */
@MappedSuperclass
public abstract class PersistentEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 9071032503006489853L;


    @Id
    @Column(name = "_id", nullable = false, length = 40)
    @Convert(converter = UUIDAttributeConverter.class)
    private UUID id;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime created;

    @Column(name = "last_update", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime lastUpdate;


    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        var currentTimestamp = currentTimestampUTC();
        if (created == null) {
            created = currentTimestamp;
        }
        this.lastUpdate = currentTimestamp;
        validate();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdate = currentTimestampUTC();
        validate();
    }


    public void validate() {
//        if (! isOmitValidation()) {
//            EJBBeanService.getInstance().getEJBBean(PersistenceValidationService.class).processBeanValidation(this);
//        }
    }


    public boolean isNew() {
        return getId() == null;
    }

    public String getIdAsString() {
        return getId() != null ? getId().toString() : "";
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public OffsetDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(OffsetDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersistentEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PersistentEntity{" +
                "\"id\":\"" + id +
                "\"}";
    }


    protected OffsetDateTime currentTimestampUTC() {
        return OffsetDateTime.now(ZoneId.of("UTC"));
    }
}
