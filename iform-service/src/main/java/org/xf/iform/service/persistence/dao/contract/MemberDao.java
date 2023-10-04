package org.xf.iform.service.persistence.dao.contract;

import org.xf.iform.core.entity.cathay.MemberEntity;

import java.util.List;

public interface MemberDao {
    public List<MemberEntity> findMemberByCtId(Integer ctId);
    public MemberEntity findMemberById(Integer mbId);

    public MemberEntity insertMember(MemberEntity memberEntity);

    public int updateMember(MemberEntity memberEntity);

    public int deleteMember(Integer mbId);

    public int deleteMember(List<MemberEntity> memberEntityList);


}
