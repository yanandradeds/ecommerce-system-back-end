CREATE TABLE IF NOT EXISTS product (
    id INT AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) DEFAULT "0.00",
    quantity INT DEFAULT "0",
    PRIMARY KEY(id)
);