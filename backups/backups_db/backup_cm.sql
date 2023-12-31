-- MySQL dump 10.13  Distrib 8.0.33, for Linux (x86_64)
--
-- Host: localhost    Database: ms_cm
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account_ms`
--

DROP TABLE IF EXISTS `account_ms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_ms` (
  `id` bigint NOT NULL,
  `balance` decimal(19,4) NOT NULL,
  `id_client` varchar(50) DEFAULT NULL,
  `name_client` varchar(50) NOT NULL,
  `number` varchar(50) NOT NULL,
  `opening_balance` decimal(19,4) NOT NULL,
  `state` bit(1) NOT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_ms`
--

LOCK TABLES `account_ms` WRITE;
/*!40000 ALTER TABLE `account_ms` DISABLE KEYS */;
INSERT INTO `account_ms` VALUES (2,2500.0000,'1','Jose Lema','478759',2000.0000,_binary '','Ahorros');
/*!40000 ALTER TABLE `account_ms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_ms_seq`
--

DROP TABLE IF EXISTS `account_ms_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_ms_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_ms_seq`
--

LOCK TABLES `account_ms_seq` WRITE;
/*!40000 ALTER TABLE `account_ms_seq` DISABLE KEYS */;
INSERT INTO `account_ms_seq` VALUES (101);
/*!40000 ALTER TABLE `account_ms_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_ms`
--

DROP TABLE IF EXISTS `transaction_ms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_ms` (
  `id` bigint NOT NULL,
  `date` datetime(6) NOT NULL,
  `type` varchar(50) NOT NULL,
  `value` decimal(19,4) NOT NULL,
  `account_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKri6mf41xeifxsl0ybreqhvm7s` (`account_id`),
  CONSTRAINT `FKri6mf41xeifxsl0ybreqhvm7s` FOREIGN KEY (`account_id`) REFERENCES `account_ms` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_ms`
--

LOCK TABLES `transaction_ms` WRITE;
/*!40000 ALTER TABLE `transaction_ms` DISABLE KEYS */;
INSERT INTO `transaction_ms` VALUES (1,'2023-10-18 00:00:00.000000','deposito',1000.0000,2),(2,'2023-10-18 00:00:00.000000','retiro',500.0000,2);
/*!40000 ALTER TABLE `transaction_ms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_ms_seq`
--

DROP TABLE IF EXISTS `transaction_ms_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_ms_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_ms_seq`
--

LOCK TABLES `transaction_ms_seq` WRITE;
/*!40000 ALTER TABLE `transaction_ms_seq` DISABLE KEYS */;
INSERT INTO `transaction_ms_seq` VALUES (101);
/*!40000 ALTER TABLE `transaction_ms_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-21  3:28:04
