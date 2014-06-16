package com.huaxin.pager;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.huaxin.exception.ApplyException;


public abstract class HibernateUtil<T> extends HibernateDaoSupport{
	
	private Class<T> persistentClass;  
	
	private static final Logger logger = Logger.getLogger("persistentClass");
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	@SuppressWarnings("unchecked")
	public HibernateUtil() {   
        this.persistentClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];  
    }
	
	public int count() {
		return statistics(null);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final Criterion... criterion) {
		List<T> list = (List<T>) getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria(persistentClass);
				for(Criterion c : criterion) {
					criteria.add(c);
				}
				return criteria.list();
			}
		});
		return list;
	}
	
	//统计
	@SuppressWarnings("unchecked")
	public int statistics(final List<Criterion> criterion) {
		
		int sum = (Integer)getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Criteria counts = session.createCriteria(persistentClass);
				if(criterion != null) {
					for (Criterion c : criterion) {   
						counts.add(c);   
			        }  
				}
				//总记录数
				int count = ((Long)counts.setProjection(Projections.rowCount()).uniqueResult()).intValue(); 
				return count;
			}
		});
		return sum;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(){
			List<T> list = (List<T>)getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					
					Criteria criteria = session.createCriteria(persistentClass);
					//条件
					List list = criteria.list();
					return list;
				}
			});
		return list;
	}
	

	@SuppressWarnings("unchecked")
	public PageModel<T> findAll(final int pageNo, final int pageSize) {
		return search(new QueryParse<T>().addFetch(pageNo, pageSize));
	}
	
	
	@SuppressWarnings("unchecked")
	public PageModel search(final QueryParse<T> queryParse) {
		
		final PageModel pm = new PageModel();
		List<T> list = (List<T>) getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				if(queryParse != null) {
					Criteria criteria = session.createCriteria(persistentClass);
					List<Criterion> criterions = queryParse.criterion();
					
					PageModel pageModel = queryParse.getPageModel();
					if(pageModel != null) {
						int pageNo = pageModel.getPageNo();
						int pageSize = pageModel.getPageSize();
						criteria.setFirstResult((pageNo - 1) * pageSize);
						criteria.setMaxResults(pageSize);
						
						int count = statistics(criterions);
						pm.setTotal(count);
						pm.setPageNo(pageNo);
						pm.setPageSize(pageSize);
					}
					
					
					for(int i = 0; i < criterions.size(); i++) {
						criteria.add(criterions.get(i));
					}
					
					OrderObj ord = queryParse.getOrder();
					if(ord != null) {
						if(ord.getOrderBy() != null && !"".equals(ord.getOrderBy())) {
							if(OrderObj.ORDER_ASC.equals(ord.getOrderBy())) {
								criteria.addOrder(Order.asc(ord.getName()));
							} else {
								criteria.addOrder(Order.desc(ord.getName()));
							}
						}
					}
					return criteria.list();
				} else {
					return null;
				}
				
			}
		});
		pm.setDatas(list);
		return pm;
	}
	
	public void delete(T entity) throws ApplyException {
		try {
			this.getHibernateTemplate().delete(entity);
		} catch(Exception e) {
			logger.error(e);
			throw new ApplyException(e);
		}
	}
	
	

	@SuppressWarnings("unchecked")
	public T get(Integer id){
		return (T)this.getHibernateTemplate().get(getPersistentClass(), id);
	}

	public void save(T entity) throws ApplyException {
		try {
			this.getHibernateTemplate().save(entity);
		}catch(Exception e) {
			logger.error(e);
			throw new ApplyException(e);
		}
	}

	public void update(T entity) throws ApplyException {
		try {
			this.getHibernateTemplate().update(entity);
		}catch(Exception e) {
			logger.error(e);
			throw new ApplyException(e);
		}
	}
	
}
