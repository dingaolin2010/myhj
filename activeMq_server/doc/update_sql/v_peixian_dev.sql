create or replace view v_peixian_dev as
select
       ocfid module_id,
       ocfname module_mane,
       devrackid rack_id,
       dbo.sf('ODF', 'DEVRACKID', devrackid, null) rack_name,
       'ODF' module_type,
       SNUM sn,                                       --模块序号
       '' total_row,
       '' total_col,
       dbo.sf('ODF', 'VENDORID', vendorid, null) vender,
       workdate beg_date,
       '在用' service_state,                      --  资源状态=使用状态
       '自维' maintain_dept,    -- 维护方式=维护单位
       dbo.sf('ODF', 'MANATYPE', MANATYPE, null) disp_dept,

       (case  when exists (select 1  from com_rackbox t where t.devresid = 'ODF'  and t.devid = OCF.OCFID) then
          '是'
         else
          '否'  end) rack_used,
       '' purpose,
       remark remarks,
       to_char(MANATYPE) MANATYPE
  from OCF
 where restype = 'ODF'

 union all

 select
       ddfid module_id,
       ddfname  module_mane,
       devrackid rack_id,
       dbo.sf('TR_DDF', 'DEVRACKID', devrackid, null) rack_name,
       'DDF设备' module_type,
       SNUM sn,
        mroomrow total_row,
       mroomcol total_col,
       dbo.sf('TR_DDF', 'VENDORID', vendorid, null) vender,
       workdate beg_date,
        '在用' service_state,
       '自维' maintain_dept,
       (select dbo.sf('COM_STATION','MANATYPE',MANATYPE,null)  from com_station where com_station.sn = TR_DDF.Sn) disp_dept,
       (case  when exists (select 1 from com_rackbox t where t.devresid = 'TR_DDF' and t.devid = TR_DDF.DDFID) then
          '是'
         else
          '否'  end) rack_used,
       '' purpose,
       remark remarks,
       (select to_char(s.MANATYPE) from com_station s where s.sn=TR_DDF.sn) MANATYPE
  from TR_DDF;
