-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: liu
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `mid` int NOT NULL COMMENT '主键',
  `fid` int NOT NULL COMMENT '发送方id',
  `fname` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '发送方名字',
  `sid` int NOT NULL COMMENT '接收方id',
  `sname` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '接收方名字',
  `text` varchar(355) COLLATE utf8_bin DEFAULT NULL COMMENT '内容',
  `time` timestamp(6) NOT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='消息\ntime: ''2021-04-02 11:23:16'',\n        fid: 88888888,\n        fname:''无连接'',\n        sid:10000000,\n        sname:''无连接'',\n        text:''无内容''';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,2,'kk',1,'admin','hello world!','2021-05-05 15:16:14.000000'),(569931137,35738692,'川普',1,'admin','Hello World!Admin...','2021-05-06 06:59:40.652000'),(594282557,1,'admin',28539169,'哆啦A梦','下午来一起玩啊','2021-05-06 08:06:23.328000'),(1517583278,1,'admin',35738692,'川普','China!','2021-05-06 05:57:18.059000'),(1846900771,1,'admin',2,'kk','你好！ \nkk','2021-05-05 15:28:20.325000');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `text`
--

DROP TABLE IF EXISTS `text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `text` (
  `tid` int NOT NULL COMMENT '博文编号',
  `author` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '作者-user的name',
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `path` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '路径索引',
  `tag` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '标签',
  `picture` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '封面图片路径（可以为空）',
  `time` timestamp(6) NOT NULL,
  `size` varchar(255) COLLATE utf8_bin NOT NULL,
  `up` int DEFAULT NULL COMMENT '点赞数',
  `collection` int DEFAULT NULL COMMENT '收藏数',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='博客';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `text`
--

LOCK TABLES `text` WRITE;
/*!40000 ALTER TABLE `text` DISABLE KEYS */;
INSERT INTO `text` VALUES (1,'kk','第一篇博客','第一篇博客.html','随笔，杂谈','a.jpg','2021-04-26 04:32:22.000000','0.01MB',2,2),(2,'cc','b','b.html','杂谈','b.jpg','2021-04-26 04:32:20.000000','0.01MB',3,1),(3,'静香','c','c.html','原创','c.jpg','2021-04-26 04:32:18.000000','0.01MB',1,1),(4,'admin','d','d.html','杂谈','d.jpg','2021-04-26 04:32:23.000000','0.01MB',1,0),(5,'胖虎','e','e.html','转载','e.jpg','2021-04-26 04:32:24.000000','0.01MB',0,0),(6,'kk','f','f.html','原创','f.jpg','2021-04-26 04:32:25.000000','0.01MB',2,2),(7,'admin','后端接口','后端接口.html','原创，技术','h.jpg','2021-04-26 04:32:25.000000','0.01MB',0,0),(16226350,'王智玮','老王日记','老王日记.html','原创,富文本','8ffcef07c9e74ffab4b9ab4b855dd403.jpg','2021-04-26 04:32:26.000000','0.01MB',0,0),(16909949,'kk','网站日志','网站日志.html','计划,原创,机密','0ac903234be0497d8299c6f16a05b81a.png','2021-04-26 04:32:29.000000','0.01MB',1,0),(28935404,'kk','局域网连接测试','局域网连接测试.html','局域网,原创','8019799ea0144a4aaebbd125e710cfec.jpg','2021-04-26 04:32:28.000000','0.01MB',1,0),(30289270,'kk','每日美图分享','每日美图分享.html','转载,黑皮,美人','43966e2f82b34e14b0eabac5e01712ff.png','2021-04-26 18:52:31.439000','0.01MB',0,0),(36248045,'admin','第二篇新博文','第二篇新博文.html','原创,技术,vue,springboot,FileIO','a02f5913d4e34ed5ba51c86c8e1d858d.jpg','2021-04-26 18:52:35.653000','0.01MB',0,1),(54791295,'admin','第一篇上传的博客','第一篇上传的博客.html','原创，技术，vue,springboot','a699b2980e2e41cfb4e7e86547ecc8a1.jpg','2021-04-26 18:52:36.609000','0.01MB',1,0),(63587316,'admin','第三次写新博文','第三次写新博文.html','springboot,vue,原创,FIleIO','5b61f8a9ea4f4105ad3bbc86c75d7f64.jpg','2021-04-26 18:52:37.537000','0.01MB',1,1),(83208395,'admin','震惊，梵蒂冈被袋鼠入侵了','震惊，梵蒂冈被袋鼠入侵了.html','夸张,虚假','5f3eb1015206430594388c2b7c0f10ab.jpg','2021-04-26 18:52:38.319000','0.01MB',0,0),(89939132,'胖虎','胖虎的一天','胖虎的一天.html','原创,随笔,杂文','eb5dfea216744b239db61a0dcd0d5e8d.jpg','2021-04-26 18:52:39.075000','0.01MB',0,0),(90672776,'admin','RDEAME','RDEAME.html','原创','5fdecb6781b44ef3948f759bf935cfce.png','2021-04-26 18:52:39.772000','0.01MB',3,3),(95414434,'kk','偷b站的图','偷b站的图.html','转载','13756998351c4239a8134d30e359a07d.jpg','2021-04-26 18:52:40.423000','0.01MB',0,0);
/*!40000 ALTER TABLE `text` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL COMMENT '用户id，主键',
  `name` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `sex` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '性别（男，女）',
  `jurisdiction` int NOT NULL COMMENT '权限（0：用户，1：管理员）',
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pic` varchar(255) COLLATE utf8_bin NOT NULL,
  `btime` timestamp(6) NOT NULL,
  `phone` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','123456','男',1,'10097','b.jpg','2021-04-20 16:00:00.000000','01566548'),(2,'kk','123456','女',0,'1009748481@qq.com','a.jpg','2021-04-21 15:46:55.000000','0516511'),(3,'cc','654321','女',0,'1','0ac903234be0497d8299c6f16a05b81a.png','2021-04-20 16:00:00.000000','565151'),(10086,'dd','123456','男',0,'1','5f3eb1015206430594388c2b7c0f10ab.jpg','2021-04-20 16:00:00.000000','5464811'),(1911935,'胖虎','654321','男',0,'1','a699b2980e2e41cfb4e7e86547ecc8a1.jpg','2021-04-20 16:00:00.000000','456445654'),(28539169,'哆啦A梦','abc','男',0,'1','13756998351c4239a8134d30e359a07d.jpg','2021-04-20 16:00:00.000000','45645612'),(35738692,'川普','123456','男',0,'wpoiufiew@google.com','a02f5913d4e34ed5ba51c86c8e1d858d.jpg','2021-04-20 16:00:00.000000','4564521'),(45519187,'王智玮','123456','女',0,'1','h.jpg','2021-04-20 16:00:00.000000','4564561'),(71554342,'蜡笔小新','123456','男',0,'8964984@qq.com','201950bdaefa4028bf1947f954f4c846.png','2021-04-20 16:00:00.000000','4564512'),(77743360,'NewAdmin','123456','男',0,'23048530@Google.com','6bc31885e21b44acbfa7640cad5a31b4.png','2021-04-20 16:00:00.000000','45645656'),(93420630,'静香','123456','女',0,'1','e.jpg','2021-04-20 16:00:00.000000','4564568'),(482258487,'我是大哥大','47456345','男',0,'error@zone.null.com','3619f50056b14435b0383e403d09381d.jpg','2021-05-06 08:12:50.670000','10101010101010');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ut`
--

DROP TABLE IF EXISTS `ut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ut` (
  `uid` int NOT NULL COMMENT '用户id',
  `tid` int NOT NULL COMMENT '博文tid',
  `name` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `path` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '博文名',
  `author` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '作者',
  `up` int NOT NULL COMMENT '点赞',
  `collection` int NOT NULL COMMENT '收藏',
  PRIMARY KEY (`uid`,`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='这个表是有关与用户与博文的点赞，收藏相关，\\n用户id，博文tid，用户名，博文名，博文作者，点赞（0/1），收藏（0/1），0是没有点赞，1是点赞了。';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ut`
--

LOCK TABLES `ut` WRITE;
/*!40000 ALTER TABLE `ut` DISABLE KEYS */;
INSERT INTO `ut` VALUES (1,1,'admin','第一篇博客.html','kk',1,1),(1,2,'admin','b.html','cc',1,0),(1,3,'admin','c.html','静香',1,1),(1,4,'admin','d.html','admin',0,0),(1,6,'admin','f.html','kk',1,1),(1,7,'admin','后端接口.html','admin',0,0),(1,16909949,'admin','网站日志.html','kk',1,0),(1,27169506,'admin','待删除的文章.html','admin',0,0),(1,28935404,'admin','局域网连接测试.html','kk',1,0),(1,54791295,'admin','第一篇上传的博客.html','admin',0,0),(1,63587316,'admin','第三次写新博文.html','admin',0,0),(1,83208395,'admin','震惊，梵蒂冈被袋鼠入侵了.html','admin',0,0),(1,89939132,'admin','胖虎的一天.html','胖虎',0,0),(1,90672776,'admin','RDEAME.html','admin',1,1),(1,108991371,'admin','a.html','admin',0,0),(2,1,'kk','第一篇博客.html','kk',1,1),(2,2,'kk','b.html','cc',1,0),(2,3,'kk','c.html','静香',0,0),(2,4,'kk','d.html','admin',1,0),(2,16909949,'kk','网站日志.html','kk',0,0),(2,30289270,'kk','每日美图分享.html','kk',0,0),(2,63587316,'kk','第三次写新博文.html','admin',1,1),(2,90672776,'kk','RDEAME.html','admin',1,1),(35738692,2,'川普','b.html','cc',1,1),(35738692,4,'川普','d.html','admin',0,0),(71554342,1,'蜡笔小新','第一篇博客.html','kk',0,0),(71554342,36248045,'蜡笔小新','第二篇新博文.html','admin',0,1),(71554342,54791295,'蜡笔小新','第一篇上传的博客.html','admin',1,0),(71554342,63587316,'蜡笔小新','第三次写新博文.html','admin',0,0),(71554342,90672776,'蜡笔小新','RDEAME.html','admin',0,0);
/*!40000 ALTER TABLE `ut` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uu`
--

DROP TABLE IF EXISTS `uu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uu` (
  `uid` int NOT NULL,
  `vipuid` int NOT NULL,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `vipname` varchar(45) COLLATE utf8_bin NOT NULL,
  `vip` int NOT NULL,
  PRIMARY KEY (`uid`,`vipuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户关注表，用户id，关注的用户id，用户名字，关注用户名字,关注的标志，1关注0没关注';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uu`
--

LOCK TABLES `uu` WRITE;
/*!40000 ALTER TABLE `uu` DISABLE KEYS */;
INSERT INTO `uu` VALUES (1,1,'admin','admin',1),(1,2,'admin','kk',1),(1,3,'admin','cc',1),(1,10086,'admin','dd',0),(1,77743360,'admin','NewAdmin',1),(2,71554342,'kk','蜡笔小新',0),(35738692,1,'川普','admin',1),(35738692,2,'川普','kk',1),(35738692,3,'川普','cc',1),(35738692,10086,'川普','dd',1),(35738692,45519187,'川普','王智玮',0),(35738692,77743360,'川普','NewAdmin',0),(71554342,1,'蜡笔小新','admin',1),(71554342,2,'蜡笔小新','kk',1),(71554342,3,'蜡笔小新','cc',1),(71554342,10086,'蜡笔小新','dd',1);
/*!40000 ALTER TABLE `uu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-06 21:41:50
