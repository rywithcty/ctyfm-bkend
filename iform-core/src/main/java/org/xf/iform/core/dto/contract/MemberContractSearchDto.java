package org.xf.iform.core.dto.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberContractSearchDto {

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
     * 承辦人
     */
    private String mbLv0;

    /**
     * 上級
     */
    private String mbLv1;

    /**
     * 高層
     */
    private String mbLv2;

    /**
     * 狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    private Integer mbStatus;

    /**
     * 當前人員
     */
    private String mbNow;

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
     * 文件編輯狀態 0:草稿 1:進行 2:退件
     */
    private Integer ctStatus;

    /**
     * 文件樣板編號
     */
    private Integer ctpId;

}
