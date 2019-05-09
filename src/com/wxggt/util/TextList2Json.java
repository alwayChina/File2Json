package com.wxggt.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.wxggt.dto.Practisequestion;

/**
 * @author :alway
 * @descrition:Turn List to json,In order to send data to the web front
 * @date :2019年5月5日
 */
public class TextList2Json {

	/**
	 * 
	 * @Title readFile
	 * @Description Get the InputStream from the servlet,and judge what kind of file
	 *              it is,then turn to the method.
	 * @param inputStream
	 * @param fileExtraName
	 * @param pId
	 * @return
	 */
	public String readFile(InputStream inputStream, String fileExtraName, int pId) {
		try {
			List<Practisequestion> QList = new ArrayList<Practisequestion>();
			if ((".doc").equals(fileExtraName)) {
				QList = ReadWord.readDoc(inputStream, pId);
			} else if ((".docx").equals(fileExtraName)) {
				QList = ReadWord.readDocx(inputStream, pId);
			} else if ((".txt").equals(fileExtraName)) {
				ReadTxt readfileEx = new ReadTxt();
				QList = readfileEx.readFileText(inputStream, pId);
			} else {
				System.out.println("文件类型错误");
			}
			Gson gson = new Gson();
			return gson.toJson(QList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
