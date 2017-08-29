package com.hj.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BaseDaoSuper {

	private static Log log = LogFactory.getLog(BaseDaoSuper.class);
	
	private static InputStream inputStream;
    
	private static SqlSessionFactory sqlSesstionFactory;
  
    private static SqlSession sqlSesstioin;
   
    private static final String resource = "com/hj/sysxml/Configuration.xml";// SRC
    
	static{

		try {
	            inputStream = Resources.getResourceAsStream(resource);
	            sqlSesstionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	        } catch (IOException e) {
	        	log.error("can't find " + resource,e);
	        }
	}
	
	public SqlSession getSqlSession() {
		
		sqlSesstioin = sqlSesstionFactory.openSession();

        return sqlSesstioin;
	
	}

	public void colseSqlSesstion(SqlSession sqlSesstion) {
		if (sqlSesstion != null) {
			sqlSesstion.clearCache();
			sqlSesstion.close();
		}
	}
}
