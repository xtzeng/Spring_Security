package com.huaxin.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.huaxin.bean.Notice;
import com.huaxin.dao.NoticeDao;
import com.huaxin.pager.HibernateUtil;

public class NoticeDaoImpl extends HibernateUtil<Notice> implements NoticeDao{
	//单独对象用notice
	//有关系的用hibernate criteria
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Notice> findAll() {
		
		return this.jdbcTemplate.query("SELECT * FROM notice", new NoticeMapper());
	}
	
	private static final class NoticeMapper  implements RowMapper {
		
		public Object mapRow(ResultSet rs, int index) throws SQLException {
			
			Notice notice = new Notice();
			notice.setTitle(rs.getString("Title"));
			notice.setNoticeId(rs.getInt("NoticeId"));
			notice.setAddTime(rs.getTimestamp("AddTime"));
			return notice;
		}
		
	}
}
