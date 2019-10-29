CREATE TABLE user_system (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(15) NOT NULL,
    email VARCHAR(50) NOT NULL,
    pass VARCHAR(150) NOT NULL,
    label VARCHAR(100),
	account_expiration BOOLEAN,
	credential_expiration BOOLEAN,
	locked BOOLEAN,
	enabled BOOLEAN
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE lab (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(15) NOT NULL,
    label VARCHAR(100),
    id_user BIGINT(20) NOT NULL,
    FOREIGN KEY (id_user) REFERENCES user_system(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE permission (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    label VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE user_permission (
	id_user BIGINT(20) NOT NULL AUTO_INCREMENT,
	id_permission BIGINT(20) NOT NULL,
	PRIMARY KEY (id_user, id_permission),
	FOREIGN KEY (id_user) REFERENCES user_system(id),
	FOREIGN KEY (id_permission) REFERENCES permission(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- "admin"
INSERT INTO user_system (name, email, pass) values ('admin', 'admin@server.com', '$2a$10$2KMEwRxVNtYyJxSsm5n.7uGBln75EhCygsVfQ4daKBAf9Qu.EtQXO');
-- "user"
INSERT INTO user_system (name, email, pass) values ('user', 'user@server.com', '$2a$10$E3f7e3XzIEomHUEO8SfH4eay4na4zuLDcupfdmM.FRc/6Bg.ZTtVC');

INSERT INTO lab (name, label, id_user) VALUES ('dummy lab', 'dummy label', 1);

INSERT INTO permission (label) values ('ROLE_CREATE_COLOR');
INSERT INTO permission (label) values ('ROLE_DELETE_COLOR');
INSERT INTO permission (label) values ('ROLE_UPDATE_COLOR');
INSERT INTO permission (label) values ('ROLE_READ_COLOR');


INSERT INTO permission (label) values ('ROLE_CREATE_SELECT_COLOR');
INSERT INTO permission (label) values ('ROLE_DELETE_SELECT_COLOR');
INSERT INTO permission (label) values ('ROLE_UPDATE_SELECT_COLOR');
INSERT INTO permission (label) values ('ROLE_READ_SELECT_COLOR');

INSERT INTO permission (label) values ('ROLE_CREATE_DEVICE');
INSERT INTO permission (label) values ('ROLE_DELETE_DEVICE');
INSERT INTO permission (label) values ('ROLE_UPDATE_DEVICE');
INSERT INTO permission (label) values ('ROLE_READ_DEVICE');

INSERT INTO permission (label) values ('ROLE_CREATE_STATE');
INSERT INTO permission (label) values ('ROLE_DELETE_STATE');
INSERT INTO permission (label) values ('ROLE_UPDATE_STATE');
INSERT INTO permission (label) values ('ROLE_READ_STATE');

INSERT INTO permission (label) values ('ROLE_CREATE_USER');
INSERT INTO permission (label) values ('ROLE_DELETE_USER');
INSERT INTO permission (label) values ('ROLE_UPDATE_USER');
INSERT INTO permission (label) values ('ROLE_READ_USER');

INSERT INTO permission (label) values ('ROLE_CREATE_PERMISSION');
INSERT INTO permission (label) values ('ROLE_DELETE_PERMISSION');
INSERT INTO permission (label) values ('ROLE_UPDATE_PERMISSION');
INSERT INTO permission (label) values ('ROLE_READ_PERMISSION');

INSERT INTO permission (label) values ('ROLE_CREATE_USER_PERMISSION');
INSERT INTO permission (label) values ('ROLE_DELETE_USER_PERMISSION');
INSERT INTO permission (label) values ('ROLE_UPDATE_USER_PERMISSION');
INSERT INTO permission (label) values ('ROLE_READ_USER_PERMISSION');

-- admin
INSERT INTO user_permission (id_user, id_permission) values (1, 1);
INSERT INTO user_permission (id_user, id_permission) values (1, 2);
INSERT INTO user_permission (id_user, id_permission) values (1, 3);
INSERT INTO user_permission (id_user, id_permission) values (1, 4);
INSERT INTO user_permission (id_user, id_permission) values (1, 5);
INSERT INTO user_permission (id_user, id_permission) values (1, 6);
INSERT INTO user_permission (id_user, id_permission) values (1, 7);
INSERT INTO user_permission (id_user, id_permission) values (1, 8);
INSERT INTO user_permission (id_user, id_permission) values (1, 9);
INSERT INTO user_permission (id_user, id_permission) values (1, 10);
INSERT INTO user_permission (id_user, id_permission) values (1, 11);
INSERT INTO user_permission (id_user, id_permission) values (1, 12);
INSERT INTO user_permission (id_user, id_permission) values (1, 13);
INSERT INTO user_permission (id_user, id_permission) values (1, 14);
INSERT INTO user_permission (id_user, id_permission) values (1, 15);
INSERT INTO user_permission (id_user, id_permission) values (1, 16);
INSERT INTO user_permission (id_user, id_permission) values (1, 17);
INSERT INTO user_permission (id_user, id_permission) values (1, 18);
INSERT INTO user_permission (id_user, id_permission) values (1, 19);
INSERT INTO user_permission (id_user, id_permission) values (1, 20);
INSERT INTO user_permission (id_user, id_permission) values (1, 21);
INSERT INTO user_permission (id_user, id_permission) values (1, 22);
INSERT INTO user_permission (id_user, id_permission) values (1, 23);
INSERT INTO user_permission (id_user, id_permission) values (1, 24);
INSERT INTO user_permission (id_user, id_permission) values (1, 25);
INSERT INTO user_permission (id_user, id_permission) values (1, 26);
INSERT INTO user_permission (id_user, id_permission) values (1, 27);
INSERT INTO user_permission (id_user, id_permission) values (1, 28);

-- user
INSERT INTO user_permission (id_user, id_permission) values (2, 4);
INSERT INTO user_permission (id_user, id_permission) values (2, 8);
INSERT INTO user_permission (id_user, id_permission) values (2, 12);
INSERT INTO user_permission (id_user, id_permission) values (2, 16);
INSERT INTO user_permission (id_user, id_permission) values (2, 20);
INSERT INTO user_permission (id_user, id_permission) values (2, 24);
INSERT INTO user_permission (id_user, id_permission) values (2, 28);