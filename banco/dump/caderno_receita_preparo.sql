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
-- Table structure for table `preparo`
--

DROP TABLE IF EXISTS `preparo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preparo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `modo_preparo` varchar(2000) NOT NULL,
  `tempo_preparo` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preparo`
--

LOCK TABLES `preparo` WRITE;
/*!40000 ALTER TABLE `preparo` DISABLE KEYS */;
INSERT INTO `preparo` VALUES (1,'Bata os ingredientes no liquidificador, asse por 40 minutos e finalize com cobertura.',50),(2,'Misture os ingredientes manualmente, leve ao forno pré-aquecido por 35 minutos.',45),(3,'Refogue os ingredientes, adicione o arroz aos poucos com caldo até ficar cremoso.',40),(4,'Prepare a massa, adicione cobertura e asse até dourar.',60),(5,'Cozinhe os ingredientes lentamente mexendo sempre.',35),(6,'Misture os ingredientes da massa, deixe crescer e asse.',90),(7,'Refogue camarões, acrescente arroz e finalize com parmesão.',45),(8,'Monte a pizza com molho, recheio e queijo e asse.',30),(9,'Misture ingredientes secos e líquidos, asse até dourar.',55),(10,'Prepare o risoto adicionando caldo aos poucos até atingir cremosidade.',50);
/*!40000 ALTER TABLE `preparo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-17 21:57:27
