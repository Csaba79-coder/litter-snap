package com.csaba79coder.littersnap.model.report.dto;

import com.csaba79coder.littersnap.model.litter.entity.Litter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This class contains the report model.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportModel {

    /**
     * The report model fields.
     * <p>
     *     id: the report id
     *     createdAt: the report created at
     *     updatedAt: the report updated at
     *     createdBy: the report created by
     *     updatedBy: the report updated by
     *     litter: the report litter
     * </p>
     */
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private UUID createdBy = UUID.fromString("6772c9dc-a7be-4826-963a-e376074fd4e7");
    private UUID updatedBy = UUID.fromString("dbd58012-9ee7-47d5-8f87-9bbc91583009");
    private Litter litter;
}
