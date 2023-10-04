package org.xf.iform.core.dto.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/*
 * 作業項目
 */
public class ContractItemSubDto {

    /**
     * cti_id
     */
    private Integer ctId;

    /**
     * 作業項目編號
     */
    private Integer ctiId;

    /**
     * 標題
     */
    private String ctiTitle;

    /**
     * 作業種類ss_type=w
     */
    private String ctiWork;

    /**
     * 服務時間ss_type=t
     */
    private String ctiTime;

    /**
     * 本項目使用公司
     */
    private String ctiSubsidiaries;

    /**
     * 權限控管及資料管制
     */
    private String ctiControl;

    /**
     * 分類原則ss_type=a
     */
    private String ctiAppo;

    /**
     * 分攤方式
     */
    private String ctiType;

    /**
     * 分攤方式描述
     */
    private String ctiTypenote;

    /**
     * 預估項目總額
     */
    private Integer ctiCost;

    /**
     * 共用作業說明
     */
    private String ctiDescription;

    /**
     * 會議記錄
     */
    private String ctiFilemeeting;

    /**
     * 專案規劃報告
     */
    private String ctiFileplan;

    /**
     * json{ title : 附件名稱 ， file : 檔案路徑 }
     */
    private String ctiFile;

    /**
     * 系統自帶文字
     */
    private String ctiWord;

    /**
     * 其他約定
     */
    private String ctiNote;

    /**
     * 比例加總
     */
    private Integer ratioCount;

    /**
     * CTIS_ID
     */
    private Integer ctisId;

    /**
     * 公司編號 SS_Type=S
     */
    private Integer ssId;

    /**
     * 分攤比例
     */
    private BigDecimal ctisRatio;

    /**
     * 分攤金額
     */
    private Integer ctisCost;

    /**
     * ss_title
     */
    private String ssTitle;

    /**
     * 作業項目序號
     */
    private String ssType;

    /**
     * 標題
     */
    private String ssCode;
}