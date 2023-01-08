create database kezbek_config;
create user "config" with encrypted password 'config123';
grant all privileges on database kezbek_config to "config";
create database kezbek_authorization;
create user "authorization" with encrypted password 'authorization123';
grant all privileges on database kezbek_authorization to "authorization";
create database kezbek_transaction;
create user "transaction" with encrypted password 'transaction123';
grant all privileges on database kezbek_transaction to "transaction";
\connect kezbek_config config;
create schema "config";
set search_path = "config";

CREATE TABLE config.t_config_properties (
	application varchar NOT NULL,
	"key" varchar NOT NULL,
	value varchar NULL,
	profile varchar NOT NULL,
	"label" varchar NULL,
	CONSTRAINT t_config_properties_pk PRIMARY KEY (application, key, profile)
);
CREATE INDEX idx_application ON config.t_config_properties USING btree (application);
CREATE INDEX idx_profile ON config.t_config_properties USING btree (profile);

INSERT INTO config.t_config_properties (application,"key",value,profile,"label") VALUES
	 ('all','rabbitmq.email.queue','email-queue','container','master'),
	 ('all','rabbitmq.exchange','kezbek.exchange','container','master'),
	 ('all','rabbitmq.transaction.queue','transaction-queue','container','master'),
	 ('consumer-service','url.topup.partner','https://dummy.restapiexample.com/api/v1/create','container','master');

-- config.t_user_detail definition

-- Drop table

-- DROP TABLE config.t_user_detail;

CREATE TABLE config.t_user_detail (
	id int4 NOT NULL,
	username varchar NULL,
	"password" varchar NULL,
	CONSTRAINT t_user_detail_pk PRIMARY KEY (id)
);

INSERT INTO config.t_user_detail (id,username,"password") VALUES
	 (1,'admin','$2a$12$xVEzhL3RTFP1WCYhS4cv5ecNZIf89EnOW4XQczWHNB/Zi4zQAnkuS');

\connect kezbek_transaction "transaction";
create schema "transaction";
set search_path = "transaction";

-- "transaction".t_transaction definition

-- Drop table

-- DROP TABLE "transaction".t_transaction;

CREATE TABLE "transaction".t_transaction (
	id varchar NOT NULL,
	quantity int4 NULL,
	price float8 NULL,
	email varchar NULL,
	partner_id varchar NULL,
	quantity_cashback float8 NULL,
	tier_cashback float8 NULL,
	created_date timestamp NULL,
	phone_number varchar NULL,
	topup_status varchar NULL,
	CONSTRAINT t_transaction_pk PRIMARY KEY (id)
);


-- "transaction".t_user_tier definition

-- Drop table

-- DROP TABLE "transaction".t_user_tier;

CREATE TABLE "transaction".t_user_tier (
	id varchar NOT NULL,
	email varchar NULL,
	partner_id varchar NULL,
	tier_id varchar NULL,
	last_transaction_date date NULL,
	total_transaction int4 NULL,
	CONSTRAINT t_user_tier_pk PRIMARY KEY (id)
);

\connect kezbek_authorization "authorization";
create schema "authorization";
set search_path = "authorization";

-- "authorization".oauth_access_token definition

-- Drop table

-- DROP TABLE "authorization".oauth_access_token;

CREATE TABLE "authorization".oauth_access_token (
	token_id varchar(256) NULL,
	"token" bytea NULL,
	authentication_id varchar(256) NOT NULL,
	user_name varchar(256) NULL,
	client_id varchar(256) NULL,
	authentication bytea NULL,
	refresh_token varchar(256) NULL,
	CONSTRAINT oauth_access_token_pkey PRIMARY KEY (authentication_id)
);


-- "authorization".oauth_approvals definition

-- Drop table

-- DROP TABLE "authorization".oauth_approvals;

CREATE TABLE "authorization".oauth_approvals (
	userid varchar(256) NULL,
	clientid varchar(256) NULL,
	"scope" varchar(256) NULL,
	status varchar(10) NULL,
	expiresat timestamp NULL
);


-- "authorization".oauth_client_details definition

-- Drop table

-- DROP TABLE "authorization".oauth_client_details;

CREATE TABLE "authorization".oauth_client_details (
	client_id varchar(255) NOT NULL,
	client_secret varchar(255) NOT NULL,
	web_server_redirect_uri varchar(2048) NULL DEFAULT NULL::character varying,
	"scope" varchar(255) NULL DEFAULT NULL::character varying,
	access_token_validity int4 NULL,
	refresh_token_validity int4 NULL,
	resource_ids varchar(1024) NULL DEFAULT NULL::character varying,
	authorized_grant_types varchar(1024) NULL DEFAULT NULL::character varying,
	authorities varchar(1024) NULL DEFAULT NULL::character varying,
	additional_information varchar(4096) NULL DEFAULT NULL::character varying,
	autoapprove varchar(255) NULL DEFAULT NULL::character varying,
	CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id)
);


-- "authorization".oauth_client_token definition

-- Drop table

-- DROP TABLE "authorization".oauth_client_token;

CREATE TABLE "authorization".oauth_client_token (
	token_id varchar(256) NULL,
	"token" bytea NULL,
	authentication_id varchar(256) NOT NULL,
	user_name varchar(256) NULL,
	client_id varchar(256) NULL,
	CONSTRAINT oauth_client_token_pkey PRIMARY KEY (authentication_id)
);


-- "authorization".oauth_code definition

-- Drop table

-- DROP TABLE "authorization".oauth_code;

CREATE TABLE "authorization".oauth_code (
	code varchar(256) NULL,
	authentication bytea NULL
);


-- "authorization".oauth_refresh_token definition

-- Drop table

-- DROP TABLE "authorization".oauth_refresh_token;

CREATE TABLE "authorization".oauth_refresh_token (
	token_id varchar(256) NULL,
	"token" bytea NULL,
	authentication bytea NULL
);

INSERT INTO "authorization".oauth_client_details (client_id,client_secret,web_server_redirect_uri,"scope",access_token_validity,refresh_token_validity,resource_ids,authorized_grant_types,authorities,additional_information,autoapprove) VALUES
	 ('admin','$2a$12$xVEzhL3RTFP1WCYhS4cv5ecNZIf89EnOW4XQczWHNB/Zi4zQAnkuS',NULL,'READ, WRITE',3600,10000,'microservice','client_credentials',NULL,NULL,'NULL');