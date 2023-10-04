package org.xf.iform.service.services.cathay;

import org.xf.iform.core.dto.contract.*;
import org.xf.iform.core.entity.cathay.ContractEntity;
import org.xf.iform.core.entity.cathay.ItemEntity;
import org.xf.iform.core.entity.ContractItemExes;

import java.util.List;

public interface ContractService {
    public List<ContractEntity> getContractList(Integer ctId, Integer ctStatus, Integer ctpId);

    public ContractEntity getContract(Integer ctId);

    public ContractEntity addContract(ContractEntity contractEntity);

    public int editContract(ContractEditDto contractEditDto);

    public int editContractStatus(ContractEditStatusDto contractEditStatusDto);

    public int editContractCtDate(ContractEditConDateDto contractEditConDateDto);

    public int deleteContract(Integer conId);

    public int deleteContractDetail(Integer conId);

    public ContractDto addContractNew(ContractDto contractDto);

    public ContractDto editContractNew(ContractDto contractDto);

    public List<ContractItemSubDto> getContractItemSub(Integer conId, Integer ctiId);

    public List<ItemEntity> getContractItem(Integer ctId);

    public List<ContractItemExes> getContractItemExes(Integer ctId, Integer ctiId, Integer cieId);
}
