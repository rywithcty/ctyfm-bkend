package org.xf.iform.core.dto.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractEditConDateDto {

    /**
     * conId
     */
    @NotNull(message = "文件ID不可為空")
    @Min(value = 1, message = "文件ID不可為空")
    private Integer conId;

    @NotNull(message = "生效日期不可為空")
    private String conDate;
}
