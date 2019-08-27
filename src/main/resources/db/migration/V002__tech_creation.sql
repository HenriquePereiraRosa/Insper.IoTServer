CREATE TABLE technician (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(15) NOT NULL,
    email VARCHAR(50) NOT NULL,
    lab VARCHAR(50) NOT NULL,
    bio VARCHAR(50) NOT NULL,
    label VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

 /* TODO: to implement this i need to check how I can work with SET implementation in Spring Data 

 CREATE TABLE end_switch ( /* fim de curso *  /
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    id_device BIGINT(20) NOT NULL,
    FOREIGN KEY (id_device) REFERENCES device(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

/  * CREATE TABLE state_switch ( /* State to read  only * / 
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    msg_code CHAR(5),
    state BIT,
    date_time DATETIME,
    id_end_switch BIGINT(20) NOT NULL,
    FOREIGN KEY (id_end_switch) REFERENCES end_switch(id) 
) ENGINE=InnoDB DEFAULT CHARSET=UTF8; */