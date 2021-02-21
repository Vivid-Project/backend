CREATE TABLE IF NOT EXISTS dream_themes (
  id INT PRIMARY KEY,
  dream_id INT,
  theme_id INT,
  CONSTRAINT fk_dreamTheme_dream
    FOREIGN KEY(dream_id) 
	    REFERENCES dreams(id),
  CONSTRAINT fk_dreamTheme_theme
    FOREIGN KEY(theme_id) 
	    REFERENCES themes(id)
);

