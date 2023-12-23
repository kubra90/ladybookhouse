BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, orders;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE orders (
   order_id SERIAL,
   firstname varchar(50) NOT NULL,
   lastname varchar(50) NOT NULL,
   country varchar(50) NOT NULL,
   city varchar(50) NOT NULL,
   state varchar(50) NOT NULL,
--   check 5 character is acceptable or not?
   zipcode varchar(5) NOT NULL,
   address varchar(100) NOT NULL,
   phoneNumber varchar(15),
   email varchar(50),
   bookNo varchar(10) NOT NULL UNIQUE,
   CONSTRAINT PK_order PRIMARY KEY(order_id)
)

COMMIT TRANSACTION;
