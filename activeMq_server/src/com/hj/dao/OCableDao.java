package com.hj.dao;

import com.hj.utils.DBUtilsHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-06-12.
 */
public class OCableDao {


    /**
     * 光缆查询
     *
     * @param pageCount
     * @param pageSize
     * @param stId
     * @param stName
     * @param roomId
     * @param roomName
     * @param ocableId
     * @param ocableName
     * @return
     */
    public List<Map<String, Object>> getOCableList(int pageCount, int pageSize, String stId, String stName, String roomId, String roomName,
                                                   String ocableId, String ocableName) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;

        try {
            String pageSql = (pageCount > 0 && pageSize > 0) ?
                    String.format(" where rownum_>%d and rownum_<=%d", (pageCount - 1) * pageSize,
                            pageCount * pageSize) : "";
            String stIdSql = stId.isEmpty() ? "" :
                    String.format(" and (c.a_sn='%s' or c.b_sn='%s')", stId, stId);
            String stNameSql = stName.isEmpty() ? "" :
                    String.format(" and (dbo.sf('OPTSEG', 'A_SN', c.A_SN, null) like '%%%s%%'" +
                            " or dbo.sf('OPTSEG', 'B_SN', c.B_SN, null) like '%%%s%%')", stName, stName);
            String roomIdSql = roomId.isEmpty() ? "" :
                    String.format(" and (c.a_mroomid='%s' or c.b_mroomid='%s')", roomId, roomId);
            String roomNameSql = roomName.isEmpty() ? "" :
                    String.format(" and (dbo.sf('OPTSEG', 'A_MROOMID', c.A_MROOMID, null) like '%%%s%%'" +
                            " or dbo.sf('OPTSEG', 'B_MROOMID', c.B_MROOMID, null) like '%%%s%%')", roomName, roomName);
            String ocableIdSql = ocableId.isEmpty() ? "" :
                    String.format(" and c.OPTSEGID='%s'", ocableId);
            String ocableNameSql = ocableName.isEmpty() ? "" :
                    String.format(" and c.OPTSEGNAME like '%%%s%%'", ocableName);

            String sql = String.format("select s.OPTSEGID ocable_id,\n" +
                    "       s.OPTSEGCODE ocable_code,\n" +
                    "       s.OPTSEGNAME ocable_name,\n" +
                    "       dbo.sf('OPTSEG', 'OPTCABLEID', s.OPTCABLEID, null) purpose,\n" +
                    "       s.a_sn station_a_id,\n" +
                    "       s.b_sn station_z_id,\n" +
                    "       dbo.sf('OPTSEG', 'A_SN', s.A_SN, null) station_a_name,\n" +
                    "       dbo.sf('OPTSEG', 'B_SN', s.B_SN, null) station_z_name,\n" +
                    "       dbo.sf('OPTSEG', 'A_MROOMID', s.A_MROOMID, null) room_a,\n" +
                    "       s.a_mroomid room_a_id,\n" +
                    "       dbo.sf('OPTSEG', 'B_MROOMID', s.B_MROOMID, null) room_z,\n" +
                    "       s.B_MROOMID room_z_id,\n" +
                    "       s.ELTLINECODE trans_line,\n" +
                    "       s.optseglen / 1000.0 length,\n" +
                    "       s.FIBERNUM fiber_num,\n" +
                    "       dbo.sf('OPTSEG', 'OPTSETUP', s.OPTSETUP, null) lay_way,\n" +
                    "       dbo.sf('OPTSEG', 'FACSTATUS', s.FACSTATUS, null) status,\n" +
                    "       to_char(s.WORKDATE, 'yyyy-mm-dd') beg_date,\n" +
                    "       dbo.sf('OPTSEG', 'DUTYUNITID', s.DUTYUNITID, null) maintain_dept,\n" +
                    "       dbo.sf('OPTSEG', 'MANAGEUNIT', s.MANAGEUNIT, null) disp_dept,\n" +
                    "       to_char(s.RESTDATE, 'yyyy-mm-dd') end_date,\n" +
                    "       dbo.sf('OPTSEG', 'MODELID', s.MODELID, null) model,\n" +
                    "       dbo.sf('OPTSEG', 'VENDORID', s.VENDORID, null) vender,\n" +
                    "       dbo.sf('OPTSEG', 'OPTTPYE', s.OPTTPYE, null) ocable_type,\n" +
                    "       s.remark remarks\n" +
                    "  from optseg s" +
                    " where s.OPTSEGID in\n" +
                    "       (select OPTSEGID\n" +
                    "          from (select c.OPTSEGID, ROW_NUMBER() OVER(ORDER BY c.OPTSEGID) ROWNUM_\n" +
                    "                  from optseg c" +
                    "                   where 1=1 %s %s %s %s %s %s)\n" +
                    "         %s)", stIdSql, stNameSql, roomIdSql, roomNameSql, ocableIdSql, ocableNameSql, pageSql);
            System.out.println(sql);
            mapListResult = runner.query(sql, new MapListHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }


    /**
     * 光缆承载的光路
     *
     * @param pageCount
     * @param pageSize
     * @param oCableId
     * @return
     */
    public List<Map<String, Object>> getOCableRouteList(int pageCount, int pageSize, String oCableId) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String pageSql = (pageCount > 0 && pageSize > 0) ? String.format(" where rownum_>%d and rownum_<=%d", (pageCount - 1) * pageSize,
                    pageCount * pageSize) : "";
            String oCableIdSql = oCableId.isEmpty() ? "" : String.format(" and f.OPTSEGID='%s'", oCableId);
            String sql = String.format("select s.OPTROADID oroute_id,\n" +
                    "       s.OPTROADNAME oroute_name,\n" +
                    "       s.A_MROOMID room_a_id,\n" +
                    "       s.Z_MROOMID room_z_id,\n" +
                    "       dbo.sf('OPTROAD', 'A_MROOMID', s.A_MROOMID, null) room_a_name,\n" +
                    "       dbo.sf('OPTROAD', 'Z_MROOMID', s.Z_MROOMID, null) room_z_name,\n" +
                    "       s.A_SN station_a_id,\n" +
                    "       s.Z_SN station_z_id,\n" +
                    "       dbo.sf('OPTROAD', 'A_SN', s.A_SN, null) station_a_name,\n" +
                    "       dbo.sf('OPTROAD', 'Z_SN', s.Z_SN, null) station_z_name\n" +
                    "  from optroad s\n" +
                    "  where s.optroadid in\n" +
                    "       (select optroadid\n" +
                    "          from (select distinct c.optroadid,\n" +
                    "                                ROW_NUMBER() OVER(ORDER BY c.optroadid) ROWNUM_\n" +
                    "                  from fiber f, optroad c\n" +
                    "                 where f.optroadid = c.optroadid" +
                    "                   %s)\n" +
                    "                 %s)", oCableIdSql, pageSql);
            System.out.println(sql);
            mapListResult = runner.query(sql, new MapListHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }

    /**
     * 光路承载的电路
     *
     * @param pageCount
     * @param pageSize
     * @param routId
     * @return
     */
    public List<Map<String, Object>> getOCableCircuitList(int pageCount, int pageSize, String routId) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;

        try {
            String pageSql = (pageCount > 0 && pageSize > 0) ? String.format(" where rownum_>%d and rownum_<=%d", (pageCount - 1) * pageSize,
                    pageCount * pageSize) : "";
            String sql = String.format("select s.CIRCUITID circuit_id,\n" +
                    "       s.CIRCUITCODE circuit_code,\n" +
                    "       s.CIRCUITNAME circuit_name,\n" +
                    "       dbo.sf('TR_CIRCUIT','RATE',s.RATE,null) rate,\n" +
                    "       '' net_type,\n" +
                    "       '' disp_type,\n" +
                    "       k.DISPATCHBILLNO oper_id,\n" +
                    "       s.CUSTOMERID user_name,\n" +
                    "       TO_CHAR(s.finishdate, 'YYYY-MM-DD') beg_date,\n" +
                    "       '' status,\n" +
                    "       np_public_busi.get_key_exch_show(s.a_devtype, 'sn', s.a_devid) station_a_name,\n" +
                    "       np_public_busi.get_key_exch_show(s.z_devtype, 'sn', s.z_devid) station_z_name,\n" +
                    "       np_public_busi.get_key_exch(s.a_devtype, 'sn', s.a_devid) station_a_id,\n" +
                    "       np_public_busi.get_key_exch(s.z_devtype, 'sn', s.z_devid) station_z_id,\n" +
                    "       np_public_busi.get_key_exch('TR_DEVPORT', 'supcode', s.a_devrxportid) port_a_name,\n" +
                    "       np_public_busi.get_key_exch('TR_DEVPORT', 'supcode', s.z_devrxportid) port_z_name,\n" +
                    "       s.a_devrxportid port_a_id,\n" +
                    "       s.z_devrxportid port_z_id,\n" +
                    "       s.A_TSID slot_a,\n" +
                    "       s.Z_TSID slot_z\n" +
                    "  from TR_CIRCUIT s\n" +
                    "  left join kaitong_designbill k\n" +
                    "    on s.circuitid = k.remoteid\n" +
                    " where s.circuitid in\n" +
                    "       (select circuitid\n" +
                    "          from (select t.circuitid,\n" +
                    "                       ROW_NUMBER() OVER(ORDER BY t.circuitid) ROWNUM_\n" +
                    "                  from TR_CIRCUIT t\n" +
                    "                 where exists\n" +
                    "                 (select 1\n" +
                    "                          from TR_LOGICCHN l\n" +
                    "                         where l.Circuitid = t.CIRCUITID\n" +
                    "                           and exists\n" +
                    "                         (select 1\n" +
                    "                                  from TR_PORTLINK p\n" +
                    "                                 where p.portlinkid = l.portlinkid\n" +
                    "                                   and p.supresid = 'OPTROAD'\n" +
                    "                                   and exists\n" +
                    "                                 (select 1\n" +
                    "                                          from OPTROAD o\n" +
                    "                                         where o.optroadid = p.supinsid\n" +
                    "                                           and o.optroadid ='%s'))))" +
                    "                                      %s)", routId, pageSql);
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
     * 修改光缆行走向经纬度
     *
     * @param oCableId
     * @param direction
     * @return
     */
    public int updateOptSegDirection(String oCableId, String direction) {
        if (direction.isEmpty())
            return 0;
        return updateOptSeg(oCableId, "", "", "", "", "", direction);
    }


    /**
     * 光纤查询
     *
     * @param pageCount
     * @param pageSize
     * @param equipId
     * @param equipName
     * @param termId
     * @param termName
     * @param oCableId
     * @param oCableName
     * @return
     */
    public List<Map<String, Object>> getFiberList(int pageCount, int pageSize, String equipId, String equipName,
                                                  String termId, String termName, String oCableId, String oCableName) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String pageSql = (pageCount > 0 && pageSize > 0) ?
                    String.format(" where rownum_>%d and rownum_<=%d", (pageCount - 1) * pageSize,
                            pageCount * pageSize) : "";
            String equipIdSql = equipId.isEmpty() ? "" : String.format(" and (c.a_Devinsid='%s' or c.b_Devinsid='%s')", equipId, equipId);
            String equipNameSql = equipName.isEmpty() ? "" : String.format(" and (dbo.sf('FIBER','A_DEVINSID',c.A_DEVINSID, c.A_DEVRESID) like '%%%s%%' " +
                    "or dbo.sf('FIBER','B_DEVINSID',c.B_DEVINSID, c.B_DEVRESID) like '%%%s%%')", equipName, equipName);
            String termIdSql = termId.isEmpty() ? "" : String.format(" and (c.a_portinsid='%s' or c.b_portinsid='%s')", termId);
            String termNameSql = termName.isEmpty() ? "" : String.format(" and (dbo.sf('FIBER','A_PORTINSID',c.A_PORTINSID, c.A_PORTRESID) like '%%%s%%'" +
                    "or dbo.sf('FIBER','B_PORTINSID',c.B_PORTINSID, c.B_PORTRESID) like '%%%s%%')", termName, termName);
            String oCableIdSql = oCableId.isEmpty() ? "" : String.format(" and c.optsegid='%s'", oCableId);
            String oCableNameSql = oCableName.isEmpty() ? "" : String.format(" and dbo.sf('FIBER','OPTSEGID',c.OPTSEGID,null) like '%%%s%%'", oCableName);
            String sql = String.format("SELECT s.FIBERID fiber_id,\n" +
                            "       '' name_std,\n" +
                            "       '' fiber_type,\n" +
                            "       '' status,\n" +
                            "       s.FIBERORDER sn,\n" +
                            "       s.FIBERLEN / 1000.0 length,\n" +
                            "       s.OPTSEGID ocable_id,\n" +
                            "       dbo.sf('FIBER', 'OPTSEGID', s.OPTSEGID, null) ocable_name,\n" +
                            "       dbo.sf('FIBER', 'A_DEVINSID', s.A_DEVINSID, s.A_DEVRESID) equip_a_name,\n" +
                            "       dbo.sf('FIBER', 'B_DEVINSID', s.B_DEVINSID, s.B_DEVRESID) equip_z_name,\n" +
                            "       dbo.sf('FIBER', 'A_PORTINSID', s.A_PORTINSID, s.A_PORTRESID) term_a_name,\n" +
                            "       dbo.sf('FIBER', 'B_PORTINSID', s.B_PORTINSID, s.B_PORTRESID) term_z_name,\n" +
                            "       s.a_Devinsid equip_a_id,\n" +
                            "       s.b_devinsid equip_z_id,\n" +
                            "       s.a_portinsid term_a_id,\n" +
                            "       s.b_portinsid term_z_id\n" +
                            "  from fiber s\n" +
                            "  where s.FIBERID in\n" +
                            "       (select FIBERID\n" +
                            "          from (select c.FIBERID,\n" +
                            "                                ROW_NUMBER() OVER(ORDER BY c.FIBERID) ROWNUM_\n" +
                            "                  from fiber c\n" +
                            "                 where 1=1  %s %s %s %s %s %s)\n" +
                            "                 %s)",
                    equipIdSql, equipNameSql, termIdSql, termNameSql, oCableIdSql, oCableNameSql, pageSql);
            System.out.println(sql);
            mapListResult = runner.query(sql, new MapListHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }


    /**
     * 接头盒查询
     *
     * @param pageCount
     * @param pageSize
     * @param cableId
     * @param cableName
     * @param boxId
     * @param boxName
     * @return
     */
    public List<Map<String, Object>> getOptJointList(int pageCount, int pageSize, String cableId, String cableName,
                                                     String boxId, String boxName) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;

        try {
            String pageSql = (pageCount > 0 && pageSize > 0) ?
                    String.format(" where rownum_>%d and rownum_<=%d", (pageCount - 1) * pageSize, pageCount * pageSize) : "";
            String cableIdSql = cableId.isEmpty() ? "" :
                    String.format(" and tt.optsegid='%s'", cableId);
            String cableNameSql = cableName.isEmpty() ? "" :
                    String.format(" and tt.optsegname like '%%%s%%'", cableName);
            String boxIdSql = boxId.isEmpty() ? "" :
                    String.format(" and c.optjointid='%s'", boxId);
            String boxNameSql = boxName.isEmpty() ? "" :
                    String.format(" and c.optjointname like '%%%s%%'", boxName);

            String sql = String.format("select s.optjointid box_id,\n" +
                    "       s.optjointname box_name,\n" +
                    "       o.optsegid ocable_id,\n" +
                    "       o.optsegname ocable_name,\n" +
                    "       '' serial,\n" +
                    "       dbo.sf('OPTJOINT', 'optjoint', s.optjoint, null) box_type,\n" +
                    "       dbo.sf('OPTJOINT', 'vendorid', s.vendorid, null) vendor,\n" +
                    "       dbo.sf('OPTJOINT', 'vendormodelid', s.vendormodelid, null) model,\n" +
                    "       s.longitudes lng,\n" +
                    "       s.latitudes lat,\n" +
                    "       s.address address,\n" +
                    "       '' capacity,\n" +
                    "       dbo.sf('OPTSEG', 'MANAGEUNIT', o.MANAGEUNIT, null) disp_dept,\n" +
                    "       dbo.sf('OPTSEG', 'DUTYUNITID', o.DUTYUNITID, null) maintain_dept,\n" +
                    "       to_char(s.WORKDATE, 'yyyy-mm-dd') productdate,\n" +
                    "       s.remark remark\n" +
                    "  from optjoint s\n" +
                    "  left join optseg o\n" +
                    "    on (o.a_resid = 'OPTJOINT' and o.a_resinsid = s.optjointid)\n" +
                    "    or (o.b_resid = 'OPTJOINT' and o.b_resinsid = s.optjointid)\n" +
                    " where s.optjointid in\n" +
                    "       (select optjointid\n" +
                    "          from (select c.optjointid,\n" +
                    "                       ROW_NUMBER() OVER(ORDER BY c.optjointid) ROWNUM_\n" +
                    "                  from optjoint c\n" +
                    "                  left join optseg tt\n" +
                    "                    on (tt.a_resid = 'OPTJOINT' and\n" +
                    "                       tt.a_resinsid = c.optjointid)\n" +
                    "                    or (tt.b_resid = 'OPTJOINT' and\n" +
                    "                       tt.b_resinsid = c.optjointid) \n" +
                    "                       where 1=1 %s %s %s %s)\n" +
                    "                       %s)", cableIdSql, cableNameSql, boxIdSql, boxNameSql, pageSql);
            System.out.println(sql);
            mapListResult = runner.query(sql, new MapListHandler());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }


    /**
     * 接头盒经纬度更新
     * @param boxId
     * @param lat
     * @param lng
     * @return
     */
    public int updateOptJointLatLng(String boxId, double lng, double lat) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        int result = 0;
        if (lat < 0 || lng < 0) {
            return result;
        }
        String sql = String.format("update optjoint set LONGITUDES=%f,LATITUDES=%f " +
                " where optjointid='%s'", lng, lat, boxId);
        try {
            result = runner.update(sql);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
