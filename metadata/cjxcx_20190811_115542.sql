-- MySQL dump 10.13  Distrib 5.5.62, for Linux (x86_64)
--
-- Host: localhost    Database: cjxcx
-- ------------------------------------------------------
-- Server version	5.5.62-log

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
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(255) NOT NULL COMMENT 'openid',
  `nickName` varchar(255) DEFAULT NULL COMMENT '用户名',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (3,'o3mym5N6UBpA5ETmaoD_To0FilG0','Bai','我是第一条评论',NULL),(4,'o3mym5N6UBpA5ETmaoD_To0FilG0','Bai','我是第一条评论',NULL),(5,'o3mym5N6UBpA5ETmaoD_To0FilG0','Bai','我是第一条评论',NULL),(6,'o3mym5N6UBpA5ETmaoD_To0FilG0','Bai','我是第一条评论',NULL),(14,'ooNi15B1TuvknFnIkwt1s5CgjXJw','Bai','我是白世辉','2019-08-07 02:45:46'),(15,'ooNi15B1TuvknFnIkwt1s5CgjXJw','Bai','我是白世辉23','2019-08-07 02:46:13'),(16,'ooNi15B1TuvknFnIkwt1s5CgjXJw','Bai','我是白世辉24','2019-08-07 02:46:22'),(18,'ooNi15MTAQq5p75Sb49wSEgCH7tg','Ambiguous','我是赵晓卿','2019-08-08 04:06:53'),(19,'ooNi15MTAQq5p75Sb49wSEgCH7tg','Ambiguous','dsadsad','2019-08-08 04:15:49'),(20,'ooNi15MTAQq5p75Sb49wSEgCH7tg','Ambiguous','谁知道呢','2019-08-08 04:35:25'),(21,'ooNi15MTAQq5p75Sb49wSEgCH7tg','Ambiguous','dsadsadadaa','2019-08-10 04:03:11');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setting` (
  `name` varchar(255) DEFAULT NULL COMMENT '设置项',
  `box0` double(255,2) DEFAULT NULL COMMENT '奖品1',
  `box1` double(255,2) DEFAULT NULL COMMENT '奖品2',
  `box2` double(255,2) DEFAULT NULL COMMENT '奖品3',
  `box3` double(255,2) DEFAULT NULL COMMENT '奖品4',
  `box4` double(255,2) DEFAULT NULL COMMENT '奖品5',
  `box5` double(255,2) DEFAULT NULL COMMENT '奖品6',
  `box6` double(255,2) DEFAULT NULL COMMENT '奖品7',
  `box7` double(255,2) DEFAULT NULL COMMENT '奖品8',
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `serial` varchar(255) DEFAULT NULL COMMENT '顺序',
  `reward_index` int(255) DEFAULT '0' COMMENT '奖品序号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` VALUES ('0',5.00,3.00,2.00,1.00,100.00,500.00,1000.00,1.00,1,'45601237',4);
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userInfo`
--

DROP TABLE IF EXISTS `userInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userInfo` (
  `avatarUrl` varchar(255) DEFAULT NULL COMMENT '头像路径',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `language` varchar(255) DEFAULT NULL COMMENT '语言',
  `nickName` varchar(255) DEFAULT NULL COMMENT '用户名',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `account` double(255,2) DEFAULT '5.00' COMMENT '余额',
  `count` int(20) DEFAULT NULL COMMENT '次数',
  `openid` varchar(255) NOT NULL COMMENT 'openid',
  `session_key` varchar(255) DEFAULT NULL COMMENT 'session_key',
  `status` int(2) DEFAULT NULL COMMENT '状态0新，1旧',
  `share` int(20) DEFAULT NULL COMMENT '分享次数',
  `sum_money` double(255,2) DEFAULT '0.00' COMMENT '总金额',
  `state` int(255) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`openid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userInfo`
--

LOCK TABLES `userInfo` WRITE;
/*!40000 ALTER TABLE `userInfo` DISABLE KEYS */;
INSERT INTO `userInfo` VALUES ('https://wx.qlogo.cn/mmopen/vi_32/U7wHtIG8KNH1ibNVCIVXU5tXgxv76ou8WYiaQmzTrlOoz10v5jaK2a9Cm6vA84bp6xRWMkNd03MvqMcJEeZyRuFA/132','Baoding','China','1','zh_CN','Bai','Hebei',5.00,NULL,'ooNi15B1TuvknFnIkwt1s5CgjXJw','qSNEl3GyyKbxRkvB+7irnA==',1,NULL,16.23,0),('https://wx.qlogo.cn/mmopen/vi_32/Hia2FuzfViaMw27Nicjme1AJQS2CdKCa099QuicEcAjqUoXOyDw7k0Z7iazsEVDymuLenCPvw2NFpzYhxHTxU97vwjA/132','保定','中国','1','zh_CN','Ambiguous','河北',7130.00,502,'ooNi15MTAQq5p75Sb49wSEgCH7tg','O1w1HW4mCaAo8yFyyPxYeQ==',1,3,6.00,0),('https://wx.qlogo.cn/mmopen/vi_32/MkTjC25mB07ibPnnpkRTnlCbra6ibLFHfONQCVzGWvHoIAVwC08cRuZ1f0WnibcmAY5J8w9tZqzZSk3NS7BAicRc6Q/132',NULL,'北马里亚纳群岛','1','zh_CN','Run','',5.00,NULL,'ooNi15OriOKcG94FCEyccyk94TRA','LPG4EVzNrD8vgvOrmP5+vA==',1,NULL,0.00,0);
/*!40000 ALTER TABLE `userInfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-11 11:55:43
