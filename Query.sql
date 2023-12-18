create database AssignTwo;

use AssignTwo;

create table Registration(username varchar(40),password varchar(40),repassword varchar(40),
usertype varchar(40),Street varchar(40),City varchar(40),State varchar(40),zip  varchar(20));




Create table Productdetails
(
ProductType varchar(20),
Id varchar(20),
productName varchar(40),
productPrice double,
productImage varchar(40),
productManufacturer varchar(40),
productCondition varchar(40),
productDiscount double,
Primary key(Id)
);

ALTER TABLE productdetails
ADD COLUMN rebat varchar(4);
CREATE TABLE Product_accessories (
    productName varchar(20),
    accessoriesName  varchar(20),
    
    
    FOREIGN KEY (productName) REFERENCES Productdetails(Id) ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (accessoriesName) REFERENCES Productdetails(Id) ON DELETE SET NULL
        ON UPDATE CASCADE    
);

Create table CustomerOrders
(
OrderId integer,
userName varchar(40),
orderName varchar(40),
orderPrice double,
userAddress varchar(40),
creditCardNo varchar(40),
purdate varchar(20),
shipdate varchar(20),
quantity varchar(20),
deliveryType varchar(20),
zip varchar(10),
storeAddress varchar(50),
Primary key(OrderId,userName,orderName)
);

select * from Registration;
select * from productdetails;
select * from productdetails where rebat="Yes";
select * from productdetails where productDiscount>0;




select * from CustomerOrders;
CREATE TABLE StoreLocations (
StoreID varchar(10) PRIMARY KEY,
    
    Street VARCHAR(20),
    City VARCHAR(20),
    State VARCHAR(20),
    ZipCode VARCHAR(10) 
);
select * from StoreLocations;
INSERT INTO Registration (username, password, repassword, usertype, Street, City, State, zip)
VALUES
    ('user1', 'pass1', 'pass1', 'customer', '123 Main St', 'New York', 'NY', '10001'),
    ('user2', 'pass2', 'pass2', 'customer', '456 Elm St', 'Los Angeles', 'CA', '90001'),
    ('user3', 'pass3', 'pass3', 'admin', '789 Oak St', 'Chicago', 'IL', '60601'),
    ('user4', 'pass4', 'pass4', 'customer', '101 Pine St', 'Houston', 'TX', '77001'),
    ('user5', 'pass5', 'pass5', 'admin', '202 Maple St', 'Phoenix', 'AZ', '85001'),
    ('user6', 'pass6', 'pass6', 'customer', '303 Birch St', 'Philadelphia', 'PA', '19101'),
    ('user7', 'pass7', 'pass7', 'customer', '404 Cedar St', 'San Antonio', 'TX', '78201'),
    ('user8', 'pass8', 'pass8', 'admin', '505 Redwood St', 'San Diego', 'CA', '92101'),
    ('user9', 'pass9', 'pass9', 'customer', '606 Walnut St', 'Dallas', 'TX', '75201'),
    ('user10', 'pass10', 'pass10', 'customer', '707 Willow St', 'San Jose', 'CA', '95101'),
    ('user11', 'pass11', 'pass11', 'admin', '808 Palm St', 'Austin', 'TX', '78701'),
    ('user12', 'pass12', 'pass12', 'customer', '909 Cedar St', 'San Francisco', 'CA', '94101'),
    ('user13', 'pass13', 'pass13', 'customer', '1010 Pine St', 'Seattle', 'WA', '98101'),
    ('user14', 'pass14', 'pass14', 'admin', '1111 Oak St', 'Denver', 'CO', '80201'),
    ('user15', 'pass15', 'pass15', 'customer', '1212 Elm St', 'Boston', 'MA', '02101'),
    ('user16', 'pass16', 'pass16', 'customer', '1313 Maple St', 'Miami', 'FL', '33101'),
    ('user17', 'pass17', 'pass17', 'admin', '1414 Birch St', 'Atlanta', 'GA', '30301'),
    ('user18', 'pass18', 'pass18', 'customer', '1515 Walnut St', 'New Orleans', 'LA', '70101'),
    ('user19', 'pass19', 'pass19', 'customer', '1616 Redwood St', 'Las Vegas', 'NV', '89101'),
    ('user20', 'pass20', 'pass20', 'admin', '1717 Cedar St', 'Portland', 'OR', '97201');
