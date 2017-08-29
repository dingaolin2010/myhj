create or replace view v_peixian_dev as
select
       ocfid module_id,
       ocfname module_mane,
       devrackid rack_id,
       dbo.sf('ODF', 'DEVRACKID', devrackid, null) rack_name,
       'ODF' module_type,
       SNUM sn,                                       --ģ�����
       '' total_row,
       '' total_col,
       dbo.sf('ODF', 'VENDORID', vendorid, null) vender,
       workdate beg_date,
       '����' service_state,                      --  ��Դ״̬=ʹ��״̬
       '��ά' maintain_dept,    -- ά����ʽ=ά����λ
       dbo.sf('ODF', 'MANATYPE', MANATYPE, null) disp_dept,

       (case  when exists (select 1  from com_rackbox t where t.devresid = 'ODF'  and t.devid = OCF.OCFID) then
          '��'
         else
          '��'  end) rack_used,
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
       'DDF�豸' module_type,
       SNUM sn,
        mroomrow total_row,
       mroomcol total_col,
       dbo.sf('TR_DDF', 'VENDORID', vendorid, null) vender,
       workdate beg_date,
        '����' service_state,
       '��ά' maintain_dept,
       (select dbo.sf('COM_STATION','MANATYPE',MANATYPE,null)  from com_station where com_station.sn = TR_DDF.Sn) disp_dept,
       (case  when exists (select 1 from com_rackbox t where t.devresid = 'TR_DDF' and t.devid = TR_DDF.DDFID) then
          '��'
         else
          '��'  end) rack_used,
       '' purpose,
       remark remarks,
       (select to_char(s.MANATYPE) from com_station s where s.sn=TR_DDF.sn) MANATYPE
  from TR_DDF;
