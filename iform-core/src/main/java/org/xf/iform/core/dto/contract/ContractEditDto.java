package org.xf.iform.core.dto.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractEditDto {
    /**
     * ct_id
     */
    @NotNull(message = "文件ID不可為空")
    @Min(value = 1, message = "文件ID不可為空")
    private Integer ctId;

    /**
     * 發起人
     */
    @NotBlank(message = "發起人不可為空")
    private String plAccount;

    /**
     * ct_title
     */
    @NotBlank(message = "標題不可為空")
    private String ctTitle;

    /**
     * 申請類別 0:新增 1:變更 2:終止
     */
    @NotNull(message = "申請類別不可為空")
    private Integer ctType;

    /**
     * 維運公司ss_type=s
     */
    @NotNull(message = "維運公司不可為空")
    private Integer ssId;

    /**
     * 使用公司
     */
    @NotBlank(message = "使用公司不可為空")
    private String ctSubsidiary;

    /**
     * 自帶文字
     */
    private String ctWord;

    /**
     * 目的與預期效益
     */
    @NotNull(message = "目的與預期效益不可為空")
    private String ctPurpose;

    /**
     * 作業種類
     */
    private String ctWork;

    /**
     * 對應資料json
     */
    @NotBlank(message = "對應資料json不可為空")
    private String ctValue;
}
