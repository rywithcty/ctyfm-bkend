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
 * 文件項目金額報表
 */
public class RptItemExesPo {

    private Integer ctpId;

    private Integer ctId;

    private Integer ctiId;

    private String ctTitle;

    private String ctiAppo;

    private String ctiWork;

    private String ctiTitle;

    private Integer ssId;

    private Integer sumCost;
}