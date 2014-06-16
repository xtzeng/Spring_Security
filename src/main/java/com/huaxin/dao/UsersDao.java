package com.huaxin.dao;

import com.huaxin.bean.Users;
import com.huaxin.pager.BaseDao;

public interface UsersDao  extends BaseDao<Users>{
	public Users findByName(String name);
}
