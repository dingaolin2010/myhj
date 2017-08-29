package com.hj.dao;

import java.util.List;
import java.util.Map;

/**
 * 公共处理接口
 * 
 * @author lenovo
 */
public interface IDao {

	/**
	 * 所有查询接口
	 * 
	 * @param sqlId
	 *            查询的SQLID
	 * @map 请求参数
	 * @return
	 */
	public List getList(String sqlId, Map map);

	/**
	 * 修改通用方法
	 * 
	 * @param sqlId
	 *            修改的SQlID
	 * @param map
	 *            修改的参数值
	 * @return
	 */
	public int updateObj(String sqlId, Map map);

	/**
	 * 插入数据 多笔插入
	 * 
	 * @param sqlId
	 * @param list
	 */
	public int insert(String sqlId, List<Map> list);

	/**
	 * 插入数据  单笔插入
	 * 
	 * @param sqlId
	 * @param list
	 */
	public int insert(String sqlId, Map map);

	
	/**
	 * 调用存储过程
	 * 
	 * @param sqlId
	 * @param map
	 */
	public void exePrd(String sqlId, Map map);

	
	/**
	 * 返回对象实例
	 * @param sqlId
	 * @param map
	 * @return
	 */
	public Object getObject(String sqlId, Map map);
	
	
	/**
	 * 对象插入
	 * @param sqlId
	 * @param obj
	 * @return
	 */
	public int inssert(String sqlId,Object obj);

}
