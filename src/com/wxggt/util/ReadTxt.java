package com.wxggt.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.wxggt.dto.Practisequestion;

/**
 * 
 * @author :alway
 * @descrition:This Class include two methods,'readFileText' is to read txt's
 *                  content,'codeString' is to find what code the txt file is.
 * @date :2019年5月8日
 */
public class ReadTxt {

	/**
	 * 
	 * @Title: readFileText
	 * @Description: TODO
	 * @param: filePath
	 * @param: inputStream
	 * @param: @return
	 * @return: List<Practisequestion>
	 */
	public List<Practisequestion> readFileText(InputStream inputStream, int pId) {
		List<Practisequestion> QList = new ArrayList<Practisequestion>();
		try {
				String code = codeString(inputStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, code));
			String txt = "";
			int index = 0;
			String[] strArray = new String[7];
			while ((txt = br.readLine()) != null) {
				txt.replaceAll("\r|\n", "");
				if (txt.equals("")) {
					index = 0;
					QList = ReadWord.packageQuestion(QList, strArray, pId);
					strArray = new String[7];
				} else {
					strArray[index++] = txt.trim();
				}
			}
			QList = ReadWord.packageQuestion(QList, strArray, pId);
			br.close();
		} catch (Exception e) {
			System.err.println("File不存在或内容有错误！");
		}
		return QList;
	}
	
	/**
	 * 
	 * @Title codeString
	 * @Description Judge a InputStream what kind of code it is.
	 * @param inputStream
	 * @return code
	 * @throws Exception
	 */
	public static String codeString(InputStream inputStream) throws Exception {
		byte[] head = new byte[3];
		inputStream.read(head);
		String code = "";
		{
			code = "gb2312";
		}
		if (head[0] == -1 && head[1] == -2) {
			code = "UTF-16";
		}
		if (head[0] == -2 && head[1] == -1) {
			code = "Unicode";
		}
		if (head[0] == -17 && head[1] == -69 && head[2] == -65) {
			code = "UTF-8";
		}
		return code;
	}
}
