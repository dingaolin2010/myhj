--�����豸��;�ֶ�
alter table tr_sdh add (purpose varchar2(800));
comment on column tr_sdh.purpose  is '���豸��;';


--���� ������������γ�� �ֶ�
alter table OPTSEG add (passponits clob);
comment on column OPTSEG.passponits  is '������������γ��';
