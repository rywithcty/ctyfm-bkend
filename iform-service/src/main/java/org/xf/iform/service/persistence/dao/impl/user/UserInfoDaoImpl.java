package org.xf.iform.service.persistence.dao.impl.user;

import org.xf.iform.service.data.po.user.ModulePo;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;
import org.xf.iform.service.persistence.dao.user.UserInfoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UserInfoDaoImpl extends BaseDaoImpl implements UserInfoDao {
	public List<ModulePo> getUserAuthModule(String employeeId) {
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("SELECT AAT.WARROOM_MODULE_ID moduleId    , AAT.WARROOM_ORGEH_ID orgehId \n");
		sqlSb.append("     , MD.ROUTE_INDEX_NAME routeIndexName, MD.ZH_NAME moduleName        \n");
		sqlSb.append("  FROM WR_ACCOUNT_AUTH AAT                                              \n");
		sqlSb.append("  JOIN WR_MODULE MD ON (AAT.WARROOM_MODULE_ID = MD.MODULE_ID)           \n");
		sqlSb.append(" WHERE MEMBER_CODE = :employeeId                                        \n");
		sqlSb.append("   AND AAT.STATUS  = '1'                                                \n");
		sqlSb.append("   AND MD.STATUS   = '1'                                                \n");

		Map<String, Object> sqlParam = new HashMap<>();
		sqlParam.put("employeeId", employeeId);
		log.info(sqlSb.toString());

        @SuppressWarnings("unchecked")
		List<ModulePo> modulePos = (List<ModulePo>) findBySqlParam(sqlSb.toString(), sqlParam, ModulePo.class);
        log.info("modulePos.size=" + modulePos.size());

		return modulePos;
	}

}
