package org.xf.iform.core.dto.cathay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * 文件樣板
 */
public class CategoryDto {

    /**
     * catId
     */
    private Integer catId;

    /**
     * catTitle
     */
    private String catTitle;

    /**
     * catType
     */
    private String catType;

    /**
     * catWord
     */
    private String catWord;

    /**
     * catRequired
     */
    private String catRequired;
}
