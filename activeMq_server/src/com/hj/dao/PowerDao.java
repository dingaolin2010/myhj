package com.hj.dao;

import com.hj.utils.DBUtilsHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-06-09.
 */
public class PowerDao {
    /**
     * 电源查询
     *	page_count	每页条数	    整型	是	每一页查询多少条
     *	page_index	所查页数	    整型	是	查询第几页
     *	station_id	站点ID	        字符串	否	根据所属站点ID查询电源
     *	station_name站点名称	    字符串	否	根据站点名称模糊查询电源
     *	power_id	电源ID	        字符串	否	根据所属站点ID查询电源
     *	power_name	电源设备名称	字符串	否	根据电源名称模糊查询电源
     *	power_type	电源类型	    字符串	否	根据电源类型查询电源
     */
    public List<Map<String, Object>> getAllPowersList(int page_count, int page_index, String station_id, String station_name, String power_id,
                                                      String power_name, String power_type) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String pageSql = (page_count > 0 && page_index > 0) ? String.format(" where rownum_>%d and rownum_<=%d", (page_index - 1) * page_count,
                    page_count * page_index) : "";
            String station_idSql = station_id.isEmpty() ? "" :
                    String.format(" and sn='%s'", station_id);
            String station_nameSql = station_name.isEmpty() ? "" :
                    String.format(" and dbo.sf('pw_switch', 'SN', SN, null) like '%%%s%%'", station_name);
            String power_idSql = power_id.isEmpty() ? "" :
                    String.format(" and deviceid='%s'", power_id);
            String power_nameSql = power_name.isEmpty() ? "" :
                    String.format(" and devicename like '%%%s%%'", power_name);
            String power_typeSql = power_type.isEmpty() ? "" :
                    String.format(" and dbo.sf('pw_switch', 'devtype', devtype, null) like '%%%s%%'", power_type);

            String sql = String.format("select p.deviceid power_id,\n" +
                    "       p.devicename power_name,\n" +
                    "       dbo.sf('pw_switch', 'devtype', p.devtype, null) power_type,\n" +
                    "       dbo.sf('pw_switch', 'VENDORID', p.VENDORID, null) vender,\n" +
                    "       '' maintain_dept,\n" +
                    "       dbo.sf('pw_switch', 'manatype', p.manatype, null) disp_dept,\n" +
                    "       round(KWPERPORT) capacity,\n" +
                    "       '' soft_version,\n" +
                    "       to_char(WORKDATE, 'YYYY-MM-DD') beg_date,\n" +
                    "       to_char(RESTDATE, 'YYYY-MM-DD') end_date,\n" +
                    "       DEVRACKID rack_id,\n" +
                    "       dbo.sf('pw_switch', 'DEVRACKID', p.DEVRACKID, null) rack_name,\n" +
                    "       dbo.sf('pw_switch', 'VENDORMODELID', p.VENDORMODELID, null) model,\n" +
                    "       SN station_id,\n" +
                    "       dbo.sf('pw_switch', 'SN', p.SN, null) station_name\n" +
                    "  from pw_switch p\n" +
                    " where p.deviceid in\n" +
                    "       (select deviceid\n" +
                    "          from (select deviceid, row_number() over(order by deviceid) rownum_\n" +
                    "                  from pw_switch\n" +
                    "                 where 1 = 1 %s %s %s %s %s) tt\n" +
                    "         %s)\n" +
                    " order by deviceid",station_idSql,station_nameSql,power_idSql,power_nameSql,power_typeSql,pageSql);

            System.out.println(sql);

            mapListResult = runner.query(sql, new MapListHandler());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }

    /**
     * 电源负载查询
     *	page_count	每页条数	    整型	是	每一页查询多少条
     *	page_index	所查页数	    整型	是	查询第几页
     *  equip_id	设备id	        字符串	否	指定设备ID查询电源负载
     *  equip_name	设备名称	    字符串	否	指定设备名称模糊查询电源负载
     *  power_id	电源ID	        字符串	否	指定电源ID查询电源负载
     *  power_name	电源名称	    字符串	否	指定电源名称模糊查询电源负载
     */
    public List<Map<String, Object>> getAllPowerLoadsList(int page_count, int page_index, String equip_id, String equip_name, String power_id,
                                                      String power_name) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String pageSql = (page_count > 0 && page_index > 0) ? String.format(" where rownum_>%d and rownum_<=%d", (page_index - 1) * page_count,
                    page_count * page_index) : "";
            String equip_idSql = equip_id.isEmpty() ? "" :
                    String.format(" and deviceid='%s'", equip_id);
            String equip_nameSql = equip_name.isEmpty() ? "" :
                    String.format(" and dbo.sf('pw_switch', 'SN', SN, null) like '%%%s%%'", equip_name);
            String power_idSql = power_id.isEmpty() ? "" :
                    String.format(" and deviceid='%s'", power_id);
            String power_nameSql = power_name.isEmpty() ? "" :
                    String.format(" and devicename like '%%%s%%'", power_name);

            String sql = String.format("select p.deviceid power_id,\n" +
                    "       p.devicename power_name,\n" +
                    "       dbo.sf('pw_switch', 'devtype', p.devtype, null) power_type,\n" +
                    "       dbo.sf('pw_switch', 'VENDORID', p.VENDORID, null) vender,\n" +
                    "       '' maintain_dept,\n" +
                    "       dbo.sf('pw_switch', 'manatype', p.manatype, null) disp_dept,\n" +
                    "       round(KWPERPORT * PORTNUM) capacity,\n" +
                    "       '' soft_version,\n" +
                    "       to_char(WORKDATE, 'YYYY-MM-DD') beg_date,\n" +
                    "       to_char(RESTDATE, 'YYYY-MM-DD') end_date,\n" +
                    "       DEVRACKID rack_id,\n" +
                    "       dbo.sf('pw_switch', 'DEVRACKID', p.DEVRACKID, null) rack_name,\n" +
                    "       dbo.sf('pw_switch', 'VENDORMODELID', p.VENDORMODELID, null) model,\n" +
                    "       SN station_id,\n" +
                    "       dbo.sf('pw_switch', 'SN', p.SN, null) station_name\n" +
                    "  from pw_switch p\n" +
                    " where p.deviceid in\n" +
                    "       (select deviceid\n" +
                    "          from (select deviceid, row_number() over(order by deviceid) rownum_\n" +
                    "                  from pw_switch\n" +
                    "                 where 1 = 1 %s %s %s %s) tt\n" +
                    "         %s)\n" +
                    " order by deviceid",equip_idSql,equip_nameSql,power_idSql,power_nameSql,pageSql);

            System.out.println(sql);

            mapListResult = runner.query(sql, new MapListHandler());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }

    /**
     * 光缆信息修改
     *
     * @param oCableId
     * @param begDate
     * @param matainDept
     * @param dispDept
     * @param endDate
     * @param remark
     * @return
     */
    public int updateOptSeg(String oCableId, String begDate, String matainDept, String dispDept,
                            String endDate, String remark, String direction) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        int result = 0;
        if (begDate.isEmpty() && matainDept.isEmpty() && dispDept.isEmpty()
                && endDate.isEmpty() && remark.isEmpty() && direction.isEmpty()) {
            return result;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("update optseg set ");
        if (!begDate.isEmpty()) {
            builder.append(String.format(" WORKDATE=to_char('%s','yyyy-mm-dd'", begDate));
        }
        if (!matainDept.isEmpty()) {
            builder.append(String.format(" DUTYUNITID='%s'", matainDept));
        }
        if (!dispDept.isEmpty()) {
            builder.append(String.format(" MANAGEUNIT='%s'", dispDept));
        }
        if (!endDate.isEmpty()) {
            builder.append(String.format(" RESTDATE=to_date('%s','yyyy-mm-dd'", endDate));
        }
        if (!remark.isEmpty()) {
            builder.append(String.format(" REMARK='%s'", remark));
        }
        if (!direction.isEmpty()) {
            String passPoints = direction.replace(',', ' ');
            passPoints = direction.replace(';', ',');
            builder.append(String.format(" SHAPE=sde.st_polygon('polygon(('||%s||'))','2')", passPoints));
        }
        builder.append(String.format(" where OPTSEGID='%s'", oCableId));
        try {
            System.out.println(builder);
            result = runner.update(builder.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    /**
     * 电源修改
     *  power_id	    唯一标示	    字符串	是
     *  maintain_dept	维护单位	    字符串	否	树状选择类型
     *  disp_dept	    调管单位	    字符串	否	树状选择类型
     *  capacity	    电源设备容量	整数	否	单位: mA
     *  soft_version	软件版本	    字符串	否
     */
    public int changePowerInfo(String power_id, String maintain_dept, String disp_dept,int capacity,String soft_version) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        int result = 0;
        if (power_id.isEmpty()) {
            return result;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("update pw_switch set ");
        if (!disp_dept.isEmpty()) {
            builder.append(String.format(" manatype='%s'", disp_dept));
        }
        if (!(capacity == 0)) {
            builder.append(String.format(" KWPERPORT=%s", capacity));
        }
        /*if (!soft_version.isEmpty()) {
            builder.append(String.format(" soft_version='%s'", soft_version));
        }*/

        builder.append(String.format(" where deviceid='%s'", power_id));
        try {
            System.out.println(builder);
            result = runner.update(builder.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
