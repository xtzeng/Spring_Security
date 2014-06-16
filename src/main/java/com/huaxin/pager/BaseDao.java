package com.huaxin.pager;


import java.util.List;

import com.huaxin.exception.ApplyException;



public interface BaseDao<T> {
	T get(Integer id);
    void save(T entity) throws ApplyException;
    void update(T entity) throws ApplyException;
    void delete(T entity) throws ApplyException;
    PageModel<T> findAll(int pageNo, int PageSize);
    List<T> findAll();
    int count();
    PageModel<T> search(QueryParse<T> qp);
}
