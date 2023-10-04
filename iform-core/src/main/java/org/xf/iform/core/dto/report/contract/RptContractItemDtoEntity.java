package org.xf.iform.core.dto.report.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.xf.iform.core.entity.cathay.ContractEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
/*
 * 文件項目報表
 */
public class RptContractItemDtoEntity extends ContractEntity {

    Integer minYear;

    Integer maxYear;

    Integer SumCost;
}