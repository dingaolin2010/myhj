package com.hj.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sun.xml.internal.ws.util.xml.XmlUtil;
import com.sun.xml.internal.ws.wsdl.writer.document.Types;

public class TestParseXml {
	// "<?xml version=\"1.0\" encoding=\"gb2312\"?>";
	public static void main(String[] args) {
		/*
		 * String strXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
		 * "<data sender=\"0100\" receiver=\"01040102\" functionName=\"checkoutUser\"  time=\"2014-09-12 14:56:55\">"
		 * + "	<condition>	   " + "			<user_id>admin</user_id>" +
		 * "			<user_pwd>admin</user_pwd>" + "			<user_dept>佛山供电局</user_dept > "
		 * + "</condition>" + "	<condition>	   " + "			<user_id>3333</user_id>"
		 * + "			<user_pwd>4444</user_pwd>" +
		 * "			<user_dept>5555局</user_dept > " + "</condition>" + "</data>";
		 */
		String strXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><data sender=\"0100\" receiver=\"01040102\" functionName=\"checkoutUser\"  time=\"2014-09-12 14:56:55\"><condition><user><user_id>admin</user_id></user><user_pwd>adminpwd</user_pwd><user_dept>佛山供电局</user_dept></condition></data>";
		
		try {
			Map<String,String> map = new HashMap<String,String>();
			
			System.out.println(strXml);

			StringReader sr = new StringReader(strXml);
			InputSource is = new InputSource(sr);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			
		 	File file = new File("D:\\xml\\getAllStations.xml");
			
			Document document = builder.parse(is);
			
			

			NodeList headAttrList = document.getElementsByTagName("data");

			/**
			 * 解析头部信息 因为头部信息是固定了，可以写死
			 */
			for (int i = 0; i < headAttrList.getLength(); i++) {
				Element el = (Element) headAttrList.item(i);
				map.put("sender", el.getAttribute("sender"));
				map.put("receiver", el.getAttribute("receiver"));
				map.put("functionName", el.getAttribute("functionName"));
				map.put("time", el.getAttribute("time"));
			}
			
			
			//System.out.println("------------子节点的信息----");
		
			// 获取xml中的所有condition 节点集合
			NodeList conditionNodeList = document.getElementsByTagName("condition");
			// 循环请求参数 conditioni节点
			for (int j = 0; j < conditionNodeList.getLength(); j++) {

				NodeList conditionChildNodes = conditionNodeList.item(j).getChildNodes();

				// 循环解析每一个condition 中的节点
				for (int k = 0; k < conditionChildNodes.getLength(); k++) {

					// condition节点中的每一个节点
					Node childNodesChids = conditionChildNodes.item(k);

					// condition节点中的节点的集合
					NodeList childNodesChidsList = childNodesChids.getChildNodes();

					//System.out.println("childNodesAttr="+ childNodesChidsList.getLength());
					
					int childListCount = childNodesChidsList.getLength();  //获取子节点中集合数量
					
					//if (childListCount > 1) {
					// 循环解析每个节点中的子节点
					for (int n = 0; n < childListCount; n++) {
						String childNodesChildName = childNodesChidsList.item(n).getNodeName();
						if ("#text".equals(childNodesChildName) || childNodesChildName == null) {
							continue;
						}

						String childNodesChildValue = childNodesChidsList.item(n).getTextContent();
						map.put(childNodesChildName, childNodesChildValue.trim());
						
						//System.out.println(childNodesChildName + "=" + childNodesChildValue);
					}
				
					
					String childNodesName = childNodesChids.getNodeName();
					String childNodesValue = childNodesChids.getTextContent();
				
					if(childNodesName==null || childNodesValue==null || "#text".equals(childNodesName) ){
						continue;
					}
					map.put(childNodesName.trim(), childNodesValue.trim());
					//System.out.println(childNodesName + "---------->"+ childNodesValue);
				}
			}
			
			System.out.println("map="+map.toString());

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		// Map map = readStringXmlOut(strXml);
		// System.out.println(map.toString());
	}
}
