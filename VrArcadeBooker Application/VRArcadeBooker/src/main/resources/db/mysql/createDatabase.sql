
-- Create database
--
-- @author OB
-- @since 20200524


DROP DATABASE if exists CIS_2232_vrarcadebooker;

CREATE DATABASE CIS_2232_vrarcadebooker;
USE vrarcadebooker;


/*Create table with 3 rows*/
CREATE TABLE IF NOT EXISTS VrBooker (

id int(5) NOT NULL PRIMARY KEY AUTO_INCREMENT, 
name1 varchar(100) NOT NULL COMMENT 'Booker name',
name2 varchar(100) DEFAULT NULL COMMENT 'Player 2',
name3 varchar(100) DEFAULT NULL COMMENT 'Player 3',
name4 varchar(100) DEFAULT NULL COMMENT 'Player 4',
dateOfBooking varchar(10) DEFAULT NULL COMMENT 'yyyy-MM-dd',
timeOfBooking varchar(5) DEFAULT NULL COMMENT 'hh:mm',
lengthOfBooking int (5) NOT NULL
);