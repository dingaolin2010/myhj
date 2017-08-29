package com.hj.dao;

import com.csg.activemq.model.FileDownloadModel;
import com.hj.utils.DBUtilsHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-06-09.
 */
public class CircuitDao {

    /**
     * 业务电路查询
     *
     * @param pageCount
     * @param pageIndex
     * @param circuitId
     * @param circuitName
     * @param operId
     * @return
     */
    public List<Map<String, Object>> getCircuitList(int pageCount, int pageIndex, String circuitId, String circuitName, String operId) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String idSql = circuitId.isEmpty() ? "" :
                    String.format(" and c.circuitid='%s'", circuitId);
            String nameSql = circuitName.isEmpty() ? "" :
                    String.format(" and c.circuitname like '%%%s%%'", circuitName);
            String operSql = operId.isEmpty() ? "" :
                    String.format(" and c.DISPATCHID='%s'", operId);
            String pageSql = (pageCount > 0 && pageIndex > 0) ?
                    String.format(" where rownum_>%d and rownum_<=%d", (pageIndex - 1) * pageCount,
                            pageIndex * pageCount) : "";

            String sql = String.format("select t.CIRCUITID circuit_id,\n" +
                    "       t.CIRCUITCODE circuit_code,\n" +
                    "       t.CIRCUITNAME circuit_name,\n" +
                    "       dbo.sf('TR_CIRCUIT','RATE', t.RATE,null) rate,\n" +
                    "       k.DISPATCHBILLNO oper_id,\n" +
                    "       t.CUSTOMERID user_name,\n" +
                    "       TO_CHAR(t.finishdate, 'YYYY-MM-DD') beg_date,\n" +
                    "       np_public_busi.get_key_exch_show(t.a_devtype, 'sn', t.a_devid) station_a_name,\n" +
                    "       np_public_busi.get_key_exch_show(t.z_devtype, 'sn', t.z_devid) station_z_name,\n" +
                    "       np_public_busi.get_key_exch(t.a_devtype, 'sn', t.a_devid) station_a_id,\n" +
                    "       np_public_busi.get_key_exch(t.z_devtype, 'sn', t.z_devid) station_z_id,\n" +
                    "       np_public_busi.get_key_exch('TR_DEVPORT', 'supcode', t.a_devrxportid) port_a_name,\n" +
                    "       np_public_busi.get_key_exch('TR_DEVPORT', 'supcode', t.z_devrxportid) port_z_name,\n" +
                    "       t.a_devrxportid port_a_id,\n" +
                    "       t.z_devrxportid port_z_id,\n" +
                    "       t.A_TSID slot_a,\n" +
                    "       t.Z_TSID slot_z\n" +
                    "  from TR_CIRCUIT t\n" +
                    "  left join kaitong_designbill k\n" +
                    "    on t.circuitid = k.remoteid\n" +
                    " where t.circuitid IN (SELECT circuitid\n" +
                    "                         FROM (SELECT c.circuitid,\n" +
                    "                                      ROW_NUMBER() OVER(ORDER BY c.circuitid) ROWNUM_\n" +
                    "                                 FROM TR_CIRCUIT c\n" +
                    "                                where 1 = 1 %s %s %s) tt\n" +
                    "                         %s)", idSql, operSql, nameSql, pageSql);
            System.out.println(sql);
            mapListResult = runner.query(sql, new MapListHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }

    /**
     * 业务方式单
     *
     * @param pageCount
     * @param pageSize
     * @param circuitId
     * @return
     */
    public List<Map<String, Object>> getCircuitOperationResList(int pageCount, int pageSize, String circuitId) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String pageSql = (pageCount > 0 && pageSize > 0) ? String.format(" where rownum_>%d and rownum_<=%d", (pageCount - 1) * pageSize,
                    pageCount * pageSize) : "";
            String circuitSql = circuitId.isEmpty() ? "" : String.format(" and c.circuitid='%s'", circuitId);
            String sql = String.format("select t.CIRCUITID circuit_id,\n" +
                    "       t.CIRCUITCODE circuit_code,\n" +
                    "       t.CIRCUITNAME circuit_name,\n" +
                    "       dbo.sf('TR_CIRCUIT','RATE',t.RATE,null) rate,\n" +
                    "       '' net_type,\n" +
                    "       '' disp_type,\n" +
                    "       k.DISPATCHBILLNO oper_id,\n" +
                    "       t.CUSTOMERID user_name,\n" +
                    "       TO_CHAR(t.finishdate, 'YYYY-MM-DD') beg_date,\n" +
                    "       '' status,\n" +
                    "       np_public_busi.get_key_exch_show(t.a_devtype, 'sn', t.a_devid) station_a_name,\n" +
                    "       np_public_busi.get_key_exch_show(t.z_devtype, 'sn', t.z_devid) station_z_name,\n" +
                    "       np_public_busi.get_key_exch(t.a_devtype, 'sn', t.a_devid) station_a_id,\n" +
                    "       np_public_busi.get_key_exch(t.z_devtype, 'sn', t.z_devid) station_z_id,\n" +
                    "       np_public_busi.get_key_exch('TR_DEVPORT', 'supcode', t.a_devrxportid) port_a_name,\n" +
                    "       np_public_busi.get_key_exch('TR_DEVPORT', 'supcode', t.z_devrxportid) port_z_name,\n" +
                    "       t.a_devrxportid port_a_id,\n" +
                    "       t.z_devrxportid port_z_id,\n" +
                    "       t.A_TSID slot_a,\n" +
                    "       t.Z_TSID slot_z\n" +
                    "  from TR_CIRCUIT t\n" +
                    "  left join kaitong_designbill k\n" +
                    "    on t.circuitid = k.remoteid\n" +
                    " where t.circuitid IN (SELECT circuitid\n" +
                    "                         FROM (SELECT c.circuitid,\n" +
                    "                                      ROW_NUMBER() OVER(ORDER BY c.circuitid) ROWNUM_\n" +
                    "                                 FROM TR_CIRCUIT c\n" +
                    "                                where 1 = 1 %s ) tt\n" +
                    "                         %s)", circuitSql, pageSql);
            System.out.println(sql);
            mapListResult = runner.query(sql, new MapListHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }

    /**
     * 业务关联其他业务列表
     *
     * @param pageCount
     * @param pageSize
     * @param circuitId
     * @return
     */
    public List<Map<String, Object>> getCircuitBussinessResList(int pageCount, int pageSize, String circuitId) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }
}
