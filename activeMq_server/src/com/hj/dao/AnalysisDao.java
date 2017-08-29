package com.hj.dao;

import com.hj.utils.DBUtilsHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/10.
 */
public class AnalysisDao {
    /**
     * N-1分析查询
     *
     *	page_count	每页条数	整型	是	每一页查询多少条
     *	page_index	所查页数	整型	是	查询第几页
     *  equip_id	设备ID	    字符串	否	设备ID
     *  card_id	    板卡ID	    字符串	否	板卡ID
     *  port_id	    端口ID	    字符串	否	端口ID
     *  ocable_id	光缆ID	    字符串	否	光缆ID
     *  trans_id	传输段ID	字符串	否	传输段ID
     *  influence	影响范围	字符串	否	数据字典（中断或可靠性降低）
     *  bus_type	网络类型	字符串	否	根据电路类型查询
     */
    public List<Map<String, Object>> getAnalysisN_1List(int page_count, int page_index, String equip_id, String card_id, String port_id,
                                                        String ocable_id,String trans_id,String influence,String bus_type) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String pageSql = (page_count > 0 && page_index > 0) ? String.format(" where rownum_>%d and rownum_<=%d", (page_index - 1) * page_count,
                    page_count * page_index) : "";
            String equip_idSql = equip_id.isEmpty() ? "" :
                    String.format(" and deviceid='%s'", equip_id);
            String card_idSql = card_id.isEmpty() ? "" :
                    String.format(" and deviceid='%s'", card_id);
            String port_idSql = port_id.isEmpty() ? "" :
                    String.format(" and deviceid='%s'", port_id);
            String ocable_idSql = ocable_id.isEmpty() ? "" :
                    String.format(" and deviceid='%s'", ocable_id);
            String trans_idSql = trans_id.isEmpty() ? "" :
                    String.format(" and deviceid='%s'", trans_id);
            String influenceSql = influence.isEmpty() ? "" :
                    String.format(" and deviceid='%s'", influence);
            String bus_typeSql = bus_type.isEmpty() ? "" :
                    String.format(" and deviceid='%s'", bus_type);

            String sql = String.format(" select *\n" +
                    "   from (select c.ocfid term_id,\n" +
                    "                c.ocfname term_name,\n" +
                    "                SUPNAME name_std,\n" +
                    "                '' col_index,\n" +
                    "                '' row_index,\n" +
                    "                'OCF' term_type,\n" +
                    "                '' term_label,\n" +
                    "                '' sn,\n" +
                    "                dbo.sf('OCF', 'FACSTATUS', FACSTATUS, null) status,\n" +
                    "                '' module_id,\n" +
                    "                '' module_name,\n" +
                    "                REMARK remark\n" +
                    "           from OCF c\n" +
                    "         union all\n" +
                    "         select d.ddfid term_id,\n" +
                    "                d.ddfname term_name,\n" +
                    "                SUPNAME name_std,\n" +
                    "                d.mroomcol col_index,\n" +
                    "                d.mroomrow row_index,\n" +
                    "                'DDF' term_type,\n" +
                    "                '' term_label,\n" +
                    "                '' sn,\n" +
                    "                dbo.sf('OCF', 'FACSTATUS', FACSTATUS, null) status,\n" +
                    "                '' module_id,\n" +
                    "                '' module_name,\n" +
                    "                REMARK remark\n" +
                    "           from TR_DDF d) t\n" +
                    "  where t.term_id in\n" +
                    "        (select term_id\n" +
                    "           from (select term_id, row_number() over(order by term_id) rownum_\n" +
                    "                   from (select c.ocfid term_id,\n" +
                    "                                c.ocfname term_name,\n" +
                    "                                SUPNAME name_std,\n" +
                    "                                '' col_index,\n" +
                    "                                '' row_index,\n" +
                    "                                'OCF' term_type,\n" +
                    "                                '' term_label,\n" +
                    "                                '' sn,\n" +
                    "                                dbo.sf('OCF', 'FACSTATUS', FACSTATUS, null) status,\n" +
                    "                                '' module_id,\n" +
                    "                                '' module_name,\n" +
                    "                                REMARK remark\n" +
                    "                           from OCF c\n" +
                    "                         union all\n" +
                    "                         select d.ddfid term_id,\n" +
                    "                                d.ddfname term_name,\n" +
                    "                                SUPNAME name_std,\n" +
                    "                                d.mroomcol col_index,\n" +
                    "                                d.mroomrow row_index,\n" +
                    "                                'DDF' term_type,\n" +
                    "                                '' term_label,\n" +
                    "                                '' sn,\n" +
                    "                                dbo.sf('OCF', 'FACSTATUS', FACSTATUS, null) status,\n" +
                    "                                '' module_id,\n" +
                    "                                '' module_name,\n" +
                    "                                REMARK remark\n" +
                    "                           from TR_DDF d)\n" +
                    "                  where 1 = 1) tt\n" +
                    "          where rownum_ > 0 and rownum_ <= 10)\n" +
                    "  order by term_id",equip_idSql,card_idSql,port_idSql,ocable_id,trans_id,influence,bus_type,pageSql);

            System.out.println(sql);

            mapListResult = runner.query(sql, new MapListHandler());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }

    /**
     * N-X分析查询
     *
     *	page_count	每页条数	整型	是	每一页查询多少条
     *	page_index	所查页数	整型	是	查询第几页
     *  equip_ids	设备ID	    字符串	否	多个设备ID用分号;隔开
     *  ocable_ids	光缆ID	    字符串	否	多个光缆ID用分号;隔开
     *  trans_ids	传输段ID	字符串	否	多个传输段ID用分号;隔开
     *  port_ids	端口ID	    字符串	否	多个端口ID用分号;隔开
     *  influence	影响范围	字符串	否	数据字典（中断或可靠性降低）
     *  bus_type	电路类型	字符串	否	根据电路类型查询
     */
    public List<Map<String, Object>> getAnalysisN_X(int page_count, int page_index, String equip_ids, String ocable_ids, String trans_ids,
                                                    String port_ids, String influence, String bus_type) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String pageSql = (page_count > 0 && page_index > 0) ? String.format(" where rownum_>%d and rownum_<=%d", (page_index - 1) * page_count,
                    page_count * page_index) : "";

            String equip_idsSql = "";
            if(!equip_ids.isEmpty()) {
                String[] equipIds = equip_ids.split(";");
                String equipId = "";
                for (int i = 0; i < equipIds.length; i++) {
                    equipId += "'" + equipIds[i] + "',";
                }
                if (equipId != "" && equipId.contains(","))
                    equipId = equipId.substring(0, equipId.length() - 1);
                equip_idsSql = equip_ids.isEmpty() ? "" :
                        String.format(" and deviceid in (%s)", equipId);
            }

            String ocable_idsSql = "";
            if(!ocable_ids.isEmpty()) {
                String[] ocableIds = ocable_ids.split(";");
                String ocableId = "";
                for (int i = 0; i < ocableIds.length; i++) {
                    ocableId += "'" + ocableIds[i] + "',";
                }
                if (ocableId != "" && ocableId.contains(","))
                    ocableId = ocableId.substring(0, ocableId.length() - 1);
                ocable_idsSql = ocable_ids.isEmpty() ? "" :
                        String.format(" and deviceid in (%s)", ocableId);
            }

            String trans_idsSql = "";
            if(!trans_ids.isEmpty()) {
                String[] transIds = trans_ids.split(";");
                String transId = "";
                for (int i = 0; i < transIds.length; i++) {
                    transId += "'" + transIds[i] + "',";
                }
                if (transId != "" && transId.contains(","))
                    transId = transId.substring(0, transId.length() - 1);
                trans_idsSql = trans_ids.isEmpty() ? "" :
                        String.format(" and deviceid in (%s)", transId);
            }

            String port_idsSql = "";
            if(!port_ids.isEmpty()) {
                String[] portIds = port_ids.split(";");
                String portId = "";
                for (int i = 0; i < portIds.length; i++) {
                    portId += "'" + portIds[i] + "',";
                }
                if (portId != "" && portId.contains(","))
                    portId = portId.substring(0, portId.length() - 1);
                port_idsSql = port_ids.isEmpty() ? "" :
                        String.format(" and deviceid in (%s)", portId);
            }

            String influenceSql = influence.isEmpty() ? "" :
                    String.format(" and deviceid='%s'", influence);
            String bus_typeSql = bus_type.isEmpty() ? "" :
                    String.format(" and deviceid='%s'", bus_type);

            String sql = String.format(" select *\n" +
                    "   from (select c.ocfid term_id,\n" +
                    "                c.ocfname term_name,\n" +
                    "                SUPNAME name_std,\n" +
                    "                '' col_index,\n" +
                    "                '' row_index,\n" +
                    "                'OCF' term_type,\n" +
                    "                '' term_label,\n" +
                    "                '' sn,\n" +
                    "                dbo.sf('OCF', 'FACSTATUS', FACSTATUS, null) status,\n" +
                    "                '' module_id,\n" +
                    "                '' module_name,\n" +
                    "                REMARK remark\n" +
                    "           from OCF c\n" +
                    "         union all\n" +
                    "         select d.ddfid term_id,\n" +
                    "                d.ddfname term_name,\n" +
                    "                SUPNAME name_std,\n" +
                    "                d.mroomcol col_index,\n" +
                    "                d.mroomrow row_index,\n" +
                    "                'DDF' term_type,\n" +
                    "                '' term_label,\n" +
                    "                '' sn,\n" +
                    "                dbo.sf('OCF', 'FACSTATUS', FACSTATUS, null) status,\n" +
                    "                '' module_id,\n" +
                    "                '' module_name,\n" +
                    "                REMARK remark\n" +
                    "           from TR_DDF d) t\n" +
                    "  where t.term_id in\n" +
                    "        (select term_id\n" +
                    "           from (select term_id, row_number() over(order by term_id) rownum_\n" +
                    "                   from (select c.ocfid term_id,\n" +
                    "                                c.ocfname term_name,\n" +
                    "                                SUPNAME name_std,\n" +
                    "                                '' col_index,\n" +
                    "                                '' row_index,\n" +
                    "                                'OCF' term_type,\n" +
                    "                                '' term_label,\n" +
                    "                                '' sn,\n" +
                    "                                dbo.sf('OCF', 'FACSTATUS', FACSTATUS, null) status,\n" +
                    "                                '' module_id,\n" +
                    "                                '' module_name,\n" +
                    "                                REMARK remark\n" +
                    "                           from OCF c\n" +
                    "                         union all\n" +
                    "                         select d.ddfid term_id,\n" +
                    "                                d.ddfname term_name,\n" +
                    "                                SUPNAME name_std,\n" +
                    "                                d.mroomcol col_index,\n" +
                    "                                d.mroomrow row_index,\n" +
                    "                                'DDF' term_type,\n" +
                    "                                '' term_label,\n" +
                    "                                '' sn,\n" +
                    "                                dbo.sf('OCF', 'FACSTATUS', FACSTATUS, null) status,\n" +
                    "                                '' module_id,\n" +
                    "                                '' module_name,\n" +
                    "                                REMARK remark\n" +
                    "                           from TR_DDF d)\n" +
                    "                  where 1 = 1 %s %s %s %s) tt\n" +
                    "          where rownum_ > 0 and rownum_ <= 10)\n" +
                    "  order by term_id",equip_idsSql,port_idsSql,ocable_idsSql,trans_idsSql,influenceSql,bus_typeSql,pageSql);

            System.out.println(sql);

            mapListResult = runner.query(sql, new MapListHandler());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }
}
