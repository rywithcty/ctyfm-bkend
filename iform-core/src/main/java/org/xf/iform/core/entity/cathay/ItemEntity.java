package org.xf.iform.core.entity.cathay;

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
 * 作業項目
 */
@Table(name="item")
public class ItemEntity implements Serializable {

    private static final long serialVersionUID = -7817591055330391263L;

    /**
     * iteId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iteid")
    private Integer iteId;

    /**
     * 文件編號
     */
    @Column(name="conid")
    private Integer conId;

//    /**
//     * 作業項目序號
//     */
//    @Column(name="cti_serial")
//    private String ctiSerial;

    /**
     * 標題
     */
    @Column(name="itetitle")
    private String iteTitle;

    /**
     * 作業種類ss_type=w
     */
    @Column(name="worid")
    private String worId;

    /**
     * 服務時間ss_type=t
     */
    @Column(name="itetime")
    private String iteTime;

    /**
     * 本項目使用公司
     */
    @Column(name="itesubsidiaries")
    private String iteSubsidiaries;

    /**
     * 權限控管及資料管制
     */
    @Column(name="itecontrol")
    private String iteControl;

    /**
     * 分類原則ss_type=a cti_appo
     */
    @Column(name="disid")
    private String disId;

    /**
     * 分攤方式
     */
    @Column(name="itetypenote")
    private String iteTypeNote;

    /**
     * 共用作業說明
     */
    @Column(name="itedescription")
    private String iteDescription;

//    /**
//     * 分攤方式描述
//     */
//    @Column(name="ctiTypeNote")
//    private String ctiTypeNote;

    /**
     * 會議記錄
     */
    @Column(name="itefilemeeting")
    private String iteFileMeeting;

    /**
     * 專案規劃報告
     */
    @Column(name="itefileplan")
    private String iteFilePlan;

    /**
     * json{ title : 附件名稱 ， file : 檔案路徑 }
     */
    @Column(name="itefile")
    private String iteFile;

//    /**
//     * 預估項目總額
//     */
//    @Column(name="cti_cost")
//    private Integer ctiCost;

    /**
     * 系統自帶文字
     */
    @Column(name="iteword")
    private String iteWord;

    /**
     * 其他約定
     */
    @Column(name="itenote")
    private String iteNote;
}