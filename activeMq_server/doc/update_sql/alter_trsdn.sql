--新增设备用途字段
alter table tr_sdh add (purpose varchar2(800));
comment on column tr_sdh.purpose  is '增设备用途';


--新增 光缆线行走向经纬度 字段
alter table OPTSEG add (passponits clob);
comment on column OPTSEG.passponits  is '光缆线行走向经纬度';
