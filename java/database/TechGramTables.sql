BEGIN TRANSACTION;

DROP TABLE IF EXISTS photos;
DROP TABLE IF EXISTS likes;
DROP TABLE IF EXISTS activity;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS favorites;

DROP SEQUENCE IF EXISTS seq_photo_id, seq_like_id, seq_activity_id, seq_comment_id, seq_favorite_id;

CREATE TABLE photos
(
photo_id serial not null,
user_id int not null,
caption varchar(500) not null,
image_location text not null,
date_and_time timestamp without time zone not null,
CONSTRAINT pk_photos PRIMARY KEY (photo_id),
CONSTRAINT fk_photos_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE SEQUENCE seq_photo_id
 INCREMENT BY 1
 START WITH 1001
 NO MAXVALUE;

CREATE TABLE activity
(
activity_id serial not null,
like_count int not null,
CONSTRAINT pk_activity PRIMARY KEY (activity_id)
);

CREATE SEQUENCE seq_activity_id
 INCREMENT BY 1
 START WITH 5001
 NO MAXVALUE;

CREATE TABLE likes
(
like_id serial not null,
photo_id int not null,
is_active boolean default true,
user_id int not null,
date_and_time timestamp without time zone not null,
CONSTRAINT pk_likes PRIMARY KEY (like_id),
CONSTRAINT fk_likes_photos  FOREIGN KEY (photo_id) REFERENCES photos(photo_id),
CONSTRAINT fk_likes_users  FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE SEQUENCE seq_like_id
 INCREMENT BY 1
 START WITH 3001
 NO MAXVALUE;

CREATE TABLE comments (
comment_id serial not null,
photo_id int not null,
user_id int not null,
text varchar(500) not null,
date_and_time timestamp without time zone not null,
CONSTRAINT pk_comments PRIMARY KEY (comment_id),
CONSTRAINT fk_comments_users FOREIGN KEY (user_id) REFERENCES users(user_id),
CONSTRAINT fk_comments_photos FOREIGN KEY (photo_id) REFERENCES photos(photo_id)
);

CREATE SEQUENCE seq_comment_id
 INCREMENT BY 1
 START WITH 2001
 NO MAXVALUE;

CREATE TABLE favorites
(
favorite_id serial not null,
photo_id int not null,
user_id int not null,
date_and_time timestamp without time zone not null,
CONSTRAINT pk_favorites PRIMARY KEY (favorite_id),
CONSTRAINT fk_favorites_users  FOREIGN KEY (user_id) REFERENCES users(user_id),
CONSTRAINT fk_favorites_photos FOREIGN KEY (photo_id) REFERENCES photos(photo_id)
);

CREATE SEQUENCE seq_favorite_id
 INCREMENT BY 1
 START WITH 4001
 NO MAXVALUE;
 
 GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;
 
COMMIT TRANSACTION;