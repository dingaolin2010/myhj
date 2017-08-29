package com.hj.dao;

import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.hj.utils.ConfigPropertiesUtils;

public class DbPoolConnection {

    private static DbPoolConnection databasePool = null;
    private static DruidDataSource dataSource = null;

    static {
        Properties properties = ConfigPropertiesUtils.getInstance().loadPropertyFile("/db_server.properties");
        try {
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DbPoolConnection() {
    }

    public static synchronized DbPoolConnection getInstance() {
        if (null == databasePool) {
            databasePool = new DbPoolConnection();
        }
        return databasePool;
    }

    public DruidDataSource getDataSource() throws SQLException {
        return dataSource;
    }

    public DruidPooledConnection getConnection() throws SQLException {
        return dataSource.getConnection();
    }



}