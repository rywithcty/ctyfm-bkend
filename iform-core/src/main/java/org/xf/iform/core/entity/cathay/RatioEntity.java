package org.xf.iform.core.entity.cathay;

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
 * 項目使用公司
 */
@Table(name = "ratio")
public class RatioEntity implements Serializable {

    private static final long serialVersionUID = 2856597079646585212L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * CTIS_ID
     */
    @Column(name = "ratid")
    private Integer ratId;

    /**
     * 作業項目編號
    */
    @Column(name = "disid")
    private Integer disId;

    /**
     * 公司編號 SS_Type=S
     */
    @Column(name = "comid")
    private Integer comId;

    /**
     * 分攤比例
     */
    @Column(name = "ratratio")
    private BigDecimal ratRatio;

//    /**
//     * 分攤金額
//     */
//    @Column(name = "ctis_cost")
//    private Integer ctisCost;


}