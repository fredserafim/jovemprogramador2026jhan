-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: caderno_receita
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `ingrediente_receita`
--

DROP TABLE IF EXISTS `ingrediente_receita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingrediente_receita` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `receita_id` int(11) NOT NULL,
  `ingredientes_id` int(11) NOT NULL,
  `quantidade` decimal(10,2) NOT NULL,
  `observacao` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ingrediente_receita_receita1_idx` (`receita_id`),
  KEY `fk_ingrediente_receita_ingredientes1_idx` (`ingredientes_id`),
  CONSTRAINT `fk_ingrediente_receita_ingredientes1` FOREIGN KEY (`ingredientes_id`) REFERENCES `ingredientes` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_ingrediente_receita_receita1` FOREIGN KEY (`receita_id`) REFERENCES `receita` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingrediente_receita`
--

LOCK TABLES `ingrediente_receita` WRITE;
/*!40000 ALTER TABLE `ingrediente_receita` DISABLE KEYS */;
INSERT INTO `ingrediente_receita` VALUES (1,2,1,2.00,''),(2,2,2,200.00,''),(3,2,3,2.00,'');
/*!40000 ALTER TABLE `ingrediente_receita` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-22 19:48:31
