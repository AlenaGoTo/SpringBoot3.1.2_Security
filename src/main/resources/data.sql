INSERT INTO roles (id, role) VALUES (1, 'ROLE_ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO roles (id, role) VALUES (2, 'ROLE_USER') ON CONFLICT DO NOTHING;

INSERT INTO users (name, lastname, age, username, password, enabled)
VALUES ('admin', 'admin', 22, 'admin', 'admin', true) ON CONFLICT DO NOTHING;
INSERT INTO users (name, lastname, age, username, password, enabled)
VALUES ('user', 'user', 22, 'user', 'user', true) ON CONFLICT DO NOTHING;

INSERT INTO users_roles (user_id, roles_id)
VALUES ((select id from users where username='admin'), 1) ON CONFLICT DO NOTHING;
INSERT INTO users_roles (user_id, roles_id)
VALUES ((select id from users where username='user'), 2) ON CONFLICT DO NOTHING;
