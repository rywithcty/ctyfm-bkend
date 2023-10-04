package org.xf.iform.service.persistence.dao.impl.contract;

import org.springframework.stereotype.Repository;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;
import org.xf.iform.service.persistence.dao.contract.SequenceDao;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.xf.iform.core.entity.Sequence;

@Repository
public class SequenceDaoImpl extends BaseDaoImpl implements SequenceDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public int nextVal(String seqName) {
        Sequence sequence = em.find(Sequence.class, seqName, LockModeType.PESSIMISTIC_WRITE);
        int val = sequence.getCurrentVal();
        sequence.setCurrentVal(val + sequence.getIncrementVal());
        em.merge(sequence);
        return val;
    }
}
