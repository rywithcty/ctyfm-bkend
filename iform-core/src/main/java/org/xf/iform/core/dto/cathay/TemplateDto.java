package org.xf.iform.core.dto.cathay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * 文件樣板
 */
public class TemplateDto {

    /**
     * temId
     */
    private Integer temId;

    /**
     * temTitle
     */
    private String temTitle;

    /**
     * 自訂義表格格式json
     */
    private String temStyle;
}
