
--վ�� �� ��ά���ǩ
alter table com_station add (qr_code varchar2(200));

--���� �� ��ά���ǩ
alter table com_mroom add (qr_code varchar2(200));


--���� �� ��ά���ǩ
alter table COM_RACK add (qr_code varchar2(200));


--�豸 �� ��ά���ǩ
alter table tr_sdh add (qr_code varchar2(200));

--��ǩ��������ͼ
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
