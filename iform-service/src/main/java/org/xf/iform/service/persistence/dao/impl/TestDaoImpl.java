package org.xf.iform.service.persistence.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import org.xf.iform.service.data.po.TestEmployeePo;
import org.xf.iform.service.persistence.dao.TestDao;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TestDaoImpl extends BaseDaoImpl implements TestDao {


    @Override
    public Long getEmployeeCount() {

        return null;
    }

//    @SuppressWarnings("unchecked")
    @Override
    public TestEmployeePo getEmployee(String employeeId) {
        StringBuilder sqlSb = new StringBuilder();
        sqlSb.append("SELECT EMPLOYEE_ID employeeId         \n");
        sqlSb.append("     , EMPLOYEE_CNNAME employeeCnname \n");
        sqlSb.append("  FROM WR_EMPLOYEE                    \n");
        sqlSb.append(" WHERE EMPLOYEE_ID = :employeeId      \n");

        Map<String, Object> sqlParam = new HashMap<>();
        sqlParam.put("employeeId", employeeId);
        log.info(sqlSb.toString());
        
//        測試1.
//        List<TestEmployeePo> employeePoList = (List<TestEmployeePo>) findBySqlParam(sqlSb.toString(), sqlParam, TestEmployeePo.class);
//        log.info("PoList.size=" + employeePoList.size());
//        return employeePoList.stream().findFirst().orElse(null);
//        測試2.
        return (TestEmployeePo) findSingleBySqlParam(sqlSb.toString(), sqlParam, TestEmployeePo.class);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<TestEmployeePo> getEmployeePage(int page, int size) {
        StringBuilder sqlSb = new StringBuilder();
        sqlSb.append("SELECT EMPLOYEE_ID employeeId         \n");
        sqlSb.append("     , EMPLOYEE_CNNAME employeeCnname \n");
        sqlSb.append("  FROM WR_EMPLOYEE                    \n");
        sqlSb.append(" ORDER BY 1                           \n");
        log.info(sqlSb.toString());
        
        return (List<TestEmployeePo>) findBySqlAndPage(sqlSb.toString(), TestEmployeePo.class, page, size);
    }
}
