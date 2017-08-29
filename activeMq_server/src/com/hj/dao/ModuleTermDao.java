package com.hj.dao;

import com.hj.utils.DBUtilsHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-06-09.
 */
public class ModuleTermDao {
    /**
     * 配线查询
     *
     *	page_count	每页条数	    整型	是	每一页查询多少条
     *	page_index	所查页数	    整型	是	查询第几页
     *  module_name	配线模块名称	字符串	否	通过配线模块名称模糊查询配线端子
     *  module_id	配线模块ID	    字符串	否	指定配线模块ID查询配线端子
     *  term_id	    唯一标示	    字符串	是	唯一标示
     */
    public List<Map<String, Object>> getAllModuleTermsList(int page_count, int page_index, String module_name, String module_id, String term_id) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        List<Map<String, Object>> mapListResult = null;
        try {
            String pageSql = (page_count > 0 && page_index > 0) ? String.format(" where rownum_>%d and rownum_<=%d", (page_index - 1) * page_count,
                    page_count * page_index) : "";
            String module_nameSql = module_name.isEmpty() ? "" :
                    String.format(" and dbo.sf('pw_switch', 'SN', SN, null) like '%%%s%%'", module_name);
            String module_idSql = module_id.isEmpty() ? "" :
                    String.format(" and deviceid='%s'", module_id);
            String term_idSql = term_id.isEmpty() ? "" :
                    String.format(" and devicename like '%%%s%%'", term_id);

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
                    "  order by term_id",module_nameSql,module_idSql,term_idSql,pageSql);

            System.out.println(sql);

            mapListResult = runner.query(sql, new MapListHandler());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mapListResult;
    }

    /**
     * 配线端子信息修改修改
     *  term_id	        配线端子ID	字符串	是	主键
     *	term_label	    端子标签	字符串	是	端子标签
     */
    public int changeModuleTermInfo(String term_id, String term_label) {
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();
        int result = 0;
        if (term_id.isEmpty()) {
            return result;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("update OCF set ");
        if (!term_label.isEmpty()) {
            builder.append(String.format(" term_label='%s'", term_label));
        }

        builder.append(String.format("where ocfid='%s'", term_id));

        try {
            System.out.println(builder);
            result = runner.update(builder.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
