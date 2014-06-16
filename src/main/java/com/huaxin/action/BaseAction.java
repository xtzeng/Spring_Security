package com.huaxin.action;

import java.lang.reflect.Method;

import org.apache.struts2.ServletActionContext;

import com.huaxin.pager.PageModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	//分页
	private int pageNo;
	private int pageSize;
	private PageModel pageModel;
	
	//DAO
	
	public PageModel getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	public int getPageNo() {
		if(this.pageNo == 0) {
			this.pageNo = 1;
		}
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		if(this.pageSize == 0) {
			this.pageSize = 1;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setRequestValue(String name, Object object) {
		ServletActionContext.getRequest().setAttribute(name, object);
	}
	public Object getRequestValue(String name) {
		return ServletActionContext.getRequest().getAttribute(name);
	}
	
	
	public Object getSessionValue(String name) {
		return ActionContext.getContext().getSession().get(name);
	}
	
	
	public void setSessionValue(String name, Object obj) {
		ActionContext.getContext().getSession().put(name, obj);
	}
	
	protected String executeMethod(String method) throws Exception {
		Class[] c = null;
		Method m = this.getClass().getMethod(method, c);
		Object[] o = null;
		String result = (String) m.invoke(this, o);
		return result;
	}
}
