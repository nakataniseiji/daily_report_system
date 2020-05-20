-- MySQL dump 10.13  Distrib 5.7.29, for macos10.14 (x86_64)
--
-- Host: localhost    Database: daily_report_system
-- ------------------------------------------------------
-- Server version	5.7.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_flag` int(11) NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `delete_flag` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(64) NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3um79qgwg340lpaw7phtwudtc` (`code`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,0,'10101','2020-05-12 12:34:42',1,'あとむa','050482425F7EBE701301907C4F51D7C696D6B619A42691F372CBB92AFD471503','2020-05-12 12:54:04'),(2,0,'874578','2020-05-12 12:53:52',0,'1010','050482425F7EBE701301907C4F51D7C696D6B619A42691F372CBB92AFD471503','2020-05-12 12:53:52'),(3,1,'1','2020-05-12 17:24:10',0,'管理者','F18B434DF76D3CAD7F7970424857446C1DBF4B89F035CD70AC25DAB5AE6990AD','2020-05-12 17:24:10'),(4,1,'10','2020-05-14 02:29:04',0,'10','A147C71B49165E0F14F534827A7262300EF997E90FF86F7AE95473020A8B2D81','2020-05-14 02:29:04'),(5,0,'5','2020-05-18 16:54:47',0,'a','53EADA2FD1C3CF73754A741EBD1AA4E2E91294DE185985D358B7713547CCBB53','2020-05-18 16:54:47');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext NOT NULL,
  `created_at` datetime NOT NULL,
  `report_date` date NOT NULL,
  `title` varchar(255) NOT NULL,
  `updated_at` datetime NOT NULL,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7m58c0h3phjcm6bco7aqikfen` (`employee_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (1,'null','2020-05-14 18:47:36','2020-05-14','初投稿','2020-05-14 18:47:36',4),(2,'as','2020-05-18 16:57:41','2020-05-18','初投稿','2020-05-18 16:57:47',5);
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-19 13:25:18
