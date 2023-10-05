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
 * source
 */
public class SourceDto {

    /**
     * souId
     */
    private Integer souId;

    /**
     * catId
     */
    private Integer catId;

    /**
     * souTitle
     */
    private String souTitle;

}
