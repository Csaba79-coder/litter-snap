package com.csaba79coder.littersnap.model.litter.dto;

import com.csaba79coder.littersnap.value.LitterStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class contains the litter admin modify model.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LitterAdminModifyModel {

    /**
     * The litter admin modify model field, only the status by the admin can be modified.
     * <p>
     *     status: the litter status
     * </p>
     */
    private LitterStatus status;

}
