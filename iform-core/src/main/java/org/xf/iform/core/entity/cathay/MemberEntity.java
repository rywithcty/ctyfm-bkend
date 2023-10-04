package org.xf.iform.core.entity.cathay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
/**
 * 文件
 */
@Table(name="member")
public class MemberEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * mb_id
     */
    @Column(name="memid")
    private Integer memId;

    /**
     * 文件編號
     */
    @Column(name="conid")
    private Integer conId;

    /**
     * 簽核群組類型0:發起 1:維運 2:使用
     */
    @Column(name="memtype")
    private Integer memType;

    /**
     * 子公司ss_type=s
     */
    @Column(name="comid")
    private Integer comId;

    /**
     * 部門
     */
    @Column(name="memdepartment")
    private String memDepartment;

    /**
     * 科別
     */
    @Column(name="membranch")
    private String memBranch;

    /**
     * 承辦人
     */
    @Column(name="memlv0")
    private String memLv0;

    /**
     * 承辦人職稱代碼
     */
    @Column(name="memlv0position")
    private String memLv0Position;

    /**
     * 承辦人簽核狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    @Builder.Default
    @Column(name="memlv0status")
    private Integer memLv0Status = -1;

    /**
     * 承辦人簽核時間
     */
    @Column(name="memlv0time")
    private Date memLv0Time;


    /**
     * 窗口
     */
    @Column(name="memlvc")
    private String memLvc;

    /**
     * 窗口職稱代碼
     */
    @Column(name="memlvcposition")
    private String memLvcPosition;

    /**
     * 窗口簽核狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    @Builder.Default
    @Column(name="memlvcstatus")
    private Integer memLvcStatus = -1;

    /**
     * 窗口簽核時間
     */
    @Column(name="memlvctime")
    private Date memLvcTime;

    /**
     * 上級
     */
    @Column(name="memlv1")
    private String memLv1;

    /**
     * 上級
     */
    @Column(name="memlv1position")
    private String memLv1Position;

    /**
     * 上級簽核狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    @Builder.Default
    @Column(name="memlv1status")
    private Integer memLv1Status = -1;

    /**
     * 上級簽核時間
     */
    @Column(name="memlv1time")
    private Date memLv1Time;

    /**
     * 高層
     */
    @Column(name="memlv2")
    private String memLv2;

    /**
     * 高層
     */
    @Column(name="memlv2position")
    private String memLv2Position;

    /**
     * 高層簽核狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    @Builder.Default
    @Column(name="memlv2status")
    private Integer memLv2Status = -1;

    /**
     * 高層簽核時間
     */
    @Column(name="memlv2time")
    private Date memLv2Time;

    /**
     * mb_phone
     */
    @Column(name="memphone")
    private String memPhone;

    /**
     * 當前人員
     */
    @Column(name="memnow")
    private String memNow;

    /**
     * 當前人員職稱代碼
     */
    @Column(name="memnowposition")
    private String memNowPosition;

    /**
     * 當前簽核狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    @Column(name="memnowstatus")
    private String memNowStatus;
//
//    /**
//     * 流程紀錄json
//     */
//    @Column(name="mb_log")
//    @Builder.Default
//    private String mbLog = "";

    /**
     * 狀態0:草擬 1:待檢視 2:簽核中 3:退件 4:簽核(生效) 5:簽核(終止)
     */
    @Builder.Default
    @Column(name="memstatus")
    private Integer memStatus = -1;
}