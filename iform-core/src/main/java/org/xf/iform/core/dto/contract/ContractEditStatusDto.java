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
public class ContractEditStatusDto {

    /**
     * ct_id
     */
    @NotNull(message = "文件ID不可為空")
    @Min(value = 1, message = "文件ID不可為空")
    private Integer ctId;

    @NotNull(message = "狀態不可為空")
    private Integer ctStatus;
}
