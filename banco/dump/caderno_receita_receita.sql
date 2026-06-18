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
-- Table structure for table `receita`
--

DROP TABLE IF EXISTS `receita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receita` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `tempo` int(11) NOT NULL,
  `complexidade_id` int(11) NOT NULL,
  `porcoes` int(11) NOT NULL,
  `categoria_id` int(11) NOT NULL,
  `ferramentas_id` int(11) NOT NULL,
  `preparo_id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `data_criacao` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_receita_ferramentas_idx` (`ferramentas_id`),
  KEY `fk_receita_preparo1_idx` (`preparo_id`),
  KEY `fk_receita_usuario1_idx` (`usuario_id`),
  KEY `fk_receita_categoria1_idx` (`categoria_id`),
  KEY `fk_receita_complexidade1_idx` (`complexidade_id`),
  CONSTRAINT `fk_receita_categoria1` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_receita_complexidade1` FOREIGN KEY (`complexidade_id`) REFERENCES `complexidade` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_receita_ferramentas` FOREIGN KEY (`ferramentas_id`) REFERENCES `ferramentas` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_receita_preparo1` FOREIGN KEY (`preparo_id`) REFERENCES `preparo` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_receita_usuario1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receita`
--

LOCK TABLES `receita` WRITE;
/*!40000 ALTER TABLE `receita` DISABLE KEYS */;
INSERT INTO `receita` VALUES (1,'Bolo de Cenoura',50,1,8,1,1,1,1,'2026-06-16'),(2,'Bolo de Limão',45,1,8,1,2,2,1,'2026-06-16'),(3,'Bolo de Coco Gelado',55,2,10,1,6,9,1,'2026-06-16'),(4,'Risoto de Funghi',40,2,4,2,3,3,1,'2026-06-16'),(5,'Risoto de Camarão',45,3,4,2,3,7,1,'2026-06-16'),(6,'Risoto de Alho-Poró com Bacon',50,2,5,2,5,10,1,'2026-06-16'),(7,'Pizza Margherita',60,1,6,3,7,4,1,'2026-06-16'),(8,'Pizza Calabresa',30,1,6,3,7,8,1,'2026-06-16'),(9,'Pizza Quatro Queijos',35,2,6,3,7,8,1,'2026-06-16'),(10,'Pizza Frango com Catupiry',40,2,6,3,7,8,1,'2026-06-16');
/*!40000 ALTER TABLE `receita` ENABLE KEYS */;
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
