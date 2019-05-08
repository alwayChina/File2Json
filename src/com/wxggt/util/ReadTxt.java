package com.wxggt.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.wxggt.dto.Practisequestion;
/**
 * 
 * @author    :alway
 * @descrition:This Class include two methods,'readFileText' is to read txt's content,'codeString' is to find what code the txt file is.
 * @date      :2019年5月8日
 */
public class ReadTxt {

/**
 * 
 * @Title: readFileText   
 * @Description: TODO
 * @param: @param filePath
 * @param: @param inputStream
 * @param: @return      
 * @return: List<Practisequestion>
 */
	public List<Practisequestion> readFileText(String filePath,InputStream inputStream) {
		List<Practisequestion> QList = new ArrayList<Practisequestion>();
		try {
				String code = ReadTxt.codeString(filePath);
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, code));
				String txt = "";
				int index = 0;
				String[] strArray = new String[7];
				while ((txt = br.readLine()) != null) {
					txt.replaceAll("\r|\n", "");
					if (txt.equals("")) {
						index = 0;
						QList = ReadWord.packageQuestion(QList, strArray);
						strArray = new String[7];
					} else {
						strArray[index++] = txt.trim();
					}
				}
				QList = ReadWord.packageQuestion(QList, strArray);
				br.close();
		} catch (Exception e) {
			System.err.println("File不存在或内容有错误！");
		}
		return QList;
	}

/**
 * 
 * @Title: codeString   
 * @Description: TODO
 * @param: @param filePath
 * @param: @return
 * @param: @throws Exception      
 * @return: String
 */
	public static String codeString(String filePath) throws Exception {
		URL url = new URL(filePath); // 创建URL
		URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
		urlconn.connect();
		InputStream inputStream = urlconn.getInputStream();
		BufferedInputStream bin = new BufferedInputStream(inputStream);
		int p = (bin.read() << 8) + bin.read();
		String code = null;
		switch (p) {
		case 0xefbb:
			code = "UTF-8";
			break;
		case 0xfffe:
			code = "Unicode";
			break;
		case 0xfeff:
			code = "UTF-16BE";
			break;
		default:
			code = "GBK";
		}
		System.out.println("文件类型:" + code);
		return code;
	}
}
