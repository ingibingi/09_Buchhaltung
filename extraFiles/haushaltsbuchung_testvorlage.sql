-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 23. Okt 2024 um 15:31
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
-- Stellvertreter-Struktur des Views `allebuchungen`
-- (Siehe unten für die tatsächliche Ansicht)
--
CREATE TABLE `allebuchungen` (
`ID` int(11)
,`ZuletztVeraendert` timestamp
,`Kategorie` int(11)
,`Datum` date
,`Zusatzinfo` text
,`Betrag` double
);

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

--
-- Daten für Tabelle `t_buchung`
--

INSERT INTO `t_buchung` (`ID`, `ZuletztVeraendert`, `Kategorie`, `Datum`, `Zusatzinfo`, `Betrag`) VALUES
(2, '2024-10-23 13:16:22', 1, '2024-10-21', 'Karotten, Erbsen, Bohnen, Steak, Chips, Bier, Kernöl', 127.13),
(3, '2024-10-23 13:18:23', 6, '2024-10-21', 'Zahnbürste, Zahnpasta, Zahnseide', 12.35),
(4, '2024-10-23 13:19:16', 4, '2024-10-15', '', 2000),
(5, '2024-10-23 13:20:10', 2, '2024-10-01', '', 500),
(6, '2024-10-23 13:20:24', 2, '2024-10-01', '', 500),
(7, '2024-10-23 13:21:42', 1, '2024-10-06', 'Ravioli', 187.99);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `t_kategorie`
--

CREATE TABLE `t_kategorie` (
  `ID` int(11) NOT NULL,
  `Bezeichnung` varchar(30) NOT NULL,
  `Kurzbezeichnung` varchar(6) NOT NULL,
  `IstEinzahlung` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `t_kategorie`
--

INSERT INTO `t_kategorie` (`ID`, `Bezeichnung`, `Kurzbezeichnung`, `IstEinzahlung`) VALUES
(1, 'EK Lebensmittel', 'E_LM', 0),
(2, 'Miete', 'Miete', 0),
(4, 'Gehalt', 'Gehalt', 1),
(5, 'Taschengeld', 'TG', 0),
(6, 'EK Hygieneartikel', 'E_Hyg', 0);

-- --------------------------------------------------------

--
-- Struktur des Views `allebuchungen`
--
DROP TABLE IF EXISTS `allebuchungen`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `allebuchungen`  AS SELECT `t_buchung`.`ID` AS `ID`, `t_buchung`.`ZuletztVeraendert` AS `ZuletztVeraendert`, `t_buchung`.`Kategorie` AS `Kategorie`, `t_buchung`.`Datum` AS `Datum`, `t_buchung`.`Zusatzinfo` AS `Zusatzinfo`, `t_buchung`.`Betrag` AS `Betrag` FROM `t_buchung` ;

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
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT für Tabelle `t_kategorie`
--
ALTER TABLE `t_kategorie`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
