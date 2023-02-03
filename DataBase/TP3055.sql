-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : ven. 03 fév. 2023 à 10:38
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `TP3055`
--

-- --------------------------------------------------------

--
-- Structure de la table `Medecin`
--

CREATE TABLE `Medecin` (
  `idMedecin` bigint(20) NOT NULL,
  `idUser` bigint(20) NOT NULL,
  `poste` varchar(50) NOT NULL,
  `specialite` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Medecin`
--

INSERT INTO `Medecin` (`idMedecin`, `idUser`, `poste`, `specialite`) VALUES
(0, 2, 'batteur', 'batterie électronique'),
(1, 2, 'Licence 3', 'Génie Logiciel');

-- --------------------------------------------------------

--
-- Structure de la table `Patients`
--

CREATE TABLE `Patients` (
  `idPatient` bigint(20) NOT NULL,
  `id_User` bigint(20) NOT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Commentaire` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Users`
--

CREATE TABLE `Users` (
  `idUser` int(11) NOT NULL,
  `nomUser` varchar(25) NOT NULL,
  `Tel` int(10) NOT NULL,
  `age` int(11) NOT NULL,
  `localite` varchar(20) NOT NULL,
  `profession` varchar(20) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `Users`
--

INSERT INTO `Users` (`idUser`, `nomUser`, `Tel`, `age`, `localite`, `profession`, `login`, `password`) VALUES
(1, 'Sheik\'s', 0, 0, '7', '', 'Login 1', 'Password 1'),
(2, 'Nduwarugira', 695156866, 22, 'Yaoundé', 'Étudiant', '#Link\'s', '123Link\'s'),
(3, '1brun', 665766, 143, '77', '', '', '');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Medecin`
--
ALTER TABLE `Medecin`
  ADD PRIMARY KEY (`idMedecin`);

--
-- Index pour la table `Patients`
--
ALTER TABLE `Patients`
  ADD PRIMARY KEY (`idPatient`);

--
-- Index pour la table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`idUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
