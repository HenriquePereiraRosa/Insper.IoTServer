
ALTER TABLE color DROP COLUMN desired_color;
ALTER TABLE color DROP COLUMN desired_date_time;

CREATE TABLE select_color (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	color CHAR(6),
    date_time DATETIME,
    id_device BIGINT(20) NOT NULL,
    FOREIGN KEY (id_device) REFERENCES device(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
 