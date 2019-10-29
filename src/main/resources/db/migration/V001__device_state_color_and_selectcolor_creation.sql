CREATE TABLE device (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(15) NOT NULL,
    label VARCHAR(50) NOT NULL,
    hw_version VARCHAR(15),
    fw_version VARCHAR(15),
    ip VARCHAR(15),
    mac VARCHAR(17)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE state ( /* State to read and write */
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    msg_code CHAR(5),
    state BIT,
    date_time DATETIME,
    id_device BIGINT(20) NOT NULL,
    FOREIGN KEY (id_device) REFERENCES device(id) 
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE color (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	msg VARCHAR(100),
    color CHAR(6),
    date_time DATETIME,
    id_device BIGINT(20) NOT NULL,
    FOREIGN KEY (id_device) REFERENCES device(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE select_color (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	color CHAR(6),
    date_time DATETIME,
    id_device BIGINT(20) NOT NULL,
    FOREIGN KEY (id_device) REFERENCES device(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO device (code, label, hw_version, fw_version, ip, mac) VALUES ('00000000', 'dummy label', '0.00', '0.00', '0.0.0.0', 'AA:AA:AA:AA:AA:AA');

INSERT INTO state (msg_code, state, date_time, id_device) VALUES ('00000', false, '2019-06-04 12:00:01', 1);

INSERT INTO color (msg, color, date_time, id_device) VALUES ('dummy msg with max of (100)', 'FFFFFF', '2019-08-04 23:13:25', 1);

INSERT INTO select_color (color, date_time, id_device) VALUES ('AAAAAA', '2019-08-22 16:30:25', 1);