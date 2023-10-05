package org.xf.iform.core.dto.cathay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * 分類原則
 */
public class DistributionDto {

    /**
     * disId
     */
    private Integer disId;

    /**
     * disTitle
     */
    private String disTitle;

}
