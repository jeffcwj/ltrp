CREATE TABLE IF NOT EXISTS property 
(
  id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT NOW()
)ENGINE=innodb;