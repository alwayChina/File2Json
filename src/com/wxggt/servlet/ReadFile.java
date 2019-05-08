package com.wxggt.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wxggt.dto.Practisequestion;
import com.wxggt.util.TextList2Json;

@WebServlet("/ReadFile")
public class ReadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReadFile() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		List<Practisequestion> QList = TextList2Json.readFile("http://wxggt.oss-cn-beijing.aliyuncs.com/ReadFile/c.doc");
		Gson gson = new Gson();
		String json = gson.toJson(QList);
		Writer out = response.getWriter();
		out.write(json);
		String jsonList = json;
		Gson gson1 = new Gson();
		List<Practisequestion> list = gson1.fromJson(jsonList, new TypeToken<List<Practisequestion>>() {
		}.getType());
		for (Practisequestion p : list) {
			System.out.println(p.getQuestion());
			System.out.println(p.getA());
			System.out.println(p.getB());
			System.out.println(p.getC());
			System.out.println(p.getD());
			System.out.println(p.getRightAnswer());
			System.out.println(p.getqAnalyze());
			System.out.println();
		}
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
