package org.xf.iform.service.services;

import java.util.List;

import org.xf.iform.service.data.dto.TestEmployeeDto;

public interface TestService {
    /**
     * 取得員工總數
     * @return
     */
    public Long countEmployee();
    
    /**
     * 取得員工資料
     * @param employeeId
     * @return
     */
    public TestEmployeeDto getEmployee(String employeeId) throws Exception;
    
    public List<TestEmployeeDto> getEmployeePage(int page, int size) throws Exception;
}
