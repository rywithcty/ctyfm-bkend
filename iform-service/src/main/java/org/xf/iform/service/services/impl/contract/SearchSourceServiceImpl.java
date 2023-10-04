package org.xf.iform.service.services.impl.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xf.iform.core.entity.SearchSource;
import org.xf.iform.service.persistence.dao.contract.SearchSourceDao;
import org.xf.iform.service.services.contract.SearchSourceService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SearchSourceServiceImpl implements SearchSourceService {

    @Autowired
    private SearchSourceDao searchSourceDao;

    /**
     * @return
     */
    @Override
    public List<SearchSource> getSearchSourceList() {
        return searchSourceDao.findSearchSourceAll();
    }

    /**
     * @param ssId
     * @return
     */
    @Override
    public SearchSource getSearchSource(Integer ssId) {
        return searchSourceDao.findSearchSourceById(ssId);
    }

    /**
     * @param searchSource
     * @return
     */
    @Override
    @Transactional
    public int addSearchSource(SearchSource searchSource) {
        return searchSourceDao.insertSearchSource(searchSource).getSsId();
    }

    /**
     * @param searchSource
     * @return
     */
    @Override
    @Transactional
    public int editSearchSource(SearchSource searchSource) {
        return searchSourceDao.updateSearchSource(searchSource);
    }

    /**
     * @param ssId
     * @return
     */
    @Override
    @Transactional
    public int deleteSearchSource(Integer ssId) {
        return searchSourceDao.deleteSearchSource(ssId);
    }
}
