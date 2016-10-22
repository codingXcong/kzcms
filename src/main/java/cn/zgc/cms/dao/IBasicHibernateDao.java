package cn.zgc.cms.dao;

import java.util.List;
import java.util.Map;

import cn.zgc.cms.model.Pager;

/**
 * 使用Hibernate对数据库基本的操作进行一些封装
 * @author gczhang
 */
public interface IBasicHibernateDao<T> {
	/**
	 * 添加一条记录
	 * @param entity 添加的实体
	 * @return
	 */
	public T add(T entity);
	/**
	 * 删除对象
	 * @param id
	 */
	public void delete(long id);
	/**
	 * 更新对象
	 * @param newEntity
	 */
	public void update(T newEntity);
	/**
	 * 根据主键获取对象
	 * @param id 主键
	 * @return
	 */
	public T getByPk(long id);
	
	/**
	 * 查询列表--不分页
	 * @param hql 
	 * @param params
	 * @return
	 */
	public List<T> list(String hql,Object[] params);
	public List<T> list(String hql,Object param);
	public List<T> list(String hql);
	public List<T> list(String hql,Map<String,Object> alias);
	//形如：select user from User where name = :name and sex = :sex and age =? and home = ?
	public List<T> list(String hql,Object[] params,Map<String,Object> alias);
	
	
	
	/**
	 * 查询列表--分页
	 * @param hql 
	 * @param params
	 * @return
	 */
	public Pager<T> find(String hql,Object[] params);
	public Pager<T> find(String hql,Object param);
	public Pager<T> find(String hql);
	public Pager<T> find(String hql,Map<String,Object> alias);
	//形如：select user from User where name = :name and sex = :sex and age =? and home = ?
	public Pager<T> find(String hql,Object[] params,Map<String,Object> alias);
	
	/**
	 * 根据HQL查询单个对象
	 * @param hql
	 * @param params
	 * @return
	 */
	public Object queryObject(String hql,Object[] params);
	public Object queryObject(String hql,Object param);
	public Object queryObject(String hql);
	public Object queryObject(String hql, Map<String, Object> alias);
	public Object queryObject(String hql, Object[] params, Map<String, Object> alias);
	
	public void updateObject(String hql,Object[] params);
	public void updateObject(String hql,Object param);
	public void updateObject(String hql);
	
	/**
	 * 通过SQL语句查询数据集合
	 * @param sql 查询的SQL语句
	 * @param params
	 * @param clzss 查询的实体对象
	 * @param hasEntity 该对象是否是Hibernate所管理的实体，如果不是需要使用setResultTransform来查询
	 * @return
	 */
	public List<T> listBySQL(String sql,Object[] params,Class<T> clzss,boolean hasEntity);
	public List<T> listBySQL(String sql,Object param,Class<T> clzss,boolean hasEntity);
	public List<T> listBySQL(String sql,Class<T> clzss,boolean hasEntity);
	public List<T> listBySQL(String sql,Object[] params,Map<String,Object> alias,Class<T> clzss,boolean hasEntity);
	public List<T> listBySQL(String sql,Map<String,Object> alias,Class<T> clzss,boolean hasEntity);
	
	
	public Pager<T> findBySQL(String sql,Object[] params,Class<T> clzss,boolean hasEntity);
	public Pager<T> findBySQL(String sql,Object param,Class<T> clzss,boolean hasEntity);
	public Pager<T> findBySQL(String sql,Class<T> clzss,boolean hasEntity);
	public Pager<T> findBySQL(String sql,Object[] params,Map<String,Object> alias,Class<T> clzss,boolean hasEntity);
	public Pager<T> findBySQL(String sql,Map<String,Object> alias,Class<T> clzss,boolean hasEntity);
	
	
}
