package com.csaba79coder.littersnap.model.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.util.UUID;

/**
 * This class contains the identifier entity.
 */
@MappedSuperclass
@Getter
public class Identifier {

    /**
     * The identifier entity fields.
     * <p>
     *     id: the identifier id
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
}
