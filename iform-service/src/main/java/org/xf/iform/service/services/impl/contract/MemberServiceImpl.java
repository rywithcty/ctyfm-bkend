package org.xf.iform.service.services.impl.contract;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xf.iform.core.dto.contract.MemberDto;
import org.xf.iform.core.entity.cathay.MemberEntity;
import org.xf.iform.service.data.mapper.contract.MemberMapper;
import org.xf.iform.service.persistence.dao.contract.MemberDao;
import org.xf.iform.service.services.contract.MemberService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    /**
     * @return
     */
    @Override
    public List<MemberDto> getMemberByCtId(int ctId) {
        return MemberMapper.INSTANCE.toDto(memberDao.findMemberByCtId(ctId));
    }

    /**
     * @param mbId
     * @return
     */
    @Override
    public MemberDto getMember(Integer mbId) {
        return MemberMapper.INSTANCE.toDto(memberDao.findMemberById(mbId));
    }

    /**
     * @param memberEntity
     * @return
     */
    @Override
    @Transactional
    public MemberDto addMember(MemberEntity memberEntity) {
        return MemberMapper.INSTANCE.toDto(memberDao.insertMember(memberEntity));
    }

    /**
     * @param memberDto 更新內容
     * @return
     */
    @Override
    @Transactional
    public MemberDto editMember(MemberDto memberDto) {
        Date now = new Date();
        if (memberDto.getMbLv0Status() != null) memberDto.setMbLv0Time(now);
        if (memberDto.getMbLv1Status() != null) memberDto.setMbLv1Time(now);
        if (memberDto.getMbLv2Status() != null) memberDto.setMbLv2Time(now);

        MemberEntity memberEntity = memberDao.findMemberById(memberDto.getMbId());
        log.info(memberEntity.toString());
        MemberMapper.INSTANCE.memberUpdate(memberDto, memberEntity);
        log.info("mapperEd");
        log.info(memberEntity.toString());
        if (memberDao.updateMember(memberEntity) <= 0) return null;
        return MemberMapper.INSTANCE.toDto(memberEntity);
    }

    /**
     * @param mbId
     * @return
     */
    @Override
    @Transactional
    public int deleteMember(Integer mbId) {
        return memberDao.deleteMember(mbId);
    }
}
