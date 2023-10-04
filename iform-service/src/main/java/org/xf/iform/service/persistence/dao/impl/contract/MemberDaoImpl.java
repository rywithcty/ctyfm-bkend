package org.xf.iform.service.persistence.dao.impl.contract;

import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.cathay.MemberEntity;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;
import org.xf.iform.service.persistence.dao.contract.MemberDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberDaoImpl extends BaseDaoImpl implements MemberDao {

    /**
     * @param ctId
     * @return
     */
    @Override
    public List<MemberEntity> findMemberByCtId(Integer ctId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ctId", ctId);
        List<MemberEntity> memberEntityList = (List<MemberEntity>) findByField(MemberEntity.class, paramMap);
        return memberEntityList;
    }

    /**
     * @param mbId
     * @return
     */
    @Override
    public MemberEntity findMemberById(Integer mbId) {
        return (MemberEntity) findById(MemberEntity.class, mbId);
    }

    /**
     * @param memberEntity
     */
    @Override
    public MemberEntity insertMember(MemberEntity memberEntity) {
        return (MemberEntity) insert(memberEntity);
    }

    /**
     * @param memberEntity
     */
    @Override
    public int updateMember(MemberEntity memberEntity) {
        return update(memberEntity);
    }

    /**
     * @param mbId
     */
    @Override
    public int deleteMember(Integer mbId) {
        return deleteById(MemberEntity.class, mbId);
    }

    /**
     * @param memberEntityList
     * @return
     */
    @Override
    public int deleteMember(List<MemberEntity> memberEntityList) {
        return deleteList(memberEntityList);
    }

}
