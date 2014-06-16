package com.huaxin.dao;

import java.util.List;

import com.huaxin.bean.Notice;
import com.huaxin.pager.BaseDao;


public interface NoticeDao extends BaseDao<Notice> {
	List<Notice> findAll();
}
