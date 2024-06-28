CREATE DATABASE  IF NOT EXISTS `excuse_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `excuse_db`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: excuse_db
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
-- Table structure for table `history_tbl`
--

DROP TABLE IF EXISTS `history_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history_tbl` (
  `history_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `excuse_id` bigint(20) DEFAULT NULL,
  `excuse_content` varchar(255) DEFAULT NULL,
  `category_name` varchar(50) DEFAULT NULL,
  `saved_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`history_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `history_tbl_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_tbl` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_tbl`
--

LOCK TABLES `history_tbl` WRITE;
/*!40000 ALTER TABLE `history_tbl` DISABLE KEYS */;
INSERT INTO `history_tbl` VALUES (8,3,118,'The last bus from my house left early and I have no transportation.','office','2024-06-26 15:31:09'),(9,1,612,'Yesterday, the date was supposed to be 30th February so the whole day, I was figuring out the calendar.','unbelievable','2024-06-26 15:31:36'),(10,3,516,'I drove to the previous employer yesterday.','funny','2024-06-26 15:33:59'),(12,1,120,'A crime took place in my locality. And police has asked everyone to not leave the premises','office','2024-06-26 20:19:40'),(17,1,408,'My boss just called me saying that I need to get on a flight for an emergency meeting. I can\'t miss this one, but I\'ll send your gift to your P.O box.','party','2024-06-26 20:38:17'),(24,1,301,'I forgot my driving license and got in a heated argument with traffic police.','college','2024-06-26 20:46:47'),(37,5,508,'Clothes were dirty today, so I did laundry work first.','funny','2024-06-27 16:38:03'),(38,5,507,'Her hair dryer was shorted out so she needed to wait till her hair was dry.','funny','2024-06-27 16:43:46'),(39,5,712,'My wifi wasn\'t working, so I went to find a hot-spot i.e Sun','developers','2024-06-27 17:50:05'),(40,5,504,'The alarm betrayed me today.','funny','2024-06-27 17:50:19'),(41,5,513,'I saw a firetruck on my way so I had a doubt and to clarify I went back home to check.','funny','2024-06-27 17:50:35'),(42,5,402,'I have the worst migraine. Yeah, I don\'t think that I\'ll be able to make it to your party tonight.','party','2024-06-27 18:16:40'),(43,5,826,'I forgot which console I was playing on','gaming','2024-06-27 19:07:53'),(44,5,824,'My dog just threw up in my lap','gaming','2024-06-27 19:09:21'),(45,3,715,'It must be a hardware issue, not my code problem.','developers','2024-06-27 19:35:40'),(47,1,512,'The left signal of my car was not working, so I only took right to reach to the office.','funny','2024-06-27 20:20:26'),(48,1,420,'I really want to come but I have a screaming yeast infection and I don\'t want to stand around all night scratching my crotch. I really hope you understand.','party','2024-06-27 20:24:22'),(49,1,503,'I heard there will be a thunderstorm today.','funny','2024-06-27 22:40:38');
/*!40000 ALTER TABLE `history_tbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-27 18:43:52
