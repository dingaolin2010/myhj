package com.hj.test;

import com.alibaba.fastjson.JSON;
import com.hj.dao.BaseDao;
import com.hj.dao.CircuitDao;
import com.hj.utils.DBUtilsHelper;
import com.hj.utils.XmlHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Druid_Dbutils_fully_test {


    public static void main(String[] args) throws SQLException {
        XmlHelper xmlHelper = new XmlHelper();
        /*RepairDao db = new RepairDao();
        Boolean flag = db.getWxRoot("827", "791");
        System.out.println(flag);*/
        DBUtilsHelper dbh = new DBUtilsHelper();
        QueryRunner runner = dbh.getRunner();

        BaseDao baseDao = new BaseDao();
//测试站点
        List<Map<String, Object>> returnList = baseDao.com_stationDao.getAllStations(1,100,"德义站","");
        String xml = xmlHelper.getMapListXml(returnList,"station","01040101","0100","getAllStations",true);
       System.out.println(xml);
//测试站点下机房
          List<Map<String, Object>> returnList1 = baseDao.com_stationDao.getAllRooms(1,100,"d951949a8abc40fbb5afd4646e8866a2");

          String xml1 = xmlHelper.getMapListXml(returnList,"station","01040101","0100","getAllRooms",true);
          System.out.println(xml1);

        List<Map<String, Object>> listPower = baseDao.PowerDao.getAllPowersList(10, 1, "FDA562A9CF3B04F8E043F201A8C07A0E", "毛阳站", "", "电源", "独立通信电源");
        System.out.println(xmlHelper.getMapListXml(listPower, "power",
                "test01", "test02", "getAllPowers", true));

        List<Map<String, Object>> list = baseDao.OCableDao.getOCableList(1, 2, "FDA562A9CF0A04F8E043F201A8C07A0E", "", "",
                "", "", "");
        System.out.println(xmlHelper.getMapListXml(list, "ocable",
                "test01", "test02", "getAllOcables", true));

        int result = 0;
        result = baseDao.OCableDao.updateOptJointLatLng("47617a60f949407b86dd5c5aa43316af",
                109.659546,18.346609);
        System.out.println(xmlHelper.getMapListXml(null,"",
                "test01","test02","changeConnectBoxInfo",result > 0));
        /*list = baseDao.EquipmentDao.getSDHBusiList(1,2,"","中调");
        System.out.println(xmlHelper.getMapListXml(list, "business",
                "test01", "test02", "getAllEquipBussiness", true));*/
        list = baseDao.OCableDao.getFiberList(2, 10, "8f7cd2856ab54ce8baba422345516e90", "", "", "", "", "");
        System.out.println(xmlHelper.getMapListXml(list, "fiber",
                "test01", "test02", "getAllFibers", true));


        list=baseDao.TopoDao.getPortLinkList(1,5,"大广");
        System.out.println(xmlHelper.getMapListXml(list, "transroute",
                "test01", "test02", "getAllTransRoutes ", true));
        list=baseDao.TopoDao.getPorttsList(1,5,"012F564836A6BCAFE053F201A8C0C3DA");
        System.out.println(xmlHelper.getMapListXml(list, "time_slot",
                "test01", "test02", "getAllTimeSlots  ", true));
        list=baseDao.TopoDao.getInnerJumpList(1,3,"1E0B8D34945C2D10E053F201A8C09141","");
        System.out.println(xmlHelper.getMapListXml(list, "cross",
                "test01", "test02", "getAllCross  ", true));
        /*list=baseDao.OCableDao.getOCableRouteList(1,2,"491d1a2eeed54710b014ac35cba17e4d");
        System.out.println(xmlHelper.getMapListXml(list, "ocable_route",
                "test01", "test02", "getAllOcableRoutes", true));
        list = baseDao.CircuitDao.getCircuitList(10, 1,
        List<Map<String,Object>> list = baseDao.AnalysisDao.getAnalysisN_X(0,0,"aaa;bbb","ccc","ddd;eee","fff;ggg","","");

        /*List<Map<String, Object>> list = baseDao.CircuitDao.getCircuitList(10, 1,
                "00b89a5b12574175bc0c64c60f77e0ee", "陵水局", "");
        System.out.println(xmlHelper.getMapListXml(list, "circuit",
                "test01", "test02", "getAllCircults", true));

        list = baseDao.EquipmentDao.getSDHList(0, 0, "", "", "",
                "", "", "", "", "", "博鳌", "", "");
        System.out.println(xmlHelper.getMapListXml(list, "tr_sdh",
                "test01", "test02", "getAllEquipments",true));*/
        /*String sql = "select sn, stname, stno from com_station";
        String result = xmlHelper.getSelectResultXml(sql, XmlHelper.ResultType.MAP, "com_station", "test", "test2", "getComStationTest");
        System.out.println(result);*/


        // 选取5行
        /*String sql2 = "select t.sn,t.stname, t.stno from" +
                " (select s.*,row_number() over (order by stname desc) as rn_ from com_station s) t" +
                " where rn_<=5";
        String result2 = xmlHelper.getSelectResultXml(sql2, XmlHelper.ResultType.MAPLIST, "com_station", "test00", "test01", "getComStationListTest");
        System.out.println(result2);*/


   /*     String sql = "insert into test(userId) values(?)";
        Long insertRs = runner.insert(sql, new ScalarHandler<Long>(), 19888);
        System.out.println("第一次扑街：" + insertRs);


        String sql2 = "insert into sys_file (FILEID,TYPEID,FILENAME,FILEPATH,CREATETIME,EXT,FILETYPE,NOTE,CREATORID,CREATOR,TOTALBYTES,DELFLAG,FILEBLOB) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Long insertRs2 = runner.insert(sql2, new ScalarHandler<Long>(), 1234567889, 1, "很好非常好", "nice", new Date(), "xxx", "xxx", "nice", 168, "wuzc", 1888, 0, "扑街");
        System.out.println("扑街：" + insertRs2);*/


        // 返回ArrayHandler结果,第一行结果：Object[]
        /*System.out.println("A:返回ArrayHandler结果......");
        Object[] arrayResult;
        arrayResult = runner.query("select t.stname,t.stno from com_station t", new ArrayHandler());
        for (int i = 0; i < arrayResult.length; i++) {
           System.out.print(JSON.toJSONString(arrayResult[i]));
        }*/

     /*   System.out.println();
     // 返回ArrayListHandler结果,第一行结果：List<Object[]>
        System.out.println("B:返回ArrayListHandler结果(仅显示5行).........");
        List<Object[]> arrayListResult = runner.query("select * from test",
                new ArrayListHandler());
        for (int i = 0; i < arrayListResult.size() && i < 5; i++) {
            for (int j = 0; j < arrayListResult.get(i).length; j++) {
                System.out.print(arrayListResult.get(i)[j] + "    ");
            }
            System.out.println();
        }
        System.out.println();*/
   /*
        // 返回bean
        System.out.println("X:单条返回bean结果.");
        ShuangSeQiu ssq = runner.query("select * from ssq where qishu like ?",
                new BeanHandler<ShuangSeQiu>(ShuangSeQiu.class), "2009%");
        System.out.println("bean:" + ssq.getQishu());
        System.out.println("X1:单条返回bean结果");
        ResultSetHandler<ShuangSeQiu> h = new BeanHandler<ShuangSeQiu>(
                ShuangSeQiu.class);
        ShuangSeQiu p = runner.query(
                "select * from ssq where qishu like ? limit 1", h, "2009%");
        System.out.println(p.getQishu());

        // 返回beanlist
        System.out.println("C:返回BeanList结果(仅显示5行)......");
        List<ShuangSeQiu> beanListResult = runner.query("select * from ssq",
                new BeanListHandler<ShuangSeQiu>(ShuangSeQiu.class));
        Iterator<ShuangSeQiu> iter_beanList = beanListResult.iterator();
        int shownum = 0;
        while (iter_beanList.hasNext() && shownum < 5) {
            System.out.println(iter_beanList.next().getQishu());
            shownum++;
        }

        // 返回指定列
        System.out.println("D:返回ColumnList结果......");
        List<Object> columnResult = runner.query("select * from ssq",
                new ColumnListHandler<Object>("qishu"));
        Iterator<Object> iter = columnResult.iterator();
        shownum = 0;
        while (iter.hasNext() && shownum < 5) {
            System.out.println(iter.next());
            shownum++;
        }

        // 返回KeyedHandler结果：Map<Object,Map<String,Object>>：map的key为KeyedHandler指定
        System.out.println("E:返回KeyedHandler结果,期数:2003001的a列值.........");
        Map<Object, Map<String, Object>> keyedResult = runner.query(
                "select * from ssq", new KeyedHandler<Object>("qishu"));
        System.out.println(keyedResult.get("2003001").get("a"));

        // MapHandler
        System.out.println("F:返回MapHandler结果.........");
        Map<String, Object> mapResult = runner.query("select * from ssq",
                new MapHandler());
        Iterator<String> iter_mapResult = mapResult.keySet().iterator();
        while (iter_mapResult.hasNext()) {
            System.out.print(mapResult.get(iter_mapResult.next()) + "   ");
        }
        System.out.println();

        // 返回MapListHandler结果
        System.out.println("G:返回MapListHandler结果.........");
        List<Map<String, Object>> mapListResult = runner.query(
                "select * from ssq", new MapListHandler());
        for (int i = 0; i < mapListResult.size() && i < 5; i++) {
            Iterator<String> values = mapListResult.get(i).keySet().iterator();
            while (values.hasNext()) {
                System.out.print(mapListResult.get(i).get(values.next())
                        + "   ");
            }
            System.out.println();
        }

        Object increaseId = runner.query("select last_insert_id()",
                new ScalarHandler<Object>());
        System.out.println(increaseId);*/
    }

}