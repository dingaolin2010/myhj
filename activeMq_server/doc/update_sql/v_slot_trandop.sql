create or replace view v_slot_trandop as
select t.slotid slot_id,
       to_char(t.slotorder) sn,
       t.slotname slot_name,
       t.supname name_std,
       '' status,
       (select dbo.sr(x.devresid, x.devinsid)
          from op_devbox x
         where x.boxid = t.boxid) equip_id,
       (select dbo.sr('COM_RACK', x.rackid)
          from op_devbox x
         where x.boxid = t.boxid) rack_name,
       '' slot_type,
       '' remark,
       '' width,
       '' length,
       '' qr_code,
       (select x.rackid
          from op_devbox x
         where x.boxid = t.boxid) rack_id,
       (select to_char(max(f.MANATYPE))
          from op_devbox x,ocf f
         where x.boxid = t.boxid
         and x.devinsid=f.ocfid) MANATYPE
  from op_devboxslot t
 where exists (select 1
          from op_devbox x
         where x.devresid = 'ODF'
           and x.boxid = t.boxid)
union all
select t.slotid slot_id,
       to_char(t.slotorder) sn,
       t.slotname slot_name,
       t.supname name_std,
       '' status,
       (select dbo.sr(x.devresid, x.devinsid)
          from tr_devbox x
         where x.boxid = t.boxid) equip_id,
       (select dbo.sr('COM_RACK', x.rackid)
          from tr_devbox x
         where x.boxid = t.boxid) rack_name,
       '' slot_type,
       remark remark,
       '' width,
       '' length,
       '' qr_code,
       (select x.rackid
          from tr_devbox x
         where x.boxid = t.boxid) rack_id,
       (case when exists (select 1
          from tr_devbox x
         where x.devresid ='TR_SDH'
           and x.boxid = t.boxid)
       then (select to_char(max(sdh.manatype))
          from tr_devbox x,tr_sdh sdh
         where x.boxid = t.boxid
         and x.devinsid=sdh.devid)
         else

       (select to_char(max(n.manatype))
          from tr_devbox x,tr_ddf ddf,com_station n
         where x.boxid = t.boxid
         and x.devinsid=ddf.ddfid
         and ddf.sn=n.sn )
         end  ) MANATYPE
  from TR_DEVBOXSLOT t
 where exists (select 1
          from tr_devbox x
         where x.devresid in ('TR_SDH', 'TR_DDF')
           and x.boxid = t.boxid);
