  CREATE TABLE users (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    token VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL,
    passwordDigest VARCHAR(128) NOT NULL
  );
