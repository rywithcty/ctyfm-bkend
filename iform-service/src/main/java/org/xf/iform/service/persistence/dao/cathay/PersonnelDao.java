package org.xf.iform.service.persistence.dao.cathay;

import java.util.List;
import java.util.Optional;

import org.xf.iform.core.entity.cathay.PersonnelEntity;

public interface PersonnelDao {
	public Optional<PersonnelEntity> findPersonnelById(String employeeId);

	public PersonnelEntity findPersonnelByPerNo(String perNo);

	public PersonnelEntity insertPersonnel(PersonnelEntity personnelEntity);

	List<PersonnelEntity> findPersonnelByFields(Integer comId, String perEmail, String perAccount,
												String perNo, String perPosition);

	/**
	 * @param personnelEntity
	 * @return
	 */
	public int updatePersonnel(PersonnelEntity personnelEntity);
	int deletePersonnel(Integer perId);
}
