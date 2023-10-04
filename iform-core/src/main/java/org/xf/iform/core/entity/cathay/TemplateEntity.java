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
@Table(name = "template")
/**
 * 文件樣板
 */
public class TemplateEntity implements Serializable {

    private static final long serialVersionUID = -3996593575177134297L;

    /**
     * temId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temid")
    private Integer temId;

    /**
     * temTitle
     */
    @Column(name = "temtitle")
    private String temTitle;

    /**
     * 自訂義表格格式json
     */
    @Column(name = "temstyle")
    private String temStyle;
}
