package com.hj.activemq;

import com.csg.activemq.model.FileDownloadModel;
import com.csg.exception.AdpException;
import com.csg.protocol.ResourceMgr_IStub;
import com.hj.common.CommonDealData;
import com.hj.entity.Head;

import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by Administrator on 2017-6-7.
 */
public class HjActiveMQResource extends ResourceMgr_IStub {
	
	private static Log log = LogFactory.getLog(HjActiveMQResource.class);

	public static void main(String[] str) throws AdpException {
		log.info("启动-->进入main-->");
		//HjActiveMQResource obj = new HjActiveMQResource();
		 //StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?><data sender=\"0100\" receiver=\"01040102\" functionName=\"checkoutUser\"  time=\"2014-09-12 14:56:55\">");
		
		//6.5.1.1	获取用户信息 
		
		/* 用户验证 */
		//sb.append("<condition><user><user_id>李博</user_id></user><user_pwd>test1234</user_pwd></condition>");
		/* 站点查询 */
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><station_id>3232323232</station_id><station_name></station_name><dept_code></dept_code></condition>");

		 //6.1.1.2	站点内机房查询
		//sb.append("<condition><user><user_id></user_id></user><page_count>10</page_count><page_index>1</page_index><station_id>FDA562A9CF8204F8E043F201A8C07A0E</station_id><dept_code></dept_code></condition>");
		
		//6.1.1.3	站点内通信光缆查询
		//sb.append("<condition><user><user_id></user_id></user><page_count>10</page_count><page_index>1</page_index><station_id>FDA562A9CEFF04F8E043F201A8C07A0E</station_id><dept_code></dept_code></condition>");
		 
		//6.1.1.4	站点信息修改
		//sb.append("<condition><user><user_id></user_id></user><page_count>10</page_count><page_index>1</page_index><station_id>FDA562A9CEDF04F8E043F201A8C07A0E</station_id><addr></addr><dept_code></dept_code><qr_code>555555555555555555555</qr_code></condition>");

		//机房查询
	  //	sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><dept_code></dept_code><station_id>FDA562A9CF8204F8E043F201A8C07A0E</station_id><room_name>11</room_name><room_id></room_id><qr_code></qr_code></condition>");
	  	
	  	//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><room_name>11</room_name></condition>");
	  	 
		
		//sb.append("<condition><user><user_id></user_id></user><station><station_id>FDA562A9CEE104F8E043F201A8C07A0E</station_id></station><addr></addr><lon></lon><latit>8888</latit></condition>");
	
		//6.1.2.2	机房内机柜查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><dept_code></dept_code><room_id>1B4BC38A2D611EE053F201A8C08D47</room_id></condition>");
		
		//6.1.2.3	机房信息修改
		//sb.append("<condition><user><user_id></user_id></user><room><room_id>FDA562A9CF9B04F8E043F201A8C07A0E</room_id><floor_num></floor_num><room_no></room_no><disp_dept></disp_dept><maintain_dept></maintain_dept><length></length><width></width><qr_code>111111</qr_code></room></condition>");
		
		
		 // 6.1.3.1	机柜查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><dept_code></dept_code><room_id></room_id><rack_name></rack_name><rack_id></rack_id></condition>");
		

		//6.1.3.2	机柜内子框查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><rack_id></rack_id></condition>");
		
		//6.1.3.3	机柜内配线模块查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><dept_code></dept_code><shelf_id></shelf_id><shelf_name></shelf_name></condition>");
		
		 //6.1.3.5	机柜信息修改
//		sb.append("<condition><user><user_id>林密</user_id></user><rack><rack_id>FDA562A9D35804F8E043F201A8C07A0E</rack_id><disp_dept></disp_dept><qr_code>33333</qr_code></rack></condition>");
		
		 //6.1.4.1	机槽查询
		//  sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><rack_id></rack_id><slot_id>FE1F63BD7DC3E1F4E043F201A8C0F1F7</slot_id><rack_name>14PA</rack_name></condition>");
		 
		//6.1.5.1	通信设备查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><station_id>123</station_id><station_name>3123</station_name><room_id>312312</room_id><room_name>3123</room_name><rack_id>312312</rack_id><rack_name>321312</rack_name><equip_id>13213</equip_id><equip_name>312312</equip_name></condition>");
		
		//6.1.5.2	设备承载的业务列表查询
		//sb.append("<condition><user><dept_code>3232</dept_code><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><equip_id>5248c1351b74408ab2a24e6077a06051</equip_id><equip_name>云</equip_name></condition>");
		
		//6.1.5.3	设备上板卡查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><equip_id>dbe086fc5c0c43fe973c9a9fbb647424</equip_id><equip_name>大</equip_name></condition>");
		
		//6.1.5.6	设备信息修改
		//sb.append("<condition><user><user_id>林密</user_id></user><equip_id>4515c40bb8c34f778f42df7644fd1dde</equip_id><maintain_dept>2</maintain_dept><disp_dept></disp_dept><end_date></end_date><soft_version></soft_version><qr_code>22222</qr_code></condition>");
		
		//6.1.5.7	逻辑端口查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><equip_id>02bf16387b074ebf87acd5254ffd143d</equip_id><card_id>0130AAD205D10A9FE053F201A8C097A9</card_id></condition>");
		
		//6.1.6.1	业务、电路查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><circuit_id>aefd87a7f6a844acb3a1cc332bc69f5a</circuit_id><circuit_name>中调</circuit_name><oper_id>1</oper_id></condition>");
		
		//6.1.6.2	电路路由图查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><circuit_id>03d6268886bc46e0ab8ca0a3a36535f3</circuit_id></condition>");
		
		
		//* 6.1.6.3	业务方式单查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><circuit_id>1f71e9be-737a-44e6-93eb-6ff7442cb4e8</circuit_id></condition>");
		
		//6.1.6.4	业务关联其他业务列表查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><circuit_id></circuit_id></condition>");
		
		//6.1.7.1	光缆查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><station_id></station_id><station_name></station_name><room_id></room_id><room_name></room_name><ocable_id>8831317fe70943af98ed4cbf6d1ee1ec</ocable_id><ocable_name>10KV</ocable_name></condition>");
		
		//6.1.7.2	光缆承载的光路查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><ocable_id>325caefc36084a12b7f1ba9feaf2dc98</ocable_id></condition>");
		
		//6.1.7.3	光路承载的电路查询z
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><oroute_id>00b385493fe5457cb5a3ddd2dbf6e313</oroute_id></condition>");
		
		//6.1.7.4	光缆信息修改
		//sb.append("<condition><user><user_id>林密</user_id></user><ocable><ocable_id>5d1e4818106349a2bb5518bf5a90122a</ocable_id><beg_date>2016-01-01</beg_date><maintain_dept>2</maintain_dept><disp_dept>2</disp_dept><end_date>2016-02-02</end_date><remarks>testtest</remarks></ocable></condition>");
	
		//6.1.7.5	光缆行走向经纬度修改
		//sb.append("<condition><user><user_id>林密</user_id></user><ocable><ocable_id>5d1e4818106349a2bb5518bf5a90122a</ocable_id><direction>11111111111,22222222</direction></ocable></condition>");
		
		//6.1.7.6	光纤查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><equip_id>197a5fe285e9459395c6e40a71ffb44a</equip_id><equip_name>O</equip_name><term_id>00166C980C29AE42E053F201A8C05B9A</term_id><term_name>0</term_name><ocable_id>3d33045ed9d64e2cbbd319a08e06d7d6</ocable_id><ocable_name>110</ocable_name></condition>");
		
		//6.1.7.7	接头盒查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><ocable_id></ocable_id><ocable_name></ocable_name><box_id></box_id><box_name>那大-八一</box_name></condition>");
		
		//6.1.7.8	接头盒经纬度更新
		//sb.append("<condition><user><user_id>林密</user_id></user><connect_box><box_id>f4851d4b441140ada974b28adc2e7184</box_id></connect_box><lon>1111.11111</lon><latit>2222.222</latit></condition>");
		
		//6.1.8.1	电源查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><station_id>FDA562A9CEBF04F8E043F201A8C07A0E</station_id><station_name>潭门</station_name><power_id>2c83442a655f4377b4b7921eb9d81fca</power_id><power_name>潭门站</power_name><power_type>独立通信电源</power_type></condition>");

		//6.1.8.2	电源负载查询
	//	 sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><equip_id>7cc7c6e1b47f40ea878153b179d6851a</equip_id><equip_name>三门</equip_name><power_id>330607ca6eae49568aa5218411ec803b</power_id><power_name>三门</power_name></condition>");
		
		//6.1.8.3	电源基本信息修改
		//sb.append("<condition><user><user_id>林密</user_id></user><power><power_id>2c83442a655f4377b4b7921eb9d81fca</power_id><disp_dept>3</disp_dept><capacity>60</capacity></power></condition>");
		
		//6.1.10.1	配线端子查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><module_name>营根站ODF05</module_name><module_id>6b0040e6de554ff3bb8a18cbe7408a01</module_id><term_id>FDD23B6FE59A4629E043F201A8C01942</term_id></condition>");

		//6.1.10.2	配线端子信息修改
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><term_id>014C90C8D38F3046E053F201A8C0F885</term_id><term_label>99</term_label></condition>");
		
		//6.1.12.1	拓扑图查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><system_name>三亚传输B网.png</system_name></condition>");
		//6.1.12.2	传输段查询
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><trans_name>长流</trans_name></condition>");
		
		//6.1.12.3	时隙查询
		//b.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><port_id>012F564836A6BCAFE053F201A8C0C3DA</port_id></condition>");
		 
		 //6.1.12.4	交叉查询
		// sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><port_id>32323</port_id><slot_id>32323</slot_id></condition>");
		
		//6.1.9.1	N-1分析查询
	 	//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><ocable_id>e2a8839d0fc34dd780c57075a86cad93</ocable_id></condition>");
		
		//6.4.4	附件下载
		//sb.append("<condition><user><user_id>林密</user_id></user><page_count>10</page_count><page_index>1</page_index><fileid>2ee28c709ea14ed9b4f17067226abc77</fileid></condition>");
		
		//6.1.11.1	二维码标签查询
		//sb.append("<condition><user><user_id>林密</user_id></user><qr_code>22222</qr_code></condition>");
		
		//6.4.2	树状节点选择属性
	//	sb.append("</data>");
		
		//obj.getAllResouceByQrcode(sb.toString());
		com.csg.AdapterDriver.invoke();
	}

	/**
	 * 获取用户列表和用户详情的方法
	 */
	@Override
	public String getAllUserInfos(String s) {
		log.info("进入-->获取用户列表和用户详情的方法->getAllUserInfos 方法-- 请求参数t:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllUserInfosSqlId"); // 设置查询id
		head.setFunctionName("getAllUserInfos");
		head.setItemsName("user");
		String resultXml = CommonDealData.commonRequestDeal(head);

		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	/**
	 * 6.5.1.2 用户信息校验
	 */
	@Override
	public String checkoutUser(String s) {
		log.info("进入-->用户信息校验->checkoutUser 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("checkoutUserSqlId"); // 设置查询id
		head.setFunctionName("checkoutUser");
		
		head.setItemsName(null); //无报文体，只有头部
		
		String resultXml = CommonDealData.checkuser(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}
	
	
	

	/**
	 * 获取通信站点列表和站点详情的方法
	 */
	@Override
	public String getAllStations(String s) {
		log.info("进入-->获取通信站点列表和站点详情的方法-->getAllStations 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllStationsSqlId"); 
		head.setFunctionName("getAllStations");
		head.setItemsName("station");
		
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}
	

	
	/**
	 * 以下两个接口是同一个方法 需要分开处理
	 * 6.1.2.1	机房查询
	 * 6.1.1.2	站点内机房查询
	 */
	@Override
	public String getAllRooms(String s) {
		
		log.info("进入-->getAllRooms 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("dSqlId"); 
		head.setFunctionName("getAllRooms");
		head.setItemsName("room");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}
	
	

	/**
	 * 1：6.1.2.2	机房内机柜查询-获取通信机柜列表和机柜详情的方法
	 * 6.1.3.1	机柜查询
	 */
	@Override
	public String getAllRacks(String s) {
		log.info("进入-->getAllRacks 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllRacksSqlId"); 
		head.setFunctionName("getAllRacks");
		head.setItemsName("rack");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	/**
	 *  6.1.3.2	机柜内子框查询 获取子框列表和子框详情的方法
	 */
	@Override
	public String getAllSubShelfs(String s) {
		log.info("进入-->获取子框列表和子框详情的方法-->getAllSubShelfs 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllSubShelfsSqlId"); 
		head.setFunctionName("getAllSubShelfs");
		head.setItemsName("shelf");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	
	/**
	 * 6.1.6.2	电路路由图查询
	 */
	@Override
	public FileDownloadModel getCircuitRoutes(String s) {
		log.info("进入-->6.1.6.2	电路路由图查询-->getCircuitRoutes 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getCircuitRoutesSqlId"); 
		head.setFunctionName("getCircuitRoutes");
		return CommonDealData.commonImageDeal(head);
		
	}

	
	
	/**
	 * 以下两个接口是同一个方法 需要分开处理
	 * 6.1.1.3	站点内通信光缆查询  ->getAllOcablesSqlIdInRMStations
	 * 6.1.7.1	光缆查询		 ->getAllOcablesSqlIdInRMOcable
	 */	
	@Override
	public String getAllOcables(String s) {
		log.info("进入-->光缆查询	-->getAllOcables 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllOcablesSqlId"); 
		head.setFunctionName("getAllOcables");
		head.setItemsName("ocable");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	
	
	/**
	 * 6.1.7.2	光缆承载的光路查询
	 */
	@Override
	public String getAllOcableRoutes(String s) {
		
		log.info("进入-->6.1.7.2	光缆承载的光路查询-->getAllOcableRoutes 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllOcableRoutesSqlId"); 
		head.setFunctionName("getAllOcableRoutes");
		head.setItemsName("ocable_route");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;

	}

	
	
	/**
	 * 6.1.7.3	光路承载的电路查询
	 */
	@Override
	public String getAllOcableCircuits(String s) {
		log.info("进入-->6.1.7.3	光路承载的电路查询-->getAllOcableCircuits 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllOcableCircuitsSqlId"); 
		head.setFunctionName("getAllOcableCircuits");
		head.setItemsName("circuit");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;

	}

	
	
	/**
	 * 机房信息修改的方法
	 */
	@Override
	public String changeRoomInfo(String s) {
		
		log.info("进入-->机房信息修改的方法-->changeRoomInfo 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("changeRoomInfoSqlId"); 
		head.setFunctionName("changeRoomInfo");
		head.setItemsName(null);
		String resultXml = CommonDealData.commonRequesUpdate(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	
	/**
	 * 6.1.3.3	机柜内配线模块查询
	 * 获取机柜内配线模块的方法
	 */
	@Override
	public String getAllWiringModulesInRack(String s) {
		log.info("进入-->6.1.3.3	机柜内配线模块查询-->getAllWiringModulesInRack 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllWiringModulesInRacklIdSqlId"); 
		head.setFunctionName("getAllWiringModulesInRack");
		head.setItemsName("module");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	/**
	 * 6.1.3.5	机柜信息修改
	 * 机柜信息修改的方法
	 */
	@Override
	public String changeRackInfo(String s) {
		log.info("进入-->6.1.3.5	机柜信息修改-->changeRackInfo 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("changeRackInfoSqlId"); 
		head.setFunctionName("changeRackInfo");
		head.setItemsName(null);
		String resultXml = CommonDealData.commonRequesUpdate(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	/**
	 * 6.1.5.2	设备承载的业务列表查询
	 */
	@Override
	public String getAllEquipBussiness(String s) {
		log.info("进入-->6.1.5.2	设备承载的业务列表查询-->getAllEquipBussiness 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllEquipBussinessSqlId"); 
		head.setFunctionName("getAllEquipBussiness");
		head.setItemsName("business");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
		
	}

	
//TODO  此方法与 6.1.5.8	逻辑端口信息修改 一直
//设备信息修改的方法（changeEquipInfo）定义如下：
	/**
	 * 6.1.5.6	设备信息修改
	 */
	@Override
	public String changeEquipInfo(String s) {
		log.info("进入-->6.1.5.6	设备信息修改-->changeEquipInfo 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("changeEquipInfoSqlId"); 
		head.setFunctionName("changeEquipInfo");
		head.setItemsName(null);
		String resultXml = CommonDealData.commonRequesUpdate(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
		
		
	}

	
	/**
	 * 6.1.5.1	通信设备查询
	 */
	@Override
	public String getAllEquipments(String s) {
		log.info("进入-->6.1.5.1	通信设备查询-->getAllEquipments 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllEquipmentsSqlId"); 
		head.setFunctionName("getAllEquipments");
		head.setItemsName("equip");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;

	}

	
	
	/**
	 * 	6.1.6.1	业务、电路查询
	 */
	@Override
	public String getAllCircults(String s) {
		
		log.info("进入-->6.1.6.1	业务、电路查询-->getAllCircults 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllCircultsSqlId"); 
		head.setFunctionName("getAllCircults");
		head.setItemsName("circuit");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
		
	}

	
	/**
	 * 6.1.6.3	业务方式单查询
	 */
	@Override
	public String getAllCircuitOperationRes(String s) {
		
		log.info("进入-->6.1.6.3	业务方式单查询-->getAllCircuitOperationRes 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllCircuitOperationResSqlId"); 
		head.setFunctionName("getAllCircuitOperationRes");
		head.setItemsName("operation");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
		
	}

	
	
	/**
	 * 6.1.6.4	业务关联其他业务列表查询
	 */
	@Override
	public String getAllCircuitBussinessRes(String s) {
		log.info("进入-->6.1.6.4	业务关联其他业务列表查询-->getAllCircuitBussinessRes 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllCircuitBussinessResSqlId"); 
		head.setFunctionName("getAllCircuitBussinessRes");
		head.setItemsName("business");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
		
	}
	
	

	/**
	 * 站点内GPS坐标更新的方法
	 */
	@Override
	public String changeStationGPSInfo(String s) {
		log.info("进入-->站点内GPS坐标更新的方法-->changeStationGPSInfo 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("changeStationGPSInfoSqlId"); 
		head.setFunctionName("changeStationGPSInfo");
		head.setItemsName(null);
		String resultXml = CommonDealData.commonRequesUpdate(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	/**
	 * 站点信息修改的方法
	 */
	@Override
	public String changeStationInfo(String s) {
		log.info("进入-->站点信息修改的方法-->changeStationInfo 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("changeStationInfoSqlId"); 
		head.setFunctionName("changeStationInfo");
		head.setItemsName(null);
		String resultXml = CommonDealData.commonRequesUpdate(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	
	/**
	 * 以下两个接口都是 共用此方法， 通过 shelf_id、shelf_name 区分
	 * 6.1.5.3	设备上板卡查询  
	 * 6.1.3.4	子框内板卡查询  包含 shelf_id、shelf_name
	 * 获取子框内板卡的方法
	 */
	@Override
	public String getAllCards(String s) {
		log.info("进入-->板卡查询-->getAllCards 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllCardsSqlId"); 
		head.setFunctionName("getAllCards");
		head.setItemsName("card");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	
	
	/**
	 * 6.4.3	附件下载
	 */
	@Override
	public FileDownloadModel downloadFile(String s) {
		log.info("进入-->downloadFile 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getImageSqlId"); 
		head.setFunctionName("downloadFile");
		return CommonDealData.commonImageDeal(head);
	}

	

	/**
	 *  6.4.2	附件上传
	 */
	@Override
	public String uploadFile(String s, String s1, long l,InputStream inputStream) {
		
		log.info("进入-->uploadFile 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("uploadFileSqlId"); 
		head.setFunctionName("uploadFile");
		head.setItemsName(null);
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
		
	}
	
	
	

	
	/**
	 * 6.1.14.1	 辅助功能 所属系统名称查询
	 */
	@Override
	public String getNameAndStringValues(String s) {
		log.info("进入-->6.1.14.1	 辅助功能 所属系统名称查询-->getNameAndStringValues 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getNameAndStringValuesSqlId"); 
		head.setFunctionName("getNameAndStringValues");
		head.setItemsName("value");
		String resultXml = CommonDealData.commonRequesUpdate(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	
	
	/**
	 * 6.4.2	树状节点选择属性
	 */
	@Override
	public String getTreeXml(String s) {
		log.info("进入-->6.4.2	树状节点选择属性-->getTreeXml 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getTreeXmlDispDeptSqlId");
		head.setFunctionName("getTreeXml");
		String resultXml = CommonDealData.getTreeXmlPrase(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	
	/**
	 * 6.1.5.7	逻辑端口查询
	 */
	@Override
	public String getAllLogicPorts(String s) {
		log.info("进入-->6.1.5.7	逻辑端口查询-->getAllLogicPorts 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllLogicPortsSqlId"); 
		head.setFunctionName("getAllLogicPorts");
		head.setItemsName("logic_port");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;

	}


	/**
	 * 6.1.7.4	光缆信息修改
	 * 6.1.7.5	光缆行走向经纬度修改
	 */
	@Override
	public String changeOcableInfo(String s) {
		
		log.info("进入-->-->changeOcableInfo 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("changeOcableInfoSqlId"); 
		head.setFunctionName("changeOcableInfo");
		head.setItemsName(null);
		String resultXml = CommonDealData.commonRequesUpdate(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
		
	}

	
	/**
	 * 6.1.7.6	光纤查询
	 */
	@Override
	public String getAllFibers(String s) {
		
		log.info("进入-->6.1.7.6	光纤查询-->getAllFibers 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllFibersSqlId"); 
		head.setFunctionName("getAllFibers");
		head.setItemsName("fiber");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	/**
	 * 6.1.7.7	接头盒查询
	 */
	@Override
	public String getAllConnectBoxs(String s) {
		log.info("进入-->6.1.7.7	接头盒查询-->getAllConnectBoxs 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllConnectBoxsSqlId"); 
		head.setFunctionName("getAllConnectBoxs");
		head.setItemsName("fiber");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	
	
	
	/**
	 * 6.1.7.8	接头盒经纬度更新
	 */
	@Override
	public String changeConnectBoxInfo(String s) {
		log.info("进入-->6.1.7.8	接头盒经纬度更新-->changeConnectBoxInfo 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("changeConnectBoxInfoSqlId"); 
		head.setFunctionName("changeConnectBoxInfo");
		head.setItemsName(null);
		String resultXml = CommonDealData.commonRequesUpdate(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}	

	
	/**
	 * 6.1.8.1	电源查询
	 */
	@Override
	public String getAllPowers(String s) {
		log.info("进入-->6.1.8.1	电源查询-->getAllPowers 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllPowersSqlId"); 
		head.setFunctionName("getAllPowers");
		head.setItemsName("power");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;

	}

	
	/**
	 * 6.1.8.2	电源负载查询
	 */
	@Override
	public String getAllPowerLoads(String s) {
		log.info("进入-->6.1.8.2	电源负载查询-->getAllPowerLoads 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllPowerLoadsSqlId"); 
		head.setFunctionName("getAllPowerLoads");
		head.setItemsName("power_load");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
		
	}
	
	

	/**
	 * 6.1.8.3	电源基本信息修改
	 */
	@Override
	public String changePowerInfo(String s) {
		log.info("进入-->6.1.8.3	电源基本信息修改-->changePowerInfo 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("changePowerInfoSqlId"); 
		head.setFunctionName("changePowerInfo");
		head.setItemsName(null);
		String resultXml = CommonDealData.commonRequesUpdate(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	/**
	 * 6.1.9.1	N-1分析查询
	 * 分为三个步骤
	 * 1：获取 [资源],[实例] 插入Base_Select_List表中
	 * 2：调用过程 busi_analysis.n_m_Analyze
	 * 3:取出接口数据
	 */
	@Override
	public String getAnalysisN_1(String s) {
		log.info("进入-->6.1.9.1	N-1分析查询-->getAnalysisN_1 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAnalysisN_1SqlId"); 
		head.setFunctionName("getAnalysisN_1");
		head.setItemsName("circuit");
		
		String resultXml = CommonDealData.getAnalysis(head,"N_1");
		
		//String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	/**
	 * 6.1.9.2	N-X分析查询
	 */
	@Override
	public String getAnalysisN_X(String s) {
		log.info("进入-->6.1.9.2	N-X分析查询-->getAnalysisN_X 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAnalysisN_XSqlId"); 
		head.setFunctionName("getAnalysisN_X");
		head.setItemsName("circuit");
		String resultXml = CommonDealData.getAnalysis(head,"N_X");
		log.info("返回报文：" + resultXml);
		return resultXml;
	}
	
	

	/**
	 * 6.1.10.1	配线端子查询
	 */
	@Override
	public String getAllModuleTerms(String s) {
		log.info("进入-->6.1.10.1	配线端子查询-->getAllModuleTerms 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllModuleTermsSqlId"); 
		head.setFunctionName("getAllModuleTerms");
		head.setItemsName("module_term");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	
	/**
	 * 6.1.10.2	配线端子信息修改
	 */
	@Override
	public String changeModuleTermInfo(String s) {
		log.info("进入-->6.1.10.2	配线端子信息修改-->changeModuleTermInfo 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("changeModuleTermIn_op_devport_SqlId"); 
		head.setFunctionName("changeModuleTermInfo");
		head.setItemsName(null);
		String resultXml = CommonDealData.commonRequesUpdates(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	

	/**
	 * 6.1.11.1	二维码标签查询
	 */
	@Override
	public String getAllResouceByQrcode(String s) {
		
		log.info("进入-->6.1.11.1	二维码标签查询-->getAllResouceByQrcode 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllResouceByQrcodeSqlId"); 
		head.setFunctionName("getAllResouceByQrcode");
		head.setItemsName(null);
		String resultXml = CommonDealData.lebelManager(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	
	
	/**
	 * 6.1.12.1	拓扑图查询
	 */
	@Override
	public FileDownloadModel getTopLinks(String s) {

		log.info("进入-->6.1.12.1	拓扑图查询-->getTopLinks 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getTopLinksSqlId"); 
		head.setFunctionName("getCircuitRoutes");
		return CommonDealData.commonImageDeal(head);
		
	}

	@Override
	public String changerUserInfo(String s) {
		return null;
	}

	/**
	 * 6.1.12.2	传输段查询
	 */
	@Override
	public String getAllTransRoutes(String s) {
		
		log.info("进入-->6.1.12.2	传输段查询-->getAllTransRoutes 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllTransRoutesSqlId"); 
		head.setFunctionName("getAllTransRoutes");
		head.setItemsName("transroute");
		
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		
		return resultXml;
	}
	
	
	/**
	 * 6.1.12.3	时隙查询
	 */
	@Override
	public String getAllTimeSlots(String s) {
		
		log.info("进入-->6.1.12.3	时隙查询-->getAllTimeSlots 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllTimeSlotsSqlId"); 
		head.setFunctionName("getAllTimeSlots");
		head.setItemsName("time_slot");
		
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	/**
	 * 6.1.12.4	交叉查询
	 */
	@Override
	public String getAllCross(String s) {
		
		log.info("进入-->6.1.12.4	交叉查询-->getAllCross 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllCrossSqlId"); 
		head.setFunctionName("getAllCross");
		head.setItemsName("cross");
		
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	
	/**
	 * 机槽查询
	 */
	@Override
	public String getAllSlots(String s) {
				
		log.info("进入-->机槽查询-->getAllSlots 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("getAllSlotsSqlId"); 
		head.setFunctionName("getAllSlots");
		head.setItemsName("slot");
		String resultXml = CommonDealData.commonRequestDeal(head);
		log.info("返回报文：" + resultXml);
		return resultXml;
	}

	
	/**
	 * 6.1.13.1	资源申请变更
	 */
	@Override
	public String applyResChange(String s) {
		log.info("进入-->6.1.13.1	资源申请变更-->applyResChange 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("applyResChangeSqlId"); 
		head.setFunctionName("applyResChange");
		head.setItemsName(null);
		String resultXml = CommonDealData.commonRequesUpdate(head);
		log.info("返回报文：" + resultXml);
		
		return resultXml;
	}

	/**
	 * 6.1.5.8	逻辑端口信息修改
	 */
	@Override
	public String changeLogicportInfo(String s) {
		log.info("进入-->6.1.5.8	逻辑端口信息修改-->changeLogicportInfo 方法-- 请求参数:" + s);
		Head head = new Head();
		head.setRequestStr(s);
		head.setSqlId("changeLogicportInfoSqlId"); 
		head.setFunctionName("changeLogicportInfo");
		head.setItemsName(null);
		String resultXml = CommonDealData.commonRequesUpdate(head);
		log.info("返回报文：" + resultXml);
		
		return resultXml;
	}
}
