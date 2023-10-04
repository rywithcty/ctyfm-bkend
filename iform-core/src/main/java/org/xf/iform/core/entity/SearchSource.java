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
@Table(name="search_source")
public class SearchSource implements Serializable {

    private static final long serialVersionUID = -1524341450033132441L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * ss_id
     */
    @Column(name="ss_id")
    private Integer ssId;

    /**
     * ss_title
     */
    @Column(name="ss_title")
    private String ssTitle;

    /**
     * 作業項目序號
     */
    @Column(name="ss_type")
    private String ssType;

    /**
     * 標題
     */
    @Column(name="ss_code")
    private String ssCode;

}