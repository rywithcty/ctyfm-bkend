package org.xf.iform.data.dto.contract;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * 文件
 */
public class AddContractDto {

    /**
     * ct_id
     */
    @Column(name = "ct_id")
    private Integer ctId;

    /**
     * 發起人
     */
    @Column(name = "pl_account")
    private String plAccount;

    /**
     * 序號
     */
    @Column(name = "ct_serial")
    private String ctSerial;

    /**
     * ct_title
     */
    @Column(name = "ct_title")
    private String ctTitle;

    /**
     * 申請類別 0:新增 1:變更 2:終止
     */
    @Column(name = "ct_type")
    private Integer ctType;

    /**
     * 生效日期
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ct_date")
    private Date ctDate;

    /**
     * 維運公司ss_type=s
     */
    @Column(name = "ss_id")
    private Integer ssId;

    /**
     * 使用公司
     */
    @Column(name = "ct_subsidiary")
    private String ctSubsidiary;

    /**
     * 自帶文字
     */
    @Column(name = "ct_word")
    private String ctWord;

    /**
     * 目的與預期效益
     */
    @Column(name = "ct_purpose")
    private String ctPurpose;

    /**
     * 作業種類
     */
    @Column(name = "ct_work")
    private String ctWork;

//    /**
//     * 自訂義表格格式json
//     */
//    @Column(name = "ct_style")
//    private String ctStyle;

    /**
     * 對應資料json
     */
    @Column(name = "ct_value")
    private String ctValue;

    /**
     * 流程紀錄json
     */
    @Column(name = "ct_log")
    private String ctLog;

    /**
     * 文件編輯狀態 0:草稿 1:進行 2:退件
     */
    @Column(name = "ct_status")
    private Integer ctStatus;

    /**
     * ct_updatetime
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "ct_updatetime")
    private Date ctUpdatetime;

    /**
     * ct_createtime
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "ct_createtime")
    private Date ctCreatetime;
}