DROP DATABASE IF EXISTS projekt2_gruppe_5_db;
CREATE DATABASE projekt2_gruppe_5_db;
USE projekt2_gruppe_5_db;

CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(150) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL
);

CREATE TABLE wishlist (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(150) NOT NULL,
                          user_id INT NOT NULL,
                          share_token VARCHAR(64) UNIQUE,
                          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE wish (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(150) NOT NULL,
                      description VARCHAR(500),
                      price DECIMAL(10,2),
                      link VARCHAR(500),
                      wishlist_id INT NOT NULL,
                      FOREIGN KEY (wishlist_id) REFERENCES wishlist(id) ON DELETE CASCADE
);

SELECT * FROM users;



