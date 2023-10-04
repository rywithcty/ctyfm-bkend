package org.xf.iform.core.entity;

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
/**
 * 下拉選單
 */
@Table(name="sequence")
public class Sequence implements Serializable {

    private static final long serialVersionUID = 2725021507180818154L;

    /**
     * 名稱
     */
    @Id
    @Column(name="seq_name")
    private String seqName;

    /**
     * 當前值
     */
    @Column(name="current_val")
    private Integer currentVal;

    /**
     * 增值
     */
    @Column(name="increment_val")
    private Integer incrementVal;

}