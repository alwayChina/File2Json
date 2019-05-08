package com.wxggt.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.wxggt.vo.Practisequestion;

/**
 * @author :alway
 * @descrition:Turn List to json,In order to send data to the web front
 * @date :2019年5月5日
 */
public class TextList2Json {
	
	/**
	 * 
	 * @Title: readFile   
	 * @Description: Get the InputStream from the Internet,and judge what kind of file it is,then turn to the method.
	 * @param: @param path
	 * @param: @return      
	 * @return: List<Practisequestion>
	 */
	public static List<Practisequestion> readFile(String path) {
		try {
			int HttpResult; // 服务器返回的状态
			URL url;
			url = new URL(path);
			// 创建URL
			URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
			urlconn.connect();
			HttpURLConnection httpconn = (HttpURLConnection) urlconn;
			HttpResult = httpconn.getResponseCode();
			if (HttpResult != HttpURLConnection.HTTP_OK) {
				System.out.print("无法连接到");
				return null;
			} else {
				InputStream inputStream=urlconn.getInputStream();
				List<Practisequestion> QList = new ArrayList<Practisequestion>();
				if (path.endsWith(".doc")) {
					QList = ReadWord.readDoc(inputStream);
				} else if (path.endsWith("docx")) {
					QList = ReadWord.readDocx(inputStream);
				} else if (path.endsWith(".txt")) {
					ReadTxt readfileEx = new ReadTxt();
					QList = readfileEx.readFileText(path,inputStream);
				} else {
					System.out.println("文件类型错误");
				}
				return QList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String filePath = "http://wxggt.oss-cn-beijing.aliyuncs.com/ReadFile/c.doc";
		List<Practisequestion> QList = readFile(filePath);
		for (Practisequestion p : QList) {
			System.out.println(p.getQuestion());
			System.out.println(p.getA());
			System.out.println(p.getB());
			System.out.println(p.getC());
			System.out.println(p.getD());
			System.out.println(p.getRightAnswer());
			System.out.println(p.getqAnalyze());
			System.out.println();
		}
	}
}
