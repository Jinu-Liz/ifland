create table member (
                        member_id bigint(20) auto_increment,
                        name varchar(20),
                        ifland_nick_name varchar(40),
                        password varchar(255),
                        image varchar(100),
                        created_date datetime,
                        updated_date datetime,
                        primary key (member_id, name)
);

drop table member;

create table profile_hate (
                              hate_id bigint(20),
                              profile_id int(20) not null,
                              hate_thing varchar(50),
                              create_date datetime,
                              update_date datetime,
                              primary key (hate_id)
);


create table profile_like (
                              like_id bigint(20),
                              profile_id int(20) not null,
                              like_thing varchar(50),
                              create_date datetime,
                              update_date datetime,
                              primary key (like_id)
);



create table tag (
                     tag_id bigint(20),
                     profile_id int(20) not null,
                     tag varchar(50),
                     create_date datetime,
                     update_date datetime,
                     primary key (tag_id)
);



create table profile (
                         profile_id bigint(20),
                         ifland_name varchar(40) not null,
                         image varchar(100),
                         contents longtext,
                         instagram varchar(120),
                         facebook varchar(120),
                         blog varchar(120),
                         like_count int(20),
                         create_date datetime,
                         update_date datetime,
                         primary key (profile_id)
);



create table profile_comment (
                                 comment_id bigint(20),
                                 profile_id int(20),
                                 member_id int(20),
                                 contents longtext,
                                 like_count int(20),
                                 create_date datetime,
                                 update_date datetime,
                                 primary key (comment_id, profile_id, member_id)
);