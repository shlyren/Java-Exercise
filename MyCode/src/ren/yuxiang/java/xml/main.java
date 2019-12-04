package ren.yuxiang.java.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	private static String pathString = "/Users/yuxiang/Files/LearnJAVA/Practise Code/MyCode/data.xml";
	/**
	 * 获取内容 并更新
	 * @throws Exception
	 */
	private static void getXML() throws Exception {
		
		File file = new File(pathString);
		
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(file);

		// 获取根元素
		Element rootElement = document.getDocumentElement();
		// 获取第一个联系人元素
		Element element = (Element)rootElement.getElementsByTagName("linkman").item(0);
		// 获取mail元素
		Element mailElement = (Element)element.getElementsByTagName("name").item(0);
		
		// 重新设置mail内容
		mailElement.setTextContent("me@yuxaing.ren");
		
		// 将修改后的内容写到xml文件
		Source source = new DOMSource(document);
		StreamResult result = new StreamResult(file);
		TransformerFactory.newInstance().newTransformer().transform(source, result);
		
		
		System.out.println(mailElement.getTextContent());
		
	}
	
	
	/**
	 * 新增
	 * @throws Exception
	 */
	private static void createLinkman() throws Exception {
		File file = new File(pathString);
		
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(file);

		// 获取根元素
		Element rootElement = document.getDocumentElement();
		
		Element linmanElement = document.createElement("linkman");
		linmanElement.setAttribute("id", "3");
		
		Element namElement = document.createElement("name");
		namElement.setTextContent("联系人");
		linmanElement.appendChild(namElement);
		
		Element nickElement = document.createElement("nick");
		nickElement.setTextContent("new person");
		linmanElement.appendChild(nickElement);
		
		Element ageElement = document.createElement("age");
		ageElement.setTextContent("20");
		linmanElement.appendChild(ageElement);
		
		Element heightElement = document.createElement("height");
		heightElement.setTextContent("1.76");
		linmanElement.appendChild(heightElement);
		
		Element mailElement = document.createElement("mail");
		mailElement.setTextContent("shlyren@163.com");
		linmanElement.appendChild(mailElement);
		
		rootElement.appendChild(linmanElement);
		
		// 将修改后的内容写到xml文件
		Source source = new DOMSource(document);
		StreamResult result = new StreamResult(file);
		TransformerFactory.newInstance().newTransformer().transform(source, result);
	}
	
	/**
	 * 删除
	 * @throws Exception
	 */
	private static void delete() throws Exception {
		File file = new File(pathString);
		
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(file);

		// 获取根元素
		Element rootElement = document.getDocumentElement();
		NodeList list = rootElement.getElementsByTagName("linkman");
		
		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element)list.item(i);
			String idString = element.getAttribute("id");
			if ("3".equals(idString)) {
				rootElement.removeChild(element);
				break;
			}
		}
		
		// 将修改后的内容写到xml文件
		Source source = new DOMSource(document);
		StreamResult result = new StreamResult(file);
		TransformerFactory.newInstance().newTransformer().transform(source, result);
		
	}
	
}
