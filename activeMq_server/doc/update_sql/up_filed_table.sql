
--表字段修改

--站点 加 二维码标签
alter table com_station add (qr_code varchar2(200));

--机房 加 二维码标签
alter table com_mroom add (qr_code varchar2(200));


--机柜 加 二维码标签
alter table COM_RACK add (qr_code varchar2(200));


--设备 加 二维码标签
alter table tr_sdh add (qr_code varchar2(200));


--新增设备用途字段
alter table tr_sdh add (purpose varchar2(800));
comment on column tr_sdh.purpose  is '增设备用途';


--新增 光缆线行走向经纬度 字段
alter table OPTSEG add (passponits clob);
comment on column OPTSEG.passponits  is '光缆线行走向经纬度';
