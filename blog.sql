-- MySQL dump 10.13  Distrib 8.2.0, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: blog
-- ------------------------------------------------------
-- Server version	8.2.0

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
  `id` int NOT NULL AUTO_INCREMENT COMMENT '分类 ID',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '分类名称',
  `parent_id` int NOT NULL DEFAULT '0' COMMENT '父分类 ID',
  PRIMARY KEY (`id`),
  KEY `category_category_id_fk` (`parent_id`),
  CONSTRAINT `category_category_id_fk` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (0,'ROOT',0),(1,'JAVA',1),(2,'Spring',2),(3,'依赖注入',2),(4,'版本控制',4),(5,'Git',4),(6,'Android',6),(7,'OTA',6);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '评论 ID',
  `date` datetime NOT NULL COMMENT '评论日期',
  `likes` int NOT NULL DEFAULT '0' COMMENT '评论点赞数',
  `user_id` int NOT NULL COMMENT '用户 ID',
  `post_id` int NOT NULL COMMENT '文章 ID',
  `content` longtext NOT NULL COMMENT '评论内容',
  `parent_id` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '父评论 ID',
  `title` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '标题',
  `rating` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `comment_pk` (`id`),
  UNIQUE KEY `comment_pk_2` (`id`),
  KEY `comment_comment_id_fk` (`parent_id`),
  KEY `comment_post_id_fk` (`post_id`),
  KEY `comment_user_id_fk` (`user_id`),
  KEY `comment_id_index` (`id`),
  CONSTRAINT `comment_comment_id_fk` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `comment_post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `post1` (`id`),
  CONSTRAINT `comment_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES ('3ee6d579-54a8-4078-93cf-d45467608403','2024-06-26 00:05:31',0,0,161,'a','3ee6d579-54a8-4078-93cf-d45467608403','a',1),('407befce-a647-406c-ba94-3a8fde27ee08','2024-06-25 22:50:01',0,0,156,'312321321','df594d94-6694-4299-a4a3-fd29aa5f34d4','21312',1),('4b6cf6ff-c833-4fcd-8d17-cd8ab85b9900','2024-06-26 00:12:21',0,0,161,'a','4b6cf6ff-c833-4fcd-8d17-cd8ab85b9900','a',1),('61f2e22f-47da-472e-8408-6f57913f8e8d','2024-06-26 00:12:59',0,0,161,'123123123123123','61f2e22f-47da-472e-8408-6f57913f8e8d','123123123',5),('641dc8c2-e56a-45e2-9300-934b4fd17cc7','2024-06-26 00:12:42',0,0,161,'123123','641dc8c2-e56a-45e2-9300-934b4fd17cc7','123',5),('6660760b-7691-4496-bd66-089868659c6e','2024-06-26 00:12:51',0,0,161,'123123123','6660760b-7691-4496-bd66-089868659c6e','123123',5),('8099d1ea-3a2b-40e9-9778-4031ddf5d1c1','2024-06-26 22:52:40',0,0,235,'213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123','8099d1ea-3a2b-40e9-9778-4031ddf5d1c1','23123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123213123',5),('8626b8bd-f3de-4ab6-a3f2-43fb3e55a497','2024-06-26 22:26:47',0,0,190,'This is the first comment\nThis is the first titleThis is the first titleThis is the first titleThis is the first titleThis is the first titleThis is the first titleThis is the first titleThis is the first titleThis is the first titleThis is the first titleThis is the first titleThis is the first titleThis is the first title','8626b8bd-f3de-4ab6-a3f2-43fb3e55a497','This is the first titleThis is the first titleThis is the first titleThis is the first title',5),('918c232d-2035-4f41-9c03-f178d13d72e3','2024-06-26 22:34:17',0,0,235,'123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123','918c232d-2035-4f41-9c03-f178d13d72e3','123123123123123123123123123123123123123123123123123123123123',5),('a2ba77e6-bf52-4875-a5f5-a5cedcdd912c','2024-06-26 00:12:14',0,0,161,'a','a2ba77e6-bf52-4875-a5f5-a5cedcdd912c','a',1),('aba8a0f8-0d25-4fbc-935e-108f86d7e831','2024-06-26 22:31:21',0,0,226,'123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123','aba8a0f8-0d25-4fbc-935e-108f86d7e831','123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123',5),('b1da3d79-95a4-4787-9abc-a84f30ba7f06','2024-06-25 22:49:39',0,0,156,'123z123213123123123\n','b1da3d79-95a4-4787-9abc-a84f30ba7f06','z123123123123123213123123',3),('d70e4d3e-4e33-4e90-94e3-e5b9c6bb5cde','2024-06-26 22:26:25',0,0,190,'This is the first comment\n','d70e4d3e-4e33-4e90-94e3-e5b9c6bb5cde','This is the first title',5),('df594d94-6694-4299-a4a3-fd29aa5f34d4','2024-06-25 22:43:08',0,0,156,'z123213\n','df594d94-6694-4299-a4a3-fd29aa5f34d4','z123123',2),('e1859de3-64ab-49bd-800c-eae962af27cc','2024-06-25 22:47:56',0,0,156,'123z123213123123\n','df594d94-6694-4299-a4a3-fd29aa5f34d4','z123123123123123213',2),('e5150bc1-14c1-4628-b488-3762e59f9299','2024-06-26 23:36:37',0,0,235,'12312','e5150bc1-14c1-4628-b488-3762e59f9299','我是李伟我是李伟我是李伟我是李伟我是李伟我是李伟我是李伟我是李伟我是李伟我是李伟我是李伟我是李伟我是李伟我是李伟',5);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before_insert_comment` BEFORE INSERT ON `comment` FOR EACH ROW BEGIN
    IF NEW.parent_id IS NULL THEN
        SET NEW.parent_id = NEW.id;
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `faq`
--

DROP TABLE IF EXISTS `faq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` longtext NOT NULL,
  `answer` longtext,
  `create_time` datetime DEFAULT (now()),
  PRIMARY KEY (`id`),
  UNIQUE KEY `faq_pk2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq`
--

LOCK TABLES `faq` WRITE;
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
INSERT INTO `faq` VALUES (1,'Why should I start a blog?','不知道处于什么原因，我注销了陪伴自己多年的 qq 空间，同时清除了所有的动态（现在想想还是挺后悔的）。至此，我踏上了微信朋友圈之旅。不得不承认，起初朋友圈确实是一个好的地方，它在一定程度上成为了人们“talk”的平台。随着商业的侵蚀，朋友圈俨然称为各种广告的天下了。不过这并不是放弃朋友圈的真正原因，不知道从何时起发现，朋友圈已经无法成为聆听和欣赏的平台了，就像大制作的视频被短视频取代一样，这里充斥着没有营养的内容。所谓的营养并不是说我们的文字多么高深、我们的视频多么精细，而是它们应该是被细心雕琢的、有感而发的。因此，我产生了建立一个个人博客的想法，我想重新回到长文章、复杂文章的时代，哪怕没有一个人原因驻留10s.','2024-05-03 22:09:28'),(2,'How can I apply to the Open Source Discount?','The Open Source Discount is available for everyone who is building an open source project. You can apply to the discount by sending an email to support@acme.com','2024-05-03 22:09:58'),(3,'Can I use Acme for my freelance projects?','Yes, you can use Acme for your freelance projects. You can purchase the Freelancer License from our website.','2024-04-30 12:45:03'),(4,'What is your refund policy?','We do not provide refunds. However, we can help you with any issues you may have.','2024-05-03 22:10:41'),(5,'Can I cancel my subscription?','Yes, you can cancel and renew your subscription at any time.','2024-05-03 22:11:28'),(6,'你好呀','我很好','2024-05-03 22:13:06');
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_link`
--

DROP TABLE IF EXISTS `friend_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend_link` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '网站标题',
  `description` longtext NOT NULL COMMENT '网站描述',
  `link` varchar(200) NOT NULL COMMENT '网站链接',
  `avatar` varchar(200) NOT NULL COMMENT '网站图片',
  `status` int NOT NULL COMMENT '站点状态--- 0:zh',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `level` int NOT NULL DEFAULT '0' COMMENT '友联等级',
  PRIMARY KEY (`id`),
  UNIQUE KEY `"friend_link_pk2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='友链';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_link`
--

LOCK TABLES `friend_link` WRITE;
/*!40000 ALTER TABLE `friend_link` DISABLE KEYS */;
INSERT INTO `friend_link` VALUES (2,'1','11','1','1',1,'2024-05-28 23:15:10',0),(3,'2','2','2','2',2,'2024-05-28 23:15:18',1),(4,'3','3','3','3',3,'2024-05-28 23:15:26',2);
/*!40000 ALTER TABLE `friend_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '权限 ID',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '权限名称',
  `description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'addPost','添加文章');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` varchar(50) DEFAULT NULL COMMENT 'id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='post';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post1`
--

DROP TABLE IF EXISTS `post1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post1` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '文章 ID',
  `title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '文章标题',
  `content` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '文章内容',
  `summary` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '摘要',
  `is_top` int NOT NULL DEFAULT '0' COMMENT '是否置顶',
  `user_id` int NOT NULL COMMENT '用户 ID',
  `likes` int DEFAULT '0' COMMENT '点赞数',
  `views` int DEFAULT '0' COMMENT '浏览数',
  `comments` int DEFAULT '0' COMMENT '评论数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `cover` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '博客封面',
  `rating` float NOT NULL COMMENT '评分',
  `is_private` int NOT NULL DEFAULT '0' COMMENT '是否私密',
  `status` int DEFAULT '0' COMMENT '文章状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=236 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post1`
--

LOCK TABLES `post1` WRITE;
/*!40000 ALTER TABLE `post1` DISABLE KEYS */;
/*!40000 ALTER TABLE `post1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_category`
--

DROP TABLE IF EXISTS `post_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `post_id` int NOT NULL COMMENT '文章 ID',
  `category_id` int NOT NULL COMMENT '类别 ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `post_category_pk_2` (`id`),
  KEY `post_category_category_id_fk` (`category_id`),
  KEY `post_category_post_id_fk` (`post_id`),
  CONSTRAINT `post_category_category_id_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `post_category_post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `post1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_category`
--

LOCK TABLES `post_category` WRITE;
/*!40000 ALTER TABLE `post_category` DISABLE KEYS */;
INSERT INTO `post_category` VALUES (1,158,1),(2,163,1),(3,162,1),(4,158,2),(5,159,2),(6,160,2),(7,162,2),(8,158,7),(9,159,7),(10,162,7),(90,159,1),(91,161,1);
/*!40000 ALTER TABLE `post_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_column`
--

DROP TABLE IF EXISTS `post_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_column` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `post_id` int NOT NULL COMMENT '文章 ID',
  `column_id` int NOT NULL COMMENT '专栏 ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `post_column_pk2` (`id`),
  KEY `post_column_column_id_fk` (`column_id`),
  KEY `post_column_post_id_fk` (`post_id`),
  CONSTRAINT `post_column_column_id_fk` FOREIGN KEY (`column_id`) REFERENCES `section` (`id`),
  CONSTRAINT `post_column_post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `post1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章_专栏表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_column`
--

LOCK TABLES `post_column` WRITE;
/*!40000 ALTER TABLE `post_column` DISABLE KEYS */;
INSERT INTO `post_column` VALUES (78,159,1);
/*!40000 ALTER TABLE `post_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_tag`
--

DROP TABLE IF EXISTS `post_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_tag` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `post_id` int NOT NULL COMMENT '文章 ID',
  `tag_id` int NOT NULL COMMENT '标签 ID',
  PRIMARY KEY (`id`),
  KEY `post_tag_post_id_fk` (`post_id`),
  KEY `post_tag_tag_id_fk` (`tag_id`),
  CONSTRAINT `post_tag_post_id_fk` FOREIGN KEY (`post_id`) REFERENCES `post1` (`id`),
  CONSTRAINT `post_tag_tag_id_fk` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章标签表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_tag`
--

LOCK TABLES `post_tag` WRITE;
/*!40000 ALTER TABLE `post_tag` DISABLE KEYS */;
INSERT INTO `post_tag` VALUES (168,159,7);
/*!40000 ALTER TABLE `post_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'zhu''j',
  `name` varchar(100) NOT NULL COMMENT '物品名称',
  `href` varchar(200) NOT NULL,
  `price` varchar(10) NOT NULL COMMENT '物品价格',
  `rating` int NOT NULL COMMENT '评分',
  `description` longtext NOT NULL COMMENT '描述',
  `information` longtext NOT NULL COMMENT '详细信息',
  `image_src` longtext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_pk_2` (`name`),
  UNIQUE KEY `product_pk_3` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='好物';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'role id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'role name',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created_at',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updated_at',
  `description` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'description',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_pk_2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('b0916501-1e3b-4d34-aa20-168d05160a52','ROLE_GUEST','2024-06-28 03:30:21','2024-06-28 03:38:49','a');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role1`
--

DROP TABLE IF EXISTS `role1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role1` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色 ID',
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role1`
--

LOCK TABLES `role1` WRITE;
/*!40000 ALTER TABLE `role1` DISABLE KEYS */;
INSERT INTO `role1` VALUES (0,'ROLE_ADMIN'),(1,'ROLE_GUEST');
/*!40000 ALTER TABLE `role1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `id` int DEFAULT NULL COMMENT 'zhu''j',
  `role_id` int NOT NULL COMMENT '角色 ID',
  `permission_id` int NOT NULL COMMENT '权限 ID',
  KEY `role_permission_permission_id_fk` (`permission_id`),
  KEY `role_permission_role_id_fk` (`role_id`),
  CONSTRAINT `role_permission_permission_id_fk` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `role_permission_role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `role1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `section` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` char(50) NOT NULL COMMENT '专栏名称',
  `description` longtext COMMENT '专栏描述',
  `avatar` varchar(100) DEFAULT NULL COMMENT '封面',
  `rating` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `column_pk2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='专栏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (1,'OTA','OTA','https://images.pexels.com/photos/3769138/pexels-photo-3769138.jpeg?auto=compress&cs=tinysrgb&w=1260&',5),(2,'React','adf','https://pbs.twimg.com/profile_images/1785867863191932928/EpOqfO6d_400x400.png',4),(3,'123123','ad','12312',5),(4,'123123','ad','12312',NULL),(5,'123','123123','',5);
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `log_type` varchar(50) NOT NULL COMMENT '日志类型',
  `create_user_code` varchar(64) NOT NULL COMMENT '创建用户编码',
  `create_user_name` varchar(100) NOT NULL COMMENT '创建用户名称',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `request_uri` varchar(500) DEFAULT NULL COMMENT '请求URI',
  `request_method` varchar(10) DEFAULT NULL COMMENT '请求方式',
  `request_params` text COMMENT '请求参数',
  `request_ip` varchar(20) NOT NULL COMMENT '请求IP',
  `server_address` varchar(50) NOT NULL COMMENT '请求服务器地址',
  `is_exception` char(1) DEFAULT NULL COMMENT '是否异常',
  `exception_info` text COMMENT '异常信息',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '结束时间',
  `execute_time` int DEFAULT NULL COMMENT '执行时间',
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户代理',
  `device_name` varchar(100) DEFAULT NULL COMMENT '操作系统',
  `browser_name` varchar(100) DEFAULT NULL COMMENT '浏览器名称',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sys_log_lt` (`log_type`) USING BTREE,
  KEY `idx_sys_log_cub` (`create_user_code`) USING BTREE,
  KEY `idx_sys_log_ie` (`is_exception`) USING BTREE,
  KEY `idx_sys_log_cd` (`create_date`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '标签 ID',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tag_pk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (6,'Mysql'),(27,'NextJs'),(5,'RabbitMQ'),(3,'React'),(7,'Recovery'),(4,'redis'),(9,'SpringBoot'),(28,'update_engine'),(8,'virtual A/B');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theme`
--

DROP TABLE IF EXISTS `theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theme` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `theme_name` varchar(20) DEFAULT NULL COMMENT '主题名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='主题表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theme`
--

LOCK TABLES `theme` WRITE;
/*!40000 ALTER TABLE `theme` DISABLE KEYS */;
INSERT INTO `theme` VALUES (1,'light'),(2,'dark'),(3,'purple-dark');
/*!40000 ALTER TABLE `theme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'user id',
  `username` varchar(50) NOT NULL COMMENT 'username',
  `email` varchar(100) NOT NULL COMMENT 'email',
  `password` varchar(255) NOT NULL COMMENT 'password',
  `phone_number` varchar(20) DEFAULT NULL COMMENT 'phone_number',
  `avatar` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'avatar',
  `gender` int NOT NULL COMMENT 'gender',
  `status` int NOT NULL COMMENT '0:Active;1:Inactive;2:Pending;3:Banned;4:Deleted',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'created_at',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'updated_at',
  `birth_date` datetime DEFAULT NULL COMMENT 'birth_date',
  `motto` varchar(255) NOT NULL DEFAULT 'Work hard, play hard.' COMMENT 'motto',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_pk_2` (`id`),
  UNIQUE KEY `user_pk` (`email`),
  UNIQUE KEY `user_pk2` (`email`),
  UNIQUE KEY `user_pk3` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('0e5aa1ed-4322-4fb5-85ac-e1c22f6811d6','3141282040','3141282040@qq.com','$2a$10$itqMniUh0SIXQePPULSjo.MjWZJ/TB/fRjutc3JpWWlwVQEzueTzy',NULL,'https://images.pexels.com/photos/24491299/pexels-photo-24491299.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',2,2,'2024-06-28 11:38:51','2024-06-28 11:38:51',NULL,'Work hard, play hard.'),('24dfc889-a9bd-47aa-96f2-c28fc6fcf192','A better tomorrow','3499508634@qq.com','$2a$10$OXwesNvsxfsX7OAAh3zNDeU0rG3zOV07SKfI5OY25Xf2PFSoUiod6',NULL,'https://images.pexels.com/photos/24491299/pexels-photo-24491299.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',0,0,'2024-06-28 10:57:32','2024-06-28 12:26:07',NULL,'Work hard, play hard.'),('32dc4480-1a80-4ec8-b279-318323a75e46','john.doe','john.doe@example.com','$2a$10$An9rHFi74y9oo8FMAe8F7eiuHawA.UgWmNIlRO7vHNuYPm7kt8Hwy',NULL,'https://images.pexels.com/photos/24491299/pexels-photo-24491299.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',2,2,'2024-06-28 12:27:02','2024-06-28 12:27:02',NULL,'Work hard, play hard.'),('40db71ae-54f4-4cd3-bc6a-46f5feef0a83','11111','11111@qq.com','$2a$10$tgFJmnu3nP0ISog5S44TIOyB8Jaw70ab9pjnu73uFr8xRvHy5G2LW',NULL,'https://images.pexels.com/photos/24491299/pexels-photo-24491299.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',0,0,'2024-06-28 11:52:37','2024-06-28 12:24:57',NULL,'Work hard, play hard.'),('633c2094-8fdb-4edc-9591-e6e200e427a4','john1.doe','john1.doe@example.com','$2a$10$sPWyi7F2zi4dcm.nJx5g/OqHf3PvNY0znryj7NxroBrWOkjdxfeX.',NULL,'https://images.pexels.com/photos/24491299/pexels-photo-24491299.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',2,2,'2024-06-28 12:28:05','2024-06-28 12:28:05',NULL,'Work hard, play hard.'),('adff8958-cc05-4f49-ab0e-4ffff3b596b8','314128204011111111','314128204011111111@qq.com','$2a$10$ZtK7/x1QpuxnM6xRxl3Csebo3IlNQfykrPmWXN/YSr0OybFIQhY4W',NULL,'https://images.pexels.com/photos/24491299/pexels-photo-24491299.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',2,2,'2024-06-28 17:22:21','2024-06-28 17:22:21',NULL,'Work hard, play hard.'),('c162fc3d-5515-4075-9ad7-7cf927b33999','314','314@qq.com','$2a$10$Ix4hkkBq9M/XOd1tV9Whmu86OA8kwNy6BrTda2XnRy/lH2uIyWtt2',NULL,'https://images.pexels.com/photos/24491299/pexels-photo-24491299.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',2,2,'2024-06-28 11:37:21','2024-06-28 11:37:21',NULL,'Work hard, play hard.'),('fe20bc6f-6008-4697-adfa-a5f14f0e5fde','3141282040111111','3141282040111111@qq.com','$2a$10$/mUkZFCtgJfdiqO020ahXuxYDWR0w1HRdE1KSvbDtm79EmehPCRoK',NULL,'https://images.pexels.com/photos/24491299/pexels-photo-24491299.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',2,2,'2024-06-28 11:42:03','2024-06-28 11:42:03',NULL,'Work hard, play hard.');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user1`
--

DROP TABLE IF EXISTS `user1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user1` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户 ID',
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '邮箱',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户密码',
  `motto` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '座右铭',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `avatar` longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '头像',
  `age` int DEFAULT NULL COMMENT '年龄',
  `status` int NOT NULL DEFAULT '0' COMMENT '账号状态',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_pk2` (`username`),
  UNIQUE KEY `user_pk` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user1`
--

LOCK TABLES `user1` WRITE;
/*!40000 ALTER TABLE `user1` DISABLE KEYS */;
INSERT INTO `user1` VALUES (0,'A better tomorrow','3499508634@qq.com','$2a$10$UAUNRELrTkQrHBRa9KscJ.21RHQlst7uXYIz4OJsHv4vBHnUJZzKG','时间所有的痛苦都来自于对外部世界的欲望','2024-03-15 13:58:17','https://images.pexels.com/photos/17037983/pexels-photo-17037983/free-photo-of-sunbeds-on-beach.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',24,0,'sdfasfdasdfsdfasfdasdfsdfasfdasdf'),(1,'A better tomor','2278098503@qq.com','$2a$10$UAUNRELrTkQrHBRa9KscJ.21RHQlst7uXYIz4OJsHv4vBHnUJZzKG','时间所有的痛苦都来自于对外部世界的欲望','2024-04-16 20:48:53','https://images.pexels.com/photos/17037983/pexels-photo-17037983/free-photo-of-sunbeds-on-beach.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',23,1,'1'),(23,'asdasd','2278098503m@qq.com','$2a$10$34Ak1HZwEBJ2uE7/wMooLO662JUu/SXl41e6yAJ1d.87jEQqf8azS',NULL,'2024-06-08 09:37:06',NULL,NULL,0,NULL),(24,'07faa4fe-d2eb-445f-8bf2-55562bf8bcd2','22780985031m@qq.com','$2a$10$JcZe1KP0t5fGSgcTNcRYIulPwusY6Fms89bS4COBhS6aIbp37M7wu',NULL,'2024-06-08 09:40:02',NULL,NULL,0,NULL),(25,'eaac353a-e2ce-4f73-9906-4dc930ec4812','3141282040@qq.com','$2a$10$mQhNCgnigToOURMR3E/7s.2GRs62SWlrjPjlXGRGcOyC0JWiw5/oq',NULL,'2024-06-08 09:50:13',NULL,NULL,0,NULL),(26,'ca2d8c35-ff88-4e74-b26c-c659a00ee17f','349950861134@qq.com','$2a$10$zc6U1QTDhzr03awk3X2nXul1hUkj.bAGtjuoHpyXnP5ZX5yHaNDCi',NULL,'2024-06-08 10:11:49',NULL,NULL,0,NULL),(27,'899a0a29-6f7f-4a5a-a038-497a3c0e8e6b','123123123@qq.com','$2a$10$aVezs47A81u7lce4rCxN3uuVFxdeVZtTjg6EROq4qcxuar3nB6dAO',NULL,'2024-06-08 10:13:13',NULL,NULL,0,NULL),(28,'65062e0b-037c-4127-8a5a-e43862397523','123@qq.com','$2a$10$RMQAqTDd9ji4ToQnWajTueCeIH0eWMQgVp7ywouFWNLxLqLQcCxpu',NULL,'2024-06-08 15:14:33',NULL,NULL,0,NULL),(29,'12a40d9e-e16e-4929-abd8-0c4113217a8b','18710397393@qq.com','$2a$10$Ree//4.vFL3cCDA5OAU5/eAoQac9DLQFaCv.wj7/JRca8bLWy3Knq',NULL,'2024-06-09 18:45:54',NULL,NULL,0,NULL),(30,'755918e6-ffab-4cf1-b235-cc76814657f4','mmmm@qq.com','$2a$10$8S7hskBDghUZXhgKsjxXj.18bEpO/CACosQGdGIck98w7DDrXPn02',NULL,'2024-06-23 12:58:59',NULL,NULL,0,NULL);
/*!40000 ALTER TABLE `user1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'user_id',
  `role_id` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'role_id',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='user_role';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('32dc4480-1a80-4ec8-b279-318323a75e46','b0916501-1e3b-4d34-aa20-168d05160a52'),('40db71ae-54f4-4cd3-bc6a-46f5feef0a83','b0916501-1e3b-4d34-aa20-168d05160a52'),('633c2094-8fdb-4edc-9591-e6e200e427a4','b0916501-1e3b-4d34-aa20-168d05160a52'),('adff8958-cc05-4f49-ab0e-4ffff3b596b8','b0916501-1e3b-4d34-aa20-168d05160a52');
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

-- Dump completed on 2024-06-28 18:07:32
