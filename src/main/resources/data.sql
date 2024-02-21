-- drop table cart if exists;
-- drop table if exists account;
-- drop table if exists item;

-- create table account (
--     account_id int primary key auto_increment,
--     username varchar(255) not null unique,
--     password varchar(255) not null
-- );

-- create table item (
--     item_id int primary key auto_increment,
--     name varchar(255) not null,
--     category varchar(30),
--     seller varchar(30),
--     image_location varchar(255),
--     description varchar(255),
--     price FLOAT
-- );

-- create table cart (
--     account_id int,
--     item_id int,
--     quantity int,
--     primary key (account_id, item)
-- );

-- Starting test values with ids of 9999 to avoid test issues --
insert into account (account_id, username, password) values (9999, 'testuser1', 'password');
insert into account (account_id, username, password) values (9998, 'testuser2', 'password');
insert into account (account_id, username, password) values (9997, 'testuser3', 'password');
insert into account (account_id, username, password) values (9996, 'testuser4', 'password');

-- A bunch of sodas, because we don't have a seller portal --
insert into item (item_id, name, category, seller, image_location, description, price) values (999, 'Coke', '', 'Coca-Cola', '/images/coke.webp', 'A refreshing drink!', 2.99);
insert into item (item_id, name, category, seller, image_location, description, price) values (998, 'Cherry Coke', 'Cherry', 'Coca-Cola', '/images/coke-cherry.jpg', 'A new refreshing drink!', 1.99);
insert into item (item_id, name, category, seller, image_location, description, price) values (997, 'Coke Zero', 'Diet', 'Coca-Cola', '/images/coke-zero.webp', 'A newer refreshing drink!', 1.99);
insert into item (item_id, name, category, seller, image_location, description, price) values (996, 'Diet Coke', 'Diet', 'Coca-Cola', '/images/coke-diet.webp', 'The newest refreshing drink!', 1.99);

insert into item (item_id, name, category, seller, image_location, description, price) values (995, 'Fanta', '', 'Coca-Cola', '/images/fanta-orange.jpg', 'It tastes like fruit!', 2.99);
insert into item (item_id, name, category, seller, image_location, description, price) values (994, 'Cherry Fanta', 'Cherry', 'Coca-Cola', '/images/fanta-cherry.png', 'It ... really doesn''t taste like fruit.', 3.99);

insert into item (item_id, name, category, seller, image_location, description, price) values (993, 'Pepsi', '', 'PepsiCo', '/images/pepsi.webp', 'An alternative refreshing drink!', 2.99);
insert into item (item_id, name, category, seller, image_location, description, price) values (992, 'Cherry Pepsi', 'Cherry', 'PepsiCo', '/images/pepsi-cherry.webp', 'A new alternative refreshing drink!', 1.99);
insert into item (item_id, name, category, seller, image_location, description, price) values (991, 'Diet Pepsi', 'Diet', 'PepsiCo', '/images/pepsi-diet.webp', 'A newer alternative refreshing drink!', 1.99);
insert into item (item_id, name, category, seller, image_location, description, price) values (990, 'Pepsi Zero', 'Diet', 'PepsiCo', '/images/pepsi-zero.jpg', 'The newest alternative refreshing drink!', 1.99);

insert into item (item_id, name, category, seller, image_location, description, price) values (989, 'Sprite', '', 'Coca-Cola', '/images/sprite.jpg', 'Lemon and Lime!', 5.37);
insert into item (item_id, name, category, seller, image_location, description, price) values (988, 'Sprite Zero', 'Diet', 'Coca-Cola', '/images/sprite-zero.jpg', 'Technically has a flavor!', 2.99);

-- Carts, for test purposes --
insert into cart (account_id, item_id, quantity) values (9999, 999, 3);
-- Select * from account;