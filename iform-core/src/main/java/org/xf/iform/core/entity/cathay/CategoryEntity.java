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
@Table(name = "category")
/**
 * 文件樣板
 */
public class CategoryEntity implements Serializable {

    private static final long serialVersionUID = 9191962972452410973L;

    /**
     * catId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catid")
    private Integer catId;

    /**
     * catTitle
     */
    @Column(name = "cattitle")
    private String catTitle;

    /**
     * catType
     */
    @Column(name = "cattype")
    private String catType;

    /**
     * catWord
     */
    @Column(name = "catword")
    private String catWord;

    /**
     * catRequired
     */
    @Column(name = "catrequired")
    private String catRequired;
}
