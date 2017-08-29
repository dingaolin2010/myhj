create or replace view view_code_manage as
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
