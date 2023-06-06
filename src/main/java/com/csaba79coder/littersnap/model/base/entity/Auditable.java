package com.csaba79coder.littersnap.model.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This class contains the auditable entity.
 */
@MappedSuperclass
@Getter
@Setter
public class Auditable extends Identifier {

    /**
     * The auditable entity fields.
     * <p>
     *     createdAt: the date and time when the entity was created
     *     updatedAt: the date and time when the entity was updated
     *     createdBy: the user who created the entity
     *     updatedBy: the user who updated the entity
     * </p>
     * extended from Identifier:
     *    id: the auditable id
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @CreationTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "created_by")
    private UUID createdBy = UUID.fromString("6772c9dc-a7be-4826-963a-e376074fd4e7");

    @Column(name = "updated_by")
    private UUID updatedBy = UUID.fromString("dbd58012-9ee7-47d5-8f87-9bbc91583009");
}
