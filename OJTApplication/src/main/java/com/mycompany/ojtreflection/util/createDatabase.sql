-- Create database
--
-- @author OB
-- @since 20200524

-- CREATE DATABASE ojt;
-- USE ojt;

CREATE DATABASE ojt;
USE ojt;


/*username cis2232, password Test1234*/

grant select, insert, update, delete on ojt.*
to 'cis2232_admin'@'localhost'
identified by 'Test1234';
flush privileges;

/*Create table with 3 rows*/
CREATE TABLE IF NOT EXISTS OjtReflection (

studentId int(5) NOT NULL,
name1 varchar(20) NOT NULL,
reflection varchar(140) NOT NULL
);

