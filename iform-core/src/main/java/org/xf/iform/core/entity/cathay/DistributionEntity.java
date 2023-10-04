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
@Table(name = "distribution")
/**
 * 分類原則
 */
public class DistributionEntity implements Serializable {

    private static final long serialVersionUID = -8763647063117690560L;

    /**
     * disId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disid")
    private Integer disId;

    /**
     * disTitle
     */
    @Column(name = "distitle")
    private String disTitle;

}
