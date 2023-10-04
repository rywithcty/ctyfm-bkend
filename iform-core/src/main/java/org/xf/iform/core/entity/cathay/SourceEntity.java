package org.xf.iform.core.entity.cathay;

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
@Entity
@Table(name = "source")
/**
 * source
 */
public class SourceEntity implements Serializable {

    private static final long serialVersionUID = 6334184838078140974L;

    /**
     * souId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "souid")
    private Integer souId;

    /**
     * catId
     */
    @Column(name = "catid")
    private Integer catId;

    /**
     * souTitle
     */
    @Column(name = "soutitle")
    private String souTitle;

}
