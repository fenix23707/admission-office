insert into subjects (name) values
    ('english'),
    ('geography'),
    ('algebra'),
    ('geometry');

insert into permissions(scope, "action") values
    ('SUBJECT', 'READ'),
    ('REGISTRATION', 'READ_BY_STUDENT'),
    ('REGISTRATION', 'CREATE'),
    ('EXAM', 'READ'),
    ('EXAM', 'READ_BY_ID'),
    ('EXAM', 'CREATE'),
    ('EXAM', 'UPDATE'),
    ('EXAM', 'DELETE'),
    ('REGISTRATION', 'READ'),
    ('EXAM', 'ASSIGN');

insert into roles(name) values
    ('student'),
    ('scheduler');

insert into roles_permissions(role_id, permission_id) values
    (1,1),
    (1,2),
    (1,3),
    (2,4),
    (2,5),
    (2,6),
    (2,7),
    (2,8),
    (2,9),
    (2,10),
    (1,4);

insert into users(username, password, role_id) values
    ('admin', '$2a$10$g8ZjNE897R0QQR1pcsJARun8qvgS8xtMQKBJNNbRLm6.w11P2bt.m', 1), -- pass admin
    ('root', '$2a$10$V.TPYMleRGuZxnmiaE6YqeE2MLckhXtYc5vCGqLreghqB/6.ESoJO', 2), -- pass root
    ('test', '$2a$10$makl04WkLsNL93zWEDecNesAdNWXfhwGn9yV.qM0mBlhTrVey.WLu', 1); -- pass test

insert into students(id, user_id) values
    (1, 1);

insert into exams(time, duration, classroom, subject_id) values
    ('2018-07-14T17:45:55.9483536', 90, '100b', 1);