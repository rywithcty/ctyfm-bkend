package org.xf.iform.service.data.po.report.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/*
 * 項目實際金額-一公司與年份區分
 */
public class RptExesPo {

    private Integer ctId;

    private Integer ctiId;

    private Integer ssId;

    private Integer ciesYear;

    private Integer exesCost;
}