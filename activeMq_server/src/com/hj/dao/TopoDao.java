package com.hj.dao;

import com.csg.activemq.model.FileDownloadModel;
import com.hj.utils.DBUtilsHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-06-12.
 */
public class TopoDao {

    /**
     * 获取拓扑图
     *
     * @param sysName
     * @return
     */
    public FileDownloadModel getTopoList(String sysName) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        FileDownloadModel model = null;
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return model;
    }

    /**
     * 传输段查询
     *
     * @param pageIndex
     * @param pageCount
     * @param transName
     * @return
     */
    public List<Map<String, Object>> getPortLinkList(int pageIndex, int pageCount, String transName) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String pageSql = (pageCount > 0 && pageIndex > 0) ? String.format(" where rownum_>%d and rownum_<=%d", (pageIndex - 1) * pageCount,
                    pageIndex * pageCount) : "";
            String transNameSql = transName.isEmpty() ? "" :
                    String.format(" and c.linkname like '%%%s%%'", transName);
            String sql = String.format("select s.portlinkid trans_id,\n" +
                    "       s.linkname trans_name,\n" +
                    "       s.ancresinsid equip_a,\n" +
                    "       s.aportid port_a,\n" +
                    "       s.bncresinsid equip_z,\n" +
                    "       s.bportid port_z,\n" +
                    "       dbo.sf('TR_PORTLINK','RATE',s.rate,null) rate,\n" +
                    "       0 length,\n" +
                    "       dbo.sr('TR_TRSYSTEM', s.systemid) system_name\n" +
                    "  from tr_portlink s\n" +
                    "  where s.portlinkid in\n" +
                    "       (select portlinkid\n" +
                    "          from (select c.portlinkid,\n" +
                    "                                ROW_NUMBER() OVER(ORDER BY c.portlinkid) ROWNUM_\n" +
                    "                  from tr_portlink c\n" +
                    "                 where 1=1  %s)\n" +
                    "                 %s)", transNameSql, pageSql);
            System.out.println(sql);
            mapListResult = runner.query(sql, new MapListHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }

    /**
     * 时隙查询
     *
     * @param pageIndex
     * @param pageCount
     * @param portId
     * @return
     */
    public List<Map<String, Object>> getPorttsList(int pageIndex, int pageCount, String portId) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String pageSql = (pageCount > 0 && pageIndex > 0) ? String.format(" where rownum_>%d and rownum_<=%d", (pageIndex - 1) * pageCount,
                    pageIndex * pageCount) : "";
            String portIdSql = portId.isEmpty() ? "" : String.format(" and c.portid='%s'", portId);
            String sql = String.format("select s.tsid time_id,\n" +
                    "       s.portid port_id,\n" +
                    "       s.parenttsid p_id,\n" +
                    "       '' vc4,\n" +
                    "       '' vc3,\n" +
                    "       '' vc2,\n" +
                    "       '' vc12,\n" +
                    "       dbo.sf('TR_PORTTS', 'RATE', s.rate, null) rate,\n" +
                    "       '' dir\n" +
                    "  from tr_portts s\n" +
                    "  where s.tsid in\n" +
                    "       (select tsid\n" +
                    "          from (select c.tsid,\n" +
                    "                                ROW_NUMBER() OVER(ORDER BY c.tsid) ROWNUM_\n" +
                    "                  from tr_portts c\n" +
                    "                 where 1=1  %s)\n" +
                    "                 %s)", portIdSql, pageSql);
            System.out.println(sql);
            mapListResult = runner.query(sql, new MapListHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }

    /**
     * 交叉查询
     *
     * @param pageIndex
     * @param pageCount
     * @param portId
     * @param slotId
     * @return
     */
    public List<Map<String, Object>> getInnerJumpList(int pageIndex, int pageCount, String portId, String slotId) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String pageSql = (pageCount > 0 && pageIndex > 0) ?
                    String.format(" where rownum_>%d and rownum_<=%d", (pageIndex - 1) * pageCount, pageIndex * pageCount) : "";
            String portIdSql = portId.isEmpty() ? "" :
                    String.format(" and (c.aportid='%s' or c.bportid='%s')", portId, portId);
            String slotIdSql = slotId.isEmpty() ? "" :
                    String.format(" and (c.atsid='%s' or c.btsid='%s')", slotId, slotId);

            String sql = String.format("select s.ncresinsid equip_id,\n" +
                    "       s.aportid port_a,\n" +
                    "       s.atsid slot_a,\n" +
                    "       s.bportid port_z,\n" +
                    "       s.btsid slot_z,\n" +
                    "       dbo.sf('TR_INNERJUMP', 'RATE', s.rate, null) rate,\n" +
                    "       s.innerjumpid cross_id,\n" +
                    "       dbo.sf('TR_INNERJUMP', 'JUMPTYPE', s.JUMPTYPE, null) type\n" +
                    "  from tr_innerjump s\n" +
                    "  where s.innerjumpid in\n" +
                    "       (select innerjumpid\n" +
                    "          from (select c.innerjumpid,\n" +
                    "                                ROW_NUMBER() OVER(ORDER BY c.innerjumpid) ROWNUM_\n" +
                    "                  from tr_innerjump c\n" +
                    "                 where 1=1  %s  %s)\n" +
                    "                 %s)", portIdSql, slotIdSql, pageSql);
            System.out.println(sql);
            mapListResult = runner.query(sql, new MapListHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }
}
