package org.xf.iform.core.dto.contract;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/*
 * 文件
 */
public class MemberDto {
    /**
     * mb_id
     */
    private Integer mbId;

    /**
     * 文件編號
     */
    private Integer ctId;

    /**
     * 簽核群組類型0:發起 1:維運 2:使用
     */
    private Integer mbType;

    /**
     * 子公司ss_type=s
     */
    private Integer ssId;

    /**
     * 部門
     */
    private String mbDepartment;

    /**
     * 科別
     */
    private String mbBranch;

    /**
     * 承辦人
     */
    private String mbLv0;

    /**
     * 承辦人簽核狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    private Integer mbLv0Status;

    /**
     * 承辦人簽核時間
     */
    @JsonFormat(locale = "zh_tw", timezone = "GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date mbLv0Time;

    /**
     * 上級
     */
    private String mbLv1;

    /**
     * 上級簽核狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    private Integer mbLv1Status;

    /**
     * 上級簽核時間
     */
    @JsonFormat(locale = "zh_tw", timezone = "GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date mbLv1Time;

    /**
     * 高層
     */
    private String mbLv2;

    /**
     * 高層簽核狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    private Integer mbLv2Status;

    /**
     * 高層簽核時間
     */
    @JsonFormat(locale = "zh_tw", timezone = "GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date mbLv2Time;

    /**
     * mb_phone
     */
    private String mbPhone;

    /**
     * 狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    private Integer mbStatus;

    /**
     * 當前人員
     */
    private String mbNow;

    /**
     * 流程紀錄json
     */
    private String mbLog;
}