package org.xf.iform.core.dto.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractDto {

    /**
     * conId
     */
    private Integer conId;

    /**
     * temId
     */
    @NotNull(message = "樣板編號不可為空")
    private Integer temId;

    /**
     * 發起人
     */
    @NotBlank(message = "發起人不可為空")
    private String perNo;

    /**
     * contitle
     */
    @NotBlank(message = "標題不可為空")
    private String conTitle;

    /**
     * 申請類別 0:新增 1:變更 2:終止
     */
    @NotNull(message = "申請類別不可為空")
    private Integer conType;

    /**
     * 維運公司ss_type=s
     */
    @NotNull(message = "維運公司不可為空")
    private Integer comId;

    /**
     * 使用公司
     */
    @NotBlank(message = "使用公司不可為空")
    private String conCompany;

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
    private String conWork;

    /**
     * 對應資料json
     */
    @NotBlank(message = "對應資料json不可為空")
    private String conValue;

    /**
     * 作業項目
     */
    @NotNull(message = "作業項目不可為空")
    private List<ContractItemDto> itemList;

    /**
     * 發起單位
     */
    @NotNull(message = "發起單位不可為空")
    private ContractMemberDto iniMember;

    /**
     * 維護單位
     */
    @NotNull(message = "維護單位不可為空")
    private List<ContractMemberDto> maiMember;

    /**
     * 使用單位
     */
    @NotNull(message = "使用單位不可為空")
    private List<ContractMemberDto> useMember;
}
