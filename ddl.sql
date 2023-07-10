drop table WIFI;
drop table HISTORY;
create table WIFI
(
    management_number      TEXT ,
    district     TEXT,
    name     TEXT,
    address1     TEXT,
    address2     TEXT,
    installed_floor TEXT,
    install_type    TEXT,
    install_agency   TEXT,
    service_classification     TEXT,
    mesh_type      TEXT,
    install_year  TEXT,
	in_out_door  TEXT,
	connection_environment     TEXT,
	latitude                 REAL,
	longitude                 REAL,
	workDateStr           TEXT
);

create table HISTORY
(
	id integer,
	latitude REAL,
	longitude REAL,
	lookupDate TEXT
);

