
--���ֶ��޸�

--վ�� �� ��ά���ǩ
alter table com_station add (qr_code varchar2(200));

--���� �� ��ά���ǩ
alter table com_mroom add (qr_code varchar2(200));


--���� �� ��ά���ǩ
alter table COM_RACK add (qr_code varchar2(200));


--�豸 �� ��ά���ǩ
alter table tr_sdh add (qr_code varchar2(200));


--�����豸��;�ֶ�
alter table tr_sdh add (purpose varchar2(800));
comment on column tr_sdh.purpose  is '���豸��;';


--���� ������������γ�� �ֶ�
alter table OPTSEG add (passponits clob);
comment on column OPTSEG.passponits  is '������������γ��';
