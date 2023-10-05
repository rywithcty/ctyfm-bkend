package org.xf.iform.core.dto.cathay;

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
public class PersonnelDto {

	private Integer perId;

	private String perAccount;

	private String perNo;

	private String perName;

	private String perPar;

	private String perNick;

	private String perPosition;

	private String perPositionName;

	private String perEmail;

	private String perPhone1;

	private String perPhone2;

	private String perPhone3;

	private String perBu1Code;

	private String perBu1;

	private String perBu2Code;

	private String plBu2;

	private String plBu3Code;

	private String perBu3;

	private String perMobile;

}
