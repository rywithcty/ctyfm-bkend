package org.xf.iform.service.persistence.dao.impl.report.contract;

import org.springframework.stereotype.Repository;
import org.xf.iform.service.data.po.report.contract.RptContractItemPoEntity;
import org.xf.iform.service.data.po.report.contract.RptExesPo;
import org.xf.iform.service.data.po.report.contract.RptItemExesPo;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;
import org.xf.iform.service.persistence.dao.report.contract.RptContractDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RptContractDaoImpl extends BaseDaoImpl implements RptContractDao {

    /**
     * @param ctStatus
     * @return
     */
    @Override
    public List<RptContractItemPoEntity> getContractItemReport(Integer ctStatus) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n");
        sql.append("  CT.* \n");
        sql.append(", MIN(CIES.CIES_YEAR) AS MIN_YEAR, MAX(CIES.CIES_YEAR) AS MAX_YEAR \n");
        sql.append(", SUM(CIE.CIE_COST) AS SUM_COST \n");
        sql.append("  FROM `contract` CT \n");
        sql.append("  LEFT JOIN `contract_item` CI ON CI.CT_ID = CT.CT_ID \n");
        sql.append("  LEFT JOIN `contract_item_exes` CIE ON CI.CTI_ID = CIE.CTI_ID \n");
        sql.append("  LEFT JOIN `contract_item_exes_subsidiary` CIES ON CIES.CIE_ID = CIE.CIE_ID \n");
        sql.append("  WHERE 1 = 1 ");

        if (ctStatus != null) {
            sql.append("  AND CT.CT_STATUS = :ctStatus \n");
            paramMap.put("ctStatus", ctStatus);
        }

        sql.append(" GROUP BY CT.CT_ID");
        return (List<RptContractItemPoEntity>) findBySqlParam(sql.toString(), paramMap, RptContractItemPoEntity.class);
    }

    /**
     * @return
     */
    @Override
    public List<Integer> getExesYear() {
        String sql = "SELECT `CIES_YEAR` FROM `contract_item_exes_subsidiary` GROUP BY `CIES_Year` ORDER BY `CIES_Year`";
        return (List<Integer>)findBySql(sql, Integer.class);
    }

    /**
     * @param ctStatus
     * @return
     */
    @Override
    public List<RptItemExesPo> getItemExes(Integer ctStatus) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n");
        sql.append("  CT.CTP_ID   , CT.CT_ID   , CI.CTI_ID                    , CT.CT_TITLE, CI.CTI_WORK \n");
        sql.append(", CI.CTI_TITLE, CI.CTI_APPO, CT.SS_ID, SUM(CIE.CIE_COST) AS SUM_COST                           \n");
        sql.append("  FROM `contract` CT \n");
        sql.append("  LEFT JOIN `contract_item` CI ON CI.CT_ID = CT.CT_ID \n");
        sql.append("  LEFT JOIN `contract_item_exes` CIE ON CIE.CTI_ID = CI.CTI_ID \n");
        sql.append(" WHERE 1 = 1 \n");

        if (ctStatus != null) {
            sql.append("  AND CT.CT_STATUS = :ctStatus \n");
            paramMap.put("ctStatus", ctStatus);
        }

        sql.append(" GROUP BY CI.CTI_ID");
        return (List<RptItemExesPo>) findBySqlParam(sql.toString(), paramMap, RptItemExesPo.class);
    }

    /**
     * @param ctStatus
     * @return
     */
    @Override
    public List<RptExesPo> getExes(Integer ctStatus) {
        Map<String, Object> paramMap = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT \n");
        sql.append("  CT.CT_ID, CI.CTI_ID, CIES.SS_ID, CIES.CIES_YEAR, SUM(CIES.CIES_COST) AS EXES_COST \n");
        sql.append("  FROM `contract_item` CI \n");
        sql.append("  LEFT JOIN `contract_item_exes` CIE ON CIE.CTI_ID = CI.CTI_ID \n");
        sql.append("  LEFT JOIN `contract_item_exes_subsidiary` CIES ON CIES.CIE_ID = CIE.CIE_ID \n");
        sql.append("  LEFT JOIN `contract` CT ON CT.CT_ID = CI.CT_ID \n");
        sql.append(" WHERE 1 = 1 \n");

        if (ctStatus != null) {
            sql.append("  AND CT.CT_STATUS = :ctStatus \n");
            paramMap.put("ctStatus", ctStatus);
        }

        sql.append(" GROUP BY CIES.CIES_Year, CIES.SS_ID, CIE.CTI_ID");
        return (List<RptExesPo>) findBySqlParam(sql.toString(), paramMap, RptExesPo.class);
    }
}
