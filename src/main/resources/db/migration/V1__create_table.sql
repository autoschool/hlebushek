CREATE TABLE posts (
    id INT DEFAULT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    body  VARCHAR(1000000) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);