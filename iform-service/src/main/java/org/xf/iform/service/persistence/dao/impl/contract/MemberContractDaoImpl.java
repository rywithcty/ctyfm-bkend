package org.xf.iform.service.persistence.dao.impl.contract;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.xf.iform.core.dto.contract.MemberContractSearchDto;
import org.xf.iform.service.data.po.contract.MemberContractPo;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;
import org.xf.iform.service.persistence.dao.contract.MemberContractDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberContractDaoImpl extends BaseDaoImpl implements MemberContractDao {


    /**
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MemberContractPo> findMemberContract(MemberContractSearchDto searchDto) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("  M.MB_ID       , M.CT_ID     , M.MB_TYPE        , M.SS_ID       , M.MB_DEPARTMENT");
        sql.append(", M.MB_BRANCH   , M.MB_LV0    , M.MB_LV0STATUS AS MB_LV0_STATUS, M.MB_LV0TIME AS MB_LV0_TIME, M.MB_LV1       ");
        sql.append(", M.MB_LV1STATUS AS MB_LV1_STATUS, M.MB_LV1TIME AS MB_LV1_TIME, M.MB_LV2         , M.MB_LV2STATUS AS MB_LV2_STATUS, M.MB_LV2TIME AS MB_LV2_TIME ");
        sql.append(", M.MB_STATUS   , M.MB_NOW    , M.MB_LOG         , C.CT_SERIAL   , C.PL_ACCOUNT   ");
        sql.append(", C.CT_TITLE    , C.CT_TYPE   , C.SS_ID CT_SS_ID , C.CT_STATUS   , C.CTP_ID       ");
        sql.append(", M.MB_PHONE    , C.CT_CREATETIME");
        sql.append("  FROM `member` M");
        sql.append("  LEFT JOIN `contract` C ON M.CT_ID = C.CT_ID");
        sql.append(" WHERE C.CT_ID IS NOT NULL");

        if (searchDto.getCtId() != null && searchDto.getCtId() != 0) {
            sql.append("  AND M.CT_ID = :ctId");
            paramMap.put("ctId", searchDto.getCtId());
        }

        if (searchDto.getMbType() != null) {
            sql.append("  AND M.MB_TYPE = :mbType");
            paramMap.put("mbType", searchDto.getMbType());
        }

        if (searchDto.getSsId() != null) {
            sql.append("  AND M.SS_ID = :ssId");
            paramMap.put("ssId", searchDto.getSsId());
        }

        if (StringUtils.isNotBlank(searchDto.getMbNow())) {
            sql.append("  AND M.MB_NOW = :mdNow");
            paramMap.put("mdNow", searchDto.getMbNow());
        }

        if (searchDto.getMbStatus() != null) {
            sql.append("  AND M.MB_STATUS = :mbStatus");
            paramMap.put("mbStatus", searchDto.getMbStatus());
        }

        if (StringUtils.isNotBlank(searchDto.getCtSerial())) {
            sql.append("  AND C.CT_SERIAL = :ctSerial");
            paramMap.put("ctSerial", searchDto.getCtSerial());
        }

        if (StringUtils.isNotBlank(searchDto.getPlAccount())) {
            sql.append("  AND C.PL_ACCOUNT = :plAccount");
            paramMap.put("plAccount", searchDto.getPlAccount());
        }

        if (StringUtils.isNotBlank(searchDto.getCtTitle())) {
            sql.append("  AND C.CT_TITLE = :ctTitle");
            paramMap.put("ctTitle", searchDto.getCtTitle());
        }

        if (searchDto.getCtType() != null) {
            sql.append("  AND C.CT_TYPE = :ctType");
            paramMap.put("ctType", searchDto.getCtType());
        }

        if (searchDto.getCtStatus() != null) {
            sql.append("  AND C.CT_STATUS = :ctStatus");
            paramMap.put("ctStatus", searchDto.getCtStatus());
        }

        if (searchDto.getCtStatus() != null) {
            sql.append("  AND C.CTP_ID = :ctpId");
            paramMap.put("ctpId", searchDto.getCtpId());
        }

        if (StringUtils.isNotBlank(searchDto.getMbLv0()) || StringUtils.isNotBlank(searchDto.getMbLv1()) || StringUtils.isNotBlank(searchDto.getMbLv2())) {
            sql.append("  AND ( ");

            String sqlLv = "";

            if (StringUtils.isNotBlank(searchDto.getMbLv0())) {
                sqlLv += " M.MB_LV0 = :mbLv0";
                paramMap.put("mbLv0", searchDto.getMbLv0());
            }

            if (StringUtils.isNotBlank(searchDto.getMbLv1())) {
                sqlLv += StringUtils.isBlank(sqlLv) ? "" : " OR";
                sqlLv += " M.MB_LV1 = :mbLv1";
                paramMap.put("mbLv1", searchDto.getMbLv1());
            }

            if (StringUtils.isNotBlank(searchDto.getMbLv2())) {
                sqlLv += StringUtils.isBlank(sqlLv) ? "" : " OR";
                sqlLv += " M.MB_LV2 = :mbLv2";
                paramMap.put("mbLv2", searchDto.getMbLv2());
            }

            sql.append(sqlLv);
            sql.append(") ");
        }
        return (List<MemberContractPo>) findBySqlParam(sql.toString(), paramMap, MemberContractPo.class);
    }
}
