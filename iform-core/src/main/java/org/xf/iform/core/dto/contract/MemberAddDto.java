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
/*
 * 文件
 */
public class MemberAddDto {
    private Integer memId;

    /**
     * 文件編號
     */
    private Integer conId;

    /**
     * 簽核群組類型0:發起 1:維運 2:使用
     */
    private Integer memType;

    /**
     * 子公司ss_type=s
     */
    private Integer comId;

    /**
     * 部門
     */
    private String memDepartment;

    /**
     * 科別
     */
    private String memBranch;

    /**
     * 承辦人
     */
    private String memLv0;

    /**
     * 承辦人職稱代碼
     */
    private String memLv0Position;

    /**
     * 承辦人簽核狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    private Integer memLv0Status = -1;

    /**
     * 承辦人簽核時間
     */
    private Date memLv0Time;


    /**
     * 窗口
     */
    private String memLvc;

    /**
     * 窗口職稱代碼
     */
    private String memLvcPosition;

    /**
     * 窗口簽核狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    private Integer memLvcStatus = -1;

    /**
     * 窗口簽核時間
     */
    private Date memLvcTime;

    /**
     * 上級
     */
    private String memLv1;

    /**
     * 上級
     */
    private String memLv1Position;

    /**
     * 上級簽核狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    private Integer memLv1Status = -1;

    /**
     * 上級簽核時間
     */
    private Date memLv1Time;

    /**
     * 高層
     */
    private String memLv2;

    /**
     * 高層
     */
    private String memLv2Position;

    /**
     * 高層簽核狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    private Integer memLv2Status = -1;

    /**
     * 高層簽核時間
     */
    private Date memLv2Time;

    /**
     * mb_phone
     */
    private String memPhone;

    /**
     * 當前人員
     */
    private String memNow;

    /**
     * 當前人員職稱代碼
     */
    private String memNowPosition;

    /**
     * 當前簽核狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    private String memNowStatus;

    /**
     * 狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    private Integer memStatus = -1;
}