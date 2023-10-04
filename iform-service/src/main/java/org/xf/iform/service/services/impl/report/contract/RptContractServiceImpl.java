package org.xf.iform.service.services.impl.report.contract;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xf.iform.core.dto.report.contract.RptContractItemDtoEntity;
import org.xf.iform.core.dto.report.contract.RptExesDto;
import org.xf.iform.core.dto.report.contract.RptItemExesDto;
import org.xf.iform.service.data.mapper.report.contract.RptContractItemMapper;
import org.xf.iform.service.data.mapper.report.contract.RptExesMapper;
import org.xf.iform.service.data.mapper.report.contract.RptItemExesMapper;
import org.xf.iform.service.persistence.dao.report.contract.RptContractDao;
import org.xf.iform.service.services.report.contract.RptContractService;

import java.util.List;

@Slf4j
@Service
public class RptContractServiceImpl implements RptContractService {

    @Autowired
    RptContractDao rptContractDao;
    /**
     * @param ctStatus
     * @return
     */
    @Override
    public List<RptContractItemDtoEntity> getContractItemReport(Integer ctStatus) {
        return
            RptContractItemMapper.INSTANCE.toDto(rptContractDao.getContractItemReport(ctStatus));
    }

    /**
     * @return
     */
    @Override
    public List<Integer> getExesYear() {
        return rptContractDao.getExesYear();
    }

    /**
     * @param ctStatus
     * @return
     */
    @Override
    public List<RptItemExesDto> getItemExes(Integer ctStatus) {
        return RptItemExesMapper.INSTANCE.toDto(rptContractDao.getItemExes(ctStatus));
    }

    /**
     * @param ctStatus
     * @return
     */
    @Override
    public List<RptExesDto> getExes(Integer ctStatus) {
        return RptExesMapper.INSTANCE.toDto(rptContractDao.getExes(ctStatus));
    }

}
