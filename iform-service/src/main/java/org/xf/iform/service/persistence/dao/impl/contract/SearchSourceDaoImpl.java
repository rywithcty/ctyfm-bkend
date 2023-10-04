package org.xf.iform.service.persistence.dao.impl.contract;

import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.SearchSource;
import org.xf.iform.service.persistence.dao.common.impl.BaseDaoImpl;
import org.xf.iform.service.persistence.dao.contract.SearchSourceDao;

import java.util.List;

@Service
public class SearchSourceDaoImpl extends BaseDaoImpl implements SearchSourceDao {

    /**
     * @return
     */
    @Override
    public List<SearchSource> findSearchSourceAll() {
        return (List<SearchSource>) findAll(SearchSource.class);
    }

    /**
     * @param ssId
     * @return
     */
    @Override
    public SearchSource findSearchSourceById(Integer ssId) {
        return (SearchSource) findById(SearchSource.class, ssId);
    }

    /**
     * @param searchSource
     */
    @Override
    public SearchSource insertSearchSource(SearchSource searchSource) {
        return (SearchSource) insert(searchSource);
    }

    /**
     * @param searchSource
     */
    @Override
    public int updateSearchSource(SearchSource searchSource) {
        return update(searchSource);
    }

    /**
     * @param ssId
     */
    @Override
    public int deleteSearchSource(Integer ssId) {
        return deleteById(SearchSource.class, ssId);
    }
}
