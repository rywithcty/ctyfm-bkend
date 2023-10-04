package org.xf.iform.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
/*
  項目費用明細
 */
@Table(name = "contract_item_exes")
public class ContractItemExes implements Serializable {

    private static final long serialVersionUID = -9169218751079577211L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * CIE_ID
    */
    @Column(name = "CIE_ID")
    private Integer cieId;

    /**
     * 項目編號
     */
    @Column(name = "CTI_ID")
    private Integer ctiId;


    /**
     * 費用名稱
     */
    @Column(name = "CIE_TITLE")
    private String cieTitle;


    /**
     * 費用金額
     */
    @Column(name = "CIE_COST")
    private Integer cieCost;


}