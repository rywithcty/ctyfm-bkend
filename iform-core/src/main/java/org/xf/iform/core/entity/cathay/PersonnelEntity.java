package org.xf.iform.core.entity.cathay;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personnel")
public class PersonnelEntity implements Serializable {

	private static final long serialVersionUID = 52834819971386194L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "perid")
	private Integer perId;

	@Column(name = "peraccount")
	private String perAccount;

	@Column(name = "perno")
	private String perNo;

	@Column(name = "pername")
	private String perName;

	@Column(name = "perpar")
	private String perPar;

	@Column(name = "pernick")
	private String perNick;

	@Column(name = "perposition")
	private String perPosition;

	@Column(name = "perpositionname")
	private String perPositionName;

	@Column(name = "peremail")
	private String perEmail;

	@Column(name = "perphone1")
	private String perPhone1;

	@Column(name = "perphone2")
	private String perPhone2;

	@Column(name = "perphone3")
	private String perPhone3;

	@Column(name = "perbu1code")
	private String perBu1Code;

	@Column(name = "perbu1")
	private String perBu1;

	@Column(name = "perbu2code")
	private String perBu2Code;

	@Column(name = "perbu2")
	private String plBu2;

	@Column(name = "perbu3code")
	private String plBu3Code;

	@Column(name = "perbu3")
	private String perBu3;

	@Column(name = "permobile")
	private String perMobile;

}
