package com.hj.dao;

import com.hj.utils.DBUtilsHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-06-09.
 */
public class EquipmentDao {
    /**
     * SDH设备查询
     *
     * @param pageIndex
     * @param pageCount
     * @param sysName
     * @param stId
     * @param stName
     * @param roomId
     * @param roomName
     * @param rackId
     * @param rackName
     * @param equipId
     * @param equipName
     * @return
     */
    public List<Map<String, Object>> getSDHList(int pageIndex, int pageCount, String sysName, String stId, String stName,
                                                String roomId, String roomName, String rackId, String rackName, String equipId,
                                                String equipName, String qrcode, String relationId) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String pageSql = (pageIndex > 0 && pageCount > 0) ? String.format(" where rownum_>%d and rownum_<=%d", (pageIndex - 1) * pageCount,
                    pageIndex * pageCount) : "";
            String sysNameSql = sysName.isEmpty() ? "" :
                    String.format(" and dbo.sf('TR_SDH', 'NETTYPE', c.NETTYPE, null) like '%%%s%%'", sysName);
            String stIdSql = stId.isEmpty() ? "" :
                    String.format(" and c.sn='%s'", stId);
            String stNameSql = stName.isEmpty() ? "" :
                    String.format(" and dbo.sf('TR_SDH', 'SN', c.SN, null) like '%%%s%%'", stName);
            String roomIdSql = roomId.isEmpty() ? "" :
                    String.format(" and c.MROOMID='%s'", roomId);
            String roomNameSql = roomName.isEmpty() ? "" :
                    String.format(" and dbo.sf('TR_SDH', 'MROOMID', c.MROOMID, null) like '%%%s%%'", roomName);
            String rackIdSql = rackId.isEmpty() ? "" : String.format(" and c.DEVRACKID='%s'", rackId);
            String rackNameSql = rackName.isEmpty() ? "" :
                    String.format(" and dbo.sf('TR_SDH', 'DEVRACKID', c.DEVRACKID, null) like '%%%s%%'", rackName);
            String idSql = equipId.isEmpty() ? "" :
                    String.format(" and c.devid='%s'", equipId);
            String nameSql = equipName.isEmpty() ? "" :
                    String.format(" and c.devname like '%%%s%%'", equipName);
            String relationSql = relationId.isEmpty() ? "" :
                    String.format(" and c.devid='%s'", relationId);
            String qrcodeSql = "";

            String sql = String.format("select s.devid equip_id,\n" +
                            "       s.devcode equip_code,\n" +
                            "       s.devname equip_name,\n" +
                            "       s.supname name_std,\n" +
                            "       dbo.sf('TR_SDH', 'DEVTYPE', s.DEVTYPE, null) equip_type,\n" +
                            "       dbo.sf('TR_SDH', 'VENDORMODELID', s.VENDORMODELID, null) model,\n" +
                            "       dbo.sf('TR_SDH', 'VENDORID', s.VENDORID, null) vender,\n" +
                            "       s.facversion version,\n" +
                            "       to_char(s.workdate, 'YYYY-MM-DD') beg_date,\n" +
                            "       dbo.sf('TR_SDH', 'FACSTATUS', s.FACSTATUS, null) status,\n" +
                            "       dbo.sf('TR_SDH', 'MAINUNIT', s.MAINUNIT, null) maintain_dept,\n" +
                            "       dbo.sf('TR_SDH', 'MROOMID', s.MROOMID, null) room_name,\n" +
                            "       s.mroomid room_id,\n" +
                            "       dbo.sf('TR_SDH', 'DEVRACKID', s.DEVRACKID, null) rack_name,\n" +
                            "       s.DEVRACKID rack_id,\n" +
                            "       dbo.sf('TR_SDH', 'MANATYPE', s.MANATYPE, null) disp_dept,\n" +
                            "       to_char(s.RESTDATE, 'YYYY-MM-DD') end_date,\n" +
                            "       s.SOFTVER soft_version,\n" +
                            "       '' purpose,\n" +
                            "       '' gateway_net,\n" +
                            "       dbo.sf('TR_SDH', 'SN', s.SN, null) station_name,\n" +
                            "       s.sn station_id,\n" +
                            "       s.remark remark,\n" +
                            "       (select max(l.cx) from systopoinsloc l where l.resid='TR_SDH' and l.resinstid=s.devid) x,\n" +
                            "       (select max(l.cy) from systopoinsloc l where l.resid='TR_SDH' and l.resinstid=s.devid) y,\n" +
                            "       dbo.sf('TR_SDH', 'NETTYPE', s.NETTYPE, null) sys_name,\n" +
                            "       '' qr_code,\n" +
                            "       s.devid relation_id\n" +
                            "  from tr_sdh s" +
                            " where s.devid IN (SELECT devid\n" +
                            "                         FROM (SELECT c.devid,\n" +
                            "                                      ROW_NUMBER() OVER(ORDER BY c.devid) ROWNUM_\n" +
                            "                                 FROM tr_sdh c\n" +
                            "                                where 1 = 1 %s %s %s %s %s %s %s %s %s %s %s) tt\n" +
                            "                         %s)", sysNameSql, stIdSql, stNameSql, roomIdSql, roomNameSql,
                    rackIdSql, rackNameSql, idSql, nameSql, relationSql, qrcodeSql, pageSql);
            System.out.println(sql);
            mapListResult = runner.query(sql, new MapListHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }


    /**
     * SDH设备板卡
     *
     * @param pageIndex
     * @param pageCount
     * @param equipId
     * @param equipName
     * @return
     */
    public List<Map<String, Object>> getSDHCardList(int pageIndex, int pageCount, String equipId, String equipName) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String pageSql = (pageCount > 0 && pageIndex > 0) ?
                    String.format(" and rownum_>%d and rownum_<=%d", (pageIndex - 1) * pageCount,
                            pageCount * pageIndex) : "";
            String idSql = equipId.isEmpty() ? "" :
                    String.format(" and c.devinsid='%s'", equipId);
            String nameSql = equipName.isEmpty() ? "" :
                    String.format(" and dbo.sf('TR_DEVBOARD', 'DEVINSID', c.devinsid, c.devresid) like '%%%s%%'", equipName);

            String sql = String.format("select s.boardid card_id,\n" +
                    "       '' card_code,\n" +
                    "       s.boardorder sn,\n" +
                    "       '' card_name,\n" +
                    "       s.supcode name_std,\n" +
                    "       dbo.sf('TR_DEVBOARD', 'BOARDTYPE', s.BOARDTYPE, null) card_type,\n" +
                    "       '' model,\n" +
                    "       s.APPVERSION version,\n" +
                    "       '' status,\n" +
                    "       s.rackid rack_id,\n" +
                    "       dbo.sf('TR_DEVBOARD', 'RACKID', s.rackid, null) rack_name,\n" +
                    "       s.slotid slot_id,\n" +
                    "       dbo.sf('TR_DEVBOARD', 'SLOTID', s.slotid, null) slot_name,\n" +
                    "       s.devinsid equip_id,\n" +
                    "       dbo.sf('TR_DEVBOARD', 'DEVINSID', s.devinsid, s.devresid) equip_name\n" +
                    "  from tr_devboard s\n" +
                    " where s.boardid in (select boardid\n" +
                    "                       from (select c.boardid,\n" +
                    "                                    ROW_NUMBER() OVER(ORDER BY c.boardid) ROWNUM_\n" +
                    "                               from tr_devboard c\n" +
                    "                              where 1 = 1 %s %s)\n" +
                    "                      %s)", idSql, nameSql, pageSql);
            System.out.println(sql);
            mapListResult = runner.query(sql, new MapListHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }

    /**
     * SDH设备承载业务
     *
     * @param pageIndex
     * @param pageCount
     * @param equipId
     * @param equipName
     * @return
     */
    public List<Map<String, Object>> getSDHBusiList(int pageIndex, int pageCount, String equipId, String equipName) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String pageSql = (pageIndex > 0 && pageCount > 0) ? String.format(" where rownum_>%d and rownum_<=%d", (pageIndex - 1) * pageCount,
                    pageIndex * pageCount) : "";
            String equipIdSql = equipId.isEmpty() ? "" : String.format(" and (c.a_devinsid='%s' or c.z_devinsid='%s')", equipId, equipId);
            String equipNameSql = equipName.isEmpty() ? "" : String.format(" and (dbo.sf('CIRCUITBUSIGROUP', 'a_devinsid', c.a_devinsid, c.a_devresid) like '%%%s%%'" +
                    "or dbo.sf('CIRCUITBUSIGROUP', 'z_devinsid', c.z_devinsid, c.z_devresid) like '%%%s%%')", equipName, equipName);
            String sql = String.format("select s.busigroupid bus_id,\n" +
                    "       s.businame bus_name,\n" +
                    "       dbo.sf('CIRCUITBUSIGROUP', 'BUSITYPE', s.busitype, null) bus_type,\n" +
                    "       s.a_sn station_a_id,\n" +
                    "       s.z_sn station_z_id,\n" +
                    "       t.a_devrxportid port_a_id,\n" +
                    "       t.z_devrxportid port_z_id,\n" +
                    "       s.a_devinsid equip_a_id,\n" +
                    "       s.z_devinsid equip_z_id,\n" +
                    "       dbo.sf('CIRCUITBUSIGROUP', 'a_sn', s.a_sn, null) station_a_name,\n" +
                    "       dbo.sf('CIRCUITBUSIGROUP', 'z_sn', s.z_sn, null) station_z_name,\n" +
                    "       dbo.sf('TR_CIRCUIT',\n" +
                    "              'a_devrxportid',\n" +
                    "              t.a_devrxportid,\n" +
                    "              t.a_devrxporttype) port_a_name,\n" +
                    "       dbo.sf('TR_CIRCUIT',\n" +
                    "              'z_devrxportid',\n" +
                    "              t.z_devrxportid,\n" +
                    "              t.b_devrxporttype) port_z_name,\n" +
                    "       dbo.sf('CIRCUITBUSIGROUP', 'a_devinsid', s.a_devinsid, s.a_devresid) equip_a_name,\n" +
                    "       dbo.sf('CIRCUITBUSIGROUP', 'z_devinsid', s.z_devinsid, s.z_devresid) equip_z_name,\n" +
                    "       dbo.sf('TR_CIRCUIT', 'a_tsid', t.a_tsid, null) a_slot,\n" +
                    "       dbo.sf('TR_CIRCUIT', 'z_tsid', t.z_tsid, null) z_slot,\n" +
                    "       to_char(t.finishdate, 'yyyy-mm-dd') beg_date,\n" +
                    "       dbo.sf('CIRCUITBUSIGROUP', 'useunit', s.useunit, null) use_dept,\n" +
                    "       dbo.sf('CIRCUITBUSIGROUP', 'BUSISTATUS', s.BUSISTATUS, null) status,\n" +
                    "       '' fiber_id\n" +
                    "  from circuitbusigroup s, circuitbusilink l, tr_circuit t\n" +
                    " where s.busigroupid = l.busigroupid\n" +
                    "   and l.lineresid = 'TR_CIRCUIT'\n" +
                    "   and l.lineid = t.circuitid\n" +
                    "   and s.busigroupid in (select busigroupid\n" +
                    "                           from (select c.busigroupid,\n" +
                    "                                        row_number() over(order by c.busigroupid) rownum_\n" +
                    "                                   from circuitbusigroup c\n" +
                    "                                  where 1 = 1  %s %s)\n" +
                    "                          %s)", equipIdSql, equipNameSql, pageSql);
            System.out.println(sql);
            mapListResult = runner.query(sql, new MapListHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }


    /**
     * SDH设备信息修改
     *
     * @param equipId
     * @param matainDept
     * @param dispDept
     * @param endDate
     * @param softVer
     * @param purpose
     * @param qrcode
     * @return
     */
    public int updateSDH(String equipId, String matainDept, String dispDept, String endDate, String softVer, String purpose, String qrcode) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        int result = 0;
        StringBuilder builder = new StringBuilder();
        if (matainDept.isEmpty() && dispDept.isEmpty() && endDate.isEmpty() && softVer.isEmpty()) {
            return result;
        }

        builder.append("update tr_sdh set ");
        if (!matainDept.isEmpty()) {
            builder.append(String.format(" MAINTAINUNIT='%s'", matainDept));
        }
        if (!dispDept.isEmpty()) {
            builder.append(String.format(" MANATYPE='%s'", dispDept));
        }
        if (!endDate.isEmpty()) {
            builder.append(String.format(" RESTDATE=to_date('%s','yyyy-mm-dd')", endDate));
        }
        if (!softVer.isEmpty()) {
            builder.append(String.format(" SOFTVER='%s'", softVer));
        }
        if (!purpose.isEmpty()) {

        }

        builder.append(String.format(" where DEVID='%s'", equipId));

        try {
            System.out.println(builder);
            result = runner.update(builder.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    /**
     * SDH设备端口
     *
     * @param pageIndex
     * @param pageCount
     * @param equipId
     * @param cardId
     * @param qrcode
     * @return
     */
    public List<Map<String, Object>> getSDHPortList(int pageIndex, int pageCount, String equipId, String cardId, String qrcode) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String pageSql = (pageIndex > 0 && pageCount > 0) ?
                    String.format(" where rownum_>%d and rownum_<=%d", (pageIndex - 1) * pageCount,
                            pageIndex * pageCount) : "";
            String equipSql = equipId.isEmpty() ? "" :
                    String.format(" and c.devinsid='%s'", equipId);
            String cardSql = cardId.isEmpty() ? "" :
                    String.format(" and c.boardid='%s'", cardId);
            String qrcodeSql = "";

            String sql = String.format("select s.portid port_Dd,\n" +
                    "       s.portcode port_name,\n" +
                    "       s.supname name_std,\n" +
                    "       s.orderinboard port_serial,\n" +
                    "       dbo.sf('TR_DEVPORT', 'portmode', s.portmode, null) port_type,\n" +
                    "       dbo.sf('TR_DEVPORT', 'rxtx', s.rxtx, null) direction,\n" +
                    "       dbo.sf('TR_DEVPORT', 'circuitid', s.circuitid, null) purpose,\n" +
                    "       dbo.sf('TR_DEVPORT', 'portrate', s.portrate, null) rate,\n" +
                    "       dbo.sf('TR_DEVPORT', 'busistatus', s.busistatus, null) status,\n" +
                    "       dbo.sf('TR_DEVPORT', 'devinsid', s.devinsid, s.devresid) equip_name,\n" +
                    "       s.devinsid equip_id,\n" +
                    "       s.boardid card_id,\n" +
                    "       dbo.sf('TR_DEVPORT', 'boardid', s.boardid, null) card_name,\n" +
                    "       (select t.bportid\n" +
                    "          from tr_portlink t\n" +
                    "         where t.aportid = s.portid\n" +
                    "        union\n" +
                    "        select t.aportid\n" +
                    "          from tr_portlink t\n" +
                    "         where t.bportid = s.portid) dd_port_id,\n" +
                    "       (select dbo.sr('TR_DEVPORT', t.bportid)\n" +
                    "          from tr_portlink t\n" +
                    "         where t.aportid = s.portid\n" +
                    "        union\n" +
                    "        select dbo.sr('TR_DEVPORT', t.aportid)\n" +
                    "          from tr_portlink t\n" +
                    "         where t.bportid = s.portid) dd_port_name,\n" +
                    "       (select p.supname\n" +
                    "          from tr_portlink t, tr_devport p\n" +
                    "         where t.aportid = s.portid\n" +
                    "           and t.bportid = p.portid\n" +
                    "        union\n" +
                    "        select p.supname\n" +
                    "          from tr_portlink t, tr_devport p\n" +
                    "         where t.bportid = s.portid\n" +
                    "           and t.aportid = p.portid) dd_name_std,\n" +
                    "       (select p.orderinboard\n" +
                    "          from tr_portlink t, tr_devport p\n" +
                    "         where t.aportid = s.portid\n" +
                    "           and t.bportid = p.portid\n" +
                    "        union\n" +
                    "        select p.orderinboard\n" +
                    "          from tr_portlink t, tr_devport p\n" +
                    "         where t.bportid = s.portid\n" +
                    "           and t.aportid = p.portid) dd_port_serial\n" +
                    "  from tr_devport s\n" +
                    " where s.portid in\n" +
                    "       (select portid\n" +
                    "          from (select c.portid, ROW_NUMBER() OVER(ORDER BY c.portid) ROWNUM_\n" +
                    "                  from tr_devport c" +
                    "                   where 1=1 %s %s %s)\n" +
                    "         %s)", qrcodeSql, equipSql, cardSql, pageSql);
            System.out.println(sql);
            mapListResult = runner.query(sql, new MapListHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }

    /**
     * SDH设备端口信息修改
     *
     * @param portId
     * @param qrcode
     * @return
     */
    public int updateSDHPort(String portId, String qrcode) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        String sql = "update TR_DEVPORT set ";

        int result = 0;
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
