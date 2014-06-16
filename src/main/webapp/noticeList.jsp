<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/fn.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'noticeList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>css/css1.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/page.js"></script>
	<script src="<%=basePath %>js/xheditor/xheditor-zh-cn.min.js" type="text/javascript"></script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		body {
		font-size:13px;
		width:900px;
		margin:0 auto
		}
		ul.noticeList {
		width:600px;
		}
		ul.noticeList li {
		width:590px;
		border-bottom:1px solid #eee
		}
	</style>
	

  </head>
  
  <body>
  
	<form action="notice.action" name="searchForm">
		<input name="totalPages" id="totalPages" type="hidden" value="${pageModel.totalPages }"/>
		<input name="pageSize" id="pageSize" type="hidden" value="${pageModel.pageSize }">
		<input name="pageNo" id="pageNo" type="hidden" value="${pageModel.pageNo }">
	</form>
	
	<ul class="noticeList">
		 <c:forEach items="${datas}" var="notice">
		 <li id="li${notice.noticeId }">[<fmt:formatDate value="${notice.addTime }"  pattern= "yyyy-MM-dd HH:mm:ss" />]
		 <a href="notice!updateA.action?nid=${notice.noticeId }">修改</a>
		 <a href="javascript:del(${notice.noticeId})">删除</a>
		 <Br/><a href="notice!detail.action?nid=${notice.noticeId }">${notice.title }</a></li>
		 </c:forEach>
	</ul>
    
  </body>
</html>
