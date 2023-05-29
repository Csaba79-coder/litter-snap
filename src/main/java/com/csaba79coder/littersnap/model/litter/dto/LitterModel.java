package com.csaba79coder.littersnap.model.litter.dto;

import com.csaba79coder.littersnap.model.address.dto.AddressModel;
import com.csaba79coder.littersnap.model.report.dto.ReportModel;
import com.csaba79coder.littersnap.value.LitterStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LitterModel {
    private UUID id;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private UUID createdBy = UUID.fromString("6772c9dc-a7be-4826-963a-e376074fd4e7");
    private UUID updatedBy = UUID.fromString("dbd58012-9ee7-47d5-8f87-9bbc91583009");
    private AddressModel addresses;
    private String description;
    private byte[] image;
    private List<ReportModel> reports;
    private LitterStatus status;

}
