package com.wxggt.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Test {
	private static String openFile(String filePath) {
		String ee = new String();
		try {
			int HttpResult; // 服务器返回的状态
			URL url = new URL(filePath); // 创建URL
			URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
			urlconn.connect();
			HttpURLConnection httpconn = (HttpURLConnection) urlconn;
			HttpResult = httpconn.getResponseCode();
			if (HttpResult != HttpURLConnection.HTTP_OK) {
				System.out.print("无法连接到");
			} else {
				InputStreamReader isReader = new InputStreamReader(urlconn.getInputStream(), "UTF-8");
				BufferedReader reader = new BufferedReader(isReader);
				StringBuffer buffer = new StringBuffer();
				String line; // 用来保存每行读取的内容
				line = reader.readLine(); // 读取第一行
				while (line != null) { // 如果 line 为空说明读完了
					buffer.append(line); // 将读到的内容添加到 buffer 中
					buffer.append(" "); // 添加换行符
					line = reader.readLine(); // 读取下一行
				}
				System.out.print(buffer.toString());
				ee = buffer.toString();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ee;
	}

	public static void main(String[] args) {
		System.out.print(Test.openFile("http://wxggt.oss-cn-beijing.aliyuncs.com/ReadFile/a.txt"));
	}
}