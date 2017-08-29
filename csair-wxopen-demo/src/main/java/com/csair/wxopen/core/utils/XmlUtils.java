package com.csair.wxopen.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * modify by ayu_784900 160623 sonar
 * 
 */
public class XmlUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);

	@SuppressWarnings("rawtypes")
	public static Map<String, Object> Dom2Map(Document doc) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (doc == null)
			return map;
		Element root = doc.getRootElement();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			List list = e.elements();
			if (list.size() > 0) {
				map.put(e.getName(), Dom2Map(e));
			} else
				map.put(e.getName(), e.getText());
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(root.getName(), map);
		return result;
	}

	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private static Object dealwithList(Map<String, Object> map) {
		// 专门处理List的逻辑
		if (map != null && map.size() == 1) {
			String key = map.keySet().iterator().next().toString();
			Object firstResult = map.get(key);
			if (firstResult instanceof List) {
				List array = new ArrayList();
				for (Object arrObj : (List) firstResult) {
					Map<String, Object> arrMap = new HashMap<String, Object>();
					arrMap.put(key, arrObj);
					array.add(arrMap);
				}
				return array;
			}
		}
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map Dom2Map(Element e) {
		Map map = new HashMap();
		List list = e.elements();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Element iter = (Element) list.get(i);
				List mapList = new ArrayList();

				if (iter.elements().size() > 0) {
					Map m = Dom2Map(iter);
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!(obj instanceof ArrayList)) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(m);
						}
						if (obj instanceof ArrayList) {
							mapList = (List) obj;
							mapList.add(m);
						}

						map.put(iter.getName(), mapList);
					} else {
						map.put(iter.getName(), m);
					}
				} else {
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (!(obj instanceof ArrayList)) {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(iter.getText());
						}
						if (obj instanceof ArrayList) {
							mapList = (List) obj;
							mapList.add(iter.getText());
						}
						map.put(iter.getName(), mapList);
					} else
						map.put(iter.getName(), iter.getText());
				}
			}
		} else
			map.put(e.getName(), e.getText());
		return map;
	}

	@SuppressWarnings("unchecked")
	public static String map2Dom(Map<String, Object> map) {
		if (map == null || map.size() < 1)
			return "";
		Document doc = DocumentHelper.createDocument();
		Element e = doc.addElement("wxjsReq");
		map = (Map<String, Object>) map.get("page");
		if (map != null) {
			map2xml(e, null, map);
		}

		return doc.asXML();
	}

	@SuppressWarnings("rawtypes")
	private static void list2xml(Element e, String key, List list) {
		Element newE = e.addElement(key);
		for (Object obj : list) {
			map2xml(newE, null, (Map) obj);
		}
	}

	private static void string2xml(Element e, String key, String str) {
		if (str == null || str.trim().equals("")) {
			e.addElement(key);
		} else {
			e.addElement(key).setText(str);
		}
	}

	@SuppressWarnings("rawtypes")
	private static void map2xml(Element e, String key, Map map) {
		Element newE = e;

		if (key != null && !key.trim().equals("")) {
			newE = e.addElement(key);
		}

		for (Object newKey : map.keySet()) {
			Object val = map.get(newKey);
			if (val == null) {
				string2xml(newE, newKey.toString(), null);

			} else if (val instanceof List) {
				list2xml(newE, newKey.toString(), (List) val);

			} else if (val instanceof Map) {
				map2xml(newE, newKey.toString(), (Map) val);

			} else {
				string2xml(newE, newKey.toString(), val.toString());
			}
		}
	}

	public static Object unmarshal(InputStream xml, Class<?> clazz) {
		Object obj = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller u = jc.createUnmarshaller();
			obj = u.unmarshal(xml);
		} catch (JAXBException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException("Can't unmarshal the XML file, error message: " + e.getMessage());
		}
		return obj;
	}

	public static Object unmarshal(Document doc, Class<?> clazz) {
		Object obj = null;
		try {
			InputStream in = new ByteArrayInputStream(doc.asXML().getBytes("UTF-8"));
			obj = unmarshal(in, clazz);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return obj;
	}

	public static Object unmarshal(String xmlString, Class<?> clazz) {
		Object obj = null;
		try {
			Document dom = DocumentHelper.parseText(xmlString);
			InputStream in = new ByteArrayInputStream(dom.asXML().getBytes("UTF-8"));
			obj = unmarshal(in, clazz);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (DocumentException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return obj;
	}

	public static String marshal(Object obj, Class<?> clazz) {
		String result = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Marshaller m = jc.createMarshaller();

			StringWriter writer = new StringWriter();
			m.marshal(obj, writer);

			result = writer.toString();
		} catch (JAXBException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException("Can't marshal the XML file, error message: " + e.getMessage());
		}
		return result;
	}

	/**
	 * 获取Node值
	 */
	public static String getNodeText(Document doc, String xpath) {
		Node node = doc.selectSingleNode(xpath);
		return node == null ? null : node.getText().trim();
	}

	/**
	 * 添加一个子节点
	 */
	public static Element addChild(Element parent, String name, String value) {
		//值为null， 不添加子节点
		if (StringUtils.isBlank(name) || value == null){
			return null;
		}
		Element child = parent.addElement(name);
		child.setText(value);
		return child;
	}
	
	public static String toUpperXML(String param,String[] patterns)
	{		
		for (String s : patterns) {
			param=param.replace(s, s.toUpperCase());
		}
		return param;
	}

}
