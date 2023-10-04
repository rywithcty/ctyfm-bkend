package org.xf.iform.core.dto.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/*
  項目費用明細
 */
public class ContractItemExesDto {

    /**
     * cie_id
     */
    private Integer cieId;

    /**
     * 項目編號
     */
    private Integer ctiId;

    /**
     * 費用名稱
     */
    private String cieTitle;

    /**
     * 費用金額
     */
    private Integer cieCost;
}