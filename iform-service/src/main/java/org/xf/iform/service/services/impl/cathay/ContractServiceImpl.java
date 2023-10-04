package org.xf.iform.service.services.impl.cathay;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xf.iform.common.util.SeqUtils;
import org.xf.iform.core.dto.contract.*;
import org.xf.iform.core.entity.*;
import org.xf.iform.core.entity.cathay.*;
import org.xf.iform.core.exception.CustomException;
import org.xf.iform.service.data.mapper.contract.MemberMapper;
import org.xf.iform.service.persistence.dao.cathay.PersonnelDao;
import org.xf.iform.service.persistence.dao.contract.*;
import org.xf.iform.service.services.cathay.ContractService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ContractItemDao contractItemDao;
    @Autowired
    private ContractItemExesDao contractItemExesDao;
    @Autowired
    private ContractItemExesSubsidiaryDao contractItemExesSubsidiaryDao;
    @Autowired
    private ContractItemSubsidiaryDao contractItemSubsidiaryDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private PersonnelDao personnelDao;

    /**
     * @return List<ContractEntity>
     */
    @Override
    public List<ContractEntity> getContractList(Integer ctId, Integer ctStatus, Integer ctpId) {
        Map<String, Object> paramMap = new HashMap<>();
        if (ctId != null && ctId > 0) paramMap.put("ctId", ctId);
        if (ctStatus != null && ctStatus > 0) paramMap.put("ctStatus", ctStatus);
        if (ctpId != null && ctpId > 0) paramMap.put("ctpId", ctpId);

        if (!paramMap.isEmpty()) return contractDao.findContractByFields(paramMap);

        return contractDao.findContractAll();
    }

    /**
     * @param ctId 文件編號
     * @return ContractEntity
     */
    @Override
    public ContractEntity getContract(Integer ctId) {
        return contractDao.findContractById(ctId);
    }

    /**
     * @param contract 文件
     */
    @Override
    @Transactional
    public ContractEntity addContract(ContractEntity contract) {
        Date now = new Date();
        contract.setConId(null);
        if (contract.getConStatus() == null) contract.setConStatus(0);
        contract.setConCreateTime(now);
        contract.setConUpdateTime(now);
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        contract.setConSerial(formatter.format(now) +
            StringUtils.leftPad(String.valueOf(SeqUtils.nextVal(SeqUtils.SeqType.CONTRACT_SEQ)), 4, "0"));
        log.info("新增文件：" + contract);
        contract = contractDao.insertContract(contract);
        log.info("新增文件成功：" + contract.toString());
        return contract;
    }

    /**
     * @param contractEditDto 文件編輯內容
     */
    @Override
    @Transactional
    public int editContract(ContractEditDto contractEditDto) {
        if (contractEditDto.getCtId() == null || contractEditDto.getCtId() == 0) {
            throw new CustomException("[文件ID不可為空]");
        }
        ContractEntity contractEntity = contractDao.findContractById(contractEditDto.getCtId());
        if (null == contractEntity) throw new CustomException("原文件不存在");
//        try {
//            for (Field field : contractEntity.getClass().getDeclaredFields()) {
//                field.setAccessible(true);
//                Object obj = field.get(contractEntity);
//                if (obj instanceof CharSequence) {
//                    if (!ObjectUtils.isEmpty(obj)) {
//                        continue;
//                    }
//                }else if (null != obj) {
//                    continue;
//                }
//                Field fieldOrg = contractOrg.getClass().getDeclaredField(field.getName());
//                fieldOrg.setAccessible(true);
//                field.set(contractEntity, fieldOrg.get(contractOrg));
//            }
//        } catch (IllegalAccessException | NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        }

        contractEntity.setPerNo(contractEditDto.getPlAccount());
        contractEntity.setConTitle(contractEditDto.getCtTitle());
        contractEntity.setConType(contractEditDto.getCtType());
        contractEntity.setComId(contractEditDto.getSsId());
        contractEntity.setConCompany(contractEditDto.getCtSubsidiary());
//        contractEntity.setCtWord(contractEditDto.getCtWord());
//        contractEntity.setCtPurpose(contractEditDto.getCtPurpose());
        contractEntity.setConWork(contractEditDto.getCtWork());
        contractEntity.setConValue(contractEditDto.getCtValue());

        contractEntity.setConUpdateTime(new Date());
        if (contractEntity.getConStatus() == null) contractEntity.setConStatus(0);

        log.info("異動文件：" + contractEntity);
        return contractDao.
            updateContract(contractEntity);
    }

    /**
     * @param contractEditStatusDto
     * @return
     */
    @Override
    @Transactional
    public int editContractStatus(ContractEditStatusDto contractEditStatusDto) {
        if (contractEditStatusDto.getCtId() == null || contractEditStatusDto.getCtId() == 0) {
            throw new CustomException("[文件ID不可為空]");
        }
        ContractEntity contractEntity = contractDao.findContractById(contractEditStatusDto.getCtId());
        if (null == contractEntity) throw new CustomException("原文件不存在");
        contractEntity.setConStatus(contractEditStatusDto.getCtStatus());
        contractEntity.setConUpdateTime(new Date());
        return contractDao.
            updateContract(contractEntity);
    }

    /**
     * @param contractEditConDateDto
     * @return
     */
    @Override
    @Transactional
    public int editContractCtDate(ContractEditConDateDto contractEditConDateDto) {
        if (contractEditConDateDto.getConId() == null || contractEditConDateDto.getConId() == 0) {
            throw new CustomException("[文件ID不可為空]");
        }
        Date ctDate = null;
        try {
            ctDate = new SimpleDateFormat("yyyy-MM-dd").parse(contractEditConDateDto.getConDate());
        } catch (Exception e) {
            throw new CustomException("日期格式有誤", e);
        }
        ContractEntity contractEntity = contractDao.findContractById(contractEditConDateDto.getConId());
        if (null == contractEntity) throw new CustomException("原文件不存在");
        contractEntity.setConDate(ctDate);
        contractEntity.setConUpdateTime(new Date());
        return contractDao.
            updateContract(contractEntity);
    }

    /**
     * @param conId 文件編號
     * @return 刪除數量
     */
    @Override
    @Transactional
    public int deleteContract(Integer conId) {
        log.info("刪除文件：" + conId);
        deleteContractDetail(conId);
        return contractDao.deleteContract(conId);
    }



    /**
     * @param conId 文件編號
     * @return 刪除數量
     */
    @Override
    @Transactional
    public int deleteContractDetail(Integer conId) {
        log.info("刪除文件詳細資料：" + conId);
        int delCnt = 0;
        List<ItemEntity> itemEntityList = contractItemDao.findContractItemByContract(conId);
        List<RatioEntity> ratioEntityList = new ArrayList<>();
        List<ContractItemExes> contractItemExesList = new ArrayList<>();
        List<ContractItemExesSubsidiary> contractItemExesSubsidiaryList = new ArrayList<>();
        List<MemberEntity> memberEntityList = memberDao.findMemberByCtId(conId);
        for (ItemEntity itemEntity : itemEntityList) {
            ratioEntityList.addAll(contractItemSubsidiaryDao.findContractItemSubsidiaryByContractItem(itemEntity.getIteId()));
            contractItemExesList.addAll(contractItemExesDao.findContractItemByContractItem(itemEntity.getIteId()));
        }
        for (ContractItemExes contractItemExes : contractItemExesList) {
            contractItemExesSubsidiaryList.addAll(contractItemExesSubsidiaryDao.findContractItemExesSubByContractExesItem(contractItemExes.getCieId()));
        }
        delCnt += contractItemDao.deleteContractItem(itemEntityList);
        delCnt += contractItemSubsidiaryDao.deleteContractItemSubsidiary(ratioEntityList);
        delCnt += contractItemExesDao.deleteContractItemExes(contractItemExesList);
        delCnt += contractItemExesSubsidiaryDao.deleteContractItemExesSubsidiary(contractItemExesSubsidiaryList);
        delCnt += memberDao.deleteMember(memberEntityList);
        return delCnt;
    }

    /**
     * @param conId
     * @param ctiId
     * @return
     */
    @Override
    public List<ContractItemSubDto> getContractItemSub(Integer conId, Integer ctiId) {
        Map<String, Object> paramMap = new HashMap<>();
        if (conId != null && conId > 0) paramMap.put("ci.ct_id", conId);
        if (ctiId != null && ctiId > 0) paramMap.put("ci.cti_id", ctiId);

        return contractItemSubsidiaryDao.getContractItemSubList(paramMap);
    }

    /**
     * @param ctId
     * @return
     */
    @Override
    public List<ItemEntity> getContractItem(Integer ctId) {

        return contractItemDao.findContractItemByContract(ctId);
    }

    /**
     * @param ctId
     * @param ctiId
     * @param cieId
     * @return
     */
    @Override
    public List<ContractItemExes> getContractItemExes(Integer ctId, Integer ctiId, Integer cieId) {
        if (ctId == null && ctId == null && cieId == null) {
            throw new CustomException("必須輸入條件");
        }
        boolean filterYn = false;
        List<Integer> ctiIdList = new ArrayList<>();
        if (ctId != null) {
            filterYn = true;
            List<ItemEntity> itemEntityList = contractItemDao.findContractItemByContract(ctId);
            if (itemEntityList != null)
                ctiIdList.addAll(itemEntityList.stream().map(t -> t.getIteId()).collect(Collectors.toList()));
        }

        if (ctiId != null) {
            filterYn = true;
            ctiIdList = ctiIdList.stream().filter(t -> t.equals(ctiId)).collect(Collectors.toList());
        }
        List<ContractItemExes> contractItemExesList = new ArrayList<>();
        if (cieId != null) {
            ContractItemExes contractItemExes = contractItemExesDao.findContractItemById(cieId);
            if (contractItemExes != null) {
                if (filterYn) {
                    if (ctiIdList.contains(contractItemExes.getCtiId())) {
                        contractItemExesList.add(contractItemExes);
                    }
                } else {
                    contractItemExesList.add(contractItemExes);
                }
            }
        } else {
            for (Integer cti : ctiIdList) {
                contractItemExesList.addAll(contractItemExesDao.findContractItemByContractItem(cti));
            }
        }

        return contractItemExesList;
    }

    /**
     * @param contractDto 新增文件Dto
     * @return int 文件編號
     */
    @Override
    @Transactional
    public ContractDto addContractNew(ContractDto contractDto) {
        Date now = new Date();
        PersonnelEntity personnel = personnelDao.findPersonnelByPerNo(contractDto.getPerNo());
        if (personnel ==  null) throw new CustomException("發起人不存在");

        //文件
        ContractEntity contractEntity = ContractEntity.builder()
            .temId(contractDto.getTemId())
            .perNo(contractDto.getPerNo())
            .perPosition(personnel.getPerPosition())
            .comId(contractDto.getComId())
            .conTitle(contractDto.getConTitle())
            .conType(contractDto.getConType())
            .conCompany(contractDto.getConCompany())
//            .ctWord(contractDto.getCtWord())
//            .ctPurpose(contractDto.getCtPurpose())
            .conWork(contractDto.getConWork())
            .conValue(contractDto.getConValue())
//            .ctCreatetime(now)
//            .ctUpdatetime(now)
            .conCreateTime(now)
            .conUpdateTime(now)
            .build();

        contractEntity = addContract(contractEntity);

        addContractDetail(contractDto, contractEntity);

        return contractDto;
    }

    /**
     * @param contractDto
     * @return
     */
    @Override
    @Transactional
    public ContractDto editContractNew(ContractDto contractDto) {
        if (contractDto.getConId() == null || contractDto.getConId() == 0) {
            throw new CustomException("[文件ID不可為空]");
        }
        ContractEntity contractEntity = contractDao.findContractById(contractDto.getConId());
        if (null == contractEntity) throw new CustomException("原文件不存在");

        Date now = new Date();

        contractEntity.setTemId(contractDto.getTemId());
        contractEntity.setPerNo(contractDto.getPerNo());
        contractEntity.setConTitle(contractDto.getConTitle());
        contractEntity.setConType(contractDto.getConType());
        contractEntity.setComId(contractDto.getComId());
        contractEntity.setConCompany(contractDto.getConCompany());
//        contractEntity.setCtWord(contractDto.getCtWord());
//        contractEntity.setCtPurpose(contractDto.getCtPurpose());
        contractEntity.setConWork(contractDto.getConWork());
        contractEntity.setConValue(contractDto.getConValue());
        contractEntity.setConUpdateTime(now);

        contractDao.updateContract(contractEntity);

        deleteContractDetail(contractEntity.getConId());

        addContractDetail(contractDto, contractEntity);

        return contractDto;
    }

    public void addContractDetail(ContractDto contractDto, ContractEntity contractEntity) {

        contractDto.setConId(contractEntity.getConId());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(contractEntity.getConCreateTime());
        int nowYear = calendar.get(Calendar.YEAR);

//        Map<Integer, ItemEntity> contractItemMap = new HashMap<>();
//        Map<Integer, List<RatioEntity>> contractItemSubsidiaryMap = new HashMap<>();
//        Map<Integer, List<ContractItemExes>> contractItemExesMap = new HashMap<>();
//        Map<Integer, List<ContractItemExesSubsidiary>> contractItemExesSubsidiaryMap = new HashMap<>();
//        int contractItemCnt = 0;
        //項目
        for (ContractItemDto itemDto : contractDto.getItemList()) {
            ItemEntity itemEntity = ItemEntity.builder()
                .conId(contractEntity.getConId())
                .iteTitle(itemDto.getCtiTitle())
                .worId(itemDto.getCtiWork())
                .iteTime(itemDto.getCtiTime())
                .iteSubsidiaries(itemDto.getCtiSubsidiaries())
                .iteControl(itemDto.getCtiControl())
                .disId(itemDto.getCtiAppo())
//                .ctiCost(itemDto.getCtiCost())
//                .ctiType(itemDto.getCtiType())
                .iteTypeNote(itemDto.getCtiTypenote())
                .iteDescription(itemDto.getCtiDescription())
                .iteFileMeeting(itemDto.getCtiFilemeeting())
                .iteFilePlan(itemDto.getCtiFileplan())
                .iteFile(itemDto.getCtiFile())
                .iteWord(itemDto.getCtiWord())
                .iteNote(itemDto.getCtiNote())
                .build();

            itemEntity = contractItemDao.insertContractItem(itemEntity);
            itemDto.setCtiId(itemEntity.getIteId());

//            contractItemExesMap.put(contractItemCnt, contractItemExesList);

            //公司攤分金額
            int contractItemSubsidiaryCnt = 0;
            Map<Integer, Integer> costMap = new HashMap<>();
            List<RatioEntity> ratioEntityList = new ArrayList<>();
            for (ContractItemRatioDto ratioDto : itemDto.getRatio()) {
                BigDecimal rate = ratioDto.getCtisRatio().divide(new BigDecimal(itemDto.getRatioCount()), 4, RoundingMode.HALF_UP);
                int ctisCost = new BigDecimal(itemDto.getCtiCost()).multiply(rate).setScale(0, RoundingMode.HALF_UP).intValue();
                ratioEntityList.add(RatioEntity.builder()
                    .disId(itemEntity.getIteId())
                    .comId(ratioDto.getSsId())
                    .ratRatio(ratioDto.getCtisRatio())
//                    .ctisCost(ctisCost)
                    .build()
                );

                costMap.put(contractItemSubsidiaryCnt, ctisCost);

                contractItemSubsidiaryCnt++;
            }
            int costOver = itemDto.getCtiCost() - costMap.values().stream().mapToInt(d -> d).sum();
            int costMaxIdx = costMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
            //剩餘金額掛到最多錢的那筆，有相同金額取第一個
//不知為何沒了            ratioEntityList.get(costMaxIdx).setCtisCost(ratioEntityList.get(costMaxIdx).getCtisCost() - costOver);
//            contractItemSubsidiaryMap.put(contractItemCnt, ratioEntityList);

            for (int i = 0; i < ratioEntityList.size(); i++) {
                ratioEntityList.set(i, contractItemSubsidiaryDao.insertContractItemSubsidiary(ratioEntityList.get(i)));
                itemDto.getRatio().get(i).setCtisId(ratioEntityList.get(i).getRatId());
                itemDto.getRatio().get(i).setCtiId(ratioEntityList.get(i).getDisId());
            }


            //子項目
            List<ContractItemExes> contractItemExesList = new ArrayList<>();
            for (ContractItemExesDto exesDto : itemDto.getExes()) {
                contractItemExesList.add(ContractItemExes.builder()
                    .ctiId(itemEntity.getIteId())
                    .cieTitle(exesDto.getCieTitle())
                    .cieCost(exesDto.getCieCost())
                    .build());
            }

            for (int i = 0; i < contractItemExesList.size(); i++) {
                contractItemExesList.set(i, contractItemExesDao.insertContractItemExes(contractItemExesList.get(i)));
                itemDto.getRatio().get(i).setCtisId(ratioEntityList.get(i).getRatId());
                itemDto.getRatio().get(i).setCtiId(ratioEntityList.get(i).getDisId());
            }

            List<ContractItemExesSubsidiary> contractItemExesSubsidiaryListTmp = new ArrayList<>();
            int ciesMasterCnt = 0;
            for (ContractItemExes contractItemExes : contractItemExesList) {
                Map<Integer, Integer> ciesCostMap = new HashMap<>();
                for (RatioEntity ratioEntity : ratioEntityList) {

                    BigDecimal rate = ratioEntity.getRatRatio().divide(new BigDecimal(itemDto.getRatioCount()), 4, RoundingMode.HALF_UP);
                    int ciesCost = new BigDecimal(contractItemExes.getCieCost()).multiply(rate)
                        .setScale(0, RoundingMode.HALF_UP).intValue(); //子項目金額 * 攤分比例

                    contractItemExesSubsidiaryListTmp.add(ContractItemExesSubsidiary.builder()
                        .cieId(contractItemExes.getCieId())
                        .ssId(ratioEntity.getComId())
                        .ciesRatio(ratioEntity.getRatRatio())
                        .ciesCost(ciesCost)
                        .build());
                    ciesCostMap.put(ciesMasterCnt, ciesCost);
                    ciesMasterCnt++;
                }
                int ciesCostOver = itemDto.getCtiCost() - ciesCostMap.values().stream().mapToInt(d -> d).sum();
                int ciesCostMaxIdx = ciesCostMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
                //剩餘金額掛到最多錢的那筆，有相同金額取第一個
                contractItemExesSubsidiaryListTmp.get(ciesCostMaxIdx)
                    .setCiesCost(contractItemExesSubsidiaryListTmp.get(ciesCostMaxIdx).getCiesCost() - ciesCostOver);

            }

            List<ContractItemExesSubsidiary> contractItemExesSubsidiaryList = new ArrayList<>();
            for (ContractItemExesSubsidiary itemExesSubTmp : contractItemExesSubsidiaryListTmp) {
                int yearCostOver = itemExesSubTmp.getCiesCost();
                for (int y = 1; y <= 3; y++) {
                    int yearCost;
                    if (y < 3) {
                        yearCost = new BigDecimal(itemExesSubTmp.getCiesCost()).multiply(
                            new BigDecimal(0.3)).setScale(0, RoundingMode.HALF_UP).intValue();
                        yearCostOver -= yearCost;
                    } else {
                        yearCost = yearCostOver;
                    }
                    contractItemExesSubsidiaryList.add(
                        ContractItemExesSubsidiary.builder()
                            .cieId(itemExesSubTmp.getCieId())
                            .ssId(itemExesSubTmp.getSsId())
                            .ciesRatio(itemExesSubTmp.getCiesRatio())
                            .ciesCost(yearCost)
                            .ciesYear(nowYear + y)
                            .build()
                    );
                }
            }
            for (ContractItemExesSubsidiary itemExesSub : contractItemExesSubsidiaryList) {
                contractItemExesSubsidiaryDao.insertContractItemExesSubsidiary(itemExesSub);
            }
//            contractItemCnt++;
        }

        //人員
        MemberEntity iniMemberEntity = memberDao.insertMember(
            MemberEntity.builder()
                .conId(contractEntity.getConId())
                .memType(0)
                .comId(contractDto.getIniMember().getSsId())
                .memDepartment(contractDto.getIniMember().getMbDepartment())
                .memBranch(contractDto.getIniMember().getMbBranch())
                .memPhone(contractDto.getIniMember().getMbPhone())
                .memLv0(contractDto.getIniMember().getMbLv0())
                .memLv1(contractDto.getIniMember().getMbLv1())
                .memLv2(contractDto.getIniMember().getMbLv2())
                .memLv0Status(contractDto.getIniMember().getMbLv0Status() == null ? -1 : contractDto.getIniMember().getMbLv0Status())
                .memLv1Status(contractDto.getIniMember().getMbLv1Status() == null ? -1 : contractDto.getIniMember().getMbLv1Status())
                .memLv2Status(contractDto.getIniMember().getMbLv2Status() == null ? -1 : contractDto.getIniMember().getMbLv2Status())
                .memLv0Time(contractDto.getIniMember().getMbLv0Time())
                .memLv1Time(contractDto.getIniMember().getMbLv1Time())
                .memLv2Time(contractDto.getIniMember().getMbLv2Time())
                .memStatus(contractDto.getIniMember().getMbStatus() == null ? -1 : contractDto.getIniMember().getMbStatus())
                .memNow(contractDto.getIniMember().getMbNow())
//                .mbLog(contractDto.getIniMember().getMbLog())
                .build()
        );
        contractDto.setIniMember(MemberMapper.INSTANCE.toContractMemberDto(iniMemberEntity));

        List<MemberEntity> maiMemberListEntity = contractDto.getMaiMember().stream()
            .map(m -> memberDao.insertMember(
                MemberEntity.builder()
                    .conId(contractDto.getConId())
                    .memType(1)
                    .comId(m.getSsId())
                    .memDepartment(m.getMbDepartment())
                    .memBranch(m.getMbBranch())
                    .memPhone(m.getMbPhone())
                    .memLv0(m.getMbLv0())
                    .memLv1(m.getMbLv1())
                    .memLv2(m.getMbLv2())
                    .memLv0Status(m.getMbLv0Status() == null ? -1 : m.getMbLv0Status())
                    .memLv1Status(m.getMbLv1Status() == null ? -1 : m.getMbLv1Status())
                    .memLv2Status(m.getMbLv2Status() == null ? -1 : m.getMbLv2Status())
                    .memLv0Time(m.getMbLv0Time())
                    .memLv1Time(m.getMbLv1Time())
                    .memLv2Time(m.getMbLv2Time())
                    .memStatus(m.getMbStatus() == null ? -1 : m.getMbStatus())
                    .memNow(m.getMbNow())
//                    .mbLog(m.getMbLog())
                    .build()
            ))
            .collect(Collectors.toList());
        contractDto.setMaiMember(MemberMapper.INSTANCE.toContractMemberDto(maiMemberListEntity));

        List<MemberEntity> useMemberListEntity = contractDto.getUseMember().stream()
            .map(m -> memberDao.insertMember(
                MemberEntity.builder()
                    .conId(contractDto.getConId())
                    .memType(2)
                    .comId(m.getSsId())
                    .memDepartment(m.getMbDepartment())
                    .memBranch(m.getMbBranch())
                    .memPhone(m.getMbPhone())
                    .memLv0(m.getMbLv0())
                    .memLv1(m.getMbLv1())
                    .memLv2(m.getMbLv2())
                    .memLv0Status(m.getMbLv0Status() == null ? -1 : m.getMbLv0Status())
                    .memLv1Status(m.getMbLv1Status() == null ? -1 : m.getMbLv1Status())
                    .memLv2Status(m.getMbLv2Status() == null ? -1 : m.getMbLv2Status())
                    .memLv0Time(m.getMbLv0Time())
                    .memLv1Time(m.getMbLv1Time())
                    .memLv2Time(m.getMbLv2Time())
                    .memStatus(m.getMbStatus() == null ? -1 : m.getMbStatus())
                    .memNow(m.getMbNow())
//                    .mbLog(m.getMbLog())
                    .build()
            ))
            .collect(Collectors.toList());
        contractDto.setUseMember(MemberMapper.INSTANCE.toContractMemberDto(useMemberListEntity));
    }


}
