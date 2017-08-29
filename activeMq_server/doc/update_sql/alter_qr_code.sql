
--站点 加 二维码标签
alter table com_station add (qr_code varchar2(200));

--机房 加 二维码标签
alter table com_mroom add (qr_code varchar2(200));


--机柜 加 二维码标签
alter table COM_RACK add (qr_code varchar2(200));


--设备 加 二维码标签
alter table tr_sdh add (qr_code varchar2(200));

--标签管理创建视图
CREATE OR REPLACE VIEW view_code_manage AS
select  
   SN id,
   qr_code
from com_station 
union 
select 
     MROOMID id,
     qr_code 
from com_mroom
union 
select 
     RACKID id,
     qr_code
from COM_RACK
union 
select 
     DEVID id,
     qr_code
from tr_sdh;
