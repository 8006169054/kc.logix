CREATE TABLE com_user (
	id varchar(100) NOT NULL,
	"name" varchar(300) NOT NULL,
	"password" varchar(100) NOT NULL,
	mail varchar(200) NULL,
	activation varchar(1) NULL,
	"type" varchar(1) NULL,
	partner_code varchar(100) NULL,
	create_user_id varchar(100) NOT NULL,
	create_date date NOT NULL,
	update_user_id varchar(100) NOT NULL,
	update_date date NOT NULL,
	CONSTRAINT com_user_pkey PRIMARY KEY (id)
);

CREATE TABLE error_log (
	id int4 NOT NULL,
	url varchar(200) NOT NULL,
	log text NOT NULL,
	create_user_id varchar(100) NOT NULL,
	create_date timestamptz DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT error_log_pkey PRIMARY KEY (id)
);

CREATE TABLE mdm_cargo (
	code varchar(100) NOT NULL,
	"name" varchar(300) NOT NULL,
	"location" varchar(100) NOT NULL,
	depot varchar(20) NULL,
	cargo_date varchar(20) NULL,
	cleaning_cost varchar(20) NULL,
	difficult_level varchar(20) NULL,
	remark1 varchar(4000) NULL,
	remark2 varchar(4000) NULL,
	create_user_id varchar(100) NOT NULL,
	create_date date NOT NULL,
	update_user_id varchar(100) NOT NULL,
	update_date date NOT NULL,
	CONSTRAINT mdm_cargo_pkey PRIMARY KEY (code)
);

CREATE TABLE mdm_cargo_history (
	"name" varchar(300) NOT NULL,
	"location" varchar(100) NOT NULL,
	depot varchar(20) NULL,
	cargo_date varchar(20) NULL,
	cleaning_cost varchar(20) NULL,
	difficult_level varchar(20) NULL,
	remark1 varchar(4000) NULL,
	remark2 varchar(4000) NULL,
	create_user_id varchar(100) NOT NULL,
	create_date date NOT NULL,
	update_user_id varchar(100) NOT NULL,
	update_date date NOT NULL
);

CREATE TABLE mdm_partner (
	code varchar(100) NOT NULL,
	"name" varchar(100) NOT NULL,
	company varchar(100) NULL,
	pic varchar(100) NULL,
	representative_eml varchar(1000) NULL,
	create_user_id varchar(100) NOT NULL,
	create_date date NOT NULL,
	update_user_id varchar(100) NOT NULL,
	update_date date DEFAULT CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT mdm_partner_pkey PRIMARY KEY (code)
);

CREATE TABLE mdm_terminal (
	code varchar(10) NOT NULL,
	"name" varchar(100) NOT NULL,
	region varchar(30) NULL,
	"type" varchar(20) NULL,
	parking_lot_code varchar(10) NULL,
	homepage varchar(2000) NULL,
	create_user_id varchar(100) NOT NULL,
	create_date date NOT NULL,
	update_user_id varchar(100) NOT NULL,
	update_date date NOT NULL,
	CONSTRAINT mdm_terminal_pkey PRIMARY KEY (code)
);

CREATE TABLE website_terminal_code (
	"uuid" varchar(100) NOT NULL,
	sales varchar(100) NULL,
	carryover_sales varchar(100) NULL,
	arrival_notice varchar(100) NULL,
	invoice varchar(100) NULL,
	concine varchar(100) NULL,
	profit_date varchar(20) NULL,
	domestic_sales varchar(100) NULL,
	foreign_sales varchar(100) NULL,
	quantity varchar(10) NULL,
	partner varchar(100) NULL,
	tank_no varchar(100) NULL,
	term varchar(100) NULL,
	item varchar(200) NULL,
	vessel_voyage varchar(100) NULL,
	carrier varchar(100) NULL,
	mbl_no varchar(100) NULL,
	hbl_no varchar(100) NULL,
	pol varchar(100) NULL,
	pod varchar(100) NULL,
	terminal varchar(100) NULL,
	etd varchar(100) NULL,
	eta varchar(100) NULL,
	ata varchar(100) NULL,
	remark text NULL,
	ft varchar(100) NULL,
	dem_rate varchar(100) NULL,
	end_of_ft varchar(100) NULL,
	estimate_return_date varchar(100) NULL,
	return_date varchar(100) NULL,
	return_depot varchar(200) NULL,
	total_dem varchar(100) NULL,
	dem_received varchar(200) NULL,
	dem_rcvd varchar(100) NULL,
	dem_prch varchar(100) NULL,
	dem_sales varchar(100) NULL,
	depot_in_date varchar(100) NULL,
	reposition_prch varchar(100) NULL,
	create_user_id varchar(20) NOT NULL,
	create_date date NOT NULL,
	update_user_id varchar(20) NOT NULL,
	update_date date NOT NULL,
	CONSTRAINT import_website_terminal_code_pkey PRIMARY KEY (uuid)
);
