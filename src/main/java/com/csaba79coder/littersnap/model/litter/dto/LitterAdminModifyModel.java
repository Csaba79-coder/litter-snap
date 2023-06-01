package com.csaba79coder.littersnap.model.litter.dto;

import com.csaba79coder.littersnap.value.LitterStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LitterAdminModifyModel {

    private LitterStatus status;

}
