
    create table Student (
       id  serial not null,
        age int4 not null,
        birthday timestamp,
        name varchar(255),
        primary key (id)
    );
