CREATE OR REPLACE VIEW V_BOARD_OPANDTR AS
SELECT boardid card_id,
       BOARDLOGICCODE card_code,
       boardorder sn,
       USERBOARDNO card_name,
       SUPCODE name_std,
       'ODF机盘' card_type,
       dbo.sf('OP_DEVBOARD', 'BOARDTPLID', BOARDTPLID, null) model,
       '' version,
       dbo.sf('OP_DEVBOARD', 'FACSTATUS', FACSTATUS, null) status,
       rackid rack_id,
       dbo.sf('OP_DEVBOARD', 'RACKID', rackid, null) rack_name,
       slotid slot_id,
       dbo.sf('OP_DEVBOARD', 'SLOTID', slotid, null) slot_name,
       devinsid equip_id,
       dbo.sr(devresid, devinsid) equip_name,
       'OP_DEVBOARD' RESID,
       (select to_char(f.MANATYPE) from ocf f where f.ocfid=o.devinsid ) MANATYPE
  FROM OP_DEVBOARD O
 where O.DEVRESID = 'ODF'
  Union all
SELECT boardid card_id,
       BOARDLOGICCODE card_code,
       boardorder sn,
       USERBOARDNO card_name,
       SUPCODE name_std,
       '光传输设备机盘' card_type,
       dbo.sf('OP_DEVBOARD', 'BOARDTPLID', BOARDTPLID, null) model,
       '' version,
       '' status,
       rackid rack_id,
       dbo.sf('TR_DEVBOARD', 'RACKID', rackid, null) rack_name,
       slotid slot_id,
       dbo.sf('TR_DEVBOARD', 'SLOTID', slotid, null) slot_name,
       devinsid equip_id,
       dbo.sr(devresid, devinsid) equip_name,
       'TR_DEVBOARD' RESID,
       (case when ab.devresid='TR_DDF' then
       (select to_char(n.MANATYPE) from com_station n,tr_ddf ddf where
       ddf.ddfid=ab.devinsid and ddf.sn=n.sn )
       else
         (select to_char(sdh.MANATYPE) from tr_sdh sdh where sdh.devid=ab.devinsid)
       end ) MANATYPE
  FROM TR_DEVBOARD AB
  where ab.devresid in ('TR_SDH','TR_DDF');
