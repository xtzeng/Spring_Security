package com.huaxin.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.huaxin.bean.Users;
import com.huaxin.dao.UsersDao;
import com.huaxin.pager.HibernateUtil;

public class UsersDaoImpl extends HibernateUtil<Users> implements UsersDao {

	public Users findByName(String name) {
		
		List<Users> objs = this.findByCriteria(Restrictions.eq("account", name));
		return (objs.size() == 0 ? null : objs.get(0)); 
	}

}
