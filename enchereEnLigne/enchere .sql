-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 04 nov. 2021 à 08:28
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `enchere`
--

-- --------------------------------------------------------

--
-- Structure de la table `achatimmediat`
--

DROP TABLE IF EXISTS `achatimmediat`;
CREATE TABLE IF NOT EXISTS `achatimmediat` (
  `idMembre` int(11) NOT NULL,
  `idArticle` int(11) NOT NULL,
  `dateAchat` datetime DEFAULT NULL,
  PRIMARY KEY (`idMembre`,`idArticle`),
  KEY `FK_achatImmediat_article_idx` (`idArticle`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `achatimmediat`
--

INSERT INTO `achatimmediat` (`idMembre`, `idArticle`, `dateAchat`) VALUES
(7, 6, '2021-11-04 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

DROP TABLE IF EXISTS `administrateur`;
CREATE TABLE IF NOT EXISTS `administrateur` (
  `idadministrateur` int(11) NOT NULL AUTO_INCREMENT,
  `mail` varchar(45) DEFAULT NULL,
  `MotDePass` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idadministrateur`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `administrateur`
--

INSERT INTO `administrateur` (`idadministrateur`, `mail`, `MotDePass`, `role`) VALUES
(1, 'miloJ@gmail.com', '12345678', 'Service Juridique'),
(2, 'zijingG@gmail.com', '12345678', 'Gestionnaire Site'),
(3, 'datC@gmail.com', '12345678', 'Service Commercial');

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
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
  KEY `FK_article_regionLiv_idx` (`idRegionLiv`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`idarticle`, `titreAnnonce`, `descAnnonce`, `prixAchatImme`, `prixDep`, `prixReserve`, `fraisPort`, `dateCloture`, `pasEnchere`, `idvendeur`, `idRegionLiv`, `idCategorie`, `idSousCategorie`, `etat`) VALUES
(6, 'TV', 'Une TV', '600.0000', '700.0000', '650.0000', '50.0000', '2021-10-31 00:00:00', '70.0000', 7, 1, 1, 6, 'Vendu'),
(7, 'Sac', 'Un sac', '35.0000', '50.0000', '40.0000', '1.0000', '2021-10-31 00:00:00', '5.0000', 7, 1, 1, 6, 'encours d\'enchère'),
(8, 'Phone', 'Un nouveau', '130.0000', '2000.0000', '150.0000', '2.0000', '2021-11-18 00:00:00', '200.0000', 7, 1, 1, 6, 'encours d\'enchère');

-- --------------------------------------------------------

--
-- Structure de la table `avisarticle`
--

DROP TABLE IF EXISTS `avisarticle`;
CREATE TABLE IF NOT EXISTS `avisarticle` (
  `idarticle` int(11) NOT NULL,
  `idmembre` int(11) NOT NULL,
  `avis` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idarticle`,`idmembre`),
  KEY `FK_avisarticle_membre_idx` (`idmembre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `avismembre`
--

DROP TABLE IF EXISTS `avismembre`;
CREATE TABLE IF NOT EXISTS `avismembre` (
  `idmembreEmis` int(11) NOT NULL,
  `idmembreRecu` int(11) NOT NULL,
  `avis` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idmembreEmis`,`idmembreRecu`),
  KEY `FK_avismembre_recu_idx` (`idmembreRecu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `castat`
--

DROP TABLE IF EXISTS `castat`;
CREATE TABLE IF NOT EXISTS `castat` (
  `idcastat` int(11) NOT NULL AUTO_INCREMENT,
  `Semaine` int(11) DEFAULT NULL,
  `Annee` int(11) DEFAULT NULL,
  `CA` decimal(19,4) DEFAULT NULL,
  `idCategorie` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcastat`),
  KEY `FK_castat_cat_idx` (`idCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `idCategorie` int(11) NOT NULL AUTO_INCREMENT,
  `libelleCat` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCategorie`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`idCategorie`, `libelleCat`) VALUES
(1, 'Multimédia'),
(2, 'Electroménager'),
(6, 'Télévision'),
(7, 'Vidéo'),
(10, 'Téléviseur 4/3'),
(11, 'Téléviseurs 16/9'),
(12, 'Téléviseurs LCD'),
(13, 'Lavage'),
(14, 'Froid'),
(15, 'Lave-linge'),
(16, 'Sèche-linge'),
(17, 'Réfrigérateur'),
(18, 'Congélateur'),
(19, 'Machine à glaçons');

-- --------------------------------------------------------

--
-- Structure de la table `choisir`
--

DROP TABLE IF EXISTS `choisir`;
CREATE TABLE IF NOT EXISTS `choisir` (
  `idprestation` int(11) NOT NULL,
  `idmembre` int(11) NOT NULL,
  PRIMARY KEY (`idprestation`,`idmembre`),
  KEY `FK_choisir_membre_idx` (`idmembre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `composer`
--

DROP TABLE IF EXISTS `composer`;
CREATE TABLE IF NOT EXISTS `composer` (
  `idCategorie` int(11) NOT NULL,
  `idSousCategorie` int(11) NOT NULL,
  PRIMARY KEY (`idCategorie`,`idSousCategorie`),
  KEY `FK_composer_souscat_idx` (`idSousCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `composer`
--

INSERT INTO `composer` (`idCategorie`, `idSousCategorie`) VALUES
(1, 6),
(1, 7),
(6, 10),
(6, 11),
(6, 12),
(2, 13),
(2, 14);

-- --------------------------------------------------------

--
-- Structure de la table `composersous`
--

DROP TABLE IF EXISTS `composersous`;
CREATE TABLE IF NOT EXISTS `composersous` (
  `idSousCategorie` int(11) NOT NULL,
  `idSSousCategorie` int(11) NOT NULL,
  PRIMARY KEY (`idSousCategorie`,`idSSousCategorie`),
  KEY `FK_composerS_ssouscat_idx` (`idSSousCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `composersous`
--

INSERT INTO `composersous` (`idSousCategorie`, `idSSousCategorie`) VALUES
(6, 10),
(6, 11),
(6, 12),
(13, 15),
(13, 16),
(14, 17),
(14, 18),
(14, 19);

-- --------------------------------------------------------

--
-- Structure de la table `encherir`
--

DROP TABLE IF EXISTS `encherir`;
CREATE TABLE IF NOT EXISTS `encherir` (
  `idmembre` int(11) NOT NULL,
  `idarticle` int(11) NOT NULL,
  `nombrePas` int(11) DEFAULT NULL,
  `ifGagnant` int(11) DEFAULT NULL,
  `prixPropose` decimal(19,4) DEFAULT NULL,
  `dateEncherir` datetime DEFAULT NULL,
  PRIMARY KEY (`idmembre`,`idarticle`),
  KEY `FK_encherir_article_idx` (`idarticle`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `image`
--

DROP TABLE IF EXISTS `image`;
CREATE TABLE IF NOT EXISTS `image` (
  `idimage` int(11) NOT NULL AUTO_INCREMENT,
  `repertoire` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idimage`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `image`
--

INSERT INTO `image` (`idimage`, `repertoire`) VALUES
(2, 'C:\\\\Users\\\\Steven\\\\Desktop\\\\testImages\\\\5901292515355_1.png'),
(3, 'C:\\\\Users\\\\Steven\\\\Desktop\\\\testImages\\\\5901292515355_1.png'),
(4, 'C:\\\\Users\\\\Steven\\\\Desktop\\\\testImages\\\\3c9359cb2496e9c7c9d4175b59d6e0f5.jpg'),
(5, 'C:\\\\Users\\\\33760\\\\Downloads\\\\my icons\\\\my icons\\\\admin.png'),
(6, 'C:\\\\Users\\\\33760\\\\Downloads\\\\my icons\\\\my icons\\\\ut1.png'),
(7, 'C:\\\\Users\\\\33760\\\\Downloads\\\\my icons\\\\my icons\\\\admin.png'),
(8, 'C:\\\\Users\\\\33760\\\\Downloads\\\\my icons\\\\my icons\\\\ut1.png'),
(9, 'C:\\\\Users\\\\33760\\\\Downloads\\\\my icons\\\\my icons\\\\new-user.png'),
(10, 'C:\\\\Users\\\\33760\\\\Documents\\\\my icons\\\\backimage.jpg'),
(11, 'C:\\\\Users\\\\33760\\\\Documents\\\\my icons\\\\ut1.png'),
(12, 'C:\\\\Users\\\\33760\\\\Documents\\\\my icons\\\\backimage.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `membre`
--

DROP TABLE IF EXISTS `membre`;
CREATE TABLE IF NOT EXISTS `membre` (
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `membre`
--

INSERT INTO `membre` (`idmembre`, `nom`, `prenom`, `dateNais`, `mail`, `adressePostal`, `CP`, `ville`, `pays`, `MotDePass`, `Statut`, `NbEtoile`) VALUES
(4, 'Chantal', 'LaPorte', '1991-08-13 00:00:00', 'la@gmail.com', '31 av', '31100', 'Pekin', 'Chine', '12345678', 'membreSimple', 0),
(5, 'RAVAT', 'FRANK', '2015-12-27 08:12:01', 'franck@gmail.com', '2 allées Jean Jaurès', '79200', 'Paris', 'France', '12345678', 'membreSimple', 0),
(6, 'Strecker', 'Martin', '2018-11-17 08:13:10', 's@gmail.com', '13 Rue Paul Gaugin', '31100', 'Toulouse', 'France', '12345678', 'membreSimple', 0),
(7, 'BAH', 'Kadé', '2016-09-09 08:14:19', 'k@gmail.com', '14 rue Entat', '99999', 'Conakry', 'Guinée', '12345678', 'membreSimple', 0),
(12, 'david', 'david', '1977-10-13 00:00:00', 'david@gmail.com', '15 AV', '31400', 'Pekin', 'Chine', '12345678', 'membreSimple', 0);

-- --------------------------------------------------------

--
-- Structure de la table `nostat`
--

DROP TABLE IF EXISTS `nostat`;
CREATE TABLE IF NOT EXISTS `nostat` (
  `idnostat` int(11) NOT NULL AUTO_INCREMENT,
  `Semaine` int(11) DEFAULT NULL,
  `Annee` int(11) DEFAULT NULL,
  `NombreObjet` int(11) DEFAULT NULL,
  `idCategorie` int(11) DEFAULT NULL,
  PRIMARY KEY (`idnostat`),
  KEY `FK_nostat_cat_idx` (`idCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `posseder`
--

DROP TABLE IF EXISTS `posseder`;
CREATE TABLE IF NOT EXISTS `posseder` (
  `idimage` int(11) NOT NULL,
  `idarticle` int(11) NOT NULL,
  PRIMARY KEY (`idimage`,`idarticle`),
  KEY `FK_posseder_article_idx` (`idarticle`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `prestation`
--

DROP TABLE IF EXISTS `prestation`;
CREATE TABLE IF NOT EXISTS `prestation` (
  `idPRESTATION` int(11) NOT NULL AUTO_INCREMENT,
  `LibellePres` varchar(45) DEFAULT NULL,
  `PrixCat` decimal(19,4) DEFAULT NULL,
  `PrixGold` decimal(19,4) DEFAULT NULL,
  PRIMARY KEY (`idPRESTATION`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `prestation`
--

INSERT INTO `prestation` (`idPRESTATION`, `LibellePres`, `PrixCat`, `PrixGold`) VALUES
(1, 'Compteur de visites', '0.0000', '0.0000'),
(2, 'Photo dans la liste des objets', '0.5000', '0.3000'),
(3, 'Photo supplémentaire', '0.2000', '0.1500'),
(4, 'Titre en gras', '0.1000', '0.1000');

-- --------------------------------------------------------

--
-- Structure de la table `regionlivraison`
--

DROP TABLE IF EXISTS `regionlivraison`;
CREATE TABLE IF NOT EXISTS `regionlivraison` (
  `idregionlivraison` int(11) NOT NULL AUTO_INCREMENT,
  `region` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idregionlivraison`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `regionlivraison`
--

INSERT INTO `regionlivraison` (`idregionlivraison`, `region`) VALUES
(1, 'Alsace'),
(2, 'Aquitaine'),
(3, 'Auvergne'),
(4, 'Lorraine'),
(5, 'Midi-Pyrénées'),
(6, 'Picardie'),
(7, 'Rhône-Alpes');

-- --------------------------------------------------------

--
-- Structure de la table `visstat`
--

DROP TABLE IF EXISTS `visstat`;
CREATE TABLE IF NOT EXISTS `visstat` (
  `idvisstat` int(11) NOT NULL AUTO_INCREMENT,
  `Semaine` int(11) DEFAULT NULL,
  `Annee` int(11) DEFAULT NULL,
  `nombreDeVisite` int(11) DEFAULT NULL,
  `idCategorie` int(11) DEFAULT NULL,
  PRIMARY KEY (`idvisstat`),
  KEY `FK_visstat_cat_idx` (`idCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `achatimmediat`
--
ALTER TABLE `achatimmediat`
  ADD CONSTRAINT `FK_achatImmediat_article` FOREIGN KEY (`idArticle`) REFERENCES `article` (`idarticle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_achatImmediat_membre` FOREIGN KEY (`idMembre`) REFERENCES `membre` (`idmembre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `FK_article_cat` FOREIGN KEY (`idCategorie`) REFERENCES `categorie` (`idCategorie`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_article_regionLiv` FOREIGN KEY (`idRegionLiv`) REFERENCES `regionlivraison` (`idregionlivraison`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_article_sousCat` FOREIGN KEY (`idSousCategorie`) REFERENCES `categorie` (`idCategorie`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_article_vendeur` FOREIGN KEY (`idvendeur`) REFERENCES `membre` (`idmembre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `avisarticle`
--
ALTER TABLE `avisarticle`
  ADD CONSTRAINT `FK_avisarticle_article` FOREIGN KEY (`idarticle`) REFERENCES `article` (`idarticle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_avisarticle_membre` FOREIGN KEY (`idmembre`) REFERENCES `membre` (`idmembre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `avismembre`
--
ALTER TABLE `avismembre`
  ADD CONSTRAINT `FK_avismembre_emis` FOREIGN KEY (`idmembreEmis`) REFERENCES `membre` (`idmembre`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_avismembre_recu` FOREIGN KEY (`idmembreRecu`) REFERENCES `membre` (`idmembre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `castat`
--
ALTER TABLE `castat`
  ADD CONSTRAINT `FK_castat_cat` FOREIGN KEY (`idCategorie`) REFERENCES `categorie` (`idCategorie`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `choisir`
--
ALTER TABLE `choisir`
  ADD CONSTRAINT `FK_choisir_membre` FOREIGN KEY (`idmembre`) REFERENCES `membre` (`idmembre`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_choisir_prestation` FOREIGN KEY (`idprestation`) REFERENCES `prestation` (`idPRESTATION`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `composer`
--
ALTER TABLE `composer`
  ADD CONSTRAINT `FK_composer_cat` FOREIGN KEY (`idCategorie`) REFERENCES `categorie` (`idCategorie`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_composer_souscat` FOREIGN KEY (`idSousCategorie`) REFERENCES `categorie` (`idCategorie`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `composersous`
--
ALTER TABLE `composersous`
  ADD CONSTRAINT `FK_composerS_souscat` FOREIGN KEY (`idSousCategorie`) REFERENCES `categorie` (`idCategorie`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_composerS_ssouscat` FOREIGN KEY (`idSSousCategorie`) REFERENCES `categorie` (`idCategorie`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `encherir`
--
ALTER TABLE `encherir`
  ADD CONSTRAINT `FK_encherir_article` FOREIGN KEY (`idarticle`) REFERENCES `article` (`idarticle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_encherir_membre` FOREIGN KEY (`idmembre`) REFERENCES `membre` (`idmembre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `nostat`
--
ALTER TABLE `nostat`
  ADD CONSTRAINT `FK_nostat_cat` FOREIGN KEY (`idCategorie`) REFERENCES `categorie` (`idCategorie`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `posseder`
--
ALTER TABLE `posseder`
  ADD CONSTRAINT `FK_posseder_article` FOREIGN KEY (`idarticle`) REFERENCES `article` (`idarticle`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_posseder_image` FOREIGN KEY (`idimage`) REFERENCES `image` (`idimage`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `visstat`
--
ALTER TABLE `visstat`
  ADD CONSTRAINT `FK_visstat_cat` FOREIGN KEY (`idCategorie`) REFERENCES `categorie` (`idCategorie`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
