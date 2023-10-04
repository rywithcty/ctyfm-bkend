package org.xf.iform.service.services.contract;

import org.xf.iform.core.entity.SearchSource;

import java.util.List;

public interface SearchSourceService {
    public List<SearchSource> getSearchSourceList();
    public SearchSource getSearchSource(Integer ssId);
    public int addSearchSource(SearchSource searchSource);
    public int editSearchSource(SearchSource searchSource);
    public int deleteSearchSource(Integer ssId);
}
