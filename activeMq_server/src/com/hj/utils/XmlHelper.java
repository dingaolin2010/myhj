package com.hj.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.hj.entity.Head;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-06-07.
 */
public class XmlHelper {
    private DocumentBuilderFactory builderFactory;

    private DocumentBuilderFactory getBuilderFactory() {
        if (builderFactory == null) {
            builderFactory = DocumentBuilderFactory.newInstance();
        }
        return builderFactory;
    }

    public enum ResultType {
        ARRAY, //单行
        ARRAYLIST, //多行
        CLASS,
        CLASSLIST,
        COLUMN,
        KEYED,
        MAP, //单行map
        MAPLIST //多行map
    }

    /**
     * 执行sql并转换为xml
     *
     * @param selectSql
     * @param resultType
     * @param resultName
     * @param sender
     * @param receiver
     * @param func
     * @return
     */
    public String getSelectResultXml(String selectSql, ResultType resultType, String resultName,
                                     String sender, String receiver, String func) {
        DBUtilsHelper helper = new DBUtilsHelper();
        QueryRunner runner = helper.getRunner();
        switch (resultType) {
            case MAP:
                return getMapSelectResult(runner, selectSql, resultName, sender, receiver, func);
            case MAPLIST:
                return getMapListSelectResult(runner, selectSql, resultName, sender, receiver, func);
        }
        return null;
    }

    /**
     * 多行查询结果转换为xml
     *
     * @param mapList
     * @param className
     * @param sender
     * @param receiver
     * @param funcName
     * @return
     */
    public String getMapListXml(List<Map<String, Object>> mapList, String className,String sender, String receiver, String funcName, boolean isSuccess) {
        return getMapListXml(mapList, className, sender, receiver, funcName, isSuccess, true);
    }

    
    public String getMapListXml(List<Map<String, Object>> mapList, String className,
                                String sender, String receiver, String funcName,
                                boolean isSuccess, boolean isAllLowerCase) {
        try {
            DocumentBuilder docBuilder = getBuilderFactory().newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            doc.setXmlStandalone(true);
            Element root = getRootElement(doc, sender, receiver, funcName, isSuccess);
            doc.appendChild(root);

            if (mapList != null) {
                for (int i = 0; i < mapList.size(); i++) {
                    Map<String, Object> map = mapList.get(i);
                    Element resultNode = doc.createElement(className);
                    Iterator<String> iter_mapResult = map.keySet().iterator();
                    
                    while (iter_mapResult.hasNext()) {
                        String key = iter_mapResult.next();
                        Object valueObj = map.get(key);
                        
                        if (isAllLowerCase) {
                            key = key.toLowerCase();
                        }
                        
                        String value = "";
                        if (valueObj != null) {
                            value = valueObj.toString();
                        }else{
                           	value = " ";
                        }
                        Element valueNode = doc.createElement(key);
                        valueNode.setTextContent(value);
                        resultNode.appendChild(valueNode);
                    }
                    root.appendChild(resultNode);
                }
            }

            return documentToXmlString(doc);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 单行查询结果转换为xml
     *
     * @param map
     * @param className
     * @param sender
     * @param receiver
     * @param funcName
     * @return
     */
    public String getMapXml(Map<String, Object> map, String className,
                            String sender, String receiver, String funcName, boolean isSuccess) {
        return getMapXml(map, className, sender, receiver, funcName, isSuccess, true);
    }

    public String getMapXml(Map<String, Object> map, String className,
                            String sender, String receiver, String funcName, boolean isSuccess, boolean isAllLowerCase) {
        try {
            DocumentBuilder docBuilder = getBuilderFactory().newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            doc.setXmlStandalone(true);
            Element root = getRootElement(doc, sender, receiver, funcName, isSuccess);
            doc.appendChild(root);

            Element resultNode = doc.createElement(className);
            root.appendChild(resultNode);

            Iterator<String> iter_mapResult = map.keySet().iterator();
            while (iter_mapResult.hasNext()) {
                String key = iter_mapResult.next();
                if (isAllLowerCase) {
                    key = key.toLowerCase();
                }
                String value = map.get(key).toString();
                Element valueNode = doc.createElement(key);
                valueNode.setTextContent(value);
                resultNode.appendChild(valueNode);
            }
            return documentToXmlString(doc);
        } catch (Exception ex) {
            return null;
        }
    }

    private String getMapListSelectResult(QueryRunner runner, String sql, String className,
                                          String sender, String receiver, String funcName) {
        try {
            List<Map<String, Object>> mapListResult = runner.query(sql, new MapListHandler());
            return getMapListXml(mapListResult, className, sender, receiver, funcName, true);
        } catch (Exception ex) {
            return getMapListXml(null, className, sender, receiver, funcName, false);
        }
    }

    private String getMapSelectResult(QueryRunner runner, String sql, String className,
                                      String sender, String receiver, String funcName) {
        try {
            Map<String, Object> mapResult = runner.query(sql, new MapHandler());
            return getMapXml(mapResult, className, sender, receiver, funcName, true);
        } catch (Exception ex) {
            return getMapXml(null, className, sender, receiver, funcName, false);
        }
    }

    private Element getRootElement(Document doc, String sender, String receiver, String funcName, boolean isSuccess, String name) {
        Date dt = new Date();
        Element root = doc.createElement(name); //根节点-data
        if (isSuccess) {
            root.setAttribute("status", "success");
        } else {
            root.setAttribute("status", "failure");
        }
        root.setAttribute("sender", sender);
        root.setAttribute("receiver", receiver);
        root.setAttribute("functionName", funcName);
        root.setAttribute("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dt));
        return root;
    }
 
    
    private Element getRootElement(Document doc, String sender, String receiver, String funcName, boolean isSuccess) {
        return getRootElement(doc, sender, receiver, funcName, isSuccess, "data");
    }

    private String documentToXmlString(Document doc) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(doc);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            transformer.transform(domSource, new StreamResult(baos));
            return baos.toString();
        } catch (Exception ex) {
            return "";
        }
    }

    public final static String DATA_CIRCUIT = "circuit"; //业务电路，光路承载电路
    public final static String DATA_OPERATION  = "operation "; //业务方式单
    public final static String DATA_EQUIP = "equip"; //通信设备
    public final static String DATA_BUSINESS = "business"; //设备承载业务，业务关联业务
    public final static String DATA_CARD = "card"; //设备板卡
    public final static String DATA_LOGIC_PORT = "logic_port"; //逻辑端口
    public final static String DATA_TRANSROUTE = "transroute"; //传输段
    public final static String DATA_TIME_SLOT = "time_slot"; //时隙
    public final static String DATA_CROSS = "cross"; //交叉
    public final static String DATA_OCABLE = "ocable"; //光缆
    public final static String DATA_OCABLE_ROUTE  = "ocable_route "; //光缆承载光路
    public final static String DATA_FIBER = "fiber"; //光纤
    public final static String DATA_CONNECT_BOX = "connect_box"; //接头盒


    /**
     * 解析头部
     * @param xml
     */
    public Map<String,String> parseXmlHead(String strXml){
    	
    	Map<String,String> map = new HashMap<String,String>();
    	
    	try {
			
			//System.out.println(strXml);

			StringReader sr = new StringReader(strXml);
			InputSource is = new InputSource(sr);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
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
			
			
		//	System.out.println("------------子节点的信息----");
		
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
						map.put(childNodesChildName, childNodesChildValue);
						
						//System.out.println(childNodesChildName + "=" + childNodesChildValue);
					}
				
					
					String childNodesName = childNodesChids.getNodeName();
					String childNodesValue = childNodesChids.getTextContent();
				
					if(childNodesName==null || childNodesValue==null || "#text".equals(childNodesName) ){
						continue;
					}
					map.put(childNodesName, childNodesValue);
				//	System.out.println(childNodesName + "---------->"+ childNodesValue);
				}
			}
			
			System.out.println("map="+map.toString());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
    	return map;
    }



    /**
     * 动态构建头部信息
     */
/*    private Element getRootElement(Document doc,Head head) {
    	Element root = doc.createElement(head.getHeadName()); //根节点-data
    	root.setAttribute("status", head.getStatus());
    	root.setAttribute("sender", head.getSender());
    	root.setAttribute("receiver", head.getReceiver());
    	root.setAttribute("functionName", head.getFunctionName());
    	root.setAttribute("time", head.getTime());
        return root;
    }*/
    
    
    
    /**
     * 组装返回报文
     * @param mapList  返回报文体信息
     * @param reMap    头部信息
     * @param className 报文体集合名称
     * @return
     */
    public String getMapListXml(Head head) {
        try {
            DocumentBuilder docBuilder = getBuilderFactory().newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            doc.setXmlStandalone(true);
           
        	Element root = doc.createElement(head.getHeadName()); //根节点-data
        	
        	root.setAttribute("status", head.getStatus());
        	root.setAttribute("sender", head.getSender());
        	root.setAttribute("receiver", head.getReceiver());
        	root.setAttribute("functionName", head.getFunctionName());
        	root.setAttribute("time", head.getTime());
            
        	if(head.getTotal() !=null){
        		root.setAttribute("total",head.getTotal());	
        	}
        	if(head.getBreakStr() !=null){
        		root.setAttribute("break",head.getBreakStr());	
        	}
        	
            doc.appendChild(root);
            
            List mapList = head.getBodyList();
            
            if (mapList != null) {
            	
                for (int i = 0; i < mapList.size(); i++) {
                	
                    Map<String, Object> map = (Map<String, Object>) mapList.get(i);
                    
                    Element resultNode = doc.createElement(head.getItemsName());
                    
                    Iterator<String> iter_mapResult = map.keySet().iterator();
                    
                    while (iter_mapResult.hasNext()) {
                        
                    	String key = iter_mapResult.next();
                        
                        Object valueObj = map.get(key);
                          key = key.toLowerCase();
                        String value = "";
                        if (valueObj != null) {
                            value = valueObj.toString();
                        }else{
                           	value = " ";
                        }
                        Element valueNode = doc.createElement(key);
                        valueNode.setTextContent(value);
                        resultNode.appendChild(valueNode);
                    }
                    root.appendChild(resultNode);
                }
            }
            return documentToXmlString(doc);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    
    /**
     * 组装返回报文
     * @param mapList  返回报文体信息
     * @param reMap    头部信息
     * @param className 报文体集合名称
     * @return
     */
    public String getGetTreeXml(Head head) {
        try {
            DocumentBuilder docBuilder = getBuilderFactory().newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            doc.setXmlStandalone(true);
           
        	Element root = doc.createElement(head.getHeadName()); 	//根节点-data
        	
        	root.setAttribute("status", head.getStatus());
        	root.setAttribute("sender", head.getSender());
        	root.setAttribute("receiver", head.getReceiver());
        	root.setAttribute("functionName", head.getFunctionName());
        	root.setAttribute("time", head.getTime());
            
            doc.appendChild(root);
            
            List mapList = head.getBodyList();
            
            if (mapList != null) {
            	
                for (int i = 0; i < mapList.size(); i++) {
                	
                    Map<String, String> map = (Map<String, String>) mapList.get(i);
                    
                   // Element resultNode = doc.createElement(head.getItemsName());
                    
                    Element valueNode = doc.createElement("fir_node");
                    valueNode.setAttribute("value", map.get("SYSRESID"));
                    valueNode.setAttribute("text",map.get("STATUSNAME"));
                    valueNode.setAttribute("level", "1");
                    valueNode.setTextContent(" ");
                    root.appendChild(valueNode);
                    
                   /* while (iter_mapResult.hasNext()) {
                        
                    	String key = iter_mapResult.next();
                        
                        Object valueObj = map.get(key);
                          key = key.toLowerCase();
                        String value = "";
                        if (valueObj != null) {
                            value = valueObj.toString();
                        }else{
                           	value = " ";
                        }
                  
                       // resultNode.appendChild(valueNode);
                    }*/
                    
                    //root.appendChild(resultNode);
                }
            }
            return documentToXmlString(doc);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}

