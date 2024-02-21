BEGIN TRANSACTION;

INSERT INTO users (firstName, lastName, email, password_hash,role) VALUES ('user','user', 'example@gmail.com','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');



INSERT INTO bookshelf(sku, email) VALUES ('D00927', 'tapan@gmail.com');
INSERT INTO bookshelf(sku, email) VALUES ('D00927', 'inno@gmail.com');


COMMIT TRANSACTION;
