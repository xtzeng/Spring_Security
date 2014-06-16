<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>xhEditor demo10 : showIframeModal接口的iframe文件上传</title>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/xheditor/xheditor-1.1.6-zh-cn.min.js"></script>
<script type="text/javascript">
$(pageInit);
function pageInit()
{
	$('#elm2').xheditor({skin:'nostyle',upLinkUrl:"!{editorRoot}xheditor_plugins/multiupload/multiupload.html?uploadurl={editorRoot}demos/upload.php%3Fimmediate%3D1&ext=附件文件(*.zip;*.rar;*.txt)",
		upImgUrl:'!{editorRoot}xheditor_plugins/multiupload/multiupload.html?uploadurl=<%=basePath%>XhEditor%3Fimmediate%3D1&ext=图片文件(*.jpg;*.jpeg;*.gif;*.png)'
	})
}
function submitForm(){$('#frmDemo').submit();}
</script>
</head>
<body>

<form id="frmDemo" method="post" action="show.php">
	<h3>xhEditor demo10 : showIframeModal接口的iframe文件上传</h3>
	
	<textarea id="elm2" name="elm2" rows="12" cols="80" style="width: 80%">
	</textarea>
	<br/><br />
	<input type="submit" name="save" value="Submit" />
	<input type="reset" name="reset" value="Reset" />
</form>
</body>
</html>