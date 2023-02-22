-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_RPRbAZA
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `Journeys`
--

DROP TABLE IF EXISTS `Journeys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Journeys` (
  `id` int NOT NULL AUTO_INCREMENT,
  `train` int DEFAULT NULL,
  `departureStation` int DEFAULT NULL,
  `arrivalStation` int DEFAULT NULL,
  `departureDate` date DEFAULT NULL,
  `arrivalDate` date DEFAULT NULL,
  `departureTime` time DEFAULT NULL,
  `arrivalTime` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idJourneys_UNIQUE` (`id`),
  KEY `fk_train_id_idx` (`train`),
  KEY `fk_departureStation_id_idx` (`departureStation`),
  KEY `fk_arrivalStation_id_idx` (`arrivalStation`),
  CONSTRAINT `fk_arrivalStation_id` FOREIGN KEY (`arrivalStation`) REFERENCES `RailwayStations` (`id`),
  CONSTRAINT `fk_departureStation_id` FOREIGN KEY (`departureStation`) REFERENCES `RailwayStations` (`id`),
  CONSTRAINT `fk_train_id` FOREIGN KEY (`train`) REFERENCES `Trains` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Journeys`
--

LOCK TABLES `Journeys` WRITE;
/*!40000 ALTER TABLE `Journeys` DISABLE KEYS */;
INSERT INTO `Journeys` VALUES (1,1,3,5,'2023-01-25','2023-01-25','10:00:00','14:00:00'),(2,2,5,3,'2023-02-23','2023-02-23','10:30:00','12:50:00'),(4,2,6,5,'2023-01-17','2023-01-17','12:00:00','19:15:00'),(5,12,3,6,'2023-02-25','2023-02-26','23:20:00','03:15:00'),(6,2,3,6,'2023-02-25','2023-02-25','18:00:00','22:15:00'),(7,14,3,7,'2023-02-28','2023-02-28','10:25:00','12:55:00');
/*!40000 ALTER TABLE `Journeys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RailwayStations`
--

DROP TABLE IF EXISTS `RailwayStations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RailwayStations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idRailwayStations_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RailwayStations`
--

LOCK TABLES `RailwayStations` WRITE;
/*!40000 ALTER TABLE `RailwayStations` DISABLE KEYS */;
INSERT INTO `RailwayStations` VALUES (3,'Railway Station Sarajevo','Put života 2','Sarajevo','Bosnia and Herzegovina'),(5,'Railway Station Mostar','Trg Ivana Krndelja 1','Mostar','Bosnia and Herzegovina'),(6,'Railway Station Tuzla','Bosne Srebrene 58','Tuzla','Bosnia and Herzegovina'),(7,'Railway Station Zenica','Stanični trg 1','Zenica','Bosnia and Herzegovina');
/*!40000 ALTER TABLE `RailwayStations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Trains`
--

DROP TABLE IF EXISTS `Trains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Trains` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `dateBought` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idTrains_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trains`
--

LOCK TABLES `Trains` WRITE;
/*!40000 ALTER TABLE `Trains` DISABLE KEYS */;
INSERT INTO `Trains` VALUES (1,'Thomas','2022-12-31'),(2,'Bertie','2001-02-10'),(10,'Donald','2005-03-25'),(12,'Duck','2023-01-17'),(14,'Anna','2023-02-22');
/*!40000 ALTER TABLE `Trains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'freedb_RPRbAZA'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-22 12:32:56
