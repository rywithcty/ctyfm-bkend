package org.xf.iform.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.xf.iform.service.persistence.dao.contract.SequenceDao;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Component
public class SeqUtils{

    private static SequenceDao sequenceDao;
    @Autowired
    private SequenceDao sequenceDaoTmp;

    @PostConstruct
    public void init() {
        this.sequenceDao = sequenceDaoTmp;
    }

    public static int nextVal(SeqType seqType) {
        return sequenceDao.nextVal(seqType.getSeqName());
    }

    public enum SeqType {
        CONTRACT_SEQ("contract_seq");

        private String seqName;
        SeqType(String seqName) {
            this.seqName = seqName;
        }
        public String getSeqName() {
            return seqName;
        }
    }
}
