package org.xf.iform.service.persistence.dao.report.contract;

import org.xf.iform.service.data.po.report.contract.RptContractItemPoEntity;
import org.xf.iform.service.data.po.report.contract.RptExesPo;
import org.xf.iform.service.data.po.report.contract.RptItemExesPo;

import java.util.List;

public interface RptContractDao {
    List<RptContractItemPoEntity> getContractItemReport(Integer ctStatus);

    List<Integer> getExesYear();

    List<RptItemExesPo> getItemExes(Integer ctStatus);

    List<RptExesPo>getExes(Integer ctStatus);

}
