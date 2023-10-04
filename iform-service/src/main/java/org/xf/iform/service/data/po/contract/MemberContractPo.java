package org.xf.iform.service.data.po.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberContractPo {
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
    private Integer mbLv0Status = -1;

    /**
     * 承辦人簽核時間
     */
    private Date mbLv0Time;

    /**
     * 上級
     */
    private String mbLv1;

    /**
     * 上級簽核狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    private Integer mbLv1Status = -1;

    /**
     * 上級簽核時間
     */
    private Date mbLv1Time;

    /**
     * 高層
     */
    private String mbLv2;

    /**
     * 高層簽核狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    private Integer mbLv2Status = -1;

    /**
     * 高層簽核時間
     */
    private Date mbLv2Time;

    /**
     * 狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    private Integer mbStatus = -1;

    /**
     * 當前人員
     */
    private String mbNow;

    /**
     * 流程紀錄json
     */
    private String mbLog;
    /**
     * 序號
     */
    private String ctSerial;
    /**
     * 發起人
     */
    private String plAccount;

    /**
     * ct_title
     */
    private String ctTitle;

    /**
     * 申請類別 0:新增 1:變更 2:終止
     */
    private Integer ctType;

    /**
     * 維運公司ss_type=s
     */
    private Integer ctSsId;

    /**
     * 文件編輯狀態 0:草稿 1:進行 2:退件
     */
    private Integer ctStatus;

    /**
     * 文件樣板編號
     */
    private Integer ctpId;

    private String mbPhone;

    private Date ctCreatetime;

}
