/**
 * Copyright ? 2009 www.debug.cn
 * All Rights Reserved
 */
package com.huaxin.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;




/**
 * xhEditor文件上传的Java - Servlet实现.
 * @author easinchu
 *
 */
public class XhEditorServlet extends HttpServlet {
	
	private static final String  CARGOIMAGE = "/CargoImage/";//上传文件存储目录
	private static String fileExt = "";
	private static Long maxSize = 0l;
	private static String INFOID = "infoId";

	/**
	 * 文件上传初始化工作
	 */
	public void init() throws ServletException {
		String realBaseDir = this.getServletConfig().getServletContext().getRealPath(CARGOIMAGE);
		File baseFile = new File(realBaseDir);
		if (!baseFile.exists()) {
			baseFile.mkdir();
		}
		
	}

	/**
	 * 上传文件数据处理过程
	 */
	@SuppressWarnings({"unchecked" })
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//multipart/form-data or multipart/mixed stream
		//application/octet-stream
		
		String newFileName = "";
		response.setHeader("Cache-Control", "no-cache");
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		String imgMax = request.getParameter(INFOID);
		Integer imgQuantity = (Integer) session.getAttribute(imgMax);
		if(imgQuantity != null && imgQuantity > 2) {
			out.print(0);
			return;
		}
		
		if(imgQuantity == null || imgQuantity == 0) {
			session.setAttribute(imgMax, Integer.valueOf(1));
		} else {
			session.setAttribute(imgMax, imgQuantity+1);
		}
		
			List<FileItem> items = null;
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e1) {
				out.println(getError("服务器故障！"));
				return;
			}
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				String fileName = item.getName();
				if (!item.isFormField()) {
					//检查文件大小
					if(item.getSize() > maxSize){
						out.println(getError("上传文件大小超过限制。"));
						return;
					}
					
					/*获取文件扩展名*/
					/*索引加1的效果是只取xxx.jpg的jpg*/
					String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
					//System.out.println("extensionName:" + extensionName);
					
					/*检查文件类型*/
					if (("," + fileExt.toLowerCase() + ",").indexOf("," + extensionName.toLowerCase() + ",") < 0){
						out.println(getError("不允许上传此类型的文件"));
						return;
					}
					String saveDirPath = CARGOIMAGE;
					
					/*文件存储在容器中的绝对路径*/
					String saveFilePath = this.getServletConfig().getServletContext().getRealPath("") + saveDirPath;
					//System.out.println("saveFilePath:" + saveFilePath);
					
					/*构建文件目录以及目录文件*/
					File fileDir = new File(saveFilePath);
					if (!fileDir.exists()) {fileDir.mkdirs();}
					
					/*重命名文件*/
					Date currTime = new Date();
					SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddhhmmssS",Locale.US);
					String filename = new String((formatter2.format(currTime)).getBytes("iso-8859-1"));
					
					try{
						File savefile = new File(saveFilePath + filename + "." + extensionName);
						item.write(savefile);
					}catch(Exception e){
						out.println(getError("上传文件失败。"));
						return;
					}
					
					//返回给页面的地址,这个地方根据项目的不一样，需要做一些特别的定制。
					newFileName = this.getServletConfig().getServletContext().getContextPath() + saveDirPath + filename + "." + extensionName;		
					
					//使用FTP
					
						out.println("error:1, url:"  + newFileName);
					
				}
					
			}
	}
	
	/**
	 * 使用I/O流输出 json格式的数据
	 * @param response
	 * @param err
	 * @param newFileName
	 * @throws IOException
	 */
	private String getError(String message){
		
			return "error:1, message:"  + message;
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		
		doPost(req, resp);
		
	}
	
	
	
	
}
