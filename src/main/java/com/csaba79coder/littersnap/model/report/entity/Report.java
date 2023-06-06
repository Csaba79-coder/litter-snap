package com.csaba79coder.littersnap.model.report.entity;

import com.csaba79coder.littersnap.model.base.entity.Auditable;
import com.csaba79coder.littersnap.model.litter.entity.Litter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class contains the report entity.
 */
@Entity
@Embeddable
@Table(name = "litter_report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report extends Auditable {

    /**
     * Relation with Litter entity
     */
    @ManyToOne
    @JoinColumn(name="litter_id", nullable=false)
    private Litter litter;
    //    Litter instance should be injected as well

}
