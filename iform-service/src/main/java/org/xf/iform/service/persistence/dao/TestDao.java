package org.xf.iform.service.persistence.dao;

import java.util.List;

import org.xf.iform.service.data.po.TestEmployeePo;

public interface TestDao {
    /**
     * 取得員工總數
     * @return
     */
    public Long getEmployeeCount();

    /**
     * 取得員工資料
     * @param empolyeeId
     * @return
     */
    public TestEmployeePo getEmployee(String empolyeeId);
    
    /**
     * 取得員工資料分頁
     * @param page 
     * @param size
     * @return
     */
    public List<TestEmployeePo> getEmployeePage(int page, int size);

}
