package org.xf.iform.service.persistence.dao.common;

import java.util.List;
import java.util.Map;

public interface BaseDao {

    /**
     * 新增資料
     *
     * @param entity
     * @return 數量
     */
    Object insert(Object entity);

    /**
     * 更新資料
     * 
     * @param entity
     * @return 數量
     */
    int update(Object entity);

    /**
     * 刪除資料
     * 
     * @param entity
     * @return 數量
     */
    int delete(Object entity);

    /**
     * 批次刪除資料
     * 
     * @param entitys
     * @return 數量
     */
    int deleteList(List<?> entitys);

    /**
     * 根據PrimaryKey刪除資料,entity只是要class，直接new即可
     * 
     * @param clazz entity的class
     * @param id    entityPK
     * @return 數量
     */
    int deleteById(Class<?> clazz, Object id);

    /**
     * 根據參數刪除資料
     * 
     * @param clazz    entity的class
     * @param paramMap 參數集合
     * @return 數量
     */
    int deleteByField(Class<?> clazz, Map<String, Object> paramMap);

    /**
     * 根據PrimaryKey查詢
     * 
     * @param clazz entity的class
     * @param id    entityPK
     * @return entity 查詢結果
     */
    Object findById(Class<?> clazz, Object id);

    /**
     * 查詢所有資料
     * 
     * @param clazz entity的class
     * @return entityList 查詢結果集合
     */
    List<?> findAll(Class<?> clazz);

    /**
     * 
     * 所有資料分頁查詢
     *
     * @param clazz entity的class
     * @param page  頁碼
     * @param size  每頁筆數
     * @return 查詢結果資料集合
     */
    List<?> findByPage(Class<?> clazz, int page, int size);

    /**
     * 根據參數分頁查詢
     * 
     * @param clazz    entity的class
     * @param paramMap 參數集合
     * @param page     頁碼
     * @param size     每頁筆數
     * @return entityList 查詢結果集合
     */
    List<?> findByFieldAndPage(Class<?> clazz, Map<String, Object> paramMap, int page, int size);

    /**
     * 根據參數查詢
     * 
     * @param clazz    entity的class
     * @param paramMap 參數集合
     * @return entityList 查詢結果集合
     */
    List<?> findByField(Class<?> clazz, Map<String, Object> paramMap);

    /**
     * 查詢所有資料總數
     * 
     * @param clazz entity的class
     * @return 數量
     */
    Long countAll(Class<?> clazz);

    /**
     * 根據參數查詢資料總數
     * 
     * @param clazz
     * @param paramMap
     * @return 數量
     */
    Long countByField(Class<?> clazz, Map<String, Object> paramMap);

    /**
     * 根據SQL查詢語法，查詢資料集合
     * 
     * @param sql SQL語法
     * @return 查詢結果資料集合
     */
    List<?> findBySql(String sql, Class<?> clazz);

    /**
     * 根據SQL查詢語法，分頁查詢資料
     * 
     * @param sql  SQL語法
     * @param page 頁碼
     * @param size 每頁筆數
     * @return 查詢結果資料集合
     */
    List<?> findBySqlAndPage(String sql, Class<?> clazz, int page, int size);

    /**
     * 根據SQL與參數查詢語法，查詢資料集合
     * 
     * @param sql      SQL語法
     * @param paramMap 參數集合
     * @return 查詢結果資料集合
     */
    List<?> findBySqlParam(String sql, Map<String, Object> paramMap, Class<?> clazz);

    /**
     * 根據SQL與參數查詢語法，分頁查詢資料
     * 
     * @param sql      SQL語法
     * @param paramMap 參數集合
     * @param page     頁碼
     * @param size     每頁筆數
     * @return 查詢結果資料集合
     */
    List<?> findBySqlParamAndPage(String sql, Map<String, Object> paramMap, Class<?> clazz, int page, int size);

    /**
     * 根據SQL與參數查詢語法，查詢一筆資料
     * 
     * @param sql      SQL語法
     * @param clazz 參數集合
     * @return 查詢結果資料
     */
    Object findSingleBySql(String sql, Class<?> clazz);

    /**
     * 根據SQL與參數查詢語法，查詢一筆資料
     * 
     * @param sql      SQL語法
     * @param paramMap 參數集合
     * @return 查詢結果資料
     */
    Object findSingleBySqlParam(String sql, Map<String, Object> paramMap, Class<?> clazz);

    /**
     * 根據SQL，插入或更新或删除資料
     * 
     * @param sql SQL語法
     * @return 更改筆數
     */
    int executeSql(String sql);

    /**
     * 根據SQL與參數，插入或更新或删除資料
     * 
     * @param sql      SQL語法
     * @param paramMap 參數集合
     * @return 更改筆數
     */
    int executeSql(String sql, Map<String, Object> paramMap);

    /**
     * 根據SQL，查詢數量
     * 
     * @param sql SQL語法
     * @return 數量
     */
    Long countBySql(String sql);

    /**
     * 根據SQL與參數，查詢數量
     * 
     * @param sql      SQL語法
     * @param paramMap 參數集合
     * @return 數量
     */
    Long countBySql(String sql, Map<String, Object> paramMap);
}
