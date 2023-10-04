package org.xf.iform.core.entity.cathay;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
/**
 * 文件
 */
@Table(name="contract")
public class ContractEntity implements Serializable {

    private static final long serialVersionUID = -5070296672941590191L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * conId
     */
    @Column(name = "conid")
    private Integer conId;

    /**
     * temId
     */
    @Column(name = "temid")
    private Integer temId;

    /**
     * 發起人
     */
    @NotBlank(message = "發起人不可為空")
    @Column(name = "perno")
    private String perNo;

    /**
     * 發起人職稱代碼
     */
    @NotBlank(message = "發起人職稱代碼不可為空")
    @Column(name = "perposition")
    private String perPosition;

    /**
     * 維運公司
     */
    @NotNull(message = "維運公司不可為空")
    @Column(name = "comid")
    private Integer comId;

    /**
     * 序號
     */
    @Column(name = "conserial")
    private String conSerial;

    /**
     * 標題
     */
    @NotBlank(message = "標題不可為空")
    @Column(name = "contitle")
    private String conTitle;

    /**
     * 申請類別 0:新增 1:變更 2:終止
     */
    @NotNull(message = "申請類別不可為空")
    @Column(name = "contype")
    private Integer conType;

    /**
     * 生效日期
     */
    @JsonFormat(locale = "zh_tw", timezone = "GMT+8",pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "condate")
    private Date conDate;

    /**
     * 作業種類
     */
    @Column(name = "conwork")
    private String conWork;

    /**
     * 使用公司
     */
    @NotBlank(message = "使用公司不可為空")
    @Column(name = "concompany")
    private String conCompany;

    /**
     * 對應資料json
     */
    @NotBlank(message = "對應資料json不可為空")
    @Column(name = "convalue")
    private String conValue;

    /**
     * 流程紀錄json
     */
    @Column(name = "conlog")
    private String conLog;

    /**
     * 文件編輯狀態 0:草稿 1:進行 2:退件
     */
    @Column(name = "constatus")
    private Integer conStatus;

//    /**
//     * 自帶文字
//     */
//    @Column(name = "ct_word")
//    private String ctWord;

//    /**
//     * 目的與預期效益
//     */
//    @NotBlank(message = "目的與預期效益不可為空")
//    @Column(name = "ct_purpose")
//    private String ctPurpose;

//    /**
//     * 自訂義表格格式json
//     */
//    @Column(name = "ct_style")
//    private String ctStyle;

    /**
     * conUpdateTime
     */
    @JsonFormat(locale = "zh_tw", timezone = "GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "conupdatetime")
    private Date conUpdateTime;

    /**
     * conCreateTime
     */
    @JsonFormat(locale = "zh_tw", timezone = "GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "concreatetime")
    private Date conCreateTime;
}