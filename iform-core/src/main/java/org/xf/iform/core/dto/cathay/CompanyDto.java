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
 * 公司
 */
public class CompanyDto {

    /**
     * comId
     */
    private Integer comId;

    /**
     * comTitle
     */
    private String comTitle;

    /**
     * comCode
     */
    private String comCode;
}
