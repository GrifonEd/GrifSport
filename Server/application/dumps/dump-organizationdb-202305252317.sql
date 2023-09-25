-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: 192.168.56.104    Database: organizationdb
-- ------------------------------------------------------
-- Server version	5.5.5-10.5.18-MariaDB-0+deb11u1

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
-- Table structure for table `Application`
--

DROP TABLE IF EXISTS `Application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vacancy` varchar(50) NOT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `event_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Application_profile_id_event_id_70500835_uniq` (`profile_id`,`event_id`),
  KEY `Application_event_id_d22c912c_fk_Event_id` (`event_id`),
  CONSTRAINT `Application_event_id_d22c912c_fk_Event_id` FOREIGN KEY (`event_id`) REFERENCES `Event` (`id`),
  CONSTRAINT `Application_profile_id_68dfbf24_fk_profile_id` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Application`
--

LOCK TABLES `Application` WRITE;
/*!40000 ALTER TABLE `Application` DISABLE KEYS */;
INSERT INTO `Application` VALUES (2,'Спортсмен','Диагностика доминирующей перцептивной модальности.',2,1),(11,'Работник','Работал у вас на прошлом мероприятии.',23,18),(12,'Участник','Чемпионка Башкирии 2021 года.',24,4),(13,'Судья','Я судья всероссийской категории!',23,4);
/*!40000 ALTER TABLE `Application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `City`
--

DROP TABLE IF EXISTS `City`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `City` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `City`
--

LOCK TABLES `City` WRITE;
/*!40000 ALTER TABLE `City` DISABLE KEYS */;
INSERT INTO `City` VALUES (1,'Уфа'),(2,'Москва'),(3,'Екатеринбург'),(4,'Сыктывкар'),(5,'Тюмень'),(6,'Санкт-Петербург');
/*!40000 ALTER TABLE `City` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Discipline`
--

DROP TABLE IF EXISTS `Discipline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Discipline` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL,
  `sport_id` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Discipline_sport_id_dd6acd46_fk_Sport_id` (`sport_id`),
  KEY `Discipline_type_id_b2805a1f_fk_TypeDiscipline_id` (`type_id`),
  CONSTRAINT `Discipline_sport_id_dd6acd46_fk_Sport_id` FOREIGN KEY (`sport_id`) REFERENCES `Sport` (`id`),
  CONSTRAINT `Discipline_type_id_b2805a1f_fk_TypeDiscipline_id` FOREIGN KEY (`type_id`) REFERENCES `TypeDiscipline` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Discipline`
--

LOCK TABLES `Discipline` WRITE;
/*!40000 ALTER TABLE `Discipline` DISABLE KEYS */;
INSERT INTO `Discipline` VALUES (1,'Лазание на скорость',1,1),(2,'Лазание на трудность',1,1),(3,'Шахматы',2,1);
/*!40000 ALTER TABLE `Discipline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DisciplineForEvent`
--

DROP TABLE IF EXISTS `DisciplineForEvent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DisciplineForEvent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `discipline_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `DisciplineForEvent_discipline_id_fe525721_fk_Discipline_id` (`discipline_id`),
  KEY `DisciplineForEvent_event_id_97b4d300_fk_Event_id` (`event_id`),
  CONSTRAINT `DisciplineForEvent_discipline_id_fe525721_fk_Discipline_id` FOREIGN KEY (`discipline_id`) REFERENCES `Discipline` (`id`),
  CONSTRAINT `DisciplineForEvent_event_id_97b4d300_fk_Event_id` FOREIGN KEY (`event_id`) REFERENCES `Event` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DisciplineForEvent`
--

LOCK TABLES `DisciplineForEvent` WRITE;
/*!40000 ALTER TABLE `DisciplineForEvent` DISABLE KEYS */;
INSERT INTO `DisciplineForEvent` VALUES (3,1,24),(4,3,23);
/*!40000 ALTER TABLE `DisciplineForEvent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Event`
--

DROP TABLE IF EXISTS `Event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `adress` varchar(500) NOT NULL,
  `date_created` datetime(6) NOT NULL,
  `event_start_date` datetime(6) NOT NULL,
  `event_end_date` datetime(6) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `schedule` varchar(5000) NOT NULL,
  `results` varchar(5000) DEFAULT NULL,
  `deadline_for_accepting_applications` datetime(6) NOT NULL,
  `ages` varchar(255) NOT NULL,
  `genders` varchar(50) NOT NULL,
  `phone_number` varchar(12) NOT NULL,
  `type_id` int(11) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL,
  `sport_id` int(11) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Event_type_id_93853078_fk_TypeEvent_id` (`type_id`),
  KEY `Event_sport_id_d7fb783f_fk_Sport_id` (`sport_id`),
  KEY `Event_city_id_43f01670_fk_City_id` (`city_id`),
  CONSTRAINT `Event_city_id_43f01670_fk_City_id` FOREIGN KEY (`city_id`) REFERENCES `City` (`id`),
  CONSTRAINT `Event_sport_id_d7fb783f_fk_Sport_id` FOREIGN KEY (`sport_id`) REFERENCES `Sport` (`id`),
  CONSTRAINT `Event_type_id_93853078_fk_TypeEvent_id` FOREIGN KEY (`type_id`) REFERENCES `TypeEvent` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Event`
--

LOCK TABLES `Event` WRITE;
/*!40000 ALTER TABLE `Event` DISABLE KEYS */;
INSERT INTO `Event` VALUES (2,'Чемпионат России по скалолазанию','Первый в истории чемпионат мира ШАХМАТЫ','пушкина урамы 21','2023-05-06 12:00:28.248000','2023-05-24 10:35:52.000000','2023-05-30 10:35:59.000000','/drawable','24.05.23-скорость 25.05.23 - трудность','ну ладна','2023-05-17 10:37:00.000000','17-20, 21-27, 28-35','Мужчины и женщины','+79173720372',1,1,2,6),(9,'Атао','Лалп','Воал','2023-05-12 16:33:46.651000','2023-05-11 12:00:00.000000','2023-05-13 12:00:00.000000','5656','Улу',NULL,'2023-05-09 18:35:00.000000','Овла','Женщины','8686',1,1,4,2),(13,'Чемпионат России по скалолазанию11111111','Первый в истории чемпионат мира','пушкина урамы 21','2023-05-14 14:59:59.301000','2023-05-24 10:35:52.000000','2023-05-30 10:35:59.000000','/drawable','24.05.23-скорость 25.05.23 - трудность',NULL,'2023-05-17 10:37:00.000000','17-20, 21-27, 28-35','Мужчины и женщины','+79173720372',1,1,1,3),(20,'Первенство спортивной школы №11','Первенство СШОР по скалолазанию в дисциплине трудность','Адад','2023-05-14 22:24:59.870000','2023-05-14 12:00:00.000000','2023-04-27 13:40:00.000000','https://rusclimbing.ru/upload/iblock/875/Skalolazanie_afisha_Skala2_2022v2.jpg','Лала',NULL,'2023-05-14 10:24:00.000000','Алал','Женщины','5665',1,1,1,1),(21,'Открытая тренировка по ниндзя-спорту','Вход свободный, приходите кто хочет!','Алла','2023-05-14 22:29:29.223000','2023-05-11 12:00:00.000000','2023-05-11 12:00:00.000000','https://www.film.ru/sites/default/files/styles/thumb_260x400/public/movies/posters/7107846-2179309.jpg','Алалла',NULL,'2023-05-14 10:29:00.000000','Ала','Мужчины','59',1,1,3,1),(22,'Чемпионат мира по Керлингу','На данном событии соберутся все сильнейшие атлеты данного вида спорта.','Вьаб','2023-05-14 22:31:13.979000','2023-05-13 12:00:00.000000','2023-05-13 17:30:00.000000','https://vr-vyksa.ru/media/images/afisha-kerling-v-parke.width-1200.jpg','Влад',NULL,'2023-05-14 10:31:00.000000','Вла','Мужчины','565',1,1,5,3),(23,'Чемпионат России 2022','Чемпионат России по шахматам','г.Сызрань, пушкина 44','2023-05-14 22:33:01.329000','2022-09-13 12:00:00.000000','2022-09-30 12:00:00.000000','https://syzran-chess.ru/wp-content/uploads/2022/04/grafik-maj-1-724x1024.jpg','13-20 сентября, квалификация. 20-30 сентября - финал.',NULL,'2022-09-01 10:32:00.000000','14-18, 19-30, 31 и старше','Мужчины и женщины','+89199999993',1,0,2,4),(24,'Кубок России по скалолазанию','В Тюмени пройдет первый старт в году - кубок России.','Тюмень, ул. Пушкина 33','2023-05-15 00:46:20.640000','2023-05-15 12:00:00.000000','2023-05-17 12:00:00.000000','https://rusclimbing.ru/upload/resize_cache/webp/iblock/9eb/2023_03_Tyumen_Anons_936kh612px_01.webp','15.05-день заезда, 16,05 - день соревнований',NULL,'2023-05-15 00:46:00.000000','Старше 16-ти','Мужчины и женщины','+79173332224',1,1,1,5);
/*!40000 ALTER TABLE `Event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Files`
--

DROP TABLE IF EXISTS `Files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(80) NOT NULL,
  `pdf` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Files`
--

LOCK TABLES `Files` WRITE;
/*!40000 ALTER TABLE `Files` DISABLE KEYS */;
INSERT INTO `Files` VALUES (1,'haha','pdfs/ОГЭ_2021_Вариант_21-22й2.pdf'),(2,'hm',''),(3,'hm',''),(4,'hm',''),(5,'hm',''),(6,'hm',''),(7,'hm','<MultiValueDict: {}>'),(8,'hm','<MultiValueDict: {}>'),(9,'hm','');
/*!40000 ALTER TABLE `Files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FilesForEvent`
--

DROP TABLE IF EXISTS `FilesForEvent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FilesForEvent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `files_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FilesForEvent_event_id_3a2c30d6_fk_Event_id` (`event_id`),
  KEY `FilesForEvent_files_id_87ebe142_fk_Files_id` (`files_id`),
  CONSTRAINT `FilesForEvent_event_id_3a2c30d6_fk_Event_id` FOREIGN KEY (`event_id`) REFERENCES `Event` (`id`),
  CONSTRAINT `FilesForEvent_files_id_87ebe142_fk_Files_id` FOREIGN KEY (`files_id`) REFERENCES `Files` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FilesForEvent`
--

LOCK TABLES `FilesForEvent` WRITE;
/*!40000 ALTER TABLE `FilesForEvent` DISABLE KEYS */;
/*!40000 ALTER TABLE `FilesForEvent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Invite`
--

DROP TABLE IF EXISTS `Invite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Invite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vacancy` varchar(50) NOT NULL,
  `event_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Invite_profile_id_event_id_027660a8_uniq` (`profile_id`,`event_id`),
  KEY `Invite_event_id_e5d7aaa3_fk_Event_id` (`event_id`),
  CONSTRAINT `Invite_event_id_e5d7aaa3_fk_Event_id` FOREIGN KEY (`event_id`) REFERENCES `Event` (`id`),
  CONSTRAINT `Invite_profile_id_de5304dd_fk_profile_id` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Invite`
--

LOCK TABLES `Invite` WRITE;
/*!40000 ALTER TABLE `Invite` DISABLE KEYS */;
INSERT INTO `Invite` VALUES (1,'Зритель',24,7),(2,'Зритель',24,1),(6,'Зритель',24,18);
/*!40000 ALTER TABLE `Invite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Judge`
--

DROP TABLE IF EXISTS `Judge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Judge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(500) DEFAULT NULL,
  `profile_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `profile_id` (`profile_id`),
  CONSTRAINT `Judge_profile_id_8aa79b28_fk_profile_id` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Judge`
--

LOCK TABLES `Judge` WRITE;
/*!40000 ALTER TABLE `Judge` DISABLE KEYS */;
INSERT INTO `Judge` VALUES (1,'Судья всероссийской категории',2),(2,'Судья',16),(3,'Судья',18);
/*!40000 ALTER TABLE `Judge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `JudgeForEvent`
--

DROP TABLE IF EXISTS `JudgeForEvent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `JudgeForEvent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `judge_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `JudgeForEvent_event_id_judge_id_1a6964f8_uniq` (`event_id`,`judge_id`),
  KEY `JudgeForEvent_judge_id_729045e5_fk_Judge_id` (`judge_id`),
  CONSTRAINT `JudgeForEvent_event_id_4484ae8f_fk_Event_id` FOREIGN KEY (`event_id`) REFERENCES `Event` (`id`),
  CONSTRAINT `JudgeForEvent_judge_id_729045e5_fk_Judge_id` FOREIGN KEY (`judge_id`) REFERENCES `Judge` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `JudgeForEvent`
--

LOCK TABLES `JudgeForEvent` WRITE;
/*!40000 ALTER TABLE `JudgeForEvent` DISABLE KEYS */;
INSERT INTO `JudgeForEvent` VALUES (1,2,1);
/*!40000 ALTER TABLE `JudgeForEvent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Organizer`
--

DROP TABLE IF EXISTS `Organizer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Organizer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(500) DEFAULT NULL,
  `profile_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `profile_id` (`profile_id`),
  CONSTRAINT `Organizer_profile_id_75896e18_fk_profile_id` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Organizer`
--

LOCK TABLES `Organizer` WRITE;
/*!40000 ALTER TABLE `Organizer` DISABLE KEYS */;
INSERT INTO `Organizer` VALUES (1,'Федерация скалолазания России.',2),(2,'Организатор',16),(3,'Организатор',18);
/*!40000 ALTER TABLE `Organizer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrganizerForEvent`
--

DROP TABLE IF EXISTS `OrganizerForEvent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrganizerForEvent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `organizer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `OrganizerForEvent_event_id_organizer_id_75399437_uniq` (`event_id`,`organizer_id`),
  KEY `OrganizerForEvent_organizer_id_ad48f539_fk_Organizer_id` (`organizer_id`),
  CONSTRAINT `OrganizerForEvent_event_id_afb89974_fk_Event_id` FOREIGN KEY (`event_id`) REFERENCES `Event` (`id`),
  CONSTRAINT `OrganizerForEvent_organizer_id_ad48f539_fk_Organizer_id` FOREIGN KEY (`organizer_id`) REFERENCES `Organizer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrganizerForEvent`
--

LOCK TABLES `OrganizerForEvent` WRITE;
/*!40000 ALTER TABLE `OrganizerForEvent` DISABLE KEYS */;
INSERT INTO `OrganizerForEvent` VALUES (1,2,1),(2,23,3),(3,24,3);
/*!40000 ALTER TABLE `OrganizerForEvent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Participant`
--

DROP TABLE IF EXISTS `Participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Participant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(500) DEFAULT NULL,
  `profile_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `profile_id` (`profile_id`),
  CONSTRAINT `Participant_profile_id_bc098ca8_fk_profile_id` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Participant`
--

LOCK TABLES `Participant` WRITE;
/*!40000 ALTER TABLE `Participant` DISABLE KEYS */;
INSERT INTO `Participant` VALUES (1,'Будущий чемпион вселенной',1),(2,'Будущий чемпион вселенной',2),(3,'Участник',16),(4,'Участник',18);
/*!40000 ALTER TABLE `Participant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ParticipantForEvent`
--

DROP TABLE IF EXISTS `ParticipantForEvent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ParticipantForEvent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `participant_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ParticipantForEvent_event_id_participant_id_5b39a63b_uniq` (`event_id`,`participant_id`),
  KEY `ParticipantForEvent_participant_id_88a863f8_fk_Participant_id` (`participant_id`),
  CONSTRAINT `ParticipantForEvent_event_id_30767509_fk_Event_id` FOREIGN KEY (`event_id`) REFERENCES `Event` (`id`),
  CONSTRAINT `ParticipantForEvent_participant_id_88a863f8_fk_Participant_id` FOREIGN KEY (`participant_id`) REFERENCES `Participant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ParticipantForEvent`
--

LOCK TABLES `ParticipantForEvent` WRITE;
/*!40000 ALTER TABLE `ParticipantForEvent` DISABLE KEYS */;
INSERT INTO `ParticipantForEvent` VALUES (1,2,1),(3,24,3),(2,24,4);
/*!40000 ALTER TABLE `ParticipantForEvent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sponsor`
--

DROP TABLE IF EXISTS `Sponsor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sponsor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(500) DEFAULT NULL,
  `profile_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `profile_id` (`profile_id`),
  CONSTRAINT `Sponsor_profile_id_bd67031f_fk_profile_id` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sponsor`
--

LOCK TABLES `Sponsor` WRITE;
/*!40000 ALTER TABLE `Sponsor` DISABLE KEYS */;
/*!40000 ALTER TABLE `Sponsor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SponsorForEvent`
--

DROP TABLE IF EXISTS `SponsorForEvent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SponsorForEvent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `sponsor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `SponsorForEvent_event_id_5c9faf66_fk_Event_id` (`event_id`),
  KEY `SponsorForEvent_sponsor_id_9df26751_fk_Sponsor_id` (`sponsor_id`),
  CONSTRAINT `SponsorForEvent_event_id_5c9faf66_fk_Event_id` FOREIGN KEY (`event_id`) REFERENCES `Event` (`id`),
  CONSTRAINT `SponsorForEvent_sponsor_id_9df26751_fk_Sponsor_id` FOREIGN KEY (`sponsor_id`) REFERENCES `Sponsor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SponsorForEvent`
--

LOCK TABLES `SponsorForEvent` WRITE;
/*!40000 ALTER TABLE `SponsorForEvent` DISABLE KEYS */;
/*!40000 ALTER TABLE `SponsorForEvent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sport`
--

DROP TABLE IF EXISTS `Sport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL,
  `typeSport_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Sport_typeSport_id_d058617c_fk_TypeSport_id` (`typeSport_id`),
  CONSTRAINT `Sport_typeSport_id_d058617c_fk_TypeSport_id` FOREIGN KEY (`typeSport_id`) REFERENCES `TypeSport` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sport`
--

LOCK TABLES `Sport` WRITE;
/*!40000 ALTER TABLE `Sport` DISABLE KEYS */;
INSERT INTO `Sport` VALUES (1,'Скалолазание',1),(2,'Шахматы',1),(3,'Ниндзя-спорт',3),(4,'Бокс',2),(5,'Керлинг',1);
/*!40000 ALTER TABLE `Sport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TypeDiscipline`
--

DROP TABLE IF EXISTS `TypeDiscipline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TypeDiscipline` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `olympic` tinyint(1) NOT NULL,
  `team` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TypeDiscipline`
--

LOCK TABLES `TypeDiscipline` WRITE;
/*!40000 ALTER TABLE `TypeDiscipline` DISABLE KEYS */;
INSERT INTO `TypeDiscipline` VALUES (1,1,1);
/*!40000 ALTER TABLE `TypeDiscipline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TypeEvent`
--

DROP TABLE IF EXISTS `TypeEvent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TypeEvent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TypeEvent`
--

LOCK TABLES `TypeEvent` WRITE;
/*!40000 ALTER TABLE `TypeEvent` DISABLE KEYS */;
INSERT INTO `TypeEvent` VALUES (1,'Фестиваль');
/*!40000 ALTER TABLE `TypeEvent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TypeSport`
--

DROP TABLE IF EXISTS `TypeSport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TypeSport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `olympic` tinyint(1) NOT NULL,
  `team` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TypeSport`
--

LOCK TABLES `TypeSport` WRITE;
/*!40000 ALTER TABLE `TypeSport` DISABLE KEYS */;
INSERT INTO `TypeSport` VALUES (1,1,1),(2,1,0),(3,0,0);
/*!40000 ALTER TABLE `TypeSport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Viewer`
--

DROP TABLE IF EXISTS `Viewer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Viewer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(500) DEFAULT NULL,
  `profile_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `profile_id` (`profile_id`),
  CONSTRAINT `Viewer_profile_id_26e65d2c_fk_profile_id` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Viewer`
--

LOCK TABLES `Viewer` WRITE;
/*!40000 ALTER TABLE `Viewer` DISABLE KEYS */;
INSERT INTO `Viewer` VALUES (1,'Зритель',2),(2,'Зритель',3),(3,'Зритель',16),(4,'Зритель',18);
/*!40000 ALTER TABLE `Viewer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ViewerForEvent`
--

DROP TABLE IF EXISTS `ViewerForEvent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ViewerForEvent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `viewer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ViewerForEvent_event_id_viewer_id_92429f70_uniq` (`event_id`,`viewer_id`),
  KEY `ViewerForEvent_viewer_id_e8499885_fk_Viewer_id` (`viewer_id`),
  CONSTRAINT `ViewerForEvent_event_id_1f6b0bb9_fk_Event_id` FOREIGN KEY (`event_id`) REFERENCES `Event` (`id`),
  CONSTRAINT `ViewerForEvent_viewer_id_e8499885_fk_Viewer_id` FOREIGN KEY (`viewer_id`) REFERENCES `Viewer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ViewerForEvent`
--

LOCK TABLES `ViewerForEvent` WRITE;
/*!40000 ALTER TABLE `ViewerForEvent` DISABLE KEYS */;
INSERT INTO `ViewerForEvent` VALUES (1,2,1);
/*!40000 ALTER TABLE `ViewerForEvent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Worker`
--

DROP TABLE IF EXISTS `Worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Worker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(500) DEFAULT NULL,
  `profile_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `profile_id` (`profile_id`),
  CONSTRAINT `Worker_profile_id_75a25323_fk_profile_id` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Worker`
--

LOCK TABLES `Worker` WRITE;
/*!40000 ALTER TABLE `Worker` DISABLE KEYS */;
INSERT INTO `Worker` VALUES (1,'Федерация скалолазания России.',2),(2,'Рабочий',16),(3,'Рабочий',18);
/*!40000 ALTER TABLE `Worker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WorkerForEvent`
--

DROP TABLE IF EXISTS `WorkerForEvent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WorkerForEvent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_id` int(11) NOT NULL,
  `worker_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `WorkerForEvent_event_id_worker_id_abe5952f_uniq` (`event_id`,`worker_id`),
  KEY `WorkerForEvent_worker_id_c512be0f_fk_Worker_id` (`worker_id`),
  CONSTRAINT `WorkerForEvent_event_id_138a7b5d_fk_Event_id` FOREIGN KEY (`event_id`) REFERENCES `Event` (`id`),
  CONSTRAINT `WorkerForEvent_worker_id_c512be0f_fk_Worker_id` FOREIGN KEY (`worker_id`) REFERENCES `Worker` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WorkerForEvent`
--

LOCK TABLES `WorkerForEvent` WRITE;
/*!40000 ALTER TABLE `WorkerForEvent` DISABLE KEYS */;
INSERT INTO `WorkerForEvent` VALUES (1,2,1),(2,24,3);
/*!40000 ALTER TABLE `WorkerForEvent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_group`
--

DROP TABLE IF EXISTS `auth_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group`
--

LOCK TABLES `auth_group` WRITE;
/*!40000 ALTER TABLE `auth_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_group_permissions`
--

DROP TABLE IF EXISTS `auth_group_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_group_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group_permissions`
--

LOCK TABLES `auth_group_permissions` WRITE;
/*!40000 ALTER TABLE `auth_group_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_permission`
--

DROP TABLE IF EXISTS `auth_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_permission`
--

LOCK TABLES `auth_permission` WRITE;
/*!40000 ALTER TABLE `auth_permission` DISABLE KEYS */;
INSERT INTO `auth_permission` VALUES (1,'Can add log entry',1,'add_logentry'),(2,'Can change log entry',1,'change_logentry'),(3,'Can delete log entry',1,'delete_logentry'),(4,'Can view log entry',1,'view_logentry'),(5,'Can add permission',2,'add_permission'),(6,'Can change permission',2,'change_permission'),(7,'Can delete permission',2,'delete_permission'),(8,'Can view permission',2,'view_permission'),(9,'Can add group',3,'add_group'),(10,'Can change group',3,'change_group'),(11,'Can delete group',3,'delete_group'),(12,'Can view group',3,'view_group'),(13,'Can add content type',4,'add_contenttype'),(14,'Can change content type',4,'change_contenttype'),(15,'Can delete content type',4,'delete_contenttype'),(16,'Can view content type',4,'view_contenttype'),(17,'Can add session',5,'add_session'),(18,'Can change session',5,'change_session'),(19,'Can delete session',5,'delete_session'),(20,'Can view session',5,'view_session'),(21,'Can add user',6,'add_user'),(22,'Can change user',6,'change_user'),(23,'Can delete user',6,'delete_user'),(24,'Can view user',6,'view_user'),(25,'Can add discipline',7,'add_discipline'),(26,'Can change discipline',7,'change_discipline'),(27,'Can delete discipline',7,'delete_discipline'),(28,'Can view discipline',7,'view_discipline'),(29,'Can add event',8,'add_event'),(30,'Can change event',8,'change_event'),(31,'Can delete event',8,'delete_event'),(32,'Can view event',8,'view_event'),(33,'Can add organizer',9,'add_organizer'),(34,'Can change organizer',9,'change_organizer'),(35,'Can delete organizer',9,'delete_organizer'),(36,'Can view organizer',9,'view_organizer'),(37,'Can add type discipline',10,'add_typediscipline'),(38,'Can change type discipline',10,'change_typediscipline'),(39,'Can delete type discipline',10,'delete_typediscipline'),(40,'Can view type discipline',10,'view_typediscipline'),(41,'Can add type event',11,'add_typeevent'),(42,'Can change type event',11,'change_typeevent'),(43,'Can delete type event',11,'delete_typeevent'),(44,'Can view type event',11,'view_typeevent'),(45,'Can add type sport',12,'add_typesport'),(46,'Can change type sport',12,'change_typesport'),(47,'Can delete type sport',12,'delete_typesport'),(48,'Can view type sport',12,'view_typesport'),(49,'Can add sport',13,'add_sport'),(50,'Can change sport',13,'change_sport'),(51,'Can delete sport',13,'delete_sport'),(52,'Can view sport',13,'view_sport'),(53,'Can add profile',14,'add_profile'),(54,'Can change profile',14,'change_profile'),(55,'Can delete profile',14,'delete_profile'),(56,'Can view profile',14,'view_profile'),(57,'Can add organizer for event',15,'add_organizerforevent'),(58,'Can change organizer for event',15,'change_organizerforevent'),(59,'Can delete organizer for event',15,'delete_organizerforevent'),(60,'Can view organizer for event',15,'view_organizerforevent'),(61,'Can add discipline for event',16,'add_disciplineforevent'),(62,'Can change discipline for event',16,'change_disciplineforevent'),(63,'Can delete discipline for event',16,'delete_disciplineforevent'),(64,'Can view discipline for event',16,'view_disciplineforevent'),(65,'Can add judge',17,'add_judge'),(66,'Can change judge',17,'change_judge'),(67,'Can delete judge',17,'delete_judge'),(68,'Can view judge',17,'view_judge'),(69,'Can add participant',18,'add_participant'),(70,'Can change participant',18,'change_participant'),(71,'Can delete participant',18,'delete_participant'),(72,'Can view participant',18,'view_participant'),(73,'Can add worker',19,'add_worker'),(74,'Can change worker',19,'change_worker'),(75,'Can delete worker',19,'delete_worker'),(76,'Can view worker',19,'view_worker'),(77,'Can add worker for event',20,'add_workerforevent'),(78,'Can change worker for event',20,'change_workerforevent'),(79,'Can delete worker for event',20,'delete_workerforevent'),(80,'Can view worker for event',20,'view_workerforevent'),(81,'Can add participant for event',21,'add_participantforevent'),(82,'Can change participant for event',21,'change_participantforevent'),(83,'Can delete participant for event',21,'delete_participantforevent'),(84,'Can view participant for event',21,'view_participantforevent'),(85,'Can add judge for event',22,'add_judgeforevent'),(86,'Can change judge for event',22,'change_judgeforevent'),(87,'Can delete judge for event',22,'delete_judgeforevent'),(88,'Can view judge for event',22,'view_judgeforevent'),(89,'Can add sponsor',23,'add_sponsor'),(90,'Can change sponsor',23,'change_sponsor'),(91,'Can delete sponsor',23,'delete_sponsor'),(92,'Can view sponsor',23,'view_sponsor'),(93,'Can add sponsor for event',24,'add_sponsorforevent'),(94,'Can change sponsor for event',24,'change_sponsorforevent'),(95,'Can delete sponsor for event',24,'delete_sponsorforevent'),(96,'Can view sponsor for event',24,'view_sponsorforevent'),(97,'Can add application',25,'add_application'),(98,'Can change application',25,'change_application'),(99,'Can delete application',25,'delete_application'),(100,'Can view application',25,'view_application'),(101,'Can add viewer',26,'add_viewer'),(102,'Can change viewer',26,'change_viewer'),(103,'Can delete viewer',26,'delete_viewer'),(104,'Can view viewer',26,'view_viewer'),(105,'Can add viewer for event',27,'add_viewerforevent'),(106,'Can change viewer for event',27,'change_viewerforevent'),(107,'Can delete viewer for event',27,'delete_viewerforevent'),(108,'Can view viewer for event',27,'view_viewerforevent'),(109,'Can add files',28,'add_files'),(110,'Can change files',28,'change_files'),(111,'Can delete files',28,'delete_files'),(112,'Can view files',28,'view_files'),(113,'Can add files for event',29,'add_filesforevent'),(114,'Can change files for event',29,'change_filesforevent'),(115,'Can delete files for event',29,'delete_filesforevent'),(116,'Can view files for event',29,'view_filesforevent'),(117,'Can add invite',30,'add_invite'),(118,'Can change invite',30,'change_invite'),(119,'Can delete invite',30,'delete_invite'),(120,'Can view invite',30,'view_invite'),(121,'Can add city',31,'add_city'),(122,'Can change city',31,'change_city'),(123,'Can delete city',31,'delete_city'),(124,'Can view city',31,'view_city');
/*!40000 ALTER TABLE `auth_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_admin_log`
--

DROP TABLE IF EXISTS `django_admin_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext DEFAULT NULL,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) unsigned NOT NULL CHECK (`action_flag` >= 0),
  `change_message` longtext NOT NULL,
  `content_type_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6_fk_organizationapp_user_id` (`user_id`),
  CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `django_admin_log_user_id_c564eba6_fk_organizationapp_user_id` FOREIGN KEY (`user_id`) REFERENCES `organizationapp_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_admin_log`
--

LOCK TABLES `django_admin_log` WRITE;
/*!40000 ALTER TABLE `django_admin_log` DISABLE KEYS */;
INSERT INTO `django_admin_log` VALUES (1,'2023-05-03 12:53:02.955000','2','Eduard, daukaev.eduard@mail.ru',2,'[{\"changed\": {\"fields\": [\"Active\"]}}]',6,1),(2,'2023-05-06 10:34:37.118000','1','{\'id\': 1, \'type\': \'Фестиваль\'}',1,'[{\"added\": {}}]',11,1),(3,'2023-05-06 10:37:49.050000','1','Чемпионат России по скалолазанию Первый в истории чемпионат мира, 2023-05-24 10:35:52, 1',1,'[{\"added\": {}}]',8,1),(4,'2023-05-07 16:09:27.509000','1','Edik Dauk, daukaev.eduard@mail.ru, 1',1,'[{\"added\": {}}]',18,1),(5,'2023-05-07 17:01:19.261000','3','Eduk, daukaev.eduarikd@mail.ru',2,'[{\"changed\": {\"fields\": [\"Active\"]}}]',6,1),(6,'2023-05-08 13:04:12.543000','1','1 Чемпионат России по скалолазанию, Edik',1,'[{\"added\": {}}]',25,1),(7,'2023-05-09 18:35:17.985000','5','Olya1, eduarddaukaev1@gmail.com',2,'[{\"changed\": {\"fields\": [\"Active\"]}}]',6,1),(8,'2023-05-09 18:35:21.702000','4','Olya, eduarddaukaev@gmail.com',2,'[{\"changed\": {\"fields\": [\"Active\"]}}]',6,1),(9,'2023-05-10 20:04:12.918000','1','{\'id\': 1, \'olympic\': True, \'team\': False}',1,'[{\"added\": {}}]',12,1),(10,'2023-05-10 20:04:21.852000','1','{\'id\': 1, \'olympic\': True, \'team\': True}',1,'[{\"added\": {}}]',10,1),(11,'2023-05-10 20:05:00.698000','1','Скалолазание {\'id\': 1, \'olympic\': True, \'team\': False}, 1',1,'[{\"added\": {}}]',13,1),(12,'2023-05-10 20:05:24.454000','1','Лазание на скорость Скалолазание {\'id\': 1, \'olympic\': True, \'team\': False}, 1, {\'id\': 1, \'olympic\': True, \'team\': True}, 1',1,'[{\"added\": {}}]',7,1),(13,'2023-05-10 20:05:37.289000','1','1 Чемпионат России по скалолазанию11111111, Лазание на скорость',1,'[{\"added\": {}}]',16,1),(14,'2023-05-10 23:13:00.953000','2','Лазанме на трудность Скалолазание {\'id\': 1, \'olympic\': True, \'team\': False}, 1, {\'id\': 1, \'olympic\': True, \'team\': True}, 2',1,'[{\"added\": {}}]',7,1),(15,'2023-05-10 23:13:05.265000','2','Лазание на трудность Скалолазание {\'id\': 1, \'olympic\': True, \'team\': False}, 1, {\'id\': 1, \'olympic\': True, \'team\': True}, 2',2,'[{\"changed\": {\"fields\": [\"Name\"]}}]',7,1),(16,'2023-05-10 23:13:24.470000','2','2 Чемпионат России по скалолазанию11111111, Лазание на трудность',1,'[{\"added\": {}}]',16,1),(17,'2023-05-12 15:11:14.348000','1','1',1,'[{\"added\": {}}]',29,1),(18,'2023-05-13 23:41:09.279000','19','Grifonka, daukaevDiplom@gmail.com',2,'[{\"changed\": {\"fields\": [\"Active\"]}}]',6,1),(19,'2023-05-15 15:23:31.863000','23','Ашша Ьвлаалал, 2023-05-14 12:00:00, 23',2,'[{\"changed\": {\"fields\": [\"Is active\"]}}]',8,1),(20,'2023-05-15 15:23:41.540000','23','Ашша Ьвлаалал, 2023-05-14 12:00:00, 23',2,'[{\"changed\": {\"fields\": [\"Is active\"]}}]',8,1),(21,'2023-05-18 00:47:15.502000','9','9 Концерт, Эд',3,'',25,1),(22,'2023-05-18 00:47:15.556000','8','8 Чемпионат России по скалолазанию11111111, Эд',3,'',25,1),(23,'2023-05-18 00:47:15.578000','7','7 Концерт, Эд',3,'',25,1),(24,'2023-05-18 00:47:15.600000','6','6 Концерт, Эд',3,'',25,1),(25,'2023-05-18 00:47:15.623000','5','5 Концерт, Эд',3,'',25,1),(26,'2023-05-18 00:47:15.645000','4','4 Чемпионат России по скалолазанию11111111, Эд',3,'',25,1),(27,'2023-05-18 00:47:15.667000','3','3 Чемпионат России по скалолазанию11111111, Edik',3,'',25,1),(28,'2023-05-19 02:20:21.727000','10','10 Концерт герои на все времена, Эд',2,'[{\"changed\": {\"fields\": [\"Description\"]}}]',25,1),(29,'2023-05-19 02:20:26.141000','11','11 Ашша, Эд',2,'[{\"changed\": {\"fields\": [\"Description\"]}}]',25,1),(30,'2023-05-19 12:09:13.749000','24','Кубок России по скалолазанию В Тюмени пройдет первый старт в году - кубок России., 2023-05-15 12:00:00, 24',2,'[{\"changed\": {\"fields\": [\"Name\", \"Description\", \"Adress\", \"Event end date\", \"Schedule\"]}}]',8,1),(31,'2023-05-19 12:09:26.918000','24','Кубок России по скалолазанию В Тюмени пройдет первый старт в году - кубок России., 2023-05-15 12:00:00, 24',2,'[]',8,1),(32,'2023-05-19 12:10:10.847000','24','Кубок России по скалолазанию В Тюмени пройдет первый старт в году - кубок России., 2023-05-15 12:00:00, 24',2,'[{\"changed\": {\"fields\": [\"Schedule\", \"Ages\", \"Genders\", \"Phone_number\"]}}]',8,1),(33,'2023-05-19 12:12:55.204000','23','Чемпионат России 2022 Чемпионат России по шахматам, 2022-09-13 12:00:00, 23',2,'[{\"changed\": {\"fields\": [\"Name\", \"Description\", \"Adress\", \"Event start date\", \"Event end date\", \"Schedule\", \"Deadline for accepting applications\", \"Ages\", \"Genders\", \"Phone_number\", \"Is active\"]}}]',8,1),(34,'2023-05-19 12:20:23.051000','13','13 Чемпионат России 2022, Оля',2,'[{\"changed\": {\"fields\": [\"Description\"]}}]',25,1),(35,'2023-05-19 12:20:37.014000','11','11 Чемпионат России 2022, Эд',2,'[{\"changed\": {\"fields\": [\"Description\"]}}]',25,1),(36,'2023-05-19 12:21:04.081000','10','10 Кубок России по скалолазанию, Эд',2,'[{\"changed\": {\"fields\": [\"Description\"]}}]',25,1),(37,'2023-05-19 12:21:24.411000','12','12 Кубок России по скалолазанию, Оля',2,'[{\"changed\": {\"fields\": [\"Description\"]}}]',25,1),(38,'2023-05-19 12:35:10.228000','3','3 Кубок России по скалолазанию, Лазание на скорость',1,'[{\"added\": {}}]',16,1),(39,'2023-05-19 12:36:21.127000','2','{\'id\': 2, \'olympic\': True, \'team\': False}',1,'[{\"added\": {}}]',12,1),(40,'2023-05-19 12:36:36.568000','2','Шахматы {\'id\': 1, \'olympic\': True, \'team\': False}, 2',1,'[{\"added\": {}}]',13,1),(41,'2023-05-19 12:37:54.938000','3','Шахматы Шахматы {\'id\': 1, \'olympic\': True, \'team\': False}, 2, {\'id\': 1, \'olympic\': True, \'team\': True}, 3',1,'[{\"added\": {}}]',7,1),(42,'2023-05-19 12:38:12.581000','4','4 Чемпионат России 2022, Шахматы',1,'[{\"added\": {}}]',16,1),(43,'2023-05-21 14:26:37.732000','15','15 Кубок России по скалолазанию, Оля',3,'',25,1),(44,'2023-05-21 22:24:46.393000','22','Чемпионат мира по Керлингу На данном событии соберутся все сильнейшие атлеты данного вида спорта., 2023-05-13 12:00:00, 22',2,'[{\"changed\": {\"fields\": [\"Name\", \"Description\", \"Event start date\"]}}]',8,1),(45,'2023-05-21 22:25:34.956000','22','Чемпионат мира по Керлингу На данном событии соберутся все сильнейшие атлеты данного вида спорта., 2023-05-13 12:00:00, 22',2,'[{\"changed\": {\"fields\": [\"Event end date\", \"Image\"]}}]',8,1),(46,'2023-05-21 22:28:32.028000','21','Открытая тренировка по ниндзя-спорту Вход свободный, приходите кто хочет!, 2023-05-11 12:00:00, 21',2,'[{\"changed\": {\"fields\": [\"Name\", \"Description\", \"Event start date\", \"Event end date\", \"Image\"]}}]',8,1),(47,'2023-05-21 22:29:10.088000','22','Чемпионат мира по Керлингу На данном событии соберутся все сильнейшие атлеты данного вида спорта., 2023-05-13 12:00:00, 22',2,'[{\"changed\": {\"fields\": [\"Event end date\"]}}]',8,1),(48,'2023-05-21 22:31:39.828000','20','Первенство спортивной школы №11 Первенство СШОР по скалолазанию в дисциплине трудность, 2023-05-14 12:00:00, 20',2,'[{\"changed\": {\"fields\": [\"Name\", \"Description\", \"Event end date\", \"Image\"]}}]',8,1),(49,'2023-05-24 14:28:37.831000','24','Кубок России по скалолазанию В Тюмени пройдет первый старт в году - кубок России., 2023-05-15 12:00:00, 24',2,'[{\"changed\": {\"fields\": [\"Genders\"]}}]',8,1),(50,'2023-05-24 15:46:11.897000','1','Уфа , 1',1,'[{\"added\": {}}]',31,1),(51,'2023-05-24 15:46:16.782000','2','Москва , 2',1,'[{\"added\": {}}]',31,1),(52,'2023-05-24 15:46:24.987000','3','Екатеринбург , 3',1,'[{\"added\": {}}]',31,1),(53,'2023-05-24 15:46:42.154000','24','Кубок России по скалолазанию В Тюмени пройдет первый старт в году - кубок России., 2023-05-15 12:00:00, 24',2,'[{\"changed\": {\"fields\": [\"City\", \"Sport\"]}}]',8,1),(54,'2023-05-24 15:46:51.976000','4','Сыктывкар , 4',1,'[{\"added\": {}}]',31,1),(55,'2023-05-24 15:46:55.874000','5','Тюмень , 5',1,'[{\"added\": {}}]',31,1),(56,'2023-05-24 15:47:13.418000','6','Санкт-Петербург , 6',1,'[{\"added\": {}}]',31,1),(57,'2023-05-24 15:47:25.395000','24','Кубок России по скалолазанию В Тюмени пройдет первый старт в году - кубок России., 2023-05-15 12:00:00, 24',2,'[{\"changed\": {\"fields\": [\"City\"]}}]',8,1),(58,'2023-05-24 15:47:47.610000','1','{\'id\': 1, \'olympic\': True, \'team\': True}',2,'[{\"changed\": {\"fields\": [\"Team\"]}}]',12,1),(59,'2023-05-24 15:48:00.503000','3','{\'id\': 3, \'olympic\': False, \'team\': False}',1,'[{\"added\": {}}]',12,1),(60,'2023-05-24 15:48:03.658000','3','Ниндзя-спорт {\'id\': 3, \'olympic\': False, \'team\': False}, 3',1,'[{\"added\": {}}]',13,1),(61,'2023-05-24 15:48:18.608000','4','Бокс {\'id\': 2, \'olympic\': True, \'team\': False}, 4',1,'[{\"added\": {}}]',13,1),(62,'2023-05-24 15:48:34.530000','5','Керлинг {\'id\': 1, \'olympic\': True, \'team\': True}, 5',1,'[{\"added\": {}}]',13,1),(63,'2023-05-24 15:48:44.282000','23','Чемпионат России 2022 Чемпионат России по шахматам, 2022-09-13 12:00:00, 23',2,'[{\"changed\": {\"fields\": [\"Sport\"]}}]',8,1),(64,'2023-05-24 15:48:50.254000','22','Чемпионат мира по Керлингу На данном событии соберутся все сильнейшие атлеты данного вида спорта., 2023-05-13 12:00:00, 22',2,'[{\"changed\": {\"fields\": [\"Sport\"]}}]',8,1),(65,'2023-05-24 15:48:55.758000','21','Открытая тренировка по ниндзя-спорту Вход свободный, приходите кто хочет!, 2023-05-11 12:00:00, 21',2,'[{\"changed\": {\"fields\": [\"Sport\"]}}]',8,1),(66,'2023-05-24 15:49:00.407000','20','Первенство спортивной школы №11 Первенство СШОР по скалолазанию в дисциплине трудность, 2023-05-14 12:00:00, 20',2,'[{\"changed\": {\"fields\": [\"Sport\"]}}]',8,1),(67,'2023-05-24 15:49:04.264000','13','Чемпионат России по скалолазанию11111111 Первый в истории чемпионат мира, 2023-05-24 10:35:52, 13',2,'[{\"changed\": {\"fields\": [\"Sport\"]}}]',8,1),(68,'2023-05-24 15:49:11.295000','9','Атао Лалп, 2023-05-11 12:00:00, 9',2,'[{\"changed\": {\"fields\": [\"City\", \"Sport\"]}}]',8,1),(69,'2023-05-24 15:49:19.592000','23','Чемпионат России 2022 Чемпионат России по шахматам, 2022-09-13 12:00:00, 23',2,'[{\"changed\": {\"fields\": [\"City\"]}}]',8,1),(70,'2023-05-24 15:49:24.360000','21','Открытая тренировка по ниндзя-спорту Вход свободный, приходите кто хочет!, 2023-05-11 12:00:00, 21',2,'[{\"changed\": {\"fields\": [\"City\"]}}]',8,1),(71,'2023-05-24 15:49:28.282000','20','Первенство спортивной школы №11 Первенство СШОР по скалолазанию в дисциплине трудность, 2023-05-14 12:00:00, 20',2,'[{\"changed\": {\"fields\": [\"City\"]}}]',8,1),(72,'2023-05-24 15:49:37.829000','13','Чемпионат России по скалолазанию11111111 Первый в истории чемпионат мира, 2023-05-24 10:35:52, 13',2,'[{\"changed\": {\"fields\": [\"City\"]}}]',8,1),(73,'2023-05-24 15:49:59.349000','2','Чемпионат России по скалолазанию Первый в истории чемпионат мира, 2023-05-24 10:35:52, 2',2,'[{\"changed\": {\"fields\": [\"City\", \"Sport\"]}}]',8,1),(74,'2023-05-24 18:00:41.431000','25','Алал Аьаь, 2023-05-24 12:00:00, 25',3,'',8,1),(75,'2023-05-24 18:00:46.873000','26','Аддаалплп Ьала, 2023-05-24 12:00:00, 26',3,'',8,1),(76,'2023-05-24 18:00:55.531000','24','Кубок России по скалолазанию В Тюмени пройдет первый старт в году - кубок России., 2023-05-15 12:00:00, 24',2,'[{\"changed\": {\"fields\": [\"Genders\"]}}]',8,1),(77,'2023-05-24 20:39:51.284000','19','Шцшц Алал, 2023-05-14 12:00:00, 19',3,'',8,1),(78,'2023-05-24 20:39:56.036000','18','По Мм, 2023-05-14 12:00:00, 18',3,'',8,1),(79,'2023-05-24 20:39:59.848000','17','Поп Рор, 2023-05-14 12:00:00, 17',3,'',8,1),(80,'2023-05-24 20:40:03.481000','16','Вдвж Влад, 2023-05-14 12:00:00, 16',3,'',8,1),(81,'2023-05-24 20:40:06.864000','15','Двщв Кдда, 2023-05-14 12:00:00, 15',3,'',8,1),(82,'2023-05-24 20:40:10.882000','14','Двщв Кдда, 2023-05-14 12:00:00, 14',3,'',8,1),(83,'2023-05-24 20:40:21.169000','12','Чемпионат России по скалолазанию11111111 Первый в истории чемпионат мира, 2023-05-24 10:35:52, 12',3,'',8,1),(84,'2023-05-24 20:40:26.362000','11','Дуад Адад, 2023-05-14 12:00:00, 11',3,'',8,1),(85,'2023-05-24 20:40:31.677000','10','Лвплпаоа Аьалал, 2023-05-11 12:00:00, 10',3,'',8,1),(86,'2023-05-24 20:40:43.276000','8','Gyy Hh, 2023-05-06 12:00:00, 8',3,'',8,1),(87,'2023-05-24 20:40:50.574000','7','Gyy Hh, 2023-05-06 12:00:00, 7',3,'',8,1),(88,'2023-05-24 20:40:55.662000','6','Hrdjfh Djdj, 2023-05-11 12:00:00, 6',3,'',8,1),(89,'2023-05-24 20:40:59.720000','5','Ахахпх Шуалал, 2023-05-08 12:00:00, 5',3,'',8,1),(90,'2023-05-24 20:41:02.867000','4','Чемпионат России по скалолазанию11111111 Первый в истории чемпионат мира, 2023-05-24 10:35:52, 4',3,'',8,1),(91,'2023-05-24 20:41:08.020000','3','Чемпионат России по скалолазанию11111111 Первый в истории чемпионат мира, 2023-05-24 10:35:52, 3',3,'',8,1),(92,'2023-05-24 20:41:55.668000','22','Чемпионат мира по Керлингу На данном событии соберутся все сильнейшие атлеты данного вида спорта., 2023-05-13 12:00:00, 22',2,'[{\"changed\": {\"fields\": [\"City\"]}}]',8,1),(93,'2023-05-24 21:06:35.860000','20','Первенство спортивной школы №11 Первенство СШОР по скалолазанию в дисциплине трудность, 2023-05-14 12:00:00, 20',2,'[{\"changed\": {\"fields\": [\"Genders\"]}}]',8,1),(94,'2023-05-24 21:06:42.953000','13','Чемпионат России по скалолазанию11111111 Первый в истории чемпионат мира, 2023-05-24 10:35:52, 13',2,'[{\"changed\": {\"fields\": [\"Genders\"]}}]',8,1),(95,'2023-05-24 21:06:55.656000','2','Чемпионат России по скалолазанию Первый в истории чемпионат мира, 2023-05-24 10:35:52, 2',2,'[{\"changed\": {\"fields\": [\"Genders\"]}}]',8,1),(96,'2023-05-24 21:14:18.143000','2','Чемпионат России по скалолазанию Первый в истории чемпионат мира ШАХМАТЫ, 2023-05-24 10:35:52, 2',2,'[{\"changed\": {\"fields\": [\"Description\"]}}]',8,1),(97,'2023-05-25 22:24:00.375000','16','16 Первенство спортивной школы №11, Эд',3,'',25,1),(98,'2023-05-25 23:14:35.225966','14','14 Чемпионат России по скалолазанию, Оля',1,'[{\"added\": {}}]',25,1),(99,'2023-05-25 23:15:34.438590','14','14 Чемпионат России по скалолазанию, Оля',3,'',25,1);
/*!40000 ALTER TABLE `django_admin_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_content_type`
--

DROP TABLE IF EXISTS `django_content_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_content_type`
--

LOCK TABLES `django_content_type` WRITE;
/*!40000 ALTER TABLE `django_content_type` DISABLE KEYS */;
INSERT INTO `django_content_type` VALUES (1,'admin','logentry'),(3,'auth','group'),(2,'auth','permission'),(4,'contenttypes','contenttype'),(25,'organizationapp','application'),(31,'organizationapp','city'),(7,'organizationapp','discipline'),(16,'organizationapp','disciplineforevent'),(8,'organizationapp','event'),(28,'organizationapp','files'),(29,'organizationapp','filesforevent'),(30,'organizationapp','invite'),(17,'organizationapp','judge'),(22,'organizationapp','judgeforevent'),(9,'organizationapp','organizer'),(15,'organizationapp','organizerforevent'),(18,'organizationapp','participant'),(21,'organizationapp','participantforevent'),(14,'organizationapp','profile'),(23,'organizationapp','sponsor'),(24,'organizationapp','sponsorforevent'),(13,'organizationapp','sport'),(10,'organizationapp','typediscipline'),(11,'organizationapp','typeevent'),(12,'organizationapp','typesport'),(6,'organizationapp','user'),(26,'organizationapp','viewer'),(27,'organizationapp','viewerforevent'),(19,'organizationapp','worker'),(20,'organizationapp','workerforevent'),(5,'sessions','session');
/*!40000 ALTER TABLE `django_content_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_migrations`
--

DROP TABLE IF EXISTS `django_migrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_migrations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_migrations`
--

LOCK TABLES `django_migrations` WRITE;
/*!40000 ALTER TABLE `django_migrations` DISABLE KEYS */;
INSERT INTO `django_migrations` VALUES (1,'contenttypes','0001_initial','2023-05-25 22:56:48.641927'),(2,'contenttypes','0002_remove_content_type_name','2023-05-25 22:56:48.699924'),(3,'auth','0001_initial','2023-05-25 22:56:49.033594'),(4,'auth','0002_alter_permission_name_max_length','2023-05-25 22:56:49.096047'),(5,'auth','0003_alter_user_email_max_length','2023-05-25 22:56:49.103855'),(6,'auth','0004_alter_user_username_opts','2023-05-25 22:56:49.114591'),(7,'auth','0005_alter_user_last_login_null','2023-05-25 22:56:49.124353'),(8,'auth','0006_require_contenttypes_0002','2023-05-25 22:56:49.129235'),(9,'auth','0007_alter_validators_add_error_messages','2023-05-25 22:56:49.138016'),(10,'auth','0008_alter_user_username_max_length','2023-05-25 22:56:49.146842'),(11,'auth','0009_alter_user_last_name_max_length','2023-05-25 22:56:49.155586'),(12,'auth','0010_alter_group_name_max_length','2023-05-25 22:56:49.226879'),(13,'auth','0011_update_proxy_permissions','2023-05-25 22:56:49.237567'),(14,'auth','0012_alter_user_first_name_max_length','2023-05-25 22:56:49.246867'),(15,'organizationapp','0001_initial','2023-05-25 22:56:50.391294'),(16,'admin','0001_initial','2023-05-25 22:56:50.549078'),(17,'admin','0002_logentry_remove_auto_add','2023-05-25 22:56:50.560792'),(18,'admin','0003_logentry_add_action_flag_choices','2023-05-25 22:56:50.572508'),(19,'organizationapp','0002_judge_participant_worker_remove_organizer_age_and_more','2023-05-25 22:56:51.435171'),(20,'organizationapp','0003_alter_user_email','2023-05-25 22:56:51.466497'),(21,'organizationapp','0004_sponsor_event_if_active_sponsorforevent_application','2023-05-25 22:56:51.961526'),(22,'organizationapp','0005_rename_if_active_event_is_active_and_more','2023-05-25 22:56:52.025620'),(23,'organizationapp','0006_alter_event_results','2023-05-25 22:56:52.036312'),(24,'organizationapp','0007_alter_event_results','2023-05-25 22:56:52.047090'),(25,'organizationapp','0008_alter_judgeforevent_unique_together_and_more','2023-05-25 22:56:52.177619'),(26,'organizationapp','0009_viewer_viewerforevent','2023-05-25 22:56:52.476724'),(27,'organizationapp','0010_files_filesforevent','2023-05-25 22:56:52.655242'),(28,'organizationapp','0011_alter_application_description_and_more','2023-05-25 22:56:52.706278'),(29,'organizationapp','0012_invite','2023-05-25 22:56:52.885741'),(30,'organizationapp','0013_city_event_sport_event_city','2023-05-25 22:56:53.089162'),(31,'sessions','0001_initial','2023-05-25 22:56:53.132136');
/*!40000 ALTER TABLE `django_migrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_session`
--

DROP TABLE IF EXISTS `django_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_session`
--

LOCK TABLES `django_session` WRITE;
/*!40000 ALTER TABLE `django_session` DISABLE KEYS */;
INSERT INTO `django_session` VALUES ('g3b5qo5vch5ubag2sa03v09qluz2kj63','.eJxVi0EOwiAQRe_C2jSQzkBxpxchwwBCTGhT2k2NdxeTLnT5_3vvJRztW3Z7i6srQVyFEpffzxM_Y_2CeX1QLQdtZa60LMNJ2nDr8v3U_tpMLfdQc9LoE1uEiMoia8kQ2BvC0HeSUwCQkzYIFlWKnEZgZZQMkGSUo3h_AN6cNi8:1pwlM5:Qf-Xlv-xNX12LO39Gz8J_-uR9IBMYOnf04aMdgsg4DY','2023-05-24 20:03:49.500000'),('lk42txoqeu160zvj4zuddjy3fib2nj6b','.eJxVi0EOwiAQRe_C2jSQzkBxpxchwwBCTGhT2k2NdxeTLnT5_3vvJRztW3Z7i6srQVyFEpffzxM_Y_2CeX1QLQdtZa60LMNJ2nDr8v3U_tpMLfdQc9LoE1uEiMoia8kQ2BvC0HeSUwCQkzYIFlWKnEZgZZQMkGSUo3h_AN6cNi8:1pu6c5:acVRTVnm1nltOH0ojYYZ4dTcQxfb8JFED3mmk0s-04w','2023-05-17 12:09:21.975000'),('y0ww0p581xhqy2sujx3rk1934txwazvy','.eJxVi0EOwiAQRe_C2jSQzkBxpxchwwBCTGhT2k2NdxeTLnT5_3vvJRztW3Z7i6srQVyFEpffzxM_Y_2CeX1QLQdtZa60LMNJ2nDr8v3U_tpMLfdQc9LoE1uEiMoia8kQ2BvC0HeSUwCQkzYIFlWKnEZgZZQMkGSUo3h_AN6cNi8:1q1qZQ:xhvev3tyV_l_sncIh5cZ68D4xXH-V4DzZhwuHu4HZ2Y','2023-06-07 20:38:36.486000');
/*!40000 ALTER TABLE `django_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizationapp_user`
--

DROP TABLE IF EXISTS `organizationapp_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organizationapp_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(254) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_verified` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `organizationapp_user_username_email_6c154e28_uniq` (`username`,`email`),
  UNIQUE KEY `organizationapp_user_email_ac03125f_uniq` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizationapp_user`
--

LOCK TABLES `organizationapp_user` WRITE;
/*!40000 ALTER TABLE `organizationapp_user` DISABLE KEYS */;
INSERT INTO `organizationapp_user` VALUES (1,'pbkdf2_sha256$390000$gYGio6fTGOu7bssEH4S9jw$PJ1sOsyyU0XePtVelJLBYIG24UuXDjoRZWQdCSSbh4Q=','2023-05-24 20:38:36.474000',1,'Edik','','2023-05-03 12:00:58.361000',1,1,0),(2,'pbkdf2_sha256$390000$RIz2ixflnVr55Vt0uuOUel$6OrUWD5nqPdFfOjxS/jvmQ8ty7nn4Xef6i7KRbVi9JA=',NULL,0,'Eduard','daukaev.eduard@mail.ru','2023-05-03 12:19:23.061000',1,0,0),(3,'pbkdf2_sha256$390000$WUqQvMINibHkgL8SNFPAvv$5GUHSXWrU3mff5VIARFRmw/uH5s6mMFJuUswBauiOOo=',NULL,0,'Eduk','daukaev.eduarikd@mail.ru','2023-05-07 16:50:54.238000',1,0,0),(4,'pbkdf2_sha256$390000$DNS7HLjEhFauOHyekpa6vV$SkElukWeBrAMvYPNLnsgQY6Uh/k2sxT8LEIPplueuUc=',NULL,0,'Olya','eduarddaukaev@gmail.com','2023-05-09 17:58:28.744000',1,0,0),(5,'pbkdf2_sha256$390000$7557d68HYU38ZIvWGQ5qPw$aXDO1wUdydkVrzAFKZpsj6KcX87PbldRN7v8QhxGMfs=',NULL,0,'Olya1','eduarddaukaev1@gmail.com','2023-05-09 18:15:25.130000',1,0,0),(6,'pbkdf2_sha256$390000$kQzFnBsXCWKCTZlqYqvzVx$8NHqgXyvZFUN0YMwMI/SBiZcBIj89fH8zUiNGzlerrI=',NULL,0,'Olya12','eduarddaukaev22@gmail.com','2023-05-13 22:28:46.741000',0,0,0),(7,'pbkdf2_sha256$390000$ANoOkO56YvkdKZhoRVILLV$0YiC4Ht50Bm4rRRONZWcwYcqcQoRMs0dJoGeRn+GgLA=',NULL,0,'Olya1235','khavan11114782@gmail.com','2023-05-13 22:42:31.079000',0,0,0),(8,'pbkdf2_sha256$390000$3fDn8LuRr1nfDaLGRwFC5E$LqI3PzmgFP8BT0y4iFEk1MWLAL7SLiyypLTuUcrSOzU=',NULL,0,'Olya1009','khavanova220420011341@gmail.com','2023-05-13 22:45:40.987000',0,0,0),(9,'pbkdf2_sha256$390000$SOu6F19PGiSP8ziQ7PpdcU$WKE/iJB/HMydrroPS3N0tOvYikPTE5izCJNFt4oLnrA=',NULL,0,'Olya97','khav12anova22042001@gmail.com','2023-05-13 22:49:10.177000',0,0,0),(10,'pbkdf2_sha256$390000$wdEWMjzVGJpfTq2dcibBqp$Kh0J2DYwTAQ+/Eb0Uglvk4wzMVyZeO3BYTydiWBdwcc=',NULL,0,'Olya99','khavanova220420010001@gmail.com','2023-05-13 23:02:03.887000',0,0,0),(11,'pbkdf2_sha256$390000$OFZ0eg8lWjOwXoPw4ZQxPn$dhC+7qwjvuQIuXPJbxH1+wzU1y9NtF1oxUJV8zAgli4=',NULL,0,'Ol','kh1avanova22042001@gmail.com','2023-05-13 23:08:53.537000',0,0,0),(12,'pbkdf2_sha256$390000$qcAH4R6cAQEppBCXhP7edB$8MEVN0j954hF3kt93ghQ/vSqly30Jny7sg81Eu/yZ9g=',NULL,0,'Oaaa','khavanova2322042001@gmail.com','2023-05-13 23:12:18.463000',0,0,0),(13,'pbkdf2_sha256$390000$JDYdRyZ19M3sOmmPHpuI2P$CcLoua1u+hqVjwcEcBC+TF8pjEi6zzvnbJtj1/k61vA=',NULL,0,'Olyakkk','khavanova22042001111@gmail.com','2023-05-13 23:18:01.753000',0,0,0),(14,'pbkdf2_sha256$390000$HfUSqB7axoCRo85FRbp81a$irziQhP2Suy88GLSyBQddfot6JAw+bhXCKg6tpHdbew=',NULL,0,'Olyajj','khavanova22042001001@gmail.com','2023-05-13 23:23:28.648000',0,0,0),(15,'pbkdf2_sha256$390000$gZEP8RInDtx6kPsUh0rqyg$oXrC+7ytLEguDVWxzFcb7OUNqVuPhKMuNwBPHM/RW0Q=',NULL,0,'Olyammgg','khagffubvanova22042001@gmail.com','2023-05-13 23:25:00.286000',0,0,0),(16,'pbkdf2_sha256$390000$P48CAhqAOcA60xuPsEDdxQ$p2nH5qvTJsJPKDGKWrvgPbhIf7R6QNCc6mX87ZZlk8g=',NULL,0,'Olyammggu','khagffubvanova2204200hgg1@gmail.com','2023-05-13 23:29:13.067000',0,0,0),(17,'pbkdf2_sha256$390000$crBShdQabVexmsQeC6mECw$H60yC7fFHO3MRu567v3ZqlI3HSjQi6L62u3W5pLCq/w=',NULL,0,'Olyadhdudi','khahdjjfvanova22042001@gmail.com','2023-05-13 23:32:12.774000',0,0,0),(18,'pbkdf2_sha256$390000$RKCTmFdYgfk7BdoG9n6Ews$DY5eeeWvF7xsIsPvvYAHrg9lfxKIPdZ9+bVgpZ0w+PA=',NULL,0,'Grifon','daukaevDiplom@mail.ru','2023-05-13 23:39:07.846000',0,0,0),(19,'pbkdf2_sha256$390000$QozTJnjPOiCN6XSFlaXuoe$S0ky5Lib1oyFkE65T7cBeWpJRQU2rLRj6b7+rS1kLT4=',NULL,0,'Grifonka','daukaevDiplom@gmail.com','2023-05-13 23:40:37.637000',1,0,0);
/*!40000 ALTER TABLE `organizationapp_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizationapp_user_groups`
--

DROP TABLE IF EXISTS `organizationapp_user_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organizationapp_user_groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `organizationapp_user_groups_user_id_group_id_8bd3b82c_uniq` (`user_id`,`group_id`),
  KEY `organizationapp_user_groups_group_id_85c05724_fk_auth_group_id` (`group_id`),
  CONSTRAINT `organizationapp_user_groups_group_id_85c05724_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `organizationapp_user_user_id_7d0146a7_fk_organizat` FOREIGN KEY (`user_id`) REFERENCES `organizationapp_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizationapp_user_groups`
--

LOCK TABLES `organizationapp_user_groups` WRITE;
/*!40000 ALTER TABLE `organizationapp_user_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `organizationapp_user_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organizationapp_user_user_permissions`
--

DROP TABLE IF EXISTS `organizationapp_user_user_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organizationapp_user_user_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `organizationapp_user_use_user_id_permission_id_9659b43c_uniq` (`user_id`,`permission_id`),
  KEY `organizationapp_user_permission_id_69e9c8e1_fk_auth_perm` (`permission_id`),
  CONSTRAINT `organizationapp_user_permission_id_69e9c8e1_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `organizationapp_user_user_id_d5a4f796_fk_organizat` FOREIGN KEY (`user_id`) REFERENCES `organizationapp_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organizationapp_user_user_permissions`
--

LOCK TABLES `organizationapp_user_user_permissions` WRITE;
/*!40000 ALTER TABLE `organizationapp_user_user_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `organizationapp_user_user_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(500) NOT NULL,
  `second_name` varchar(500) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `age` int(11) NOT NULL,
  `city` varchar(500) NOT NULL,
  `phone_number` varchar(12) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `profile_user_id_2aeb6f6b_fk_organizationapp_user_id` FOREIGN KEY (`user_id`) REFERENCES `organizationapp_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (1,'Edik','Dauk','Мужской',22,'Уфа','+79173720372',2),(2,'Edik','Dauk','Мужской',22,'Уфа','+79173720372',3),(3,'Оля','Хаванова','Мужской',22,'Уфа','+79173720372',4),(4,'Оля','Хаванова','Мужской',22,'Уфа','+79173720372',5),(5,'Edik','Dauk','Мужской',22,'Уфа','+79173720372',6),(6,'Оля','Хаванова','Мужской',22,'Уфа','+79173720372',7),(7,'Оля','Хаванова','Мужской',22,'Уфа','+79173720372',8),(8,'Оля','Хаванова','Мужской',22,'Уфа','+79173720372',9),(9,'Оля','Хаванова','Мужской',22,'Уфа','+79173720372',10),(10,'Оля','Хаванова','Мужской',22,'Уфа','+79173720372',11),(11,'Оля','Хаванова','Мужской',22,'Уфа','+79173720372',12),(12,'Оля','Хаванова','Мужской',22,'Уфа','+79173720372',13),(13,'Оля','Хаванова','Мужской',22,'Уфа','+79173720372',14),(14,'Оля','Хаванова','Мужской',22,'Уфа','+79173720372',15),(15,'Оля','Хаванова','Мужской',22,'Уфа','+79173720372',16),(16,'Оля','Хаванова','Мужской',22,'Уфа','+79173720372',17),(17,'Эди','Даукаев','Мужской',21,'Уфа','+79173720372',18),(18,'Эд','Даукаев','Мужской',21,'Уфа','+79173720372',19);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'organizationdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-25 23:17:05
