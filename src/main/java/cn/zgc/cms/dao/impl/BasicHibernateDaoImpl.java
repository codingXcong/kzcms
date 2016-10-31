package cn.zgc.cms.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import cn.zgc.cms.dao.IBasicHibernateDao;
import cn.zgc.cms.model.Pager;
import cn.zgc.cms.util.SystemContext;

public class BasicHibernateDaoImpl<T> implements IBasicHibernateDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	// 表示泛型的class对象
	private Class<?> clazz;

	public Class<?> getClazz() {
		if (clazz == null) {
			// 获取泛型的Class对象
			clazz = ((Class<?>) (((ParameterizedType) (this.getClass().getGenericSuperclass()))
					.getActualTypeArguments()[0]));
		}

		return clazz;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	
	public T add(T entity) {
		getSession().save(entity);
		return entity;
	}

	
	public void delete(int id) {
		getSession().delete(this.getByPk(id));
	}

	
	public void update(T newEntity) {
		getSession().update(newEntity);
	}

	
	public T getByPk(Integer id) {
		T t = (T) getSession().load(getClazz(), id);
		return t;

	}

	
	public List<T> list(String hql, Object[] params) {
		return this.list(hql, params, null);
	}

	
	public List<T> list(String hql, Object param) {
		return this.list(hql, new Object[]{param});
	}

	
	public List<T> list(String hql) {
		return this.list(hql, null,null);
	}

	
	public List<T> list(String hql, Map<String, Object> alias) {
		return this.list(hql, null, alias);
	}
	
	/**
	 * 添加排序
	 * @param hql
	 * @return
	 */
	private String addSort(String hql){
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		if(sort!=null&&!"".equals(sort.trim())){
			hql += "order by "+sort;
			if("desc".equals(order)){
				hql += " desc";
			}else{
				hql += " asc";
			}
		}
		return hql;
	}
	
	/**
	 * 设置别名
	 * @param query
	 * @param alias
	 */
	private void setAliasParameter(Query query,Map<String,Object> alias) {
		if(alias!=null) {
			Set<String> keys = alias.keySet();
			for(String key:keys) {
				Object val = alias.get(key);
				if(val instanceof Collection) {
					//查询条件是列表
					query.setParameterList(key, (Collection)val);
				} else {
					query.setParameter(key, val);
				}
			}
		}
	}
	
	/**
	 * 设置查询参数
	 * @param query
	 * @param params
	 */
	private void setParameter(Query query,Object[] params){
		if(params!=null&&params.length>0){
			int i = 0;
			for(Object obj:params){
				query.setParameter(i++, obj);
			}
		}
	}
	
	
	public List<T> list(String hql, Object[] params, Map<String, Object> alias) {
		hql = this.addSort(hql);
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, params);
		return query.list();
	}

	
	public Pager<T> find(String hql, Object[] params) {
		return this.find(hql, params, null);
	}

	
	public Pager<T> find(String hql, Object param) {
		return this.find(hql, new Object[]{param});
	}

	
	public Pager<T> find(String hql) {
		return this.find(hql, null, null);
	}

	
	public Pager<T> find(String hql, Map<String, Object> alias) {
		return this.find(hql, null, alias);
	}
	
	private String getCountHql(String hql,boolean isHql){
		String from = hql.substring(hql.indexOf("from"));
		String countHql = "select count(*) "+from;
		if(isHql){
			countHql.replaceAll("fetch", "");
		}
		return countHql;
	}

	private void setPagers(Query query,Pager Pager) {
		Integer PagerSize = SystemContext.getPageSize();
		Integer PagerOffset = SystemContext.getPageOffset();
		if(PagerOffset==null||PagerOffset<0) PagerOffset = 0;
		if(PagerSize==null||PagerSize<0) PagerSize = 15;
		Pager.setOffset(PagerOffset);
		Pager.setSize(PagerSize);
		query.setFirstResult(PagerOffset).setMaxResults(PagerSize);
	}
	
	/**
	 * 分页查询
	 */
	
	public Pager<T> find(String hql, Object[] params, Map<String, Object> alias) {
		hql = addSort(hql);
		String countHql = getCountHql(hql, true);
		Query countQuery = getSession().createQuery(countHql);
		Query dataQuery = getSession().createQuery(hql);
		//设置别名参数
		setAliasParameter(countQuery, alias);
		setAliasParameter(dataQuery, alias);
		//设置参数
		setParameter(countQuery, params);
		setParameter(dataQuery, params);
		Pager<T> Pager = new Pager<T>();
		setPagers(dataQuery, Pager);
		Pager.setDatas(dataQuery.list());
		Pager.setTotal((Long) countQuery.uniqueResult());
		return Pager;
	}
	
	
	public Object queryObject(String hql, Object[] params) {
		return this.queryObject(hql, params, null);
	}
	
	
	public Object queryObject(String hql, Object param) {
		return this.queryObject(hql, new Object[]{param});
	}
	
	
	public Object queryObject(String hql) {
		return this.queryObject(hql, null, null);
	}
	
	
	public Object queryObject(String hql, Object[] params,
			Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, params);
		return query.uniqueResult();
	}
	
	
	public Object queryObject(String hql, Map<String, Object> alias) {
		return this.queryObject(hql,null,alias);
	}

	
	public void updateObject(String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		setParameter(query, params);
		query.executeUpdate();
	}
	
	
	public void updateObject(String hql, Object param) {
		this.updateObject(hql, new Object[]{param});
	}

	
	public void updateObject(String hql) {
		this.updateObject(hql, null);
	}

	
	public List<T> listBySQL(String sql, Object[] params, Class<T> clazz, boolean hasEntity) {
		return this.listBySQL(sql, params, null, clazz, hasEntity);
	}

	
	public List<T> listBySQL(String sql, Object param, Class<T> clazz, boolean hasEntity) {
		return this.listBySQL(sql, new Object[]{param}, clazz, hasEntity);
	}

	
	public List<T> listBySQL(String sql, Class<T> clazz, boolean hasEntity) {
		return this.listBySQL(sql, null, null, clazz, hasEntity);
	}
	
	
	public List<T> listBySQL(String sql, Object[] params, Map<String, Object> alias, Class<T> clazz,
			boolean hasEntity) {
		sql = addSort(sql);
		SQLQuery sq = getSession().createSQLQuery(sql);
		setAliasParameter(sq, alias);
		setParameter(sq, params);
		if(hasEntity) {
			sq.addEntity(clazz);
		} else 
			sq.setResultTransformer(Transformers.aliasToBean(clazz));
		return sq.list();
	}
	
	
	public List<T> listBySQL(String sql, Map<String, Object> alias, Class<T> clazz, boolean hasEntity) {
		return this.listBySQL(sql, null, alias, clazz, hasEntity);
	}

	
	public Pager<T> findBySQL(String sql, Object[] params, Class<T> clazz, boolean hasEntity) {
		return this.findBySQL(sql, params, null, clazz, hasEntity);
	}

	
	public Pager<T> findBySQL(String sql, Object param, Class<T> clazz, boolean hasEntity) {
		return this.findBySQL(sql, new Object[]{param}, clazz, hasEntity);
	}
	
	
	public Pager<T> findBySQL(String sql, Class<T> clazz, boolean hasEntity) {
		return this.findBySQL(sql, null, null, clazz, hasEntity);
	}
	
	
	public Pager<T> findBySQL(String sql, Object[] params, Map<String, Object> alias, Class<T> clazz,
			boolean hasEntity) {
		sql = addSort(sql);
		String countHql = getCountHql(sql,false);
		SQLQuery dataQuery = getSession().createSQLQuery(sql);
		SQLQuery countQuery = getSession().createSQLQuery(countHql);
		setAliasParameter(dataQuery, alias);
		setAliasParameter(countQuery, alias);
		setParameter(dataQuery, params);
		setParameter(countQuery, params);
		Pager<T> Pagers = new Pager<T>();
		setPagers(dataQuery, Pagers);
		if(hasEntity) {
			dataQuery.addEntity(clazz);
		} else {
			dataQuery.setResultTransformer(Transformers.aliasToBean(clazz));
		}
		List<T> datas = dataQuery.list();
		Pagers.setDatas(datas);
		long total = (Long) countQuery.uniqueResult();
		Pagers.setTotal(total);
		return Pagers;
	}
	
	
	public Pager<T> findBySQL(String sql, Map<String, Object> alias, Class<T> clazz, boolean hasEntity) {
		return this.findBySQL(sql, null, alias, clazz, hasEntity);
	}

}
