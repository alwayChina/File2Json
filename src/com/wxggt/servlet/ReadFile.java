package com.wxggt.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.wxggt.util.TextList2Json;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/ReadFile")
public class ReadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		/* 硬盘文件项工厂--把IO流转换成临时文件 */
		DiskFileItemFactory factory = new DiskFileItemFactory();
		/* 获得上传内容 */
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);
			if (list.size() > 0) {
				/* 获取流字符串 */
				int pId = Integer.parseInt(list.get(0).getString("UTF-8"));
				/* 由于每次上传只上传一个文件，因此直接在list中get(1)就能获得上传的该文件 */
				FileItem item = list.get(1);
				/* 获取文件后缀名 */
				String fileExtraName = item.getName().substring(item.getName().lastIndexOf("."));
				/* 得到InputStream */
				InputStream inputStream = item.getInputStream();
				/* 开始解析内容 */
				TextList2Json textList2Json = new TextList2Json();
				String json = textList2Json.readFile(inputStream, fileExtraName, pId);
				/* 将结果回传给前端供用户确定 */
				Writer out = response.getWriter();
				inputStream.close();
				out.write(json);
				out.flush();
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
			System.out.println("上传失败！");
		}
	}
}