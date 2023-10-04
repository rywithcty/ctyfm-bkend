package org.xf.iform.service.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xf.iform.core.exception.CustomException;
import org.xf.iform.service.persistence.dao.TestDao;
import org.xf.iform.service.data.dto.TestEmployeeDto;
import org.xf.iform.service.data.mapper.TestEmployeeMapper;
import org.xf.iform.service.services.TestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TestServiceImpl implements TestService {
    // -Dlogging.config=./conf/logback-spring.xml

    @Autowired
    TestDao testDao;
    
    @Override
    public Long countEmployee() {

	return null;
    }

    @Override
    public TestEmployeeDto getEmployee(String employeeId) throws Exception {
	log.info(employeeId);
	return TestEmployeeMapper.INSTANCE.toDto(
		Optional.ofNullable(testDao.getEmployee(employeeId))
		.orElseThrow(() -> new CustomException("查無員工編號")));
    }

    @Override
    public List<TestEmployeeDto> getEmployeePage(int page, int size) throws Exception {
	
	return TestEmployeeMapper.INSTANCE.toDtoList(testDao.getEmployeePage(page, size));
    }

}
