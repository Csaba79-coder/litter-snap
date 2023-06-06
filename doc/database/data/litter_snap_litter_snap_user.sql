-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: litter_snap
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `litter_snap_user`
--

DROP TABLE IF EXISTS `litter_snap_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `litter_snap_user` (
  `id` binary(16) NOT NULL,
  `created_at` datetime NOT NULL,
  `created_by` binary(16) DEFAULT NULL,
  `updated_at` datetime NOT NULL,
  `updated_by` binary(16) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nx81u918yqdb14h441ins1jgi` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `litter_snap_user`
--

LOCK TABLES `litter_snap_user` WRITE;
/*!40000 ALTER TABLE `litter_snap_user` DISABLE KEYS */;
INSERT INTO `litter_snap_user` VALUES (_binary '\rú\n*UF®∞»ß\Ï\È˝\Êw','2023-06-01 19:14:05',_binary 'gr\…‹ßæH&ñ:\„vO\‘\Á','2023-06-01 19:14:05',_binary '\€’Äû\ÁG’èáõºëX0	','kevinzefi1999@gmail.com','Kevin','$2a$10$/cIu8c0qAr9MLB6OXo3r.umaOh392bDqHudwxfAjMnUiI1u6MJYG2','ROLE_USER'),(_binary '>§4∫~N)î˚ÙAŸΩlW','2023-05-29 20:59:15',_binary 'gr\…‹ßæH&ñ:\„vO\‘\Á','2023-05-29 20:59:15',_binary '\€’Äû\ÁG’èáõºëX0	','csabavadasz79@gmail.com','Csaba','$2a$10$IHGHLrIwDrapqOl9sAFXVeQZJNCw11P.QhzDoACnNVZInaM9EV4a2','ROLE_USER'),(_binary 'IﬁâØÆ˝H∫ä/û\"9G\Èû','2023-06-04 17:27:58',_binary 'gr\…‹ßæH&ñ:\„vO\‘\Á','2023-06-04 17:27:58',_binary '\€’Äû\ÁG’èáõºëX0	','test@testemail.com','Test','$2a$10$RVyObuY7eTQXYkoQV4he3O6GwxUjbAI3o3Ul0tNTLFbDvHIU8GVcK','ROLE_USER'),(_binary 'jƒ∫6áBN_£∂ˆçTlÜ','2023-06-01 19:13:00',_binary 'gr\…‹ßæH&ñ:\„vO\‘\Á','2023-06-01 19:13:00',_binary '\€’Äû\ÁG’èáõºëX0	','verejanvasile@gmail.com','Vasile','$2a$10$nN6jg4sEjhTZvhuZunSyt.n8DLaCQuOECbQf/bKCXwJJ/8vIfYqJu','ROLE_USER'),(_binary '°\\!\Í\ÌçA–ÄÇ+_›úÆ\Õ','2023-06-02 12:07:42',_binary 'gr\…‹ßæH&ñ:\„vO\‘\Á','2023-06-02 12:07:42',_binary '\€’Äû\ÁG’èáõºëX0	','eugene@gmail.com','Eugene','$2a$10$H/5j0TlgWVGB7fbxCXodo.3/dzK41Eo/NPPpb.8VikNOaOWU0QaLO','ROLE_USER'),(_binary '\ÿ@Ø:YEA±æ\‡sqdà,G','2023-06-04 18:33:41',_binary 'gr\…‹ßæH&ñ:\„vO\‘\Á','2023-06-04 18:33:41',_binary '\€’Äû\ÁG’èáõºëX0	','regtest@gmail.com','RegistrationForm','$2a$10$M90LdTFXODRC3dsEITH2Q.Yle1ChFVwQzefzTHZjvRQRgfKA6PYIy','ROLE_USER'),(_binary 'û1ÉGúL^®†fmQÆRâ','2023-06-04 18:42:59',_binary 'gr\…‹ßæH&ñ:\„vO\‘\Á','2023-06-04 18:42:59',_binary '\€’Äû\ÁG’èáõºëX0	','redirectlogin@gmail.com','RedirectToLoginAfterReg','$2a$10$Up7Dzc5idkyftAVE/2N0Lef3f.dBfiws2mpO/HuGkTb8kuyg8vARy','ROLE_USER'),(_binary 'ˇs¡{A\ÃKóôs¸\"‘Äı','2023-06-05 20:28:28',_binary 'gr\…‹ßæH&ñ:\„vO\‘\Á','2023-06-05 20:28:28',_binary '\€’Äû\ÁG’èáõºëX0	','showvasile@test.com','TestForVasile','$2a$10$NugFZcqRsKItoiRMWFA37ud/e6ud6v31nDYt2Z/ydSgv5Zbwk6C1m','ROLE_USER');
/*!40000 ALTER TABLE `litter_snap_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-05 23:05:04
