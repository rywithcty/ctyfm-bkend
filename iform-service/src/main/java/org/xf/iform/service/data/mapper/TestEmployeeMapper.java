package org.xf.iform.service.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import org.xf.iform.service.data.dto.TestEmployeeDto;
import org.xf.iform.service.data.po.TestEmployeePo;

@Mapper
public interface TestEmployeeMapper {
    TestEmployeeMapper INSTANCE = Mappers.getMapper(TestEmployeeMapper.class);

    TestEmployeeDto toDto(TestEmployeePo testEmployeePo);

    List<TestEmployeeDto> toDtoList(List<TestEmployeePo> testEmployeePoList);
}
