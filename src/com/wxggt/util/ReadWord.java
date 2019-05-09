package com.wxggt.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import com.wxggt.dto.Practisequestion;

/**
 * @author： alway
 * 
 * @description: Read a word document and get it's content by line.
 * @date: 2019/05/05
 */

public class ReadWord {
	/**
	 * 
	 * @Title: readDocx   
	 * @Description: Recive the InputStream from the Internet,and read the content.
	 * @param: @param inputStream
	 * @param: @return      
	 * @return: List<Practisequestion>
	 */
	public static List<Practisequestion> readDocx(InputStream inputStream,int pId) {
		List<Practisequestion> QList = new ArrayList<Practisequestion>();
		try {
				XWPFDocument docx = new XWPFDocument(inputStream);

				List<XWPFParagraph> paragraphs = docx.getParagraphs();
				int index = 0;
				String[] strArray = new String[7];
				for (XWPFParagraph p : paragraphs) {
					String txt = p.getText();
					if (!txt.isEmpty()) {
						strArray[index++] = txt.trim();
					} else {
						QList = packageQuestion(QList, strArray,pId);
						strArray = new String[7];
						index = 0;
					}
				}
				if ((strArray.length > 0)) {
					QList = packageQuestion(QList, strArray,pId);
				}
				docx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return QList;
	}

	/**
	 * 
	 * @Title: readDoc   
	 * @Description: Recive the InputStream from the Internet,and read the content.
	 * @param: @param inputStream
	 * @param: @return      
	 * @return: List<Practisequestion>
	 */
	public static List<Practisequestion> readDoc(InputStream inputStream,int pId) {
		List<Practisequestion> QList = new ArrayList<Practisequestion>();
		try {
				WordExtractor extractor = new WordExtractor(inputStream);
				String[] paragraphs = extractor.getParagraphText();
				int index = 0;
				String[] strArray = new String[7];
				for (String paragraph : paragraphs) {
					String txt = WordExtractor.stripFields(paragraph).replaceAll("\r|\n", "");
					if (txt.isEmpty()) {
						QList = packageQuestion(QList, strArray,pId);
						strArray = new String[7];
						index = 0;
					} else {
						strArray[index] = txt.trim();
						index++;
					}
				}
				QList = packageQuestion(QList, strArray,pId);
				extractor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		return QList;
	}

	public static List<Practisequestion> packageQuestion(List<Practisequestion> QList, String[] strArray,int pId) {
		Practisequestion practisequestion = new Practisequestion();
		practisequestion.setPid(pId);
		practisequestion.setQuestion(strArray[0]);
		practisequestion.setA(strArray[1]);
		practisequestion.setB(strArray[2]);
		practisequestion.setC(strArray[3]);
		practisequestion.setD(strArray[4]);
		practisequestion.setRightAnswer(strArray[5]);
		practisequestion.setqAnalyze(strArray[6]);
		QList.add(practisequestion);
		return QList;
	}
}