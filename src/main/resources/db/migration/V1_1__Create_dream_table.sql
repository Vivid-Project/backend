CREATE TABLE IF NOT EXISTS dreams (
  id INT PRIMARY KEY,
  user_id INT,
  title TEXT NOT NULL,
  description TEXT NOT NULL,
  tone TEXT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  CONSTRAINT fk_user
    FOREIGN KEY(user_id) 
	    REFERENCES users(id)
);
