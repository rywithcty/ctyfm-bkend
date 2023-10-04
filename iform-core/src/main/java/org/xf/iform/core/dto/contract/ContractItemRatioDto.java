package org.xf.iform.core.dto.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/*
 * 項目使用公司
 */
public class ContractItemRatioDto {

    /**
     * ctis_id
     */
    private Integer ctisId;

    /**
     * 作業項目編號
     */
    private Integer ctiId;

    /**
     * 公司編號 SS_Type=S
     */
    private Integer ssId;

    /**
     * 分攤比例
     */
    private BigDecimal ctisRatio;
}