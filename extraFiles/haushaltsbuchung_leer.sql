-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 23. Okt 2024 um 14:55
-- Server-Version: 10.4.32-MariaDB
-- PHP-Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `haushaltsbuchung`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `t_buchung`
--

CREATE TABLE `t_buchung` (
  `ID` int(11) NOT NULL,
  `ZuletztVeraendert` timestamp NOT NULL DEFAULT current_timestamp(),
  `Kategorie` int(11) NOT NULL,
  `Datum` date NOT NULL,
  `Zusatzinfo` text NOT NULL,
  `Betrag` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `t_kategorie`
--

CREATE TABLE `t_kategorie` (
  `ID` int(11) NOT NULL,
  `Bezeichnung` varchar(30) NOT NULL,
  `Kurzbezeichnung` varchar(6) NOT NULL,
  `IstEingang` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `t_buchung`
--
ALTER TABLE `t_buchung`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Kategorie` (`Kategorie`);

--
-- Indizes für die Tabelle `t_kategorie`
--
ALTER TABLE `t_kategorie`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `t_buchung`
--
ALTER TABLE `t_buchung`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `t_kategorie`
--
ALTER TABLE `t_kategorie`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `t_buchung`
--
ALTER TABLE `t_buchung`
  ADD CONSTRAINT `Kategorie` FOREIGN KEY (`Kategorie`) REFERENCES `t_kategorie` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
