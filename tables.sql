DROP database IF EXISTS `jpa_JBD`;
CREATE database IF NOT EXISTS `jpa_JBD`;
use `jpa_JBD`;


DROP TABLE IF EXISTS `jpa_JBD`.`STUDENT`;
DROP TABLE IF EXISTS `jpa_JBD`.`BRANCH_SUBJECT`;
DROP TABLE IF EXISTS `jpa_JBD`.`SUBJECT`;
DROP TABLE IF EXISTS `jpa_JBD`.`BRANCH`;


CREATE TABLE `BRANCH` (
  `BRANCH_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BRANCH_SHORT_NAME` varchar(45) NOT NULL,
  `BRANCH_NAME` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`BRANCH_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `SUBJECT` (
  `SUBJECT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SUBJECT_NAME` varchar(100) NOT NULL,
  `SUBJECT_DESC` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`SUBJECT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;


CREATE TABLE `BRANCH_SUBJECT` (
  `BRANCH_ID` int(11) NOT NULL,
  `SUBJECT_ID` int(11) NOT NULL,
  PRIMARY KEY (`BRANCH_ID`,`SUBJECT_ID`),
  KEY `SUBJECT_ID_FK_idx` (`SUBJECT_ID`),
  CONSTRAINT `BRANCH_SUB_ID_FK` 
  	FOREIGN KEY (`BRANCH_ID`) 
  	REFERENCES `BRANCH` (`BRANCH_ID`) 
  	ON DELETE NO ACTION 
  	ON UPDATE NO ACTION,
  CONSTRAINT `SUBJECT_SUB_ID_FK` 
  	FOREIGN KEY (`SUBJECT_ID`) 
  	REFERENCES `Subject` (`SUBJECT_ID`) 
  	ON DELETE NO ACTION 
  	ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `STUDENT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(45) DEFAULT NULL,
  `LAST_NAME` varchar(45) DEFAULT NULL,
  `CONTACT_NO` varchar(45) DEFAULT NULL,
  `BRANCH_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `BRANCH_ID_FK_idx` (`BRANCH_ID`),
  CONSTRAINT `BRANCH_ID_FK` 
    FOREIGN KEY (`BRANCH_ID`) 
    REFERENCES `BRANCH` (`BRANCH_ID`) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*
 * -------------------------------------------------------------------------------- 
	SELECT * FROM `BRANCH`;
	SELECT * FROM `SUBJECT`;
	SELECT * FROM `BRANCH_SUBJECT`;
	SELECT * FROM `STUDENT`;
	
	SELECT * FROM `BRANCH`
	JOIN `BRANCH_SUBJECT` 
		ON `BRANCH`.BRANCH_ID = `BRANCH_SUBJECT`.BRANCH_ID
	JOIN SUBJECT 
		ON `BRANCH_SUBJECT`.SUBJECT_ID = `SUBJECT`.SUBJECT_ID;
 * --------------------------------------------------------------------------------
 */