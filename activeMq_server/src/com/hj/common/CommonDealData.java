package com.hj.common;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csg.activemq.model.FileDownloadModel;
import com.hj.dao.IDao;
import com.hj.dao.imp.IDaoImp;
import com.hj.entity.Head;
import com.hj.entity.Image;
import com.hj.utils.DealStr;
import com.hj.utils.XmlHelper;

/**
 * 公共处理类
 * 
 * @author lenovo
 *
 */
public class CommonDealData {

	private static XmlHelper xmlHelper = new XmlHelper();
	private static IDao iU = new IDaoImp();
	private static Log log = LogFactory.getLog(CommonDealData.class);

	/**
	 * 公共处理方法， Head对象封装了 头部参数
	 * 
	 * @return
	 */
	public static String commonRequestDeal(Head head) {

		/**
		 * 解析请求的xml字符串参数 转换成map对象
		 */
		Map<String, String> map = xmlHelper.parseXmlHead(head.getRequestStr());
		log.info("--commonUserInfoDeal--->转换map：" + map.toString());

		head = cheangeSqlid(head, map);

		// 把请求的 sender、receiver 封装在hea对象中
		head.setSender(map.get("sender"));
		head.setReceiver(map.get("receiver"));

		/**
		 * 根据请求读取数据库
		 */
		List list = iU.getList(head.getSqlId(), map);
		log.info("查询数据库-->result-->" + list);
		
		boolean flag  = true;
		if(list == null){
			flag  = false;
		}

		/**
		 * 判断判断是不是需要返回报文体
		 */
		if (head.getItemsName() == null) {
			list = null;
		}

		String resultXml = xmlHelper.getMapListXml(list, head.getItemsName(),
				head.getReceiver(), head.getSender(), head.getFunctionName(),
				flag);
		resultXml = resultXml.replace("> <", "><");
		return resultXml;
	}

	/**
	 * 公共处理方法，修改操作 Head对象封装了 头部参数
	 * 
	 * @return
	 */
	public static String commonRequesUpdate(Head head) {
		/**
		 * 解析请求的xml字符串参数 转换成map对象
		 */
		Map<String, String> map = xmlHelper.parseXmlHead(head.getRequestStr());
		log.info("--commonRequesUpdate--->转换map：" + map.toString());

		head = cheangeSqlid(head, map);
		
		// 把请求的 sender、receiver 封装在hea对象中
		head.setSender(map.get("sender"));
		head.setReceiver(map.get("receiver"));

		/**
		 * 根据请求修改操作
		 */
		int temp = iU.updateObj(head.getSqlId(), map);

		log.info("-- 修改结果-->" + temp);
		
		boolean flag = temp >= 0 ? true : false;

		/**
		 * 修改操作 只返回报文头部， 无需报文
		 */
		String resultXml = xmlHelper.getMapListXml(null, head.getItemsName(),
				head.getReceiver(), head.getSender(), head.getFunctionName(),
				flag);
		resultXml = resultXml.replace("> <", "><");
		return resultXml;
	}

	/**
	 * 插入方法
	 * 
	 * @param insertSqlId
	 * @param list
	 * @return
	 */
	public static int commonRequestInsert(String insertSqlId, List<Map> list) {
		int re = iU.insert(insertSqlId, list);
		return re;
	}

	/**
	 * 调用存储过程
	 * 
	 * @param proId
	 *            sqlID
	 * @param map
	 *            参数
	 */
	public static void commonExecutePrd(String sqlId, Map map) {
		iU.exePrd(sqlId, map);
	}

	/**
	 * 改变sqlId 有些接口与方法名称一致，通过请求参数 区分调用不同的SqlId
	 * 
	 * @param head
	 * @map 请求的参数
	 */
	public static Head cheangeSqlid(Head head, Map map) {

		/**
		 *  6.1.1.2 站点内机房查询 无   
		 *  6.1.1.2 站点内机房查询
		 *  返回接口参数都是一致，改成同一个sqlid
		 * SqlId:getAllRoomsSqlIdStations 
		 * SqlId:getAllRoomsSqlIdRooms
		 */
		if ("getAllRooms".equals(head.getFunctionName())) {
			
			//if (map.containsKey("room_id") ||  map.containsKey("room_name")) {
				// 6.1.2.1 机房查询
				head.setSqlId("getAllRoomsSqlIdInRMSRooms");
			//} else {
				// 6.1.1.2 站点内机房查询
			//	head.setSqlId("getAllRoomsSqlIdInRMStations");
			//}

		} else if ("getAllOcables".equals(head.getFunctionName())) {
			/**
			 * getAllOcables 6.1.1.3 站点内通信光缆查询 ->getAllOcablesSqlIdInRMStations
			 * 6.1.7.1 光缆查询：请求参数
			 * 返回数据一致，6.1.7.1 光缆查询
			 */
		//	if (map.containsKey("station_id")) {
				// 6.1.1.3 站点内通信光缆查询
			//	head.setSqlId("getAllOcablesSqlIdInRMStations");
			//} else {
				// 6.1.7.1 光缆查询
				head.setSqlId("getAllOcablesSqlIdInRMOcable");
			//}

		} else if ("getAllRacks".equals(head.getFunctionName())) {
			
			
			/**
			 * 1：6.1.2.2 机房内机柜查询-获取通信机柜列表和机柜详情的方法 
			 * 6.1.3.1 机柜查询 有 
			 * 两个查询的返回报文 一致，机房查询多一个 二维码标签 不影响
			 */

		//	if (map.containsKey("rack_name") && map.containsKey("rack_id")) {
				// 6.1.3.1 机柜查询 有 rack_name、rack_id
				head.setSqlId("getAllRacksSqlIdInRMSRack");
			//} else {
				// 1：6.1.2.2 机房内机柜查询-获取通信机柜列表和机柜详情的方法
				//head.setSqlId("getAllRacksSqlIdInRMSRooms");
			//}

		} else if ("getAllCards".equals(head.getFunctionName())) {
			if (map.containsKey("shelf_id") || map.containsKey("shelf_name")) {
				// 6.1.3.4 子框内板卡查询 包含 shelf_id、shelf_name
				head.setSqlId("getAllCardsSqlIdInRack");
			} else {
				// 6.1.5.3 设备上板卡查询
				head.setSqlId("getAllCardsSqlIdInEq");
			}
		
		}else if("changeOcableInfo".equals(head.getFunctionName())){
			
			if (map.containsKey("direction") && map.containsKey("direction")) {
				//  6.1.7.5	光缆行走向经纬度修改  ->direction
				head.setSqlId("changeOcablelatitudeSqlId");
			} else {
				//6.1.7.4	光缆信息修改
				head.setSqlId("changeOcableInfoSqlId");
			}
		}
		

		return head;
	}


	/**
	 * 分析管理 特殊处理 6.1.9.1 N-1分析查询 6.1.9.2 N-X分析查询
	 * @param head
	 * @param n  N—_1\N_X 
	 * @return
	 */
	public static String getAnalysis(Head head,String n) {

		String uuid = DealStr.getUUId(); 			// 获取sessonid
		Map<String, String> map = xmlHelper.parseXmlHead(head.getRequestStr());
		log.info("--getAnalysis--->转换map：" + map.toString());
		
		head.setHeadName("data");
		head.setSender(map.get("sender"));
		head.setReceiver(map.get("receiver"));
		head.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		head.setStatus("failure");
		head.setBodyList(null);
		head.setTotal("0");
		head.setBreakStr("0");

		/**
		 * 封装需要分析的sessionid、资源、实例
		 */
		List<Map> tList = new ArrayList<Map>();
		
		//N-1 与 N-M  传日参数不一样，需要判断处理
		String equip_id = null;
		String card_id = null;;
		String port_id = null;;
		String ocable_id = null;;
		String trans_id = null;;
		
		if("N_1".equals(n)){
			equip_id = map.get("equip_id");
			card_id = map.get("card_id");
			port_id = map.get("port_id");
			ocable_id = map.get("ocable_id");
			trans_id = map.get("trans_id");
		}else{
			equip_id = map.get("equip_ids");
			card_id = map.get("card_ids");
			port_id = map.get("port_ids");
			ocable_id = map.get("ocable_ids");
			trans_id = map.get("trans_ids");
		}
		
		if (equip_id != null && equip_id != "") {
			
			String[] arry_equip = equip_id.split(";");  
			
			for (int i = 0; i < arry_equip.length; i++) {
				if(arry_equip[i] !=null){
					Map<String, String> insertMap_equip_id = new HashMap<String, String>();
					// 设备id getAnalysisEquipSqlId --> TR_SDH
					insertMap_equip_id.put("SESSIONID", uuid);
					insertMap_equip_id.put("RESOURCEID", "TR_SDH");
					insertMap_equip_id.put("INSID", arry_equip[i]);
					tList.add(insertMap_equip_id);
				}
			}
		}
	
		if (card_id != null && card_id != "") {
			String[] arry_card = card_id.split(";"); 
			for (int i = 0; i < arry_card.length; i++) {
				if(arry_card[i] !=null){
					Map<String, String> insertMap_card_id = new HashMap<String, String>();
					// 板卡id getAnalysisCardSqlId -->TR_DEVBOARD
					insertMap_card_id.put("SESSIONID", uuid);
					insertMap_card_id.put("RESOURCEID", "TR_DEVBOARD");
					insertMap_card_id.put("INSID", arry_card[i]);
					tList.add(insertMap_card_id);
				}
			//insertMap_card_id = null;
			}
		}
		
		
		if (port_id != null && port_id != "") {
			String[] arry_port = port_id.split(";");
			for (int i = 0; i < arry_port.length; i++) {
				if(arry_port[i] !=null){
					Map<String, String> insertMap_port_id = new HashMap<String, String>();
					// 端口ID getAnalysisPortSqlId -->TR_DEVPORT
					insertMap_port_id.put("SESSIONID", uuid);
					insertMap_port_id.put("RESOURCEID", "TR_DEVPORT");
					insertMap_port_id.put("INSID", arry_port[i]);
					tList.add(insertMap_port_id);
					//insertMap_port_id = null;
				}
			}
		}

		
		
		
		if (ocable_id != null && ocable_id != "") {
			String[] arry_ocable = ocable_id.split(";");
			for (int i = 0; i < arry_ocable.length; i++) {
				
				if(arry_ocable[i] !=null){
					Map<String, String> insertMap_ocable_id = new HashMap<String, String>();
					// ocable_id 光缆ID getAnalysisOcableSqlId --> OPTSEG
					insertMap_ocable_id.put("SESSIONID", uuid);
					insertMap_ocable_id.put("RESOURCEID", "OPTSEG");
					insertMap_ocable_id.put("INSID", arry_ocable[i]);
					tList.add(insertMap_ocable_id);
					//insertMap_ocable_id = null;
				}
			}
		}

	
		if (trans_id != null && trans_id != "") {
			String[] arry_trans = trans_id.split(";");
			for (int i = 0; i < arry_trans.length; i++) {
				
				if(arry_trans[i] !=null){
					Map<String, String> insertMap_trans_id = new HashMap<String, String>();
					// trans_id 传输段ID getAnalysisTransSqlId -->TR_PORTLINK
					insertMap_trans_id.put("SESSIONID", uuid);
					insertMap_trans_id.put("RESOURCEID", "TR_PORTLINK");
					insertMap_trans_id.put("INSID", arry_trans[i]);
					tList.add(insertMap_trans_id);
					//insertMap_trans_id = null;
				}
			}
		}

		
		log.info("--->获取请求参数中需要获取的实例信息：" + tList);

		if (tList != null || tList.size() > 0) {
			/**
			 * 步骤1：把资源实例插入表 Base_Select_List
			 */
			int resultCount = commonRequestInsert("insert_base_list", tList); // 步骤2：插入表 	// {session、资源id、实例id}
			log.info("插入结果表数量：" + resultCount);
			tList = null;

			if (resultCount > 0) {
				
				/**
				 * 步骤二：调用存储过程
				 */
				Map<String, String> proMap = new HashMap<String, String>();
				proMap.put("sessionid", "<P_SESSIONID>" + uuid+ "</P_SESSIONID><P_TYPE>0</P_TYPE>");
				proMap.put("result", "1");

				log.info("调用存储过程完成，传入参数：" + proMap.toString());

				commonExecutePrd("RMRiskPro", proMap);
				log.info("调用存储过程完成，返回结果：" + proMap.get("result"));
				proMap = null;

				/**
				 * 步骤三查询 数据
				 */
				map.put("sessionid", uuid);

				// 根据请求读取数据库
				List list = iU.getList(head.getSqlId(), map);
				log.info("查询N-1电路分析结果--->" + list);
				
				if(list == null){
					head.setStatus("failure");
				}else{
					head.setStatus("success");
					if(list.size() >0){
						/**
						 * 判断判断是不是需要返回报文体
						 */
						if (head.getItemsName() == null) {
							list = null;
						}
						head.setBodyList(list);
						Map<String,String> rowMap = new HashMap<String,String>();
						rowMap.put("sessionid", uuid);
						List rowList = iU.getList("getAnalysisRowsSqlId", rowMap);
						log.info("查询总的影响数量和中断的数量："+rowList);
						
						if(rowList !=null && rowList.size() >0){
							// 受影响总数 、其中中断总数 暂时未获取
							Map row = (Map) rowList.get(0);
							head.setTotal(row.get("TOTAL").toString());
							head.setBreakStr(row.get("BREAKTOTAL").toString());
						}
					}
				}
			}
		}
		
		String resultXml = xmlHelper.getMapListXml(head);
		resultXml = resultXml.replace("> <", "><");
		head = null;
		return resultXml;
	}

	
	/**
	 * 获取图片对象
	 * @param head
	 * @return
	 */
	public static FileDownloadModel commonImageDeal(Head head){
	
		FileDownloadModel fdms = new  FileDownloadModel();
		//解析请求的xml字符串参数 转换成map对象
		Map<String, String> map = xmlHelper.parseXmlHead(head.getRequestStr());
		log.info("--commonUserInfoDeal--->转换map：" + map.toString());

		Image  image = (Image) iU.getObject(head.getSqlId(), map); 	//根据请求读取数据库
		if(image == null){
			return null;
		}
		
		byte[] buffer = image.getPhoto();  
		//字节数据转换成字节流
        InputStream input = new ByteArrayInputStream(buffer); 
         
        fdms.setFilename(image.getDocname());   //名称
        fdms.setFilesize(image.getBlobsize());	//大小
        fdms.setIn(input);						//输入流
        fdms.setFileid(image.getFileid());    
        
	    //本地测试 已经成功生成图片
      /* byte[] data = new byte[1024];
       int len = 0;
        FileOutputStream fos = null;
        try {
        	fos = new FileOutputStream("c:\\download.png");
        	while((len= input.read(data)) != -1){
        		fos.write(data,0,len);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fos !=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
       
       if(input !=null){
    	   try {
    			input.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
       }*/
	   return fdms;
	}

	
	/**
	 * 用户验证
	 * @param head
	 * @return
	 */
	public static String checkuser(Head head){
		
		//解析请求的xml字符串参数 转换成map对象
		Map<String, String> map = xmlHelper.parseXmlHead(head.getRequestStr());
		log.info("--commonUserInfoDeal--->转换map：" + map.toString());
		
		head.setSender(map.get("sender"));
		head.setReceiver(map.get("receiver"));
		
		if(map.containsKey("user_pwd")){
			String pwd = map.get("user_pwd");
			map.remove("user_pwd");
			map.put("user_pwd", DealStr.encode(pwd));
		}
		
		List list = iU.getList(head.getSqlId(), map);
		log.info("查询数据库-->result-->" + list);
		
		boolean flag  = true;
		if(list == null){
			flag  = false;
		}

		/**
		 * 判断判断是不是需要返回报文体
		 */
		if (head.getItemsName() == null) {
			list = null;
		}

		String resultXml = xmlHelper.getMapListXml(list, head.getItemsName(),head.getReceiver(), head.getSender(), head.getFunctionName(),flag);
		resultXml = resultXml.replace("> <", "><");
		return resultXml;
	}

	
	/**
	 *树状节点选择属性 处理
	 * @param head
	 * @return
	 */
	public static String getTreeXmlPrase(Head head){
		Map<String, String> map = xmlHelper.parseXmlHead(head.getRequestStr());
	
		log.info("--lebelManager--->转换map：" + map.toString());
		head.setHeadName("data");
		head.setSender(map.get("sender"));
		head.setReceiver(map.get("receiver"));
		head.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		head.setStatus("failure");
		
		head.setBodyList(null);
		
		String from = map.get("from"); 	//树状选择控件from值按照方法名+下划线+属性数据标示。如（changeRoomInfo_disp_dept）,资源管理树状选择都采用此规范命名。
		
		//调管单位查询
		if(from.indexOf("disp_dept") >=0){
			map.put("SGROUPID", "MANATYPE");
		}else if(from.indexOf("maintain_dept") >=0){
			map.put("SGROUPID", "MAINTAIN");
		}
		String select = map.get("select");	//关联项值上级关联项选中值属性编码，
		log.info("select-->"+select);
		/*  无则为空。
			如果是多级关联，值用;号按级联顺序隔开，例如101;102.
			101z表示同一组第一级下拉框选择值。
			102表示同一组第二级下拉框选择值。
			以此类推，多少级就有多少个值。
		 */
		List list= iU.getList(head.getSqlId(), map);
		 
		if(list != null){
			head.setStatus("success");
			if(list.size() > 0 ){
				head.setBodyList(list);
			}
		}
		
		String resultXml = xmlHelper.getGetTreeXml(head);
		resultXml = resultXml.replace("> <", "><");
		return resultXml;
	}



	/**
	 * 6.1.10.2	配线端子信息修改
	 * 特殊处理，修改多表
	 * @return
	 */
	public static String commonRequesUpdates(Head head) {
		/**
		 * 解析请求的xml字符串参数 转换成map对象
		 */
		Map<String, String> map = xmlHelper.parseXmlHead(head.getRequestStr());
		log.info("--commonRequesUpdate--->转换map：" + map.toString());

		// 把请求的 sender、receiver 封装在hea对象中
		head.setSender(map.get("sender"));
		head.setReceiver(map.get("receiver"));

		/**
		 * 根据请求修改操作
		 */
		int temp = iU.updateObj(head.getSqlId(), map);

		log.info("-- 修改结果-->" + temp);
		
		boolean flag =false; 
		
		if(temp > 0){
			flag = true;
		}else{
			//如果修改 op_devport 失败再修改  tr_devport
			int tetmp2 = iU.updateObj("changeModuleTerm_tr_devport_SqlId", map);
			if(tetmp2 >=0){
				flag = true;
			}
		}
		
		/**
		 * 修改操作 只返回报文头部， 无需报文
		 */
		String resultXml = xmlHelper.getMapListXml(null, head.getItemsName(),head.getReceiver(), head.getSender(), head.getFunctionName(),flag);
		resultXml = resultXml.replace("> <", "><");
		return resultXml;
	}



	/**
	 *特殊处理，标签管理，
	 * 先查询标签是否存在、如果存在则返回具体数据信息
	 * @param head
	 * @return
	 */
	public static String lebelManager(Head head){
		
		//解析请求的xml字符串参数 转换成map对象
		Map<String, String> map = xmlHelper.parseXmlHead(head.getRequestStr());
		log.info("--lebelManager--->转换map：" + map.toString());
		head.setHeadName("data");
		head.setSender(map.get("sender"));
		head.setReceiver(map.get("receiver"));
		head.setStatus("failure");
		
		if(map.get("qr_code") != null && map.get("qr_code") != "" ){
			List list = iU.getList(head.getSqlId(), map);
			log.info("查询数据库-->result-->" + list);
			
			if(list != null){
				head.setStatus("success");
			
				if(list.size() >0){
					head.setSqlId(null);
					Map resultMap  = (Map) list.get(0);
					String type = (String) resultMap.get("TYPE");
					
					//站点二维码查询
					if("com_station".equals(type)){
						head.setSqlId("getAllStationsSqlId");
						head.setItemsName("station");
					//机房
					}else if("com_mroom".equals(type)){
						head.setSqlId("getAllRoomsSqlIdInRMSRooms");
						head.setItemsName("room");
					//机柜
					}else if("com_rack".equals(type)){
						head.setSqlId("getAllRacksSqlIdInRMSRack");
						head.setItemsName("rack");
					//设备
					}else if("tr_sdh".equals(type)){
						head.setSqlId("getAllEquipmentsSqlId");
						head.setItemsName("equip");
					}
					
					if(head.getSqlId() != null){
						head.setBodyList(iU.getList(head.getSqlId(), map));
					}
				}
			}
		}
		
		String resultXml = xmlHelper.getMapListXml(head);
		resultXml = resultXml.replace("> <", "><");
		return resultXml;
	}
}