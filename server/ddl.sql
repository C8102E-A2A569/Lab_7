drop table if exists StudyGroup cascade;
drop table if exists users cascade;
drop sequence if exists StudyGroup_SEQ;
create sequence StudyGroup_SEQ start with 1 increment by 50;
create table StudyGroup (
    id integer not null,
    x bigint,
    y bigint not null,
    creationDate timestamp(6),
    formOfEducation varchar(255),
    height float(53) not null,
    location_name varchar(255),
    person_x integer,
    person_y bigint,
    person_z integer,
    admin_name varchar(255),
    passportID varchar(255),
    weight float(53) not null,
    name varchar(255),
    ownerName varchar(255),
    semesterEnum smallint,
    studentsCount bigint,
    primary key (id)
);
create table users (
    username varchar(255) not null,
    passwordHash varchar(255),
    passwordSalt varchar(255),
    primary key (username)
);
