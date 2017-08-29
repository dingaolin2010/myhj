package com.csair.wxopen.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @Description: XML解析,将对象转化为XML或者将XML转化为对象
 * @author: zhulei
 * @CreateDate: 2012-10-06
 * 
 */
public final class XMLParser {
	private XMLParser() {}

	private static final Logger LOGGER = LoggerFactory.getLogger(XMLParser.class);

	/**
	 * @Description: 将XML转换为对象
	 * @author: zhulei
	 * @CreateDate:
	 * @parameters: InputStream xml, Class<?> clazz
	 * @Exception：
	 * @returns:
	 */
	public static Object unmarshal(InputStream xml, Class<?> clazz) {
		Object obj = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller u = jc.createUnmarshaller();
			obj = u.unmarshal(xml);
		} catch (JAXBException e) {
			LOGGER.error("cannot unmarshal xml", e);
		}
		return obj;
	}

	/**
	 * @Description: 将对象转换为XML
	 * @author: zhulei
	 * @CreateDate:
	 * @parameters: Object obj, Class<?> clazz
	 * @Exception：
	 * @returns:
	 */
	public static String marshal(Object obj, Class<?> clazz) {
		String result = null;
		try { 
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Marshaller m = jc.createMarshaller();
			StringWriter writer = new StringWriter();
			m.marshal(obj, writer);
			result = writer.toString();
		} catch (JAXBException e) {
			LOGGER.error("cannot unmarshal xml", e);
		}
		return result;
	}

	/**
	 * @param path
	 *            xml path
	 * @param item
	 *            XML Object
	 * @param returnType
	 * @return
	 * @throws javax.xml.xpath.XPathExpressionException
	 * @throws javax.xml.parsers.ParserConfigurationException
	 * @throws java.io.IOException
	 * @throws org.xml.sax.SAXException
	 */
	public static Object xPathEvaluate(String path, String item, QName returnType) throws XPathExpressionException {
		org.w3c.dom.Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(item)));
		} catch (SAXException e) {
			LOGGER.error("sax error", e);
		} catch (IOException e){
			LOGGER.error("io error", e);
		} catch (ParserConfigurationException e) {
			LOGGER.error("cannot parse xml", e);
		}
		XPath xPath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xPath.compile(path);
		return expr.evaluate(doc, returnType);
	}
}
