package org.xf.iform.service.persistence.dao.contract;


import java.util.List;

public interface SequenceDao {

    public int nextVal(String seqName);

}
