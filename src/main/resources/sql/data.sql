insert into subjects (name) values
    ('english'),
    ('geography'),
    ('algebra'),
    ('geometry');

insert into permissions(scope, "action") values
    ('SUBJECT', 'READ');

insert into roles(name) values
    ('student');

insert into roles_permissions(role_id, permission_id) values
    (1,1);

insert into users(username, password, role_id) values
    ('admin', '$2a$10$g8ZjNE897R0QQR1pcsJARun8qvgS8xtMQKBJNNbRLm6.w11P2bt.m', 1), -- pass admin
    ('root', '$2a$10$V.TPYMleRGuZxnmiaE6YqeE2MLckhXtYc5vCGqLreghqB/6.ESoJO', 1), -- pass root
    ('test', '$2a$10$makl04WkLsNL93zWEDecNesAdNWXfhwGn9yV.qM0mBlhTrVey.WLu', 1); -- pass test
