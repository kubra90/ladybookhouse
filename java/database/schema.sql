BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, orders, bookshelf;

CREATE TABLE users (
	user_id SERIAL,
    firstName varchar(255) NOT NULL,
    lastName varchar(255) NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
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
   zipcode varchar(20) NOT NULL,
   address varchar(100) NOT NULL,
   phoneNumber varchar(15),
   email varchar(50) NOT NULL,
--   bookNo varchar(10) NOT NULL UNIQUE,
   message varchar(250),
--   added a timestamp column for order creation date and time
   created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
   CONSTRAINT PK_order PRIMARY KEY(order_id)
);

CREATE TABLE order_books (
    order_books_id SERIAL PRIMARY KEY,
    bookNo varchar(10) NOT NULL,
    order_id INTEGER NOT NULL,
    FOREIGN KEY(order_id) REFERENCES orders(order_id) ON DELETE CASCADE
    UNIQUE (order_id, bookNo)
)

CREATE TABLE books (
    isbn VARCHAR(13),
    title VARCHAR(255),
    image VARCHAR(255),
    price DECIMAL(10, 2),
    media VARCHAR(50),
    publisher VARCHAR(255),
    author VARCHAR(255),
    weight INTEGER,
    notes TEXT,
    quantity INTEGER,
    inventory_code VARCHAR(10) PRIMARY KEY UNIQUE NOT NULL,
    condition INTEGER,
    category VARCHAR(100),
    listed_date DATE,
    condition_as_text VARCHAR(255),
    used_book VARCHAR(50)
);


CREATE TABLE bookshelf (
  bookshelf_id SERIAL PRIMARY KEY,
  email varchar(50),
  sku varchar(10) NOT NULL,
--  title varchar(100) NOT NULL,
--  CONSTRAINT PK_bookshelf PRIMARY KEY(bookshelf_id)
FOREIGN KEY (sku) REFERENCES books(inventory_code)
);

COMMIT TRANSACTION;
