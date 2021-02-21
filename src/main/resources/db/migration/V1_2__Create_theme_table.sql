CREATE TABLE IF NOT EXISTS themes (
  id INT PRIMARY KEY,
  user_id INT,
  name TEXT NOT NULL,
  CONSTRAINT fk_user
    FOREIGN KEY(user_id) 
	    REFERENCES users(id)
);
