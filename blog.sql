-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (arm64)
--
-- Host: 127.0.0.1    Database: blog
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` varchar(36) NOT NULL COMMENT 'id',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created_at',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'updated_at',
  `parent_id` varchar(36) NOT NULL COMMENT 'parent_id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'name',
  `description` varchar(255) NOT NULL COMMENT 'description',
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_pk` (`id`),
  KEY `category_category_id_fk` (`parent_id`),
  CONSTRAINT `category_category_id_fk` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` varchar(36) NOT NULL COMMENT 'id',
  `name` varchar(50) NOT NULL COMMENT 'name',
  `description` varchar(100) NOT NULL COMMENT 'description',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created_at',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='permission';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES ('e5658ca2-3755-11ef-97ac-088fc3df0a43','READ_PRIVILEGE','Allows read access','2024-07-01 10:59:13'),('e565925e-3755-11ef-97ac-088fc3df0a43','WRITE_PRIVILEGE','Allows write access','2024-07-01 10:59:13'),('e5659376-3755-11ef-97ac-088fc3df0a43','DELETE_PRIVILEGE','Allows delete access','2024-07-01 10:59:13'),('e5659424-3755-11ef-97ac-088fc3df0a43','UPDATE_PRIVILEGE','Allows update access','2024-07-01 10:59:13');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'id',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'title',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'content',
  `author_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'author_id',
  `views` int NOT NULL COMMENT 'views',
  `created_at` datetime NOT NULL COMMENT 'created_at',
  `updated_at` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'updated_at',
  `status` int NOT NULL DEFAULT '0' COMMENT '0 : published;2 : archived; 1 : draft;',
  `likes` int NOT NULL COMMENT 'likes',
  `reviews` int NOT NULL COMMENT 'reviews',
  `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'summary',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'avatar',
  `feature` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `post_pk` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='post';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES ('16ec4202-8fe4-4788-9e6b-8b5c65ea7020','12','22','988d4a11-6b43-43c4-94c6-51bef2a97d48',0,'2024-07-07 12:42:31','2024-07-07 12:42:30.841',0,0,0,'32','42',0),('6b4e1825-8e4e-48a5-b35a-d23a66afc103','1','2','988d4a11-6b43-43c4-94c6-51bef2a97d48',0,'2024-07-07 12:40:25','2024-07-07 12:40:25.221',0,0,0,'3','4',0),('ae8901d3-7d0f-4f15-ae39-529e57356834','122','233','988d4a11-6b43-43c4-94c6-51bef2a97d48',0,'2024-07-07 12:55:01','2024-07-07 12:55:00.64',0,0,0,'344','455',0),('c8014cfa-6c3e-4e9a-8833-dab1be6d205d','1231231231','asdqweqwe','988d4a11-6b43-43c4-94c6-51bef2a97d48',0,'2024-07-06 11:21:41','2024-07-06 11:21:41.255',0,0,0,'1qweqwe','123qwewqe',0),('e5e7a569-c1e7-453b-870a-e5879ff065fe','1','2','988d4a11-6b43-43c4-94c6-51bef2a97d48',0,'2024-07-07 12:38:39','2024-07-07 12:38:39.399',0,0,0,'3','4',0),('ef87ae56-e006-4b66-a81c-1cbd51baf4a6','122','233','988d4a11-6b43-43c4-94c6-51bef2a97d48',0,'2024-07-07 12:54:56','2024-07-07 12:54:56.167',0,0,0,'344','455',0);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_category`
--

DROP TABLE IF EXISTS `post_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_category` (
  `post_id` varchar(36) DEFAULT NULL COMMENT 'post_id',
  `category_id` varchar(36) DEFAULT NULL COMMENT 'category_id',
  KEY `post_category_category_id_index` (`category_id`),
  KEY `post_category_post_id_index` (`post_id`),
  CONSTRAINT `post_category_category_id_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_category`
--

LOCK TABLES `post_category` WRITE;
/*!40000 ALTER TABLE `post_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_tag`
--

DROP TABLE IF EXISTS `post_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_tag` (
  `post_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tag_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'tag_id',
  KEY `post_tag_post_id_index` (`post_id`),
  KEY `post_tag_tag_id_index` (`tag_id`),
  CONSTRAINT `post_tag_post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `post_tag_tag_id_fk` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_tag`
--

LOCK TABLES `post_tag` WRITE;
/*!40000 ALTER TABLE `post_tag` DISABLE KEYS */;
INSERT INTO `post_tag` VALUES ('6b4e1825-8e4e-48a5-b35a-d23a66afc103','080091ca-b97e-4f91-a300-cdebf75201e9'),('16ec4202-8fe4-4788-9e6b-8b5c65ea7020','080091ca-b97e-4f91-a300-cdebf75201e9'),('ef87ae56-e006-4b66-a81c-1cbd51baf4a6','080091ca-b97e-4f91-a300-cdebf75201e9'),('ef87ae56-e006-4b66-a81c-1cbd51baf4a6','067ff2f4-cd87-4e45-bae3-fb16c10756b9'),('ae8901d3-7d0f-4f15-ae39-529e57356834','080091ca-b97e-4f91-a300-cdebf75201e9');
/*!40000 ALTER TABLE `post_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` varchar(36) NOT NULL COMMENT 'role id',
  `name` varchar(255) NOT NULL COMMENT 'role name',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created_at',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updated_at',
  `description` varchar(500) NOT NULL COMMENT 'description',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='role';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('f18cf02b-3755-11ef-97ac-088fc3df0a43','ROLE_ADMIN','2024-07-01 02:59:33','2024-07-01 02:59:33','Administrator role with full access and permissions.'),('f18cf790-3755-11ef-97ac-088fc3df0a43','ROLE_USER','2024-07-01 02:59:33','2024-07-01 02:59:33','Standard user role with limited access.'),('f18cf8a2-3755-11ef-97ac-088fc3df0a43','ROLE_MODERATOR','2024-07-01 02:59:33','2024-07-01 02:59:33','Moderator role with specific access.'),('f18cf91c-3755-11ef-97ac-088fc3df0a43','ROLE_GUEST','2024-07-01 02:59:33','2024-07-01 02:59:33','Guest role with minimal access.');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `role_id` varchar(36) NOT NULL,
  `permission_id` varchar(36) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `role_permission_permission_id_fk` (`permission_id`),
  CONSTRAINT `role_permission_permission_id_fk` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `role_permission_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES ('f18cf02b-3755-11ef-97ac-088fc3df0a43','e5658ca2-3755-11ef-97ac-088fc3df0a43'),('f18cf790-3755-11ef-97ac-088fc3df0a43','e5658ca2-3755-11ef-97ac-088fc3df0a43'),('f18cf8a2-3755-11ef-97ac-088fc3df0a43','e5658ca2-3755-11ef-97ac-088fc3df0a43'),('f18cf91c-3755-11ef-97ac-088fc3df0a43','e5658ca2-3755-11ef-97ac-088fc3df0a43'),('f18cf02b-3755-11ef-97ac-088fc3df0a43','e565925e-3755-11ef-97ac-088fc3df0a43'),('f18cf790-3755-11ef-97ac-088fc3df0a43','e565925e-3755-11ef-97ac-088fc3df0a43'),('f18cf02b-3755-11ef-97ac-088fc3df0a43','e5659376-3755-11ef-97ac-088fc3df0a43'),('f18cf02b-3755-11ef-97ac-088fc3df0a43','e5659424-3755-11ef-97ac-088fc3df0a43'),('f18cf8a2-3755-11ef-97ac-088fc3df0a43','e5659424-3755-11ef-97ac-088fc3df0a43');
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL COMMENT 'create_at',
  `updated_at` datetime NOT NULL COMMENT 'updated_at',
  `color` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'color',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'icon',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'name',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'description',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tag_pk` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES ('067ff2f4-cd87-4e45-bae3-fb16c10756b9','2024-07-07 12:54:56','2024-07-07 12:54:56','adf','adsf','ad','ad'),('080091ca-b97e-4f91-a300-cdebf75201e9','2024-07-06 08:55:46','2024-07-06 08:55:46','#FF5733','fa-laptop','Technology1','Tech related content'),('62a527e2-f615-442c-ab03-243c1392ec7f','2024-07-06 08:55:46','2024-07-06 08:55:46','#33FF57','fa-flask','Science1','Scientific discoveries and research'),('860c4764-dd39-4772-9bc5-d61f10f3e4ea','2024-07-06 09:15:46','2024-07-06 09:15:46','#33FF57','fa-flask','Science13','Scientific discoveries and research'),('942a8362-57e9-4f90-b308-503a03afc870','2024-07-06 09:48:52','2024-07-06 09:48:52','#FF5733','fa-laptop','Technology1111112','Tech related content'),('97578587-1ebb-412b-956c-236c226156e6','2024-07-06 09:15:46','2024-07-06 09:15:46','#FF5733','fa-laptop','Technology12','Tech related content'),('a3c26d43-8494-4528-b229-2308c160b020','2024-07-06 08:51:10','2024-07-06 08:51:10','#33FF57','fa-flask','Science','Scientific discoveries and research'),('b28d0985-e571-4f47-8edb-5fe1d9170295','2024-07-06 08:51:10','2024-07-06 08:51:10','#FF5733','fa-laptop','Technology','Tech related content'),('e950fc11-47e9-4d9b-b6a5-2f567b59532f','2024-07-06 09:29:38','2024-07-06 09:29:38','#33FF57','fa-flask','123123123','Scientific discoveries and research');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(36) NOT NULL COMMENT 'user id',
  `username` varchar(50) NOT NULL COMMENT 'username',
  `email` varchar(100) NOT NULL COMMENT 'email',
  `password` varchar(255) NOT NULL COMMENT 'password',
  `phone_number` varchar(20) DEFAULT NULL COMMENT 'phone_number',
  `avatar` varchar(300) NOT NULL COMMENT 'avatar',
  `gender` int NOT NULL COMMENT 'gender',
  `status` int NOT NULL COMMENT '0:Active;1:Inactive;2:Pending;3:Banned;4:Deleted',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created_at',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updated_at',
  `birth_date` datetime DEFAULT NULL COMMENT 'birth_date',
  `motto` varchar(255) NOT NULL DEFAULT 'Work hard, play hard.' COMMENT 'motto',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='user';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1fb9b19c-80fb-4137-8b43-8c6fe577c79b','349950128634','349950128634@qq.com','$2a$10$rh8/Z17O2efeX4yJGjHXneHlm5T15oAeJ9TdCJxjp181rvkWAbdd.',NULL,'https://images.pexels.com/photos/24491299/pexels-photo-24491299.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',2,2,'2024-07-07 12:37:31','2024-07-07 12:37:31',NULL,'Work hard, play hard.'),('3fbe7b22-6f89-433e-bd11-0113465fe6ac','12321','12321@qq.com','$2a$10$y.XmNoAwJuRKvGWGbM5Kn.IK9T9z/xJ/He0uGpUdgBWDyib.36S06',NULL,'https://images.pexels.com/photos/24491299/pexels-photo-24491299.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',2,2,'2024-07-06 11:16:45','2024-07-06 11:16:45',NULL,'Work hard, play hard.'),('988d4a11-6b43-43c4-94c6-51bef2a97d48','3499508634','3499508634@qq.com','$2a$10$gC3PnBrMKej4smO9Kr3HM.35Bmd2BC9kG8FQ.TkDSvZuUROpSY2Ru',NULL,'https://images.pexels.com/photos/24491299/pexels-photo-24491299.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',2,0,'2024-07-01 09:51:02','2024-07-01 09:51:21',NULL,'Work hard, play hard.'),('a2dddae7-ca6a-42fc-8baf-d7dda0a5652e','12320001','12320001@qq.com','$2a$10$aIFLL2XOCOC8heUjxM.g9edyU193lP50xNSgeyHT5dtkMn6N2gsRW',NULL,'https://images.pexels.com/photos/24491299/pexels-photo-24491299.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',2,2,'2024-07-06 11:17:09','2024-07-06 11:17:09',NULL,'Work hard, play hard.');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permission`
--

DROP TABLE IF EXISTS `user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_permission` (
  `user_id` varchar(36) NOT NULL COMMENT 'user_id',
  `permission_id` varchar(36) NOT NULL COMMENT 'permission_id',
  KEY `user_permission_permission_id_fk` (`permission_id`),
  KEY `user_permission_user_id_fk` (`user_id`),
  CONSTRAINT `user_permission_permission_id_fk` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `user_permission_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='user_permission';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permission`
--

LOCK TABLES `user_permission` WRITE;
/*!40000 ALTER TABLE `user_permission` DISABLE KEYS */;
INSERT INTO `user_permission` VALUES ('988d4a11-6b43-43c4-94c6-51bef2a97d48','e5659424-3755-11ef-97ac-088fc3df0a43');
/*!40000 ALTER TABLE `user_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` varchar(36) NOT NULL COMMENT 'user_id',
  `role_id` varchar(36) NOT NULL COMMENT 'role_id',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `user_role_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='user_role';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('1fb9b19c-80fb-4137-8b43-8c6fe577c79b','f18cf91c-3755-11ef-97ac-088fc3df0a43'),('3fbe7b22-6f89-433e-bd11-0113465fe6ac','f18cf91c-3755-11ef-97ac-088fc3df0a43'),('988d4a11-6b43-43c4-94c6-51bef2a97d48','f18cf91c-3755-11ef-97ac-088fc3df0a43'),('a2dddae7-ca6a-42fc-8baf-d7dda0a5652e','f18cf91c-3755-11ef-97ac-088fc3df0a43');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-09  7:32:35
