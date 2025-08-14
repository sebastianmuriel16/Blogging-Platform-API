drop table if exists blog_post;
drop table if exists tag;
drop table if exists blog_post_tag;

create table blog_post (
    id integer not null auto_increment,
    title varchar(255) not null,
    content text,
    category varchar(100),
    created_at datetime(6) not null,
    updated_at datetime(6) not null,
    primary key (id)
) engine=InnoDB;


create table tag (
    id integer not null auto_increment,
    name varchar(255) not null,
    primary key (id)
) engine=InnoDB;

create table blog_post_tag (
    blog_post_id integer not null,
    tag_id integer not null,
    primary key (blog_post_id, tag_id),
    foreign key (blog_post_id) references blog_post(id),
    foreign key (tag_id) references tag(id)
) engine=InnoDB;

