INSERT INTO app_user (user_name, bio) VALUES ('TheRock', 'It''s about grind');
INSERT INTO app_user (user_name, bio) VALUES ('John Cena', 'You can''t see me');
INSERT INTO app_user (user_name, bio) VALUES ('Kevin Hart', 'Shortest guy in the room');

INSERT INTO hot_take (content, author_id, creation_date)
VALUES ('LeBron is officially better than MJ after last night.',  1, CURRENT_TIMESTAMP);

INSERT INTO hot_take (content, author_id, creation_date)
VALUES ('LeBron is officially better than MJ after last night.', 1, CURRENT_TIMESTAMP);

INSERT INTO hot_take (content, author_id, creation_date)
VALUES ('The Celtics are winning the next 3 championships. Guaranteed.',2, CURRENT_TIMESTAMP);

INSERT INTO hot_take (content, author_id, creation_date)
VALUES ('Shaq would average 50 points in today''s NBA.', 3, CURRENT_TIMESTAMP);

INSERT INTO comment (content, user_id, hottake_id, created_date)
VALUES ('You can''t even see the truth in that statement!', 2, 1, CURRENT_TIMESTAMP);

INSERT INTO comment (content, user_id, hottake_id, created_date)
VALUES ('Shaq, you''re just big. I''d cross you up!', 3, 3, CURRENT_TIMESTAMP);

INSERT INTO comment (content, user_id, hottake_id, created_date)
VALUES ('He would not lets be realistic', 1, 3, CURRENT_TIMESTAMP);
