drop table roles_permissions;
drop table users_schedules;
drop table permissions;
drop table marks;
drop table schedules;
drop table registrations;
drop table students;
drop table users;
drop table roles;
drop table subjects;

create table if not exists subjects (
    id serial,
    name varchar(150) not null unique,

    primary key (id)
);

create table if not exists permissions (
    id serial,
    scope varchar(50) not null,
    "action" varchar(50) not null,

    unique(scope, "action"),
    primary key (id)
);

create table if not exists roles (
    id serial,
    name varchar(100) not null unique,

    primary key (id)
);

create table if not exists roles_permissions (
    role_id serial,
    permission_id serial,

    primary key (role_id, permission_id),
    foreign key (role_id) references roles (id) on delete cascade on update cascade,
    foreign key (permission_id) references permissions (id) on delete cascade on update cascade
);

create table if not exists users (
    id bigserial,
    username varchar(100) not null unique,
    password varchar(100) not null,
    role_id int not null,

    primary key (id),
    foreign key (role_id) references roles (id) on delete restrict on update cascade
);

create table if not exists students (
    id serial,
    user_id bigint not null unique,

    primary key (id),
    foreign key (user_id) references users (id) on delete restrict on update cascade
) ;

create table if not exists registrations (
    id bigserial,
    status varchar(50) not null,
    subject_id int not null,
    student_id bigint not null,

    primary key (id),
    foreign key (subject_id) references subjects (id) on delete restrict on update cascade,
    foreign key (student_id) references students (id) on delete restrict on update cascade
) ;

create table if not exists schedules (
    id bigserial,
    start timestamp  not null,
    duration int not null,
    classroom varchar(50) not null,
    subject_id bigint not null,

    primary key (id),
    constraint check_duration_range check (duration > 0 and duration < 1440),
    foreign key (subject_id) references subjects (id) on delete restrict on update cascade
);

create table if not exists users_schedules (
    schedule_id bigint,
    user_id bigint,

    primary key (schedule_id, user_id),
    foreign key (user_id) references users (id) on delete restrict on update cascade,
    foreign key (schedule_id) references schedules (id) on delete restrict on update cascade
);


create table if not exists marks (
    id bigserial,
    score int,
    schedule_id bigint not null,
    student_id bigint not null,

    primary key (id),
    foreign key (schedule_id) references schedules (id) on delete restrict on update cascade,
    foreign key (student_id) references students (id) on delete restrict on update cascade
);