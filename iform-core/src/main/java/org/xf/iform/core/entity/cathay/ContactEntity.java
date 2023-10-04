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
@Table(name = "contact")
/**
 * contact
 */
public class ContactEntity implements Serializable {

    private static final long serialVersionUID = 6518995562912628053L;

    /**
     * cotId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cotid")
    private Integer cotId;

    /**
     * comId
     */
    @Column(name = "comid")
    private String comId;

    /**
     * perNo
     */
    @Column(name = "perno")
    private String perNo;

    /**
     * perPosition
     */
    @Column(name = "perposition")
    private String perPosition;

}
