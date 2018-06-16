-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 103.240.91.2    Database: rolemanagement
-- ------------------------------------------------------
-- Server version	5.1.73-log

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
-- Table structure for table `restPanelStatusName`
--

DROP TABLE IF EXISTS `restPanelStatusName`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restPanelStatusName` (
  `id` int(11) NOT NULL,
  `panelId` varchar(45) NOT NULL,
  `statusName` varchar(45) NOT NULL,
  `moduleName` varchar(45) NOT NULL,
  `statuscount` varchar(45) NOT NULL,
  `panelName` varchar(45) NOT NULL,
  `panelCode` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restPanelStatusName`
--

LOCK TABLES `restPanelStatusName` WRITE;
/*!40000 ALTER TABLE `restPanelStatusName` DISABLE KEYS */;
INSERT INTO `restPanelStatusName` VALUES (1,'PanelId3','Pending','leave','0','Leave Management(Self)','SELF'),(2,'PanelId3','Approved','leave','0','Leave Management(Self)','SELF'),(3,'PanelId3','Rejected','leave','0','Leave Management(Self)','SELF'),(4,'PanelId3','Cancelled Or Reversal','leave','0','Leave Management(Self)','SELF'),(5,'PanelId4','Pending','leave','0','Leave Management(Others)','OTHERS'),(6,'PanelId4','Approved','leave','0','Leave Management(Others)','OTHERS'),(7,'PanelId4','Rejected','leave','0','Leave Management(Others)','OTHERS'),(8,'PanelId4','Click to view pending leaves...','leave','0','Leave Management(Others)','OTHERS'),(9,'PanelId5','Approval Pending','timesheet','0','Timesheet Management(Self)','SELF'),(10,'PanelId5','Approved','timesheet','0','Timesheet Management(Self)','SELF'),(11,'PanelId5','Rejected','timesheet','0','Timesheet Management(Self)','SELF'),(12,'PanelId5','Timesheet History...','timesheet','0','Timesheet Management(Self)','SELF'),(13,'PanelId6','Your Approval Pending','timesheet','0','Timesheet Management(Others)','OTHERS'),(14,'PanelId6','Approved','timesheet','0','Timesheet Management(Others)','OTHERS'),(15,'PanelId6','Rejected','timesheet','0','Timesheet Management(Others)','OTHERS'),(16,'PanelId6','Click to view pending pending TS...','timesheet','0','Timesheet Management(Others)','OTHERS');
/*!40000 ALTER TABLE `restPanelStatusName` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restToken`
--

DROP TABLE IF EXISTS `restToken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restToken` (
  `idrestToken` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(45) NOT NULL,
  `deviceName` varchar(45) NOT NULL,
  `userName` varchar(45) NOT NULL,
  `token` varchar(45) NOT NULL,
  PRIMARY KEY (`idrestToken`)
) ENGINE=MyISAM AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restToken`
--

LOCK TABLES `restToken` WRITE;
/*!40000 ALTER TABLE `restToken` DISABLE KEYS */;
INSERT INTO `restToken` VALUES (15,'0950E3D5-2861-4E36-995C-7FA0FFBD2CB6','iPhone','amarjeet.patel@supraits.com','9af804ba-0cfc-4d12-b3bd-9a6ff55cae49'),(61,'483A80F8-8100-4EF0-A342-CB182F7F8962','iPhone','abhinav.gupta@supraits.com','d92ee2bc-c6ee-4efc-8e8b-b3f70c2a7de5'),(60,'4582B843-0FF8-4191-9A13-D8A208A9D740','iPhone','abhinav.gupta@supraits.com','f120e924-f60a-47b9-b44a-da16b2c5dfc3'),(59,'9829A87D-995A-4C12-B31D-7242F57B4F4A','iPhone','abhinav.gupta@supraits.com','c5852809-ed3a-406f-ad52-d8ad671b2be8'),(58,'hgafsghfhg','ANDROID','abhinav.gupta@supraits.com','614e0f45-0c79-41ca-96fe-2194b5ca5fb6'),(57,'hgafsghfhg','ANDROID','abhinav.gupta@supraits.com','7a27dff3-3d75-4dde-9a3f-88cfdb2f0afd'),(56,'hgafsghfhg','ANDROID','abhinav.gupta@supraits.com','21a5b57c-3c59-4dcb-b46b-a8c5161b38d1'),(54,'7E43B933-6390-462B-89DE-342334BA882E','iPhone','abhinav.gupta@supraits.com','1937f682-7d08-4c7a-9b87-53dcb507e315'),(55,'hgafsghfhg','ANDROID','abhinav.gupta@supraits.com','e7654757-43b9-4282-b380-8fbfb25b0136'),(46,'hgafsghfhg','ANDROID','rohit.joshi@supraits.com','a7d0bcdd-2787-4850-9859-ad860e99a015'),(53,'0950E3D5-2861-4E36-995C-7FA0FFBD2CB6','iPhone','abhinav.gupta@supraits.com','a1e24b23-8732-4a8b-a4bd-228ef378b763');
/*!40000 ALTER TABLE `restToken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rest_panel_list`
--

DROP TABLE IF EXISTS `rest_panel_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rest_panel_list` (
  `restpanId` int(11) NOT NULL,
  `panId` varchar(45) NOT NULL,
  `panName` varchar(45) NOT NULL,
  PRIMARY KEY (`restpanId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rest_panel_list`
--

LOCK TABLES `rest_panel_list` WRITE;
/*!40000 ALTER TABLE `rest_panel_list` DISABLE KEYS */;
INSERT INTO `rest_panel_list` VALUES (1,'PanelId3','Leave Management(Self)'),(2,'PanelId4','Leave Management(Others)'),(3,'PanelId5','Timesheet Management(Self)'),(4,'PanelId6','Timesheet Management(Others)');
/*!40000 ALTER TABLE `rest_panel_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rest_panel_status_name`
--

DROP TABLE IF EXISTS `rest_panel_status_name`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rest_panel_status_name` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(45) NOT NULL,
  `panel_code` varchar(45) NOT NULL,
  `panel_id` varchar(45) NOT NULL,
  `panel_name` varchar(45) NOT NULL,
  `status_name` varchar(45) NOT NULL,
  `statuscount` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rest_panel_status_name`
--

LOCK TABLES `rest_panel_status_name` WRITE;
/*!40000 ALTER TABLE `rest_panel_status_name` DISABLE KEYS */;
/*!40000 ALTER TABLE `rest_panel_status_name` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restquicklink`
--

DROP TABLE IF EXISTS `restquicklink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restquicklink` (
  `idrestquicklink` int(11) NOT NULL,
  `quicklinkid` varchar(45) NOT NULL,
  `quicklinkname` varchar(45) NOT NULL,
  PRIMARY KEY (`idrestquicklink`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restquicklink`
--

LOCK TABLES `restquicklink` WRITE;
/*!40000 ALTER TABLE `restquicklink` DISABLE KEYS */;
INSERT INTO `restquicklink` VALUES (1,'FuncId11','Fill Timesheet'),(2,'FuncId12','Approve Timesheet'),(3,'FuncId13','Weekly TS  Report'),(4,'FuncId14','Monthly TS Report'),(5,'FuncId19','Modify User'),(6,'FuncId20','Add New User'),(7,'FuncId21','Add New Project'),(8,'FuncId22','Modify Project'),(9,'FuncId23','Assign Project'),(10,'FuncId24','Assigned User\'s Task'),(11,'FuncId25','Email Setting'),(12,'FuncId26','User Access'),(13,'FuncId27','Manage Group'),(14,'FuncId28','Expense Report'),(15,'FuncId29','Expense Bucket'),(16,'FuncId30','Apply Leave'),(17,'FuncId31','Leave Approval'),(18,'FuncId32','Configure Leaves'),(19,'FuncId33','Balance(Supra-Canada)'),(20,'FuncId34','Balance(Supra-Noida)'),(21,'FuncId35','Track Leave'),(22,'FuncId36','Leave Report'),(23,'FuncId37','Leave Apply For Team'),(24,'FuncId38','Add Asset'),(25,'FuncId39','Allocate Asset'),(26,'FuncId40','Track Asset'),(27,'FuncId41','Modify Asset'),(28,'FuncId42','View Attendance'),(29,'FuncId43','Fill MOAF'),(30,'FuncId44','Approve Attendance'),(31,'FuncId45','Weekly Attendance Report'),(32,'FuncId46','Monthly Attendance Report'),(33,'FuncId47','Yearly Attendance Report'),(34,'FuncId48','Upload Attendance'),(35,'FuncId49','Generate Emp Doc');
/*!40000 ALTER TABLE `restquicklink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supra_emp_profile_image_metadata`
--

DROP TABLE IF EXISTS `supra_emp_profile_image_metadata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supra_emp_profile_image_metadata` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `imagePath` varchar(200) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `lastmodifiedby` varchar(50) DEFAULT NULL,
  `lastmodifiedon` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supra_emp_profile_image_metadata`
--

LOCK TABLES `supra_emp_profile_image_metadata` WRITE;
/*!40000 ALTER TABLE `supra_emp_profile_image_metadata` DISABLE KEYS */;
INSERT INTO `supra_emp_profile_image_metadata` VALUES (1,'http://103.240.91.78:8082/profileImages/71.jpg','71','71','2018-06-11 05:22:39');
/*!40000 ALTER TABLE `supra_emp_profile_image_metadata` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-13 14:31:08
