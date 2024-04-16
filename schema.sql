CREATE TABLE orders(
  order_id SERIAL,
  email varchar(255) NOT NULL,
  billing_address_id INTEGER,
  shipping_address_id INTEGER,
  total_price DECIMAL(10, 2),
--  shipping_cost DECIMAL(10, 2),
--  total_weight INTEGER DEFAULT 0,
  delivery_option varchar(10) NOT NULL,
  saveAddress BOOLEAN,
  infoMail BOOLEAN,
  message varchar(250),
  created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT PK_order PRIMARY KEY(order_id),
  CONSTRAINT FK_billing_address FOREIGN KEY (billing_address_id) REFERENCES address(address_id),
  CONSTRAINT FK_shipping_address FOREIGN KEY (shipping_address_id) REFERENCES address(address_id)
);

CREATE TABLE address(
    address_id SERIAL,
    email varchar(50),
    firstname varchar(50) NOT NULL,
    lastname varchar(50) NOT NULL,
    country varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    state varchar(50) NOT NULL,
    zipcode varchar(20) NOT NULL,
    addressLine varchar(100) NOT NULL,
    phoneNumber varchar(15),
    addressType varchar(50) NOT NULL,
    CONSTRAINT PK_address PRIMARY KEY(address_id)

);



CREATE TABLE order_books (
    order_books_id SERIAL PRIMARY KEY,
    bookNo varchar(10) NOT NULL,
    order_id INTEGER NOT NULL,
    FOREIGN KEY(order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    UNIQUE (bookNo)
);

CREATE TABLE user_addresses (
   user_addresses_id SERIAL PRIMARY KEY,
   user_id INTEGER NOT NULL,
   address_id INTEGER NOT NULL,
   FOREIGN KEY(user_id) REFERENCES users(user_id),
   FOREIGN KEY(address_id) REFERENCES address(address_id)
)