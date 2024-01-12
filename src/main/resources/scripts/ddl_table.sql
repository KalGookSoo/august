create table tb_account (id varchar(255) not null, created_at timestamp, modified_at timestamp, name varchar(255), password varchar(255), username varchar(255), primary key (id));
create table tb_article (id varchar(255) not null, created_at timestamp, modified_at timestamp, category_id varchar(255), content TEXT, created_by varchar(255), title varchar(255), primary key (id));
create table tb_attachment (id varchar(255) not null, created_at timestamp, modified_at timestamp, article_id varchar(255), name varchar(255), path_name varchar(255), primary key (id));
create table tb_authority (id varchar(255) not null, created_at timestamp, modified_at timestamp, account_id varchar(255), name varchar(255), primary key (id));
create table tb_category (id varchar(255) not null, created_at timestamp, modified_at timestamp, created_by varchar(255), name varchar(255), primary key (id));
create table tb_comment (id varchar(255) not null, created_at timestamp, modified_at timestamp, article_id varchar(255), content TEXT, created_by varchar(255), primary key (id));
alter table if exists tb_account drop constraint if exists UK_hsqa0fp3f7rv9yj885sixijg8;