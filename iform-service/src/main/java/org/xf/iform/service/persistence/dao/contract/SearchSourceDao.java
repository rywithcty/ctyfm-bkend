package org.xf.iform.service.persistence.dao.contract;

import org.xf.iform.core.entity.SearchSource;

import java.util.List;

public interface SearchSourceDao {
    public List<SearchSource> findSearchSourceAll();

    public SearchSource findSearchSourceById(Integer ssId);

    public SearchSource insertSearchSource(SearchSource searchSource);

    public int updateSearchSource(SearchSource searchSource);

    public int deleteSearchSource(Integer ssId);


}
