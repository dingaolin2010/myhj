package com.hj.dao;

import com.hj.utils.DBUtilsHelper;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017-6-19.
 */
public class Com_StationDao {
 

	private static Log log = LogFactory.getLog(Com_StationDao.class);
	
	public List<Map<String, Object>> getAllStations(int pageIndex, int pageCount, String stName, String stId) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;

        try {
            String pageSql = (pageIndex > 0 && pageCount > 0) ? String.format(" where rownum_>%d and rownum_<=%d", (pageIndex - 1) * pageCount,
                    pageIndex * pageCount) : "";
            String sysNameSql = stName.isEmpty() ? "" : String.format(" and (like '%%%s%%') ", stName);
            String stIdSql = stId.isEmpty() ? "" : String.format(" and sn='%s'", stId);

            StringBuilder where = new StringBuilder();
            where.append("( 1=1 )");

            if (!sysNameSql.isEmpty())
            {
                where.append(sysNameSql);
            }

            if (!stIdSql.isEmpty())
            {
                where.append(stIdSql);
            }

            String sql = String.format("select sn station_id,\n" +
                    "       stno station_code,\n" +
                    "       stname station_name,\n" +
                    "       supname name_std,\n" +
                    "       stationtype station_type,\n" +
                    "       resgrade voltage_class,\n" +
                    "       areasn belong_zone,\n" +
                    "       staddress addr,\n" +
                    "       longitudes lon,\n" +
                    "       latitudes latit,\n" +
                    "       MANATYPE disp_dept,\n" +
                    "       DISPATCHNAME disp_agency,\n" +
                    "       MAINTAIN maintain_dept,\n" +
                    "       WORKDATE beg_time,\n" +
                    "       '' x_coord,\n" +
                    "       '' y_coord,\n" +
                    "       '' qr_code\n" +
                    "  from com_station\n" +
                    " inner join (select sn RECID0001, rownum_ PAM_ROWNUM\n" +
                    "               from (select ROW_NUMBER() OVER(order by stname) + 0 AS rownum_,\n" +
                    "                            sn\n" +
                    "                       from com_station\n" +
                    "                      WHERE %s ) ir\n" +
                    "              %s ) kr\n" +
                    "    on com_station.sn = kr.RECID0001\n" +
                    " order by kr.PAM_ROWNUM\n", where, pageSql);

            
            sql ="select       sn station_id,       stno station_code,       stname station_name,       supname name_std,       stationtype station_type,       resgrade voltage_class,       areasn belong_zone,       staddress addr,       longitudes lon,       latitudes latit,       MANATYPE disp_dept,       DISPATCHNAME disp_agency,       MAINTAIN maintain_dept,       WORKDATE beg_time,       '' x_coord,       '' y_coord,       '' qr_code from com_station inner join (select sn RECID0001, rownum_ PAM_ROWNUM               from (select ROW_NUMBER() OVER(order by stname) + 0 AS rownum_,                            sn                       from com_station                      WHERE  1=1 )ir               where rownum_>0 and rownum_<=100 ) kr    on com_station.sn = kr.RECID0001 order by kr.PAM_ROWNUM";
            
            System.out.println(sql);
            mapListResult = runner.query(sql, new MapListHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }

    public List<Map<String, Object>> getAllRooms(int pageIndex, int pageCount, String stId) {

        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;

        try {

            String pageSql = (pageIndex > 0 && pageCount > 0) ? String.format(" where rownum_>%d and rownum_<=%d", (pageIndex - 1) * pageCount,
                    pageIndex * pageCount) : "";
            String stIdSql = stId.isEmpty() ? "" : String.format(" and sn='%s'", stId);

            StringBuilder where = new StringBuilder();
            where.append("( 1=1 )");

            if (!stIdSql.isEmpty())
            {
                where.append(stIdSql);
            }

            String sql = String.format("select mroomid room_id,\n" +
                    "       mroomcode room_code,\n" +
                    "       mroomname room_name,\n" +
                    "       supname name_std,\n" +
                    "       mroom room_type,\n" +
                    "       layer floor_num,\n" +
                    "       ROOMNAME room_no,\n" +
                    "       MANATYPE disp_dept,\n" +
                    "       DUTYDEPART maintain_dept,\n" +
                    "       MROOMLEN length,\n" +
                    "       MROOLWIDTH width,\n" +
                    "       HEIGHT height,\n" +
                    "       MROOMLEN * MROOLWIDTH area,\n" +
                    "       dbo.sf('COM_MROOM', 'SN', SN, null) belong_station,\n" +
                    "       sn station_id,\n" +
                    "       '' lon,\n" +
                    "       '' latit,\n" +
                    "       '' qr_code\n" +
                    "  from com_mroom\n" +
                    "  inner join (select mroomid RECID0001, rownum_ PAM_ROWNUM\n" +
                    "               from (select ROW_NUMBER() OVER(order by mroomid) + 0 AS rownum_,\n" +
                    "                            mroomid\n" +
                    "                       from com_mroom\n" +
                    "                      WHERE %s) ir\n" +
                    "               %s) kr\n" +
                    "    on com_mroom.mroomid = kr.RECID0001\n" +
                    " order by kr.PAM_ROWNUM\n", where, pageSql);

            System.out.println(sql);
            mapListResult = runner.query(sql, new MapListHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mapListResult;
    }

    
    public List<Map<String, Object>> CheckoutUser2(Map<String,String> map) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
    	
        log.info("runner-->"+runner);
    	String sql = "select username , workno from hjfix_user where username = '"+map.get("user_id")+"'";
    	
    	log.info("sql="+sql);
    	
    	List<Map<String, Object>> list = null;
    	try {
    		list = runner.query(sql, new MapListHandler());
		} catch (Exception e) {
			e.printStackTrace();
			log.info("错误！！！"+e.getMessage());
			return null;
		}
    	return list;
    }
    
    public List<Map<String, Object>> getAllUserInfos(Map<String,String> map){
   
    	 DBUtilsHelper dbh = new DBUtilsHelper();
         QueryRunner runner = dbh.getRunner();
     	
         log.info("runner-->"+runner);
     	String sql = "select hu.username,hu.workno ,hu.mobile,hu.qq ,hu.mail from hjfix_user hu";
     	
     	log.info("sql="+sql);
     	
     	List<Map<String, Object>> list = null;
     	try {
     		list = runner.query(sql, new MapListHandler());
 		} catch (Exception e) {
 			e.printStackTrace();
 			log.info("错误！！！"+e.getMessage());
 			return null;
 		}
     	return list;
    }
}