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
import com.wxggt.dao.PractisequestionDAO;
import com.wxggt.dto.Practisequestion;

@WebServlet("/SaveQuestionBank")
public class SaveQuestionBank extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveQuestionBank() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		Writer out = response.getWriter();
		String jsonList = request.getParameter("jsonList");
		Gson gson1 = new Gson();
		List<Practisequestion> list = gson1.fromJson(jsonList, new TypeToken<List<Practisequestion>>() {
		}.getType());
		PractisequestionDAO dao = new PractisequestionDAO();
		try {
			for (Practisequestion question : list) {
				dao.uploadQuestion(question);
			}
			out.write("Success");
		} catch (Exception e) {
			out.write("Error");
		}
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
