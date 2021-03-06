package com.xbmu.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.xbmu.bean.Resource;
import com.xbmu.business.KindBean;
import com.xbmu.service.BussinessService;
import com.xbmu.service.impl.BussinessServiceImpl;
import java.util.UUID;

/**
 * 添加资源
 * 
 * @author Administrator
 * 
 */
@WebServlet(urlPatterns = "/android/addResource.jsp")
public class AddResourceServlet extends HttpServlet {
	// 获取业务逻辑对象
	BussinessService service = new BussinessServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mode = request.getParameter("mode");
		String op = request.getParameter("op");
		
		//response.setContentType("text/html");

		if("android".equals(mode)){
			if ("showAddResourceUI".equals(op)) {
				List<KindBean> kinds = service.getAllKind();
				JSONArray jsonArray = new JSONArray(kinds);
				response.getWriter().println(jsonArray.toString());
			} else if ("addResource".equals(op)) {
				int resourceId = addResource(request);
				// 添加成功
				if (resourceId > 0) {
					response.getWriter().println("恭喜您，资源上传成功!");
				} else {
					response.getWriter().println("对不起，资源上传失败!");
				}
			}

		}else if("web".equals(mode)){
			if ("showAddResourceUI".equals(op)) {
				showAddResourceUI(request,response);
			} else if ("addResource".equals(op)) {
				int resourceId = addResource(request);
				response.getWriter().write("资源上传成功。2秒后自动转向添加页面");
				response.setHeader("Refresh", "2;URL=" + request.getContextPath()
						+ "/servlet/ViewOwnerResourceServlet?mode=web");
			}
		}
		

	}
	private int addResource(HttpServletRequest request) {		
		// 设置request编码，主要是为了处理普通输入框中的中文问题
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置文件的缓存路径
		factory.setRepository(new File("d:/temp/"));
		//上传文件的保存路径
		String path = "d:\\Bang\\download\\";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// System.out.print("已经生成临时文件");

		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置上传文件大小的上限，-1表示无上限
		upload.setSizeMax(1000 * 1024 * 1024); //1000MB
		List items = new ArrayList();
		try {
			// 上传文件，并解析出所有的表单字段，包括普通字段和文件字段
			items = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			System.out.println("文件上传发生错误" + e1.getMessage());
		}
		
		// 解析请求参数
		// 获取userId
		Integer userId = (Integer) request.getSession(true).getAttribute(
				"userId");
		String resName = null;
		String resDesc = null;
		String resUrl = null;
		double resSize = 0.0;
		Integer isEncrypt = 0;
		String password = null;
		String kindId = null;
		
		
		// 下面对每个字段进行处理，分普通字段和文件字段
		Iterator it = items.iterator();
		while (it.hasNext()) {
			DiskFileItem fileItem = (DiskFileItem) it.next();
			// 如果是普通字段
			if (fileItem.isFormField()) {
				try {
					if ("resName".equals(fileItem.getFieldName())) {
						resName = new String(fileItem.getString().getBytes(
								"iso8859-1"), "UTF-8");
						//System.out.println(resName);
					}
					else if ("resDesc".equals(fileItem.getFieldName())) {
						resDesc = new String(fileItem.getString().getBytes(
								"iso8859-1"), "UTF-8");
						//System.out.println(resDesc);
					}
					else if ("kindId".equals(fileItem.getFieldName())) {
						kindId = new String(fileItem.getString().getBytes(
								"iso8859-1"), "UTF-8");
					}
					else if ("isEncrypt".equals(fileItem.getFieldName())) {
						if ("true".equals(fileItem.getString())) {
							isEncrypt = 1;
						}
					}
					else if ("password".equals(fileItem.getFieldName())) {
						password = new String(fileItem.getString().getBytes(
								"iso8859-1"), "UTF-8");
					}
					System.out.println(fileItem.getFieldName()
							+ "   "
							+ fileItem.getName()
							+ "   "
							+ new String(fileItem.getString().getBytes(
									"iso8859-1"), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			} 
			// 如果是文件字段
			else {
				System.out.println(fileItem.getFieldName() + "   "
						+ fileItem.getName() + "   "
						+ fileItem.isInMemory() + "    "
						+ fileItem.getContentType() + "   "
						+ fileItem.getSize());
				resName = fileItem.getName();
				resSize = fileItem.getSize();
				resUrl = path + makeFileName(resName);
					
				/*
				//注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，
				//而有些只是单纯的文件名，如：1.txt
				//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
				filename = filename.substring(filename.lastIndexOf("\\")+1);
				//得到上传文件的扩展名
				String fileExtName = resName.substring(resName.lastIndexOf(".")+1);
				//如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
				System.out.println("上传的文件的扩展名是："+fileExtName);
				*/
				
				// 保存文件，其实就是把缓存里的数据写到目标路径下
				if (resName != null && resSize != 0) {
					File newFile = new File(resUrl);
					try {
						fileItem.write(newFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("文件没有选择 或 文件内容为空");
				}
			}
		}

		Resource resource = new Resource(resName, 
				resDesc, resUrl, resSize, isEncrypt, password);
		int resId = service.addResource(resource, Integer.parseInt(kindId),
				 userId);
		return resId;
	}
	
	 /**
	  * @Method: makeFileName
	  * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
	  * @param filename 文件的原始名称
	  * @return uuid+"_"+文件的原始名称
	  */ 
	private String makeFileName(String filename){
	//为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		return UUID.randomUUID().toString() + "_" + filename;
	}
	
	
	
	
	/**
	 * 显示添加物品的UI界面
	 * @param request
	 * @param response
	 */
	private void showAddResourceUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<KindBean> kinds = service.getAllKind();
		request.setAttribute("kinds", kinds);
		request.getRequestDispatcher("/manage/addResource.jsp").forward(
				request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
