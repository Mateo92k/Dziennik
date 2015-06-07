-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 06 Cze 2015, 18:03
-- Wersja serwera: 5.6.24
-- Wersja PHP: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `dziennik`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `klasy`
--

CREATE TABLE IF NOT EXISTS `klasy` (
  `idklasy` varchar(15) NOT NULL,
  `nazwa` varchar(15) DEFAULT NULL,
  `rok_szkolny` decimal(4,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `nauczyciele`
--

CREATE TABLE IF NOT EXISTS `nauczyciele` (
  `idnauczyciel` varchar(15) NOT NULL,
  `imie` varchar(15) DEFAULT NULL,
  `nazwisko` varchar(15) DEFAULT NULL,
  `tytul` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `oceny`
--

CREATE TABLE IF NOT EXISTS `oceny` (
  `idoceny` varchar(15) NOT NULL,
  `idprzedmiotu` varchar(15) DEFAULT NULL,
  `idnauczyciel` varchar(15) DEFAULT NULL,
  `iducznia` varchar(15) DEFAULT NULL,
  `idrodzaj_oceny` varchar(15) DEFAULT NULL,
  `ocena` decimal(1,0) DEFAULT NULL,
  `data` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `opis_ocen`
--

CREATE TABLE IF NOT EXISTS `opis_ocen` (
  `idrodzaj_oceny` varchar(15) NOT NULL,
  `nazwa` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przedmioty`
--

CREATE TABLE IF NOT EXISTS `przedmioty` (
  `idprzedmiotu` varchar(15) NOT NULL,
  `idnauczyciel` varchar(15) DEFAULT NULL,
  `nazwa` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uczniowie`
--

CREATE TABLE IF NOT EXISTS `uczniowie` (
  `iducznia` varchar(15) NOT NULL,
  `idklasy` varchar(15) DEFAULT NULL,
  `imie` varchar(15) DEFAULT NULL,
  `nazwisko` varchar(15) DEFAULT NULL,
  `pesel` decimal(11,0) DEFAULT NULL,
  `data_urodzenia` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `klasy`
--
ALTER TABLE `klasy`
  ADD PRIMARY KEY (`idklasy`);

--
-- Indexes for table `nauczyciele`
--
ALTER TABLE `nauczyciele`
  ADD PRIMARY KEY (`idnauczyciel`);

--
-- Indexes for table `oceny`
--
ALTER TABLE `oceny`
  ADD PRIMARY KEY (`idoceny`), ADD KEY `FK_Relationship_1` (`idrodzaj_oceny`), ADD KEY `FK_Relationship_2` (`idprzedmiotu`), ADD KEY `FK_Relationship_3` (`iducznia`), ADD KEY `FK_Relationship_5` (`idnauczyciel`);

--
-- Indexes for table `opis_ocen`
--
ALTER TABLE `opis_ocen`
  ADD PRIMARY KEY (`idrodzaj_oceny`);

--
-- Indexes for table `przedmioty`
--
ALTER TABLE `przedmioty`
  ADD PRIMARY KEY (`idprzedmiotu`), ADD KEY `FK_Relationship_6` (`idnauczyciel`);

--
-- Indexes for table `uczniowie`
--
ALTER TABLE `uczniowie`
  ADD PRIMARY KEY (`iducznia`), ADD KEY `FK_Relationship_4` (`idklasy`);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `oceny`
--
ALTER TABLE `oceny`
ADD CONSTRAINT `FK_Relationship_1` FOREIGN KEY (`idrodzaj_oceny`) REFERENCES `opis_ocen` (`idrodzaj_oceny`),
ADD CONSTRAINT `FK_Relationship_2` FOREIGN KEY (`idprzedmiotu`) REFERENCES `przedmioty` (`idprzedmiotu`),
ADD CONSTRAINT `FK_Relationship_3` FOREIGN KEY (`iducznia`) REFERENCES `uczniowie` (`iducznia`),
ADD CONSTRAINT `FK_Relationship_5` FOREIGN KEY (`idnauczyciel`) REFERENCES `nauczyciele` (`idnauczyciel`);

--
-- Ograniczenia dla tabeli `przedmioty`
--
ALTER TABLE `przedmioty`
ADD CONSTRAINT `FK_Relationship_6` FOREIGN KEY (`idnauczyciel`) REFERENCES `nauczyciele` (`idnauczyciel`);

--
-- Ograniczenia dla tabeli `uczniowie`
--
ALTER TABLE `uczniowie`
ADD CONSTRAINT `FK_Relationship_4` FOREIGN KEY (`idklasy`) REFERENCES `klasy` (`idklasy`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
