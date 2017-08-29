create or replace view v_peixian_port as
select
   t.portid term_id,
   t.portcode term_name,
  t.supcode name_std,
  substr(t.portcode ,4,2) col_index,
  substr(t.portcode ,7,2) row_index,
  'ODF' term_type,
  '' term_label,
  t.orderinboard sn,
  dbo.sf('OP_DEVPORT','BUSISTATUS',t.busistatus,null)status,
  t.devinsid module_id,
  dbo.sr(t.devresid,t.devinsid) module_name,
  remark remark,
  (select to_char(f.manatype) from ocf f where f.ocfid=t.devinsid) manatype
  from op_devport t where t.devresid='ODF'

union all
select
   t.portid term_id,
   t.portcode term_name,
  t.supcode name_std,
  substr(t.portcode ,4,2) col_index,
  substr(t.portcode ,7,2) row_index,
  'DDF' term_type,
  '' term_label,
  t.orderinboard sn,
  dbo.sf('TR_DEVPORT','BUSISTATUS',t.busistatus,null)status,
  t.devinsid module_id,
  dbo.sr(t.devresid,t.devinsid) module_name,
  '' remark,
  (select to_char(n.manatype) from tr_ddf ddf,com_station n  where ddf.ddfid=t.devinsid
  and n.sn=ddf.sn) manatype
from tr_devport t where t.devresid='TR_DDF';
