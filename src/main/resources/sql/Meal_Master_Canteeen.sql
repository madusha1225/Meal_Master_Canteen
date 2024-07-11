drop database Meal_Master_Canteen;
create database Meal_Master_Canteen;
use Meal_Master_Canteen;

create table User(
                     userId int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     userName VARCHAR (20),
                     userPassword VARCHAR (10)
);

create table Factory_Employee(
                                 factoryEmployeeId int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 name VARCHAR (20),
                                 factoryEmployeeNumber int (10)
);

create table workers(
                        workerId VARCHAR (05) PRIMARY KEY,
                        userId int NOT NULL AUTO_INCREMENT,
                        name VARCHAR (20),
                        address VARCHAR(30),
                        email VARCHAR(20),
                        telephone_Number int(10),
                        CONSTRAINT FOREIGN KEY(userId) REFERENCES User(userId) on Delete Cascade on Update Cascade
);

create table Orders(
                       orderId VARCHAR (05) PRIMARY KEY,
                       userId int NOT NULL AUTO_INCREMENT,
                       factoryEmployeeId int,
                       totalAmount int (30),
                       date date,
                       CONSTRAINT FOREIGN KEY(userId) REFERENCES User(userId) on Delete Cascade on Update Cascade,
                       CONSTRAINT FOREIGN KEY(factoryEmployeeId) REFERENCES Factory_Employee(factoryEmployeeId) on Delete Cascade on Update Cascade
);

CREATE TABLE Attendance (
                            factoryEmployeeId int,
                            inTime TIME,
                            outTime TIME,
                            date DATE,
                            CONSTRAINT FOREIGN KEY (factoryEmployeeId) REFERENCES Factory_Employee(factoryEmployeeId) ON DELETE CASCADE ON UPDATE CASCADE
);

create table Payments(
                         paymentId int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         orderId VARCHAR (05),
                         paymentType VARCHAR (06),
                         date date,
                         CONSTRAINT FOREIGN KEY(orderId) REFERENCES Orders(orderId) on Delete Cascade on Update Cascade
);

create table meal(
                     mealId VARCHAR (05) PRIMARY KEY,
                     description VARCHAR (50),
                     price int(30),
                     status VARCHAR (15)
);

create table OrderDetails(
                             orderId VARCHAR (05),
                             mealId VARCHAR (05),
                             quantity int (10),
                             CONSTRAINT FOREIGN KEY(orderId) REFERENCES Orders(orderId) on Delete Cascade on Update Cascade,
                             CONSTRAINT FOREIGN KEY(mealId) REFERENCES meal(mealId) on Delete Cascade on Update Cascade
);

create table Meal_Ingredient_Details(
                                        ingredientId VARCHAR (05) PRIMARY KEY,
                                        mealId VARCHAR (05),
                                        CONSTRAINT FOREIGN KEY(mealId) REFERENCES meal(mealId) on Delete Cascade on Update Cascade
);

create table Ingredient(
                           ingredientId VARCHAR (05) PRIMARY KEY,
                           description VARCHAR (50),
                           price int(30)
);

create table supplier(
                         supplierId VARCHAR (05) PRIMARY KEY,
                         name VARCHAR (20),
                         address VARCHAR (30),
                         email VARCHAR(20),
                         telePhone_Number int(10)
);

insert into User values(1,'madusha',1234);
