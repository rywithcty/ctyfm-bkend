package org.xf.iform.service.persistence.dao.common.impl;

import java.util.*;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.xf.iform.service.persistence.dao.common.BaseDao;

import static org.springframework.util.ClassUtils.isPrimitiveWrapper;

public abstract class BaseDaoImpl implements BaseDao {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private NamedParameterJdbcTemplate jt;

    //    @Override
//    public int insert(Object entity) {
//	int flag = 0;
//	if (null != entity) {
//	    em.persist(entity);
//	    flag++;
//	}
//	return flag;
//    }
    @Override
    public Object insert(Object entity) {
        int flag = 0;
        if (null != entity) {
            em.persist(entity);
            flag++;
        }
        return entity;
    }

    @Override
    public int update(Object entity) {
        int flag = 0;
        if (null != entity) {
            em.merge(entity);
            flag++;
        }
        return flag;
    }

    @Override
    public int delete(Object entity) {
        int flag = 0;
        if (null != entity) {
            em.remove(em.merge(entity));
            flag++;
        }
        return flag;
    }

    @Override
    public int deleteList(List<?> entitys) {
        int flag = 0;
        for (Object entity : entitys) {
            em.remove(entity);
            flag++;
        }
        return flag;
    }

    @Override
    public int deleteById(Class<?> clazz, Object id) {
        int flag = 0;
        Object find = em.find(clazz, id);
        if (find != null) {
            em.remove(em.merge(find));
            flag++;
        }
        return flag;
    }

    @Override
    public int deleteByField(Class<?> clazz, Map<String, Object> paramMap) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM " + clazz.getSimpleName() + " U WHERE 1=1 ");
        Set<String> set = null;
        set = paramMap.keySet();
        List<String> list = new ArrayList<String>(set);
        List<Object> fieldList = new ArrayList<>();
        for (String field : list) {
            sql.append(" AND U." + field + "=? ");
            fieldList.add(field);
        }
//        String sqlStr = sql.substring(0, sql.length() - 4);
        String sqlStr = sql.toString();
        System.out.println("sql:" + sqlStr);
        Query query = em.createNativeQuery(sqlStr);
        for (int i = 0; i < fieldList.size(); i++) {
            query.setParameter(i + 1, paramMap.get(fieldList.get(i)));
        }
        int executeUpdate = query.executeUpdate();
        return executeUpdate;
    }

    @Override
    public Object findById(Class<?> clazz, Object id) {
        return em.find(clazz, id);
    }

    @Override
    public List<?> findAll(Class<?> clazz) {
        List<?> resultList = em.createQuery("from " + clazz.getSimpleName()).getResultList();
        return resultList;
    }

    @Override
    public List<?> findByPage(Class<?> clazz, int page, int size) {
        Query query = em.createQuery("from " + clazz.getSimpleName());
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        List<?> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<?> findByFieldAndPage(Class<?> clazz, Map<String, Object> paramMap, int page, int size) {
        String sql = "from " + clazz.getSimpleName() + " U WHERE 1=1 ";
        Set<String> set = paramMap.keySet();
        List<String> list = new ArrayList<String>(set);
        List<Object> fieldList = new ArrayList<>();
        for (String field : list) {
            sql += " AND U." + field + "=? ";
            fieldList.add(field);
        }
//        sql = sql.substring(0, sql.length() - 4);
        System.out.println("sql:" + sql);
        Query query = em.createQuery(sql);
        for (int i = 0; i < fieldList.size(); i++) {
            query.setParameter(i + 1, paramMap.get(fieldList.get(i)));
        }
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        List<?> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<?> findByField(Class<?> clazz, Map<String, Object> paramMap) {
        String sql = "from " + clazz.getSimpleName() + " U WHERE 1=1 ";
        Set<String> set = paramMap.keySet();
        List<String> list = new ArrayList<String>(set);
        List<String> fieldList = new ArrayList<>();
        for (String field : list) {
//	    sql += "U." + field + "=? and ";
            sql += " AND U." + field + "=:" + field + " ";
            fieldList.add(field);
        }
//        sql = sql.substring(0, sql.length() - 4);
        System.out.println("sql:" + sql);
        Query query = em.createQuery(sql);
        for (int i = 0; i < fieldList.size(); i++) {
//	    query.setParameter(i + 1, paramMap.get(fieldList.get(i)));
            query.setParameter(fieldList.get(i), paramMap.get(fieldList.get(i)));
        }
        List<?> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Long countAll(Class<?> clazz) {
        Query query = em.createQuery("SELECT COUNT(*) FROM " + clazz.getSimpleName());
        return (Long) query.getSingleResult();

    }

    @Override
    public Long countByField(Class<?> clazz, Map<String, Object> paramMap) {
        String sql = "SELECT COUNT(*) FROM " + clazz.getSimpleName() + " U WHERE 1=1 ";
        Set<String> set = null;
        set = paramMap.keySet();
        List<String> list = new ArrayList<String>(set);
        List<Object> fieldList = new ArrayList<>();
        for (String field : list) {
            sql += " AND U." + field + "=? ";
            fieldList.add(field);
        }
        System.out.println("sql:" + sql);
        Query query = em.createQuery(sql);
        for (int i = 0; i < fieldList.size(); i++) {
            query.setParameter(i + 1, paramMap.get(fieldList.get(i)));
        }
        return (Long) query.getSingleResult();
    }

    @Override
    public List<?> findBySql(String sql, Class<?> clazz) {
        if (clazz.isPrimitive() || isPrimitiveWrapper(clazz)) {
            return jt.queryForList(sql, new HashMap<String, Object>(), clazz);
        }
        return jt.query(sql, new BeanPropertyRowMapper<>(clazz));
//	Query q = em.createNativeQuery(sql);
//	List<?> results = q.getResultList();
//	return results;
    }

    @Override
    public List<?> findBySqlAndPage(String sql, Class<?> clazz, int page, int size) {
        sql += "\n OFFSET " + ((page - 1) * size) + " ROWS FETCH NEXT " + size + " ROWS ONLY";
//	Query q = em.createNativeQuery(sql);
//	List<?> results = q.setFirstResult((page - 1) * size).setMaxResults(size).getResultList();
        return findBySql(sql, clazz);
    }

    @Override
    public List<?> findBySqlParam(String sql, Map<String, Object> paramMap, Class<?> clazz) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        setQueryParameter(paramSource, paramMap);
        if (clazz.isPrimitive() || isPrimitiveWrapper(clazz)) {
            return jt.queryForList(sql, paramSource, clazz);
        }
        return jt.query(sql, paramSource, new BeanPropertyRowMapper<>(clazz));
//	Query q = em.createQuery(sql).unwrap(org.hibernate.query.Query.class).setResultTransformer(null);
//	setQueryParameter(q, paramMap);
    }

    @Override
    public List<?> findBySqlParamAndPage(String sql, Map<String, Object> paramMap, Class<?> clazz, int page, int size) {
        sql += "\n OFFSET " + ((page - 1) * size) + " ROWS FETCH NEXT " + size + " ROWS ONLY";
        return findBySqlParam(sql, paramMap, clazz);
    }

    @Override
    public Object findSingleBySql(String sql, Class<?> clazz) {
        return findBySql(sql, clazz).stream().findFirst().orElse(null);
    }

    @Override
    public Object findSingleBySqlParam(String sql, Map<String, Object> paramMap, Class<?> clazz) {
        return findBySqlParam(sql, paramMap, clazz).stream().findFirst().orElse(null);
    }

    @Override
    public Long countBySql(String sql) {
        Query q = em.createNativeQuery(sql);
        return (Long) q.getSingleResult();
//		BigDecimal count = (BigDecimal) q.getSingleResult();
//		return count.longValue();
    }

    @Override
    public Long countBySql(String sql, Map<String, Object> paramMap) {
        Query q = em.createNativeQuery(sql);
        setQueryParameter(q, paramMap);
        return (Long) q.getSingleResult();
//		BigDecimal count = (BigDecimal) q.getSingleResult();
//		return count.longValue();
    }

    @Override
    public int executeSql(String sql) {
        Query q = em.createNativeQuery(sql);
        int num = q.executeUpdate();
        return num;
    }

    @Override
    public int executeSql(String sql, Map<String, Object> paramMap) {
        Query q = em.createNativeQuery(sql);
        setQueryParameter(q, paramMap);
        int num = q.executeUpdate();
        return num;
    }

    private void setQueryParameter(Query q, Map<String, Object> params) {
        if (null != params && !params.isEmpty()) {
            Set<Entry<String, Object>> entrySet = params.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                String key = entry.getKey();
                Object value = entry.getValue();
                q.setParameter(key, value);
            }
        }
    }

    private void setQueryParameter(MapSqlParameterSource q, Map<String, Object> params) {
        if (null != params && !params.isEmpty()) {
            Set<Entry<String, Object>> entrySet = params.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                String key = entry.getKey();
                Object value = entry.getValue();
                q.addValue(key, value);
            }
        }
    }

}
