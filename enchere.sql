CREATE DATABASE  IF NOT EXISTS `enchere` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `enchere`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: enchere
-- ------------------------------------------------------
-- Server version	5.7.34

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
-- Table structure for table `Categorie`
--

DROP TABLE IF EXISTS `Categorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Categorie` (
  `idCategorie` int(11) NOT NULL AUTO_INCREMENT,
  `libelleCat` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCategorie`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categorie`
--

LOCK TABLES `Categorie` WRITE;
/*!40000 ALTER TABLE `Categorie` DISABLE KEYS */;
INSERT INTO `Categorie` VALUES (1,'Multimédia'),(2,'Electroménager'),(6,'Télévision'),(7,'Vidéo'),(10,'Téléviseur 4/3'),(11,'Téléviseurs 16/9'),(12,'Téléviseurs LCD'),(13,'Lavage'),(14,'Froid'),(15,'Lave-linge'),(16,'Sèche-linge'),(17,'Réfrigérateur'),(18,'Congélateur'),(19,'Machine à glaçons');
/*!40000 ALTER TABLE `Categorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `achatimmediat`
--

DROP TABLE IF EXISTS `achatimmediat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `achatimmediat` (
  `idMembre` int(11) NOT NULL,
  `idArticle` int(11) NOT NULL,
  `dateAchat` datetime DEFAULT NULL,
  PRIMARY KEY (`idMembre`,`idArticle`),
  KEY `FK_achatImmediat_article_idx` (`idArticle`),
  CONSTRAINT `FK_achatImmediat_article` FOREIGN KEY (`idArticle`) REFERENCES `article` (`idarticle`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_achatImmediat_membre` FOREIGN KEY (`idMembre`) REFERENCES `membre` (`idmembre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achatimmediat`
--

LOCK TABLES `achatimmediat` WRITE;
/*!40000 ALTER TABLE `achatimmediat` DISABLE KEYS */;
INSERT INTO `achatimmediat` VALUES (1,5,'2021-10-21 00:00:00');
/*!40000 ALTER TABLE `achatimmediat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrateur`
--

DROP TABLE IF EXISTS `administrateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrateur` (
  `idadministrateur` int(11) NOT NULL,
  `login` varchar(45) DEFAULT NULL,
  `motDePasse` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idadministrateur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrateur`
--

LOCK TABLES `administrateur` WRITE;
/*!40000 ALTER TABLE `administrateur` DISABLE KEYS */;
/*!40000 ALTER TABLE `administrateur` ENABLE KEYS */;
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
  `idvendeur` int(11) DEFAULT NULL,
  `idRegionLiv` int(11) DEFAULT NULL,
  `idCategorie` int(11) DEFAULT NULL,
  `idSousCategorie` int(11) DEFAULT NULL,
  `etat` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idarticle`),
  KEY `FK_article_vendeur_idx` (`idvendeur`),
  KEY `FK_article_cat_idx` (`idCategorie`),
  KEY `FK_article_sousCat_idx` (`idSousCategorie`),
  KEY `FK_article_regionLiv_idx` (`idRegionLiv`),
  CONSTRAINT `FK_article_cat` FOREIGN KEY (`idCategorie`) REFERENCES `Categorie` (`idCategorie`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_article_regionLiv` FOREIGN KEY (`idRegionLiv`) REFERENCES `regionlivraison` (`idregionlivraison`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_article_sousCat` FOREIGN KEY (`idSousCategorie`) REFERENCES `Categorie` (`idCategorie`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_article_vendeur` FOREIGN KEY (`idvendeur`) REFERENCES `membre` (`idmembre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (2,'Test','This is a test TV',150.0000,100.0000,110.0000,4.0000,'2021-10-23 00:00:00',10.0000,1,5,1,6,'Cree'),(4,'Test2','Test with image',100.0000,50.0000,80.0000,3.3000,'2021-10-30 00:00:00',5.0000,1,1,1,6,'Encours d\'enchere'),(5,'test3','test with 2 images',200000.0000,1000.0000,1400.2300,5.6000,'2021-10-23 00:00:00',100.0000,1,1,2,13,'Vendu');
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
-- Table structure for table `castat`
--

DROP TABLE IF EXISTS `castat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `castat` (
  `idcastat` int(11) NOT NULL AUTO_INCREMENT,
  `Semaine` int(11) DEFAULT NULL,
  `Annee` int(11) DEFAULT NULL,
  `CA` decimal(19,4) DEFAULT NULL,
  `idCategorie` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcastat`),
  KEY `FK_castat_cat_idx` (`idCategorie`),
  CONSTRAINT `FK_castat_cat` FOREIGN KEY (`idCategorie`) REFERENCES `Categorie` (`idCategorie`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `castat`
--

LOCK TABLES `castat` WRITE;
/*!40000 ALTER TABLE `castat` DISABLE KEYS */;
/*!40000 ALTER TABLE `castat` ENABLE KEYS */;
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
-- Table structure for table `composer`
--

DROP TABLE IF EXISTS `composer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composer` (
  `idCategorie` int(11) NOT NULL,
  `idSousCategorie` int(11) NOT NULL,
  PRIMARY KEY (`idCategorie`,`idSousCategorie`),
  KEY `FK_composer_souscat_idx` (`idSousCategorie`),
  CONSTRAINT `FK_composer_cat` FOREIGN KEY (`idCategorie`) REFERENCES `Categorie` (`idCategorie`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_composer_souscat` FOREIGN KEY (`idSousCategorie`) REFERENCES `Categorie` (`idCategorie`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composer`
--

LOCK TABLES `composer` WRITE;
/*!40000 ALTER TABLE `composer` DISABLE KEYS */;
INSERT INTO `composer` VALUES (1,6),(1,7),(6,10),(6,11),(6,12),(2,13),(2,14);
/*!40000 ALTER TABLE `composer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `composerSous`
--

DROP TABLE IF EXISTS `composerSous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `composerSous` (
  `idSousCategorie` int(11) NOT NULL,
  `idSSousCategorie` int(11) NOT NULL,
  PRIMARY KEY (`idSousCategorie`,`idSSousCategorie`),
  KEY `FK_composerS_ssouscat_idx` (`idSSousCategorie`),
  CONSTRAINT `FK_composerS_souscat` FOREIGN KEY (`idSousCategorie`) REFERENCES `Categorie` (`idCategorie`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_composerS_ssouscat` FOREIGN KEY (`idSSousCategorie`) REFERENCES `Categorie` (`idCategorie`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `composerSous`
--

LOCK TABLES `composerSous` WRITE;
/*!40000 ALTER TABLE `composerSous` DISABLE KEYS */;
INSERT INTO `composerSous` VALUES (6,10),(6,11),(6,12),(13,15),(13,16),(14,17),(14,18),(14,19);
/*!40000 ALTER TABLE `composerSous` ENABLE KEYS */;
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
  `dateEncherir` datetime DEFAULT NULL,
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
INSERT INTO `encherir` VALUES (1,5,2,0,1200.0000,'2021-10-21 00:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (2,'C:\\\\Users\\\\Steven\\\\Desktop\\\\testImages\\\\5901292515355_1.png'),(3,'C:\\\\Users\\\\Steven\\\\Desktop\\\\testImages\\\\5901292515355_1.png'),(4,'C:\\\\Users\\\\Steven\\\\Desktop\\\\testImages\\\\3c9359cb2496e9c7c9d4175b59d6e0f5.jpg');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membre`
--

LOCK TABLES `membre` WRITE;
/*!40000 ALTER TABLE `membre` DISABLE KEYS */;
INSERT INTO `membre` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `membre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nostat`
--

DROP TABLE IF EXISTS `nostat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nostat` (
  `idnostat` int(11) NOT NULL AUTO_INCREMENT,
  `Semaine` int(11) DEFAULT NULL,
  `Annee` int(11) DEFAULT NULL,
  `NombreObjet` int(11) DEFAULT NULL,
  `idCategorie` int(11) DEFAULT NULL,
  PRIMARY KEY (`idnostat`),
  KEY `FK_nostat_cat_idx` (`idCategorie`),
  CONSTRAINT `FK_nostat_cat` FOREIGN KEY (`idCategorie`) REFERENCES `Categorie` (`idCategorie`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nostat`
--

LOCK TABLES `nostat` WRITE;
/*!40000 ALTER TABLE `nostat` DISABLE KEYS */;
/*!40000 ALTER TABLE `nostat` ENABLE KEYS */;
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
INSERT INTO `posseder` VALUES (2,4),(2,5),(4,5);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestation`
--

LOCK TABLES `prestation` WRITE;
/*!40000 ALTER TABLE `prestation` DISABLE KEYS */;
INSERT INTO `prestation` VALUES (1,'Compteur de visites',0.0000,0.0000),(2,'Photo dans la liste des objets',0.5000,0.3000),(3,'Photo supplémentaire',0.2000,0.1500),(4,'Titre en gras',0.1000,0.1000);
/*!40000 ALTER TABLE `prestation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regionlivraison`
--

DROP TABLE IF EXISTS `regionlivraison`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regionlivraison` (
  `idregionlivraison` int(11) NOT NULL AUTO_INCREMENT,
  `region` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idregionlivraison`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regionlivraison`
--

LOCK TABLES `regionlivraison` WRITE;
/*!40000 ALTER TABLE `regionlivraison` DISABLE KEYS */;
INSERT INTO `regionlivraison` VALUES (1,'Alsace'),(2,'Aquitaine'),(3,'Auvergne'),(4,'Lorraine'),(5,'Midi-Pyrénées'),(6,'Picardie'),(7,'Rhône-Alpes');
/*!40000 ALTER TABLE `regionlivraison` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visstat`
--

DROP TABLE IF EXISTS `visstat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visstat` (
  `idvisstat` int(11) NOT NULL AUTO_INCREMENT,
  `Semaine` int(11) DEFAULT NULL,
  `Annee` int(11) DEFAULT NULL,
  `nombreDeVisite` int(11) DEFAULT NULL,
  `idCategorie` int(11) DEFAULT NULL,
  PRIMARY KEY (`idvisstat`),
  KEY `FK_visstat_cat_idx` (`idCategorie`),
  CONSTRAINT `FK_visstat_cat` FOREIGN KEY (`idCategorie`) REFERENCES `Categorie` (`idCategorie`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visstat`
--

LOCK TABLES `visstat` WRITE;
/*!40000 ALTER TABLE `visstat` DISABLE KEYS */;
/*!40000 ALTER TABLE `visstat` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-21 16:51:45
