package org.xf.iform.service.persistence.dao.impl.cathay;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import org.xf.iform.core.entity.cathay.PersonnelEntity;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;
import org.xf.iform.service.persistence.dao.cathay.PersonnelDao;

@Repository
public class PersonnelDaoImpl extends BaseDaoImpl implements PersonnelDao {

	@Override
	public Optional<PersonnelEntity> findPersonnelById(String employeeId) {
		
		return Optional.ofNullable((PersonnelEntity) findById(PersonnelEntity.class, Integer.valueOf(employeeId)));
	}
	@Override
	public PersonnelEntity findPersonnelByPerNo(String perNo) {

		return findPersonnelByPerNo(perNo);
	}


	@SuppressWarnings("unchecked")
	public Optional<PersonnelEntity> getUserByAd(String perAccount) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("perAccount", perAccount);
		List<PersonnelEntity> personnelEntityList = (List<PersonnelEntity>) findByField(PersonnelEntity.class, paramMap);

		return Optional.ofNullable(personnelEntityList.stream()
			.min(Comparator.comparing(PersonnelEntity::getPerNo))
			.orElse(null));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonnelEntity> findPersonnelByFields(Integer comId, String perEmail, String perAccount, String perNo, String perPosition) {

		Map<String, Object> paramMap = new HashMap<>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT P.* FROM `personnel` P");
		sql.append("  LEFT JOIN `company` C ON C.`comCode` = P.`perBu1Code`");
		sql.append(" WHERE 1 = 1 ");
		if (comId != null) {
			sql.append("  AND C.`comId` = :comId");
			paramMap.put("comId", comId);
		}
		if (StringUtils.isNotBlank(perEmail)) {
			sql.append("  AND P.`perEmail` = :perEmail");
			paramMap.put("perEmail", perEmail);
		}
		if (StringUtils.isNotBlank(perAccount)) {
			sql.append("  AND P.`perAccount` = :perAccount");
			paramMap.put("perAccount", perAccount);
		}
		if (StringUtils.isNotBlank(perNo)) {
			sql.append("  AND P.`perNo` = :perNo");
			paramMap.put("perNo", perNo);
		}
		if (StringUtils.isNotBlank(perPosition)) {
			sql.append("  AND P.`perPosition` = :perPosition");
			paramMap.put("perPosition", perPosition);
		}
		return (List<PersonnelEntity>) findBySqlParam(sql.toString(), paramMap, PersonnelEntity.class);
	}

	/**
	 */
	@Override
	public int updatePersonnel(PersonnelEntity personnelEntity) {
		return update(personnelEntity);
	}


	/**
	 */
	@Override
	public int deletePersonnel(Integer perId) {
		return deleteById(PersonnelEntity.class, perId);
	}

	/**
	 */
	@Override
	public PersonnelEntity insertPersonnel(PersonnelEntity personnelEntity) {
		return (PersonnelEntity) insert(personnelEntity);
	}

}
