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
@Table(name = "company")
/**
 * 公司
 */
public class CompanyEntity implements Serializable {
    private static final long serialVersionUID = 1833890159737512726L;

    /**
     * comId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comid")
    private Integer comId;

    /**
     * comTitle
     */
    @Column(name = "comtitle")
    private String comTitle;

    /**
     * comCode
     */
    @Column(name = "comcode")
    private String comCode;
}
