package com.hj.dao.imp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hj.dao.BaseDaoSuper;
import com.hj.dao.IDao;



public class IDaoImp extends BaseDaoSuper implements IDao {
	
	private static Log log = LogFactory.getLog(IDaoImp.class);
	
	@Override
	public List getList(String sqlId,Map map) {
		SqlSession sqlSesstioin = null;
		List list = null;
		try {
			sqlSesstioin = getSqlSession();
			list = sqlSesstioin.selectList(sqlId,map);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getList---->操作数据库异常",e);
		} finally {
			colseSqlSesstion(sqlSesstioin);
			return list;
		}
		
	}

	@Override
	public int updateObj(String sqlId, Map map) {
		SqlSession sqlSesstioin = null;
		int temp=0;
		try {
			sqlSesstioin = getSqlSession();
			temp = sqlSesstioin.update(sqlId, map);
			sqlSesstioin.commit();
		} catch (Exception e) {
			temp = -1;
			sqlSesstioin.rollback();
			e.printStackTrace();
			log.error("updateObj---->操作数据库异常",e);
		} finally {
			colseSqlSesstion(sqlSesstioin);
			return temp;
		}

	}

	@Override
	public int insert(String sqlId, List<Map> list) {
		SqlSession sqlSesstioin = null;
		int temp=0;
		try {
			sqlSesstioin = getSqlSession();
			temp = sqlSesstioin.insert(sqlId, list);
			sqlSesstioin.commit();
		} catch (Exception e) {
			temp = -1;
			sqlSesstioin.rollback();
			e.printStackTrace();
			log.error("insert---->操作数据库异常",e);
		} finally {
			colseSqlSesstion(sqlSesstioin);
			return temp;
		}
	}

	
	@Override
	public void exePrd(String sqlId, Map map) {
		SqlSession sqlSesstioin = null;
		try {
			sqlSesstioin = getSqlSession();
			sqlSesstioin.selectOne(sqlId, map);
			sqlSesstioin.commit();
		} catch (Exception e) {
			sqlSesstioin.rollback();
			e.printStackTrace();
			
		} finally {
			colseSqlSesstion(sqlSesstioin);
		}		
	}

	@Override
	public Object getObject(String sqlId, Map map) {
		SqlSession sqlSesstioin = null;
		Object obj = null;
		try {
			sqlSesstioin = getSqlSession();
			obj = sqlSesstioin.selectOne(sqlId,map);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getList---->操作数据库异常",e);
		} finally {
			colseSqlSesstion(sqlSesstioin);
			return obj;
		}
		
	}

	@Override
	public int insert(String sqlId, Map map) {
		SqlSession sqlSesstioin = null;
		int temp=0;
		try {
			sqlSesstioin = getSqlSession();
			temp = sqlSesstioin.insert(sqlId, map);
			sqlSesstioin.commit();
		} catch (Exception e) {
			temp = -1;
			sqlSesstioin.rollback();
			e.printStackTrace();
			log.error("insert---->操作数据库异常",e);
			
		} finally {
			colseSqlSesstion(sqlSesstioin);
			return temp;
		}
	}

	@Override
	public int inssert(String sqlId, Object obj) {
		SqlSession sqlSesstioin = null;
		int temp=0;
		try {
			sqlSesstioin = getSqlSession();
			temp = sqlSesstioin.insert(sqlId, obj);
			sqlSesstioin.commit();
		} catch (Exception e) {
			sqlSesstioin.rollback();
			e.printStackTrace();
			log.error("insert---->操作数据库异常",e);
			temp = -1;
		} finally {
			colseSqlSesstion(sqlSesstioin);
			return temp;
		}
	}
	 
}
