package org.xf.iform.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
/**
 * 項目使用公司費用
 */
@Table(name = "contract_item_exes_subsidiary")
public class ContractItemExesSubsidiary implements Serializable {

    private static final long serialVersionUID = -7122113253529551259L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * CIES_ID
     */
    @Column(name = "cies_id")
    private Integer ciesId;

    /**
     * 項目費用明細編號
     */
    @Column(name = "cie_id")
    private Integer cieId;

    /**
     * 公司編號 SS_Type=S
     */
    @Column(name = "ss_id")
    private Integer ssId;

    /**
     * 分攤比例
     */
    @Column(name = "cies_ratio")
    private BigDecimal ciesRatio;

    /**
     * 分攤金額
     */
    @Column(name = "cies_cost")
    private Integer ciesCost;


    /**
     * 年份
     */
    @Column(name = "cies_year")
    private Integer ciesYear;


}