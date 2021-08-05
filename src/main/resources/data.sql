INSERT INTO public.roles
    (roleid , rolename)
VALUES(1001, 'ROLE_USER');
INSERT INTO public.roles
    (roleid , rolename)
VALUES(1002, 'ROLE_ADMIN');
INSERT INTO public.roles
    (roleid , rolename)
VALUES(1003, 'ROLE_USER_LOCKED');
INSERT INTO public.roles
    (roleid , rolename)
VALUES(1004, 'ROLE_ADMIN_LOCKED');

INSERT INTO public.locations
    (locationid , address)
VALUES(101, 'HCM');
INSERT INTO public.locations
    (locationid , address)
VALUES(102, 'HN');

INSERT INTO public.categories
    (categorycode , categoryname)
VALUES('BM', 'Bluetooth Mouse');
INSERT INTO public.categories
    (categorycode , categoryname)
VALUES('PC', 'Personal Computer');
INSERT INTO public.categories
    (categorycode , categoryname)
VALUES('PJ', 'Projector');
INSERT INTO public.categories
    (categorycode , categoryname)
VALUES('MO', 'Monitor');
INSERT INTO public.categories
    (categorycode , categoryname)
VALUES('LA', 'Laptop');

insert
	into
	public.assets
    (assetcode ,
    assetname,
    installdate,
    isdeleted,
    specification,
    state,
    categorycode,
    locationid)
values('LA000001',
        'Laptop HP Probook 450 G1',
        '2021-07-06 14:24:05',
        false,
        'intel core i3',
        1,
        'LA',
        101
);
insert
	into
	public.assets
    (assetcode ,
    assetname,
    installdate,
    isdeleted,
    specification,
    state,
    categorycode,
    locationid)
values('LA000002',
        'Laptop HP Probook 450 G1',
        '2021-07-06 14:25:05',
        false,
        'intel core i5',
        1,
        'LA',
        101
);
insert
	into
	public.assets
    (assetcode ,
    assetname,
    installdate,
    isdeleted,
    specification,
    state,
    categorycode,
    locationid)
values('LA000003',
        'Laptop HP Probook 450 G1',
        '2021-07-06 14:26:05',
        false,
        'intel core i7',
        2,
        'LA',
        101
);
insert
	into
	public.assets
    (assetcode ,
    assetname,
    installdate,
    isdeleted,
    specification,
    state,
    categorycode,
    locationid)
values('LA000004',
        'Laptop HP Probook 450 G1',
        '2021-07-06 14:24:05',
        false,
        'intel core i3',
        1,
        'LA',
        102
);
insert
	into
	public.assets
    (assetcode ,
    assetname,
    installdate,
    isdeleted,
    specification,
    state,
    categorycode,
    locationid)
values('LA000005',
        'Laptop HP Probook 450 G1',
        '2021-07-06 14:25:05',
        false,
        'intel core i5',
        1,
        'LA',
        102
);
insert
	into
	public.assets
    (assetcode ,
    assetname,
    installdate,
    isdeleted,
    specification,
    state,
    categorycode,
    locationid)
values('LA000006',
        'Laptop HP Probook 450 G1',
        '2021-07-06 14:26:05',
        false,
        'intel core i7',
        1,
        'LA',
        102
);
insert
	into
	public.assets
    (assetcode ,
    assetname,
    installdate,
    isdeleted,
    specification,
    state,
    categorycode,
    locationid)
values('PC000001',
        'Personal Computer',
        '2021-07-06 14:24:05',
        false,
        'intel core i3',
        1,
        'PC',
        101
);
insert
	into
	public.assets
    (assetcode ,
    assetname,
    installdate,
    isdeleted,
    specification,
    state,
    categorycode,
    locationid)
values('PC000002',
        'Personal Computer',
        '2021-07-06 14:25:05',
        false,
        'intel core i5',
        1,
        'PC',
        101
);
insert
	into
	public.assets
    (assetcode ,
    assetname,
    installdate,
    isdeleted,
    specification,
    state,
    categorycode,
    locationid)
values('PC000003',
        'Personal Computer',
        '2021-07-06 14:26:05',
        false,
        'intel core i7',
        2,
        'PC',
        101
);
