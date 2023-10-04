package org.xf.iform.service.persistence.dao.impl.contract;

import org.springframework.stereotype.Repository;
import org.xf.iform.core.dto.contract.ContractItemSubDto;
import org.xf.iform.core.entity.cathay.RatioEntity;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;
import org.xf.iform.service.persistence.dao.contract.ContractItemSubsidiaryDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ContractItemSubsidiaryDaoImpl extends BaseDaoImpl implements ContractItemSubsidiaryDao {


    /**
     * @param ctiId
     * @return
     */
    @Override
    public List<RatioEntity> findContractItemSubsidiaryByContractItem(Integer ctiId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ctiId", ctiId);
        List<RatioEntity> ratioEntityList = (List<RatioEntity>) findByField(RatioEntity.class, paramMap);
        return ratioEntityList;
    }

    /**
     * @param ratioEntity
     * @return
     */
    @Override
    public RatioEntity insertContractItemSubsidiary(RatioEntity ratioEntity) {
        return (RatioEntity) insert(ratioEntity);
    }

    /**
     * @param ratioEntity
     * @return
     */
    @Override
    public int updateContractItemSubsidiary(RatioEntity ratioEntity) {
        return update(ratioEntity);
    }

    /**
     * @param ctisId
     * @return
     */
    @Override
    public int deleteContractItemSubsidiary(Integer ctisId) {
        return deleteById(RatioEntity.class, ctisId);
    }

    /**
     * @param ratioEntityList
     * @return
     */
    @Override
    public int deleteContractItemSubsidiary(List<RatioEntity> ratioEntityList) {
        return deleteList(ratioEntityList);
    }

    /**
     * @param paramMap
     * @return
     */
    @Override
    public List<ContractItemSubDto> getContractItemSubList(Map<String, Object> paramMap) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
//        sql.append(" cts.ctis_id as ctisId         , cts.cti_id as ctiId                   , cts.ss_id as ssId                   , ");
//        sql.append(" cts.ctis_ratio as ctisRatio   , cts.ctis_cost as ctisCost             , ss.ss_title as ssTitle              , ");
//        sql.append(" ss.ss_type as ssType          , ss.ss_code as ssCode                  , ci.cti_id as ctiId                  , ");
//        sql.append(" ci.ct_id as ctId              , ci.cti_title as ctiTitle              , ci.cti_work as ctiWork              , ");
//        sql.append(" ci.cti_time as ctiTime        , ci.cti_subsidiaries as ctiSubsidiaries, ci.cti_control as ctiControl        , ");
//        sql.append(" ci.cti_appo as ctiAppo        , ci.cti_type as ctiType                , ci.cti_typenote as ctiTypenote      , ");
//        sql.append(" ci.cti_cost as ctiCost        , ci.cti_description as ctiDescription  , ci.cti_filemeeting as ctiFilemeeting, ");
//        sql.append(" ci.cti_fileplan as ctiFileplan, ci.cti_file as ctiFile                , ci.cti_word as ctiWord              , ");
//        sql.append(" ci.cti_note as ctiNote                                                                                        ");
        sql.append("cts.ctis_id       , cts.cti_id     , cts.ss_id      , cts.ctis_ratio     , cts.ctis_cost     , ");
        sql.append("ss.ss_title       , ss.ss_type     , ss.ss_code     , ci.cti_id          , ci.ct_id          , ");
        sql.append("ci.cti_title      , ci.cti_work    , ci.cti_time    , ci.cti_subsidiaries, ci.cti_control    , ");
        sql.append("ci.cti_appo       , ci.cti_type    , ci.cti_typenote, ci.cti_cost        , ci.cti_description, ");
        sql.append("ci.cti_filemeeting, ci.cti_fileplan, ci.cti_file    , ci.cti_word        , ci.cti_note         ");
        sql.append("  FROM contract_item_subsidiary cts");
        sql.append("  LEFT JOIN search_source ss ON cts.ss_id = ss.ss_id ");
        sql.append("  LEFT JOIN contract_item ci ON cts.cti_id = ci.cti_id");
        if (paramMap.isEmpty()) {
            return (List<ContractItemSubDto>) findBySql(sql.toString(), ContractItemSubDto.class);
        }
        else {
            sql.append(" WHERE 1 = 1");
            for (String key: paramMap.keySet()) {
                sql.append("  AND " + key + " = :" + key);
            }
            return (List<ContractItemSubDto>) findBySqlParam(sql.toString(), paramMap, ContractItemSubDto.class);
        }
    }
}
