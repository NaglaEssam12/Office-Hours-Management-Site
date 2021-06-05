-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: office_hours
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `messageID` int NOT NULL AUTO_INCREMENT,
  `fromUserID` int NOT NULL,
  `toUserID` int NOT NULL,
  `actualMassage` varchar(200) NOT NULL,
  `date` varchar(45) NOT NULL,
  PRIMARY KEY (`messageID`),
  UNIQUE KEY `messageID_UNIQUE` (`messageID`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (37,1,2,'hello mai','2021-01-15'),(38,1,5,'hello staff','2021-01-15'),(39,1,51,'hello staff','2021-01-15'),(40,1,4,'hello nagla','2021-01-15'),(41,4,1,'hello mayada','2021-01-15'),(48,4,1,'reply','2021-01-15'),(49,4,1,'reply','2021-01-15');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `notificationID` int NOT NULL AUTO_INCREMENT,
  `fromEmail` varchar(45) NOT NULL,
  `toEmail` varchar(45) NOT NULL,
  `date` varchar(45) DEFAULT NULL,
  `notificationMessage` varchar(200) NOT NULL,
  UNIQUE KEY `notificationID_UNIQUE` (`notificationID`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (48,'mona76115@gmail.com','naglaaessam4@gmail.com',NULL,'Reservation request from student ID: 3'),(49,'mona76115@gmail.com','mona100@gmail.com',NULL,'Reservation request from student ID: 3'),(50,'mona76115@gmail.com','naglaaessam4@gmail.com',NULL,'Reservation cancel from student ID: 3'),(51,'aa@gmail.com','naglaaessam4@gmail.com',NULL,'Reservation request from student ID: 1'),(52,'aa@gmail.com','naglaaessam4@gmail.com',NULL,'Reservation request from student ID: 1'),(53,'aa@gmail.com','ss@gmail.com',NULL,'hello mai'),(54,'aa@gmail.com','mariamnasser979@gmail.com',NULL,'hello staff'),(55,'aa@gmail.com','mona100@gmail.com',NULL,'hello staff');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `office_hours`
--

DROP TABLE IF EXISTS `office_hours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `office_hours` (
  `officeHoursID` int NOT NULL AUTO_INCREMENT,
  `fromTime` varchar(45) NOT NULL,
  `toTime` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `state` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `userID` int NOT NULL,
  `sent` varchar(45) NOT NULL,
  PRIMARY KEY (`officeHoursID`),
  UNIQUE KEY `officeHoursID_UNIQUE` (`officeHoursID`),
  KEY `userID_idx` (`userID`),
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `office_hours`
--

LOCK TABLES `office_hours` WRITE;
/*!40000 ALTER TABLE `office_hours` DISABLE KEYS */;
INSERT INTO `office_hours` VALUES (1,'13:30','14:30','2021-01-13','offline','staff_room',4,'false'),(2,'08:30','09:30','2021-01-03','offline','faculty',4,'false'),(3,'08:30','09:30','2021-01-04','offline','zoom meeting',2,'false'),(4,'16:00','17:30','2021-01-05','online','zoom meeting',4,'false'),(5,'12:30','1:30','2021-01-08','online','elshaf3i',51,'false'),(6,'12:30','2:30','2021-01-08','offline','elshaf3i',51,'false'),(7,'12:30','2:30','2021-01-13','offline','elsahf3i',51,'false');
/*!40000 ALTER TABLE `office_hours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `reservationID` int NOT NULL AUTO_INCREMENT,
  `officeHoursID` int NOT NULL,
  `fromUserID` int NOT NULL,
  `toUserID` int NOT NULL,
  PRIMARY KEY (`reservationID`),
  UNIQUE KEY `reservationID_UNIQUE` (`reservationID`),
  KEY `officeHoursID_idx` (`officeHoursID`),
  KEY `fromUserID_idx` (`fromUserID`),
  KEY `toUserID_idx` (`toUserID`),
  CONSTRAINT `fromUserID` FOREIGN KEY (`fromUserID`) REFERENCES `user` (`userID`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `officeHoursID` FOREIGN KEY (`officeHoursID`) REFERENCES `office_hours` (`officeHoursID`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `toUserID` FOREIGN KEY (`toUserID`) REFERENCES `user` (`userID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,1,3,4),(2,1,5,4),(5,7,3,51),(8,6,3,51),(9,1,1,4),(10,1,1,4);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subjectID` int NOT NULL AUTO_INCREMENT,
  `subjectName` varchar(45) NOT NULL,
  PRIMARY KEY (`subjectID`),
  UNIQUE KEY `subjectID_UNIQUE` (`subjectID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Math-2'),(2,'CS');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `displayName` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `subjectName` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userID_UNIQUE` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Mayada','mayada','aa@gmail.com','student','mayada1','','CS'),(2,'Mai','mai','ss@gmail.com','Dr','g96PDfnbGVmE45QIfUA9','Math-2','null'),(3,'Mona','mona12','mona76115@gmail.com','student','201',NULL,'CS'),(4,'Naglaa','nagla','naglaaessam4@gmail.com','TA','naglaa123','Math-2',NULL),(5,'Mariam','mariooma','mariamnasser979@gmail.com','Dr','12345','CS','null'),(51,'Mona','Mona','mona100@gmail.com','TA','g1WCXOAn3E2qGcQ9JJ3F','CS',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_subject`
--

DROP TABLE IF EXISTS `user_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_subject` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `staffMemberID` int NOT NULL,
  `subjectID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `staffMemberID_idx` (`staffMemberID`),
  KEY `subjectID_idx` (`subjectID`),
  CONSTRAINT `staffMemberID` FOREIGN KEY (`staffMemberID`) REFERENCES `user` (`userID`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `subjectID` FOREIGN KEY (`subjectID`) REFERENCES `subject` (`subjectID`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_subject`
--

LOCK TABLES `user_subject` WRITE;
/*!40000 ALTER TABLE `user_subject` DISABLE KEYS */;
INSERT INTO `user_subject` VALUES (1,2,1),(2,4,1),(3,5,2),(4,51,2);
/*!40000 ALTER TABLE `user_subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-15 22:35:07
