Create table IF NOT EXISTS user_(
    id int Primary key auto_increment,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(150) NOT NULL UNIQUE,
    roleUser smallint not null
);

/*1*/
CREATE TABLE IF NOT EXISTS phone_number (
  `id_phone_number` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `phone_number` VARCHAR(11) NOT NULL UNIQUE
);
/*2*/
CREATE TABLE IF NOT EXISTS full_name (
  `id_full_name` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `surname` VARCHAR(50)
);
/*3*/
CREATE TABLE IF NOT EXISTS client (
  `id_client` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `full_name_id_full_name` INT NOT NULL,
  `number_of_orders` INT NOT NULL,
  `personal_discount` FLOAT NOT NULL DEFAULT 0,
  `phone_number_id_phone_number` INT NOT NULL,
  `user_id_user` INT NOT NULL,

  FOREIGN KEY (`full_name_id_full_name`) REFERENCES full_name(`id_full_name`),
  FOREIGN KEY (`phone_number_id_phone_number`) REFERENCES phone_number(`id_phone_number`),
  FOREIGN KEY (`user_id_user`) REFERENCES user_(`id`)
);
/*4*/
CREATE TABLE IF NOT EXISTS department (
  `id_department` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name_of_department` VARCHAR(45) NOT NULL,
  `number_of_employees` INT NOT NULL DEFAULT 0
);
/*5*/
CREATE TABLE IF NOT EXISTS post (
  `id_post` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name_of_post` VARCHAR(45) NOT NULL,
  `salary` FLOAT NOT NULL,
  `department_id_department` INT NOT NULL,

  FOREIGN KEY (`department_id_department`) REFERENCES department(`id_department`)
);
/*6*/
CREATE TABLE IF NOT EXISTS pizzeria_employee (
  `id_pizzeria_employee` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `post_id_post` INT NOT NULL,
  `full_name_id_full_name` INT NOT NULL,
  `phone_number_id_phone_number` INT NOT NULL,
  `date_of_employment` DATE NOT NULL,
  `employment_status` TINYINT NOT NULL,
  `date_of_dismissal` DATE,

  FOREIGN KEY (`post_id_post`) REFERENCES post(`id_post`),
  FOREIGN KEY (`full_name_id_full_name`) REFERENCES full_name(`id_full_name`),
  FOREIGN KEY (`phone_number_id_phone_number`) REFERENCES phone_number(`id_phone_number`)
);
/*7*/
CREATE TABLE IF NOT EXISTS status_of_order (
  `id_status_of_order` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `status` VARCHAR(20) NOT NULL UNIQUE
);
/*8*/
CREATE TABLE IF NOT EXISTS orders (
  `id_order` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `client_id_client` INT NOT NULL,
  `description` VARCHAR(200),
  `total_cost` INT NOT NULL,
  `status_of_order_id_status_of_order` INT NOT NULL,
  `date_of_creation` DATETIME NOT NULL,
  `pizzeria_employee_id_pizzeria_employee` INT,

  FOREIGN KEY (`client_id_client`) REFERENCES client(`id_client`),
  FOREIGN KEY (`status_of_order_id_status_of_order`) REFERENCES status_of_order(`id_status_of_order`),
  FOREIGN KEY (`pizzeria_employee_id_pizzeria_employee`) REFERENCES pizzeria_employee(`id_pizzeria_employee`)
);
/*9*/
CREATE TABLE IF NOT EXISTS dish (
  `id_dish` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `description` VARCHAR(200) NOT NULL,
  `cost` INT NOT NULL,
  `available` TINYINT NOT NULL
);

/*10*/
CREATE TABLE IF NOT EXISTS ingredient (
  `id_ingredient` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `quantity` INT NOT NULL
);
/*11*/
CREATE TABLE IF NOT EXISTS orders_has_dish (
  `dish_id_dish` INT NOT NULL,
  `order_id_order` INT NOT NULL,
  PRIMARY KEY (`dish_id_dish`, `order_id_order`),
  FOREIGN KEY (`dish_id_dish`) REFERENCES dish(`id_dish`),
  FOREIGN KEY (`order_id_order`) REFERENCES orders(`id_order`)
);
/*12*/
CREATE TABLE IF NOT EXISTS ingredient_has_dish (
  `ingredient_id_ingredient` INT NOT NULL,
  `dish_id_dish` INT NOT NULL,
  PRIMARY KEY (`ingredient_id_ingredient`, `dish_id_dish`),
  FOREIGN KEY (`ingredient_id_ingredient`) REFERENCES ingredient(`id_ingredient`),
  FOREIGN KEY (`dish_id_dish`) REFERENCES dish(`id_dish`)
);
