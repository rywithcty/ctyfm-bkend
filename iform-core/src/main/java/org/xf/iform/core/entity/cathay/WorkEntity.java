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
@Table(name = "work")
/**
 * 文件樣板
 */
public class WorkEntity implements Serializable {

    private static final long serialVersionUID = -3996593575177134297L;

    /**
     * worId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worid")
    private Integer worId;

    /**
     * worTitle
     */
    @Column(name = "wortitle")
    private String worTitle;
}
