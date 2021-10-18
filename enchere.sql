CREATE DATABASE  IF NOT EXISTS `enchere` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `enchere`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: enchere
-- ------------------------------------------------------
-- Server version	5.7.35-log

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
-- Table structure for table `achatimm`
--

DROP TABLE IF EXISTS `achatimm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `achatimm` (
  `idmembre` int(11) NOT NULL,
  `idarticle` int(11) NOT NULL,
  `dateAchat` datetime DEFAULT NULL,
  PRIMARY KEY (`idmembre`,`idarticle`),
  KEY `FK_achatimm_article_idx` (`idarticle`),
  CONSTRAINT `FK_achatimm_article` FOREIGN KEY (`idarticle`) REFERENCES `article` (`idarticle`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_achatimm_membre` FOREIGN KEY (`idmembre`) REFERENCES `membre` (`idmembre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achatimm`
--

LOCK TABLES `achatimm` WRITE;
/*!40000 ALTER TABLE `achatimm` DISABLE KEYS */;
/*!40000 ALTER TABLE `achatimm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `idarticle` int(11) NOT NULL AUTO_INCREMENT,
  `titreAnnonce` varchar(45) DEFAULT NULL,
  `descAnnonce` varchar(45) DEFAULT NULL,
  `prixAchatImme` decimal(19,4) DEFAULT NULL,
  `prixDep` decimal(19,4) DEFAULT NULL,
  `prixReserve` decimal(19,4) DEFAULT NULL,
  `fraisPort` decimal(19,4) DEFAULT NULL,
  `dateCloture` datetime DEFAULT NULL,
  `pasEnchere` decimal(19,4) DEFAULT NULL,
  `regionLiv` varchar(45) DEFAULT NULL,
  `categorie` varchar(45) DEFAULT NULL,
  `sousCat` varchar(45) DEFAULT NULL,
  `sousSousCat` varchar(45) DEFAULT NULL,
  `idvendeur` int(11) DEFAULT NULL,
  PRIMARY KEY (`idarticle`),
  KEY `FK_article_vendeur_idx` (`idvendeur`),
  CONSTRAINT `FK_article_vendeur` FOREIGN KEY (`idvendeur`) REFERENCES `membre` (`idmembre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avisarticle`
--

DROP TABLE IF EXISTS `avisarticle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avisarticle` (
  `idarticle` int(11) NOT NULL,
  `idmembre` int(11) NOT NULL,
  `avis` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idarticle`,`idmembre`),
  KEY `FK_avisarticle_membre_idx` (`idmembre`),
  CONSTRAINT `FK_avisarticle_article` FOREIGN KEY (`idarticle`) REFERENCES `article` (`idarticle`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_avisarticle_membre` FOREIGN KEY (`idmembre`) REFERENCES `membre` (`idmembre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avisarticle`
--

LOCK TABLES `avisarticle` WRITE;
/*!40000 ALTER TABLE `avisarticle` DISABLE KEYS */;
/*!40000 ALTER TABLE `avisarticle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avismembre`
--

DROP TABLE IF EXISTS `avismembre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avismembre` (
  `idmembreEmis` int(11) NOT NULL,
  `idmembreRecu` int(11) NOT NULL,
  `avis` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idmembreEmis`,`idmembreRecu`),
  KEY `FK_avismembre_recu_idx` (`idmembreRecu`),
  CONSTRAINT `FK_avismembre_emis` FOREIGN KEY (`idmembreEmis`) REFERENCES `membre` (`idmembre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_avismembre_recu` FOREIGN KEY (`idmembreRecu`) REFERENCES `membre` (`idmembre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avismembre`
--

LOCK TABLES `avismembre` WRITE;
/*!40000 ALTER TABLE `avismembre` DISABLE KEYS */;
/*!40000 ALTER TABLE `avismembre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `choisir`
--

DROP TABLE IF EXISTS `choisir`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `choisir` (
  `idprestation` int(11) NOT NULL,
  `idmembre` int(11) NOT NULL,
  PRIMARY KEY (`idprestation`,`idmembre`),
  KEY `FK_choisir_membre_idx` (`idmembre`),
  CONSTRAINT `FK_choisir_membre` FOREIGN KEY (`idmembre`) REFERENCES `membre` (`idmembre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_choisir_prestation` FOREIGN KEY (`idprestation`) REFERENCES `prestation` (`idPRESTATION`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choisir`
--

LOCK TABLES `choisir` WRITE;
/*!40000 ALTER TABLE `choisir` DISABLE KEYS */;
/*!40000 ALTER TABLE `choisir` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `encherir`
--

DROP TABLE IF EXISTS `encherir`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `encherir` (
  `idmembre` int(11) NOT NULL,
  `idarticle` int(11) NOT NULL,
  `nombrePas` int(11) DEFAULT NULL,
  `ifGagnant` int(11) DEFAULT NULL,
  `prixPropose` decimal(19,4) DEFAULT NULL,
  PRIMARY KEY (`idmembre`,`idarticle`),
  KEY `FK_encherir_article_idx` (`idarticle`),
  CONSTRAINT `FK_encherir_article` FOREIGN KEY (`idarticle`) REFERENCES `article` (`idarticle`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_encherir_membre` FOREIGN KEY (`idmembre`) REFERENCES `membre` (`idmembre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encherir`
--

LOCK TABLES `encherir` WRITE;
/*!40000 ALTER TABLE `encherir` DISABLE KEYS */;
/*!40000 ALTER TABLE `encherir` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `idimage` int(11) NOT NULL AUTO_INCREMENT,
  `repertoire` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idimage`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membre`
--

DROP TABLE IF EXISTS `membre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membre` (
  `idmembre` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `dateNais` datetime DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `adressePostal` varchar(45) DEFAULT NULL,
  `CP` varchar(45) DEFAULT NULL,
  `ville` varchar(45) DEFAULT NULL,
  `pays` varchar(45) DEFAULT NULL,
  `MotDePass` varchar(45) DEFAULT NULL,
  `Statut` varchar(45) DEFAULT NULL,
  `NbEtoile` int(11) DEFAULT NULL,
  PRIMARY KEY (`idmembre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membre`
--

LOCK TABLES `membre` WRITE;
/*!40000 ALTER TABLE `membre` DISABLE KEYS */;
/*!40000 ALTER TABLE `membre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posseder`
--

DROP TABLE IF EXISTS `posseder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posseder` (
  `idimage` int(11) NOT NULL,
  `idarticle` int(11) NOT NULL,
  PRIMARY KEY (`idimage`,`idarticle`),
  KEY `FK_posseder_article_idx` (`idarticle`),
  CONSTRAINT `FK_posseder_article` FOREIGN KEY (`idarticle`) REFERENCES `article` (`idarticle`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_posseder_image` FOREIGN KEY (`idimage`) REFERENCES `image` (`idimage`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posseder`
--

LOCK TABLES `posseder` WRITE;
/*!40000 ALTER TABLE `posseder` DISABLE KEYS */;
/*!40000 ALTER TABLE `posseder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestation`
--

DROP TABLE IF EXISTS `prestation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestation` (
  `idPRESTATION` int(11) NOT NULL AUTO_INCREMENT,
  `LibellePres` varchar(45) DEFAULT NULL,
  `PrixCat` decimal(19,4) DEFAULT NULL,
  `PrixGold` decimal(19,4) DEFAULT NULL,
  PRIMARY KEY (`idPRESTATION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestation`
--

LOCK TABLES `prestation` WRITE;
/*!40000 ALTER TABLE `prestation` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-18 15:36:07
