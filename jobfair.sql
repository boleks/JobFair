-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 10, 2019 at 01:36 PM
-- Server version: 5.7.26
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jobfair`
--
CREATE DATABASE IF NOT EXISTS `jobfair` DEFAULT CHARACTER SET latin1 COLLATE latin1_bin;
USE `jobfair`;

-- --------------------------------------------------------

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
CREATE TABLE IF NOT EXISTS `administrator` (
  `idKorisnik` int(11) NOT NULL,
  `ime` varchar(45) CHARACTER SET utf8 NOT NULL,
  `prezime` varchar(45) CHARACTER SET utf8 NOT NULL,
  `telefon` varchar(45) CHARACTER SET utf8 NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`idKorisnik`),
  KEY `idAdministrator_idx` (`idKorisnik`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrator`
--

INSERT INTO `administrator` (`idKorisnik`, `ime`, `prezime`, `telefon`, `email`) VALUES
(2, 'Admin', 'Admin', '064', 'admin@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `cv`
--

DROP TABLE IF EXISTS `cv`;
CREATE TABLE IF NOT EXISTS `cv` (
  `idCV` int(11) NOT NULL AUTO_INCREMENT,
  `idKorisnik` int(11) NOT NULL,
  `ime` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `prezime` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `telefon` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `grad` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `ostaleVestine` mediumtext CHARACTER SET utf8,
  PRIMARY KEY (`idCV`),
  KEY `idKorisnik_student_idx` (`idKorisnik`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cv`
--

INSERT INTO `cv` (`idCV`, `idKorisnik`, `ime`, `prezime`, `email`, `telefon`, `grad`, `ostaleVestine`) VALUES
(10, 1, 'Petar', 'Petrovic', 'pera@gmail.com', '0641234567', 'Beograd', 'Pokuacu ponovo\nda izmenim sve'),
(11, 5, 'Marija', 'Simic', 'marija@gmail.com', '064445552', 'Beograd', '- Komunikativna\n- Timski igra?'),
(12, 24, 'Ivan', 'Ivanovic', 'ivan@gmail.com', '064211111158', 'Beograd', 'Timski igrac'),
(13, 23, 'Ivana', 'Simic', 'ivana@gmail.com', '0611234567', 'Beograd', 'Timski igrac'),
(14, 22, 'Filip', 'Tripkovic', 'filip@gmail.com', '0662133699', 'Beograd', 'Komunikativan');

-- --------------------------------------------------------

--
-- Table structure for table `delatnost`
--

DROP TABLE IF EXISTS `delatnost`;
CREATE TABLE IF NOT EXISTS `delatnost` (
  `idDelatnost` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`idDelatnost`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `delatnost`
--

INSERT INTO `delatnost` (`idDelatnost`, `naziv`) VALUES
(1, 'Administracija'),
(2, 'Arhitektura'),
(3, 'Bankarstvo'),
(4, 'Biologija'),
(5, 'Briga o lepoti'),
(6, 'Dizajn'),
(7, 'Ekonomija (opšte)'),
(8, 'Elektrotehnika'),
(9, 'Farmacija'),
(10, 'Finansije'),
(11, 'Fizika, matematika'),
(12, 'Grafičarstvo, izdavaštvo'),
(13, 'Građevina, geodezija'),
(14, 'Hemija'),
(15, 'IT'),
(16, 'Jezici, književnost'),
(17, 'Kontrola kvaliteta'),
(18, 'Ljudski resursi (HR)'),
(19, 'Logistika'),
(20, 'Magacin'),
(21, 'Marketing, promocija'),
(22, 'Mašinstvo'),
(23, 'Mediji (novinarstvo, štampa)'),
(24, 'Menadžment (srednji)'),
(25, 'Menadžment (viši)'),
(26, 'Obezbeđenje'),
(27, 'Obrazovanje, briga o deci'),
(28, 'Osiguranje, lizing'),
(29, 'Ostalo'),
(30, 'Poljoprivreda'),
(31, 'Pozivni centri'),
(32, 'PR'),
(33, 'Pravo'),
(34, 'Prehrambena tehnologija'),
(35, 'Priprema hrane'),
(36, 'Psihologija'),
(37, 'Računovodstvo, knjigovodstvo'),
(38, 'Rudarstvo, metalurgija'),
(39, 'Saobraćaj'),
(40, 'Sociologija/socijalni rad'),
(41, 'Sport, rekreacija'),
(42, 'Stomatologija'),
(43, 'Tekstilna industrija'),
(44, 'Telekomunikacije'),
(45, 'Transport'),
(46, 'Trgovina, prodaja'),
(47, 'Turizam'),
(48, 'Ugostiteljstvo'),
(49, 'Umetnost'),
(50, 'Veterina'),
(51, 'Zabava'),
(52, 'Zaštita na radu'),
(53, 'Zaštita životne sredine, ekologija'),
(54, 'Zdravstvo'),
(55, 'Šumarstvo');

-- --------------------------------------------------------

--
-- Table structure for table `dodatne_usluge`
--

DROP TABLE IF EXISTS `dodatne_usluge`;
CREATE TABLE IF NOT EXISTS `dodatne_usluge` (
  `idDodatak` int(11) NOT NULL AUTO_INCREMENT,
  `idSajam` int(11) NOT NULL,
  `naziv` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `opis` mediumtext CHARACTER SET utf8,
  `cena` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idDodatak`),
  KEY `idSajamDodatak_idx` (`idSajam`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dodatne_usluge`
--

INSERT INTO `dodatne_usluge` (`idDodatak`, `idSajam`, `naziv`, `opis`, `cena`) VALUES
(1, 1, 'Flajer', 'Flajer ubacen u brosuru maks 1 po kompaniji', '4000'),
(2, 1, 'Prednja unutrasnja korica', 'Prednja unutrasnja korica brosure', '2000'),
(3, 1, 'Dodatna strana u boji', 'Dodatna strana u boji u brosuri', '3000'),
(4, 1, 'Doplata za bendiranje standa', 'Doplata za bendiranje standa', '5000'),
(5, 1, 'Prezentacija kompanije', 'Prezentacija kompanije u trajanju od 45 minuta', '10000');

-- --------------------------------------------------------

--
-- Table structure for table `grad`
--

DROP TABLE IF EXISTS `grad`;
CREATE TABLE IF NOT EXISTS `grad` (
  `idGrad` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`idGrad`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grad`
--

INSERT INTO `grad` (`idGrad`, `naziv`) VALUES
(1, 'Beograd'),
(2, 'Bor'),
(3, 'Čačak'),
(4, 'Jagodina'),
(5, 'Kikinda'),
(6, 'Kragujevac'),
(7, 'Kraljevo'),
(8, 'Kruševac'),
(9, 'Leskovac'),
(10, 'Loznica'),
(11, 'Niš'),
(12, 'Novi Pazar'),
(13, 'Novi Sad'),
(14, 'Pančevo'),
(15, 'Pirot'),
(16, 'Požarevac'),
(17, 'Priština'),
(18, 'Prokuplje'),
(19, 'Smederevo'),
(20, 'Sombor'),
(21, 'Sremska Mitrovica'),
(22, 'Subotica'),
(23, 'Šabac'),
(24, 'Užice'),
(25, 'Valjevo'),
(26, 'Vranje'),
(27, 'Vršac'),
(28, 'Zaječar'),
(29, 'Zrenjanin');

-- --------------------------------------------------------

--
-- Table structure for table `jezik`
--

DROP TABLE IF EXISTS `jezik`;
CREATE TABLE IF NOT EXISTS `jezik` (
  `idJezik` int(11) NOT NULL AUTO_INCREMENT,
  `idCV` int(11) NOT NULL,
  `naziv` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `nivo` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idJezik`),
  KEY `idCVjezik_idx` (`idCV`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jezik`
--

INSERT INTO `jezik` (`idJezik`, `idCV`, `naziv`, `nivo`) VALUES
(1, 10, 'Engleski', 'Visi'),
(2, 10, 'Nemacki', 'Visi'),
(4, 10, 'Spanski', 'Pocetni'),
(5, 10, 'Engleski', 'Srednji'),
(6, 11, 'Engleski', 'Pocetni'),
(7, 12, 'Engleski', 'Pocetni'),
(8, 12, 'Nemacki', 'Pocetni'),
(9, 13, 'Engleski', 'Pocetni'),
(10, 13, 'Francuski', 'Srednji'),
(11, 14, 'Engleski', 'Visi'),
(12, 14, 'Nemacki', 'Srednji');

-- --------------------------------------------------------

--
-- Table structure for table `kompanija`
--

DROP TABLE IF EXISTS `kompanija`;
CREATE TABLE IF NOT EXISTS `kompanija` (
  `idKorisnik` int(11) NOT NULL,
  `naziv` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `grad` int(11) DEFAULT NULL,
  `adresa` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `direktor` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `pib` int(11) DEFAULT NULL,
  `brojZaposlenih` int(11) DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `websajt` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `delatnost` int(11) DEFAULT NULL,
  `uzaDelatnost` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idKorisnik`),
  KEY `idKompanije_idx` (`idKorisnik`),
  KEY `idGrad_idx` (`grad`),
  KEY `idDelatnost_idx` (`delatnost`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kompanija`
--

INSERT INTO `kompanija` (`idKorisnik`, `naziv`, `grad`, `adresa`, `direktor`, `pib`, `brojZaposlenih`, `email`, `websajt`, `delatnost`, `uzaDelatnost`) VALUES
(6, 'Netlogic', 1, 'Milutina Milankovića 1', 'Netlogic', 106330646, 10, 'office@netlogic.rs', 'http://www.netlogic.rs/', 1, 'IT'),
(10, 'Telekom', 1, 'Takovska 2', 'Predrag Ćulibrk', 100002887, 1000, 'telekom@gmail.com', 'www.mts.rs', 44, 'Telekomunikacije'),
(11, 'Telenor', 1, 'Omladinskih brigada 90', 'Majk Mišel', 108220769, 1000, 'info@telenor.rs', 'www.telenor.rs', 44, 'Mobilni operater'),
(12, 'JYSK', 1, 'Batajnički drum 1', 'NA', 100, 100, 'info@jysk.com', 'www.jysk.rs', 46, 'Maloprodaja'),
(13, 'Forma Ideale', 6, 'Skladišni centar 13', 'Pera Petrovic', 103251239, 1000, 'info@formaideale.rs', 'www.formaideale.rs', 46, 'Proizvodnja namestaja'),
(14, 'Jaffa Crvenka', 13, 'Ul. Maršala Tita br. 245', 'Nikola Nikolic', 100263582, 1000, 'jaffa@jaffa.rs', 'www.jaffa.rs', 35, 'Konditorska industrija'),
(15, 'Komercijalna Banka', 1, 'Svetog Save 14', 'Nenad Nenadovic', 100001931, 500, 'ljudski.resursi@kombank.com', 'www.kombank.com', 3, 'Bankarstvo'),
(16, 'Ringier Axel Springer', 1, 'Kosovska 10', 'Nikola Nikolic', 100000889, 200, ' jobs@ringier.rs', 'http://www.ringieraxelspringer.rs', 23, 'Izdavastvo'),
(17, 'Aptiv Mobility Services', 13, 'Privrednikova 20', 'Nikola Nikolic', 100250355, 100, 'aptivposao@aptiv.com', 'www.aptiv.com', 29, 'Proizvodnja'),
(18, 'NIS - Naftna Industrija Srbije', 13, 'Narodnog fronta 12', 'Marko Markovic', 100200035, 2500, 'office@nis.eu', 'www.nis.eu', 38, 'Trgovina naftnim derivatima'),
(19, 'Atlantic Grupa', 1, 'Bulevar Peka Dapčevića 29', 'Marko', 100255336, 2500, 'info@atlanticgrupa.com', 'www.atlanticgrupa.com', 34, 'Konditori'),
(25, 'Bambi', 1, 'Bulevar Mihajla Pupina 115g', 'Marko Marinkovic', 100436827, 500, 'info@bambi.rs', 'www.bambi.rs', 34, 'Konditorski proizvodi');

-- --------------------------------------------------------

--
-- Table structure for table `kompjuterske_vestine`
--

DROP TABLE IF EXISTS `kompjuterske_vestine`;
CREATE TABLE IF NOT EXISTS `kompjuterske_vestine` (
  `idKV` int(11) NOT NULL AUTO_INCREMENT,
  `idCV` int(11) NOT NULL,
  `Program` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `nivo` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idKV`),
  KEY `idCV_idx` (`idCV`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kompjuterske_vestine`
--

INSERT INTO `kompjuterske_vestine` (`idKV`, `idCV`, `Program`, `nivo`) VALUES
(1, 10, 'Java', 'Visi'),
(2, 10, 'test', 'Pocetni'),
(3, 10, 'PHP', 'Pocetni'),
(4, 11, 'Word', 'Pocetni'),
(5, 11, 'Excel', 'Pocetni'),
(6, 12, 'Word', 'Visi'),
(7, 13, 'Word', 'Visi'),
(8, 14, 'Word', 'Visi'),
(9, 14, 'Excel', 'Srednji');

-- --------------------------------------------------------

--
-- Table structure for table `konkurs`
--

DROP TABLE IF EXISTS `konkurs`;
CREATE TABLE IF NOT EXISTS `konkurs` (
  `idKonkurs` int(11) NOT NULL AUTO_INCREMENT,
  `idKompanija` int(11) NOT NULL,
  `nazivPozicija` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `tekstKonkursa` mediumtext CHARACTER SET utf8,
  `rokZaPrijavu` date DEFAULT NULL,
  `prilog` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `tip` char(1) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idKonkurs`),
  KEY `idKompanijaKonkurs` (`idKompanija`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `konkurs`
--

INSERT INTO `konkurs` (`idKonkurs`, `idKompanija`, `nazivPozicija`, `tekstKonkursa`, `rokZaPrijavu`, `prilog`, `tip`) VALUES
(13, 6, 'System Administrator', 'OPIS POSLA, ODGOVORNOSTI I ZADUŽENJA\r\n- Dijagnostika i rešavanje problema u radu računarske opreme i infromacionog sistema što čini:\r\n- Evidencija i otklanjanje kvarova na računarskoj opremi,\r\n- Implementacija i rad sa sistemskim rešenjima ka poslovnicama klijenata,\r\n- Instalacija driver-a, software-a i osposobljavanje računara (štampači, skeneri…),\r\n- Usmena pomoć i konsultacije sa korisnicima,\r\n- Rešavanje svakodnevnih kvarova putem telefona / daljinska tehnička podrška,\r\n- Komunikacija sa internet provajderima, kao i prijava i reklamacija usluga,\r\n- Snalaženje u Windows i Linux sistemskom okruženju,\r\n- Ažuriranje, održavanje, reparacija hardvera.\r\n\r\nOBRAZOVANJE I KVALIFIKACIJE\r\n- Srednja stručna sprema,\r\n- Minimum 1 godina iskustva na istim ili sličnim poslovima,\r\n- Poznavanje Windows domenskog okruženja,\r\n- Poznavanje mrežnih, računarskih sistema (MikroTik, TP-Link, Windows OS, Linux),\r\n- Iskustvo sa remote podrškom,\r\n- Spremnost na dežurstvo,\r\n- Timski rad,\r\n- Vozačka dozvola B kategorije', '2020-08-28', '6-System Administrator-2019-08-07.pdf', 'Z'),
(15, 12, 'Prodavac (m/ž)', 'Da li ste spremni da date 110% od sebe da biste sklopili prodaju? Volite li da radite u dinamičnom okruženju sa mnogo izazova? Da li uživate u timskom radu? Ako je Vaš odgovor da, Vi ste osoba koju tražimo.\r\n\r\nVi donosite predanost i …\r\n- želite da prodajete\r\n- stavljate kupce na prvo mesto i pružate im nezaboravno iskustvo\r\n- marljivo radite i aktivan ste timski igrač\r\n- napredujete u radnom i dinamičnom okruženju\r\n- imate motiva i ambicije da izgradite karijeru u JYSKu \r\n- Vi dobijate prilike, a mi Vam nudimo …\r\n- privlačne bonuse kao nagradu za odličnu prodaju i odlične rezultate\r\n- priliku za razvoj i prvorazredne praktične treninge\r\n- šansu da se takmičite, pobeđujete i proslavljate odlične uspehe\r\n- vrhunsku kulturu kompanije\r\n\r\nJYSK se od jedne prodavnice u Danskoj 1979. godine razvio u više od 2500 prodavnica širom sveta. Da bismo obezbedili dalji razvoj, tražimo najbolje zaposlene. Iskoristite ovu odličnu priliku i postanite deo JYSKa. Ako donesete predanost, dobićete mogućnosti.', '2020-10-23', '', 'Z'),
(16, 14, 'Trgovinski aranzer', 'Obaveze i odgovornosti:\r\n- Promovisanje i pozicioniranje Jaffa proizvoda na primarnim i sekundarnim mestima izlaganja\r\n- Praćenje zaliha i asortimana, odgovornost za optimalne zalihe proizvoda u objektima\r\n- Vođenje računa o rokovima trajanja proizvoda\r\n- Održavanje polica i pozicija po jasno definisanim standardima\r\n- Pravilno postavljanje proizvoda tako da budu lako uočljivi i dostupni potrošačima\r\n- Učestvovanje u sprovođenju trade marketing aktivnosti\r\n\r\nUslovi:\r\n- Minimum III stepen stručne spreme ekonomskog, tehnološkog, tehničkog ili društvenog smera\r\n- Vozačka dozvola B kategorije\r\n- Poznavanje rada na računaru\r\n- Visok nivo inicijative i motivacije za postizanjem rezultata\r\n- Dobre komunikacijske sposobnosti\r\n\r\nBiće kontaktirani kandidati koji uđu u uži izbor.', '2020-09-30', '', 'Z'),
(17, 15, 'Analitičar IT sigurnosti', 'Pozicija i odgovornosti:\r\n\r\n- Koordinacija i realizacija operativnih aktivnosti i projekata u domenu unapređenja bezbednosti informacionog sistema\r\n- Operativan rad u tehnologijama iz oblasti IT bezbednosti\r\n- Analiziranje, planiranje i predlaganje implementacije rešenja za poboljšanje IT bezbednosti\r\n- Testiranja i nadgledanja aktivnosti usmerenih ka unapređenju IT bezbednosti (npr. upravljanje ranjivostima i sprovođenje penetration testiranja ICT sistema i sl.)\r\n- Izrada akata sa ciljem poboljšanja nivoa IT bezbednosti (npr. procedura, uputstava i sl.);\r\n- Učešće u primeni zahteva security standarda (ISO27001:2013) i prihvaćene prakse u oblasti informacione bezbednosti\r\n- Procena rizika iz domena sigurnosti informacionog sistema\r\n- Koordinacija, praćenje i rešavanje bezbednosnih incidenata u slučajevima narušavanja sigurnosti informacionog sistema\r\n\r\nNeophodna iskustva, znanja i veštine:\r\n\r\n- Višu, visoku stručnu spremu, ili završene master studije tehničkog ili informatičkog usmerenja\r\n- Minimum 2 godine radnog iskustva na poslovima iz oblasti sigurnosti informacionih sistema\r\n- Poznavanje međunarodnog standarda iz oblasti informatičke sigurnosti (ISO27001:2013)\r\n- Aktivno znanje engleskog jezika\r\n\r\nPoželjna iskustva, znanja i veštine:\r\n\r\n- Posedovanje jednog ili više relevantnih security sertifikata (CompTIA, CISSP i sl.)\r\n- Poznavanje ostalih međunarodnih standarda iz oblasti informatičke sigurnosti (PSI DSS, COBIT i sl.), kao i standarda iz ITSM oblasti (ISO20000-1:2011)\r\n- Posedovanje analitičkih sposobnosti\r\n- Posedovanje komunikacionih veština i sklonost ka timskom radu\r\n- Proaktivnost, otvorenost ka promenama, kao i sklonost ka praćenju novih tehnologija', '2020-09-25', '15-Analitičar IT sigurnosti-2019-08-29.pdf', 'Z'),
(18, 15, 'Senior sistem analitilčar', 'Pozicija i odgovornosti:\r\n- Analiza poslovnih zahteva i izrada funkcionalnih specifikacija\r\n- Dizajniranje aplikativnih rešenja na osnovu poslovnih zahteva i koordiniranje aktivnosti vezanih za implementaciju\r\n- Rad na unapređenju postojećih bankarskih aplikacija\r\n- Pripremanje testnih scenarija, planiranje, organizovanje i koordinacija testiranja\r\n- Saradnja sa internim specijalistima i eksternim partnerima za izradu novih poslovnih rešenja\r\n- Analiza novih poslovnih procesa u kontekstu uključivanja u postojeća aplikativna rešenja\r\n- Definisanje i kontrola ispunjenja ugovorenih SLA parametara koji se odnose na aplikativna rešenja.\r\n\r\nNeophodna iskustva, znanja i veštine:\r\n- Visoka stručna spremu, odnosno završene master studije tehničko-informatičkog ili ekonomskog usmerenja\r\n- Minimum 2 godine radnog iskustva na sličnim ili istim poslovima\r\n- Iskustvo u procesu poslovne analize\r\n- Dobro poznavanje bankarskih procesa i relevantnih tehnologija\r\n- Praktično znanje u radu sa Microsoft SQL-a i T-SQL-a\r\n- Aktivno znanje engleskog jezika.\r\n\r\nPoželjna iskustva, znanja i veštine:\r\n- Razvijena sposobnost uspešne komunikacije i saradnje sa različitim profilima korisnika\r\n- Sklonost timskom radu\r\n- Izražene analitičke sposobnosti\r\n- Prilagodljivost i otvorenost ka promenama, kao i sklonost ka praćenju novih tehnologija\r\n- Spremnost na kontinuirano profesionalno i lično usavršavanje i učenje.', '2020-09-25', '', 'Z'),
(19, 15, 'Specijalista za IKT sisteme', 'Pozicija i odgovornosti:\r\n- Obavlja poslove najveće složenosti na polju razvoja, unapređenja, nadogradnje i održavanja infrastrukture informaciono-komunikacionog sistema (IKT sistema) Banke, sa ciljem primene novih tehnologija i rešenja \r\n- Analizira arhitekturu serverske infrastrukture i predlaže unapređenja u vidu primene novih infrastrukturnih rešenja, uvođenje novih sistemskih  kao i  optimizaciju upotrebe postojećih resursa \r\n- Učestvuje u rešavanju problema i incidenata najveće složenosti u okviru serverske infrastrukture, samostalno, kao i u saradnji sa ostalim IT timovima i eksternim partnerima\r\n- Bavi se projektovanjem, instalacijom, konfigurisanjem, održavanjem i nadgledanjem serverske infrastrukture i sistemskog softvera Banke\r\n- Planira i realizuje poslove sa ciljem da se obezbedi visoka raspoloživost serverske infrastrukture\r\n- Učestvuje u procesima nabavke serverske opreme i sistemskog softvera i uvođenja novih tehnologija i rešenja\r\n- Učestvuje u planiranju budžeta (CAPEX, OPEX) za troškove implementacije IKT rešenja\r\n- Učestvuje u utvrđivanju standarda iz domena IKT infrastrukture\r\n\r\nNeophodna iskustva, znanja i veštine:\r\n\r\nMinimum 7 godina iskustva u radu sa:\r\n- Serverskom i ostalom IKT infrastrukturom\r\n- Virtuelnom infrastrukturom\r\n- Windows i Linux operativnim sistemima\r\n- Sistemskim softverom različitih vendora\r\n- Microsoft tehnologijama (AD, MS cluster, MS Exchange, MS SharePoint, IIS…)\r\n\r\nPoželjna iskustva, znanja i veštine: \r\n- SAN i storage tehnologijama\r\n-  Poznavanje principa softverskih rešenja za backup\r\n- IBM WebSphere, Document Management sistem, poznavanje komunikacionih tehnologija (Cisco i drugi vendori)\r\n- Poznavanje principa IT bezbednosti', '2020-10-09', '', 'Z'),
(20, 15, 'Sistem inženjer', 'Pozicija i odgovornosti:\r\n\r\n- Poslovi instalacije, konfiguracije i održavanja serverske infrastrukture i sistemskog softvera, monitoring, rešavanje incidenata i problema\r\n- Realizacija planiranih poslova sa ciljem da se obezbedi visoka raspoloživost serverske infrastrukture i infrastrukturnih servisa\r\n- Podrška u radu ostatka IKT infrastrukture i podrška ostalim timovima u okviru IT funkcije\r\nNeophodna iskustva, znanja i veštine:\r\n\r\nMinimum 3 godine iskustva u radu sa:\r\n\r\n- Serverskom i ostalom IKT infrastrukturom\r\n- Virtuelnom infrastrukturom\r\n- Windows i Linux operativnim sistemima\r\n- Sistemskim softverom različitih vendora\r\n- Microsoft tehnologijama (AD, MS cluster, MS Exchange, MS SharePoint, IIS…)\r\n- Poželjna iskustva, znanja i veštine:\r\n\r\nSAN i storage tehnologijama\r\n- poznavanje principa softverskih rešenja za backup\r\n- poznavanje koncepata IT bezbednosti', '2020-09-25', '', 'P'),
(21, 16, 'Product owner', 'Your job will be:\r\n- Take lead of production team as the Product Owner\r\n- Providing vision and direction to the development team and stakeholders throughout the project and create requirements\r\n- Plan and prioritize product feature backlog and development for the product\r\n- Define product vision, road-map and growth opportunities\r\n- Assess value, develop cases, and prioritize stories, epics and themes to ensure work focuses on those with maximum value that are aligned with product strategy\r\n- Provide backlog management, iteration planning, and elaboration of the user stories\r\n- Work closely with Product Management to create and maintain a product backlog according to business values\r\n- Lead the planning product release plans and set expectation for delivery of new functionalities and features\r\n- Plan, monitoring and reporting about development costs\r\n- Provide an active role in mitigating impediments impacting successful team completion of\r\n- Release/Sprint GoalsResearch and analyze market, the users, and the roadmap for the product\r\n- Follow our competitors and the industry in all aspects of business', '2020-09-26', '', 'P'),
(22, 16, 'Data Analyst', 'Your job will be:\r\n- Execution of data validation, profiling, auditing and data cleansing activities\r\n- Collaboration with internal stakeholders\r\n- Development, production and management of data quality reports\r\n- Development of key metrics, rules and notifications to identify critical gaps\r\n- Identify opportunities for business process improvements\r\n- Present KPIs to Senior Management to a regular basis\r\n- Support all Editorial, Marketing and Sales data requests\r\n- Standardization and automation of data collection and processing\r\n- Implementation of necessary analytics and tracking codes for correct measurements\r\n\r\nIt’s important that you have:\r\n- (Minimum) Google Analytics Certified individual\r\n- Experience with using a range of data analysis tools\r\n- Advanced analytics capability is a preferred skill\r\n- Ideally experience with Gemius Prizm\r\n- Advanced excel a must; Pivot Tables, Macros preferred, and Google Tag Manager\r\n- Ability to handle large volume of data and doing cross-analysis\r\n- Sense of ownership and pride in your performance and its impact on company’s success\r\n- Critical thinker\r\n- Team player\r\n- Good time-management skills\r\n- Great interpersonal and communication skills', '2020-09-26', '', 'P'),
(23, 16, 'Key Account Manager', 'Some things you’ll be doing:  \r\n- Growing and developing existing clients, together with effective on-boarding of new clients within the assigned industry\r\n- Creating business proposals for new and existing clients and monitoring their marketing strategy\r\n- Establishing and maintaining strong, long-lasting relationship with clients’ stakeholders and continually providing high standard of customer service\r\n- Maintaining strong collaboration with media agencies in charge of clients from the assigned industry\r\n- Communicating clearly the progress of established targets within the assigned industry to internal stakeholders\r\n- Submitting reports in a timely, organized manner\r\n\r\nProfile we are looking for:\r\n- Outstanding communication skills\r\n- Experience in Digital Advertising\r\n- Experience/knowledge of advertising sales\r\n- Highly persistent and skilled at negotiating, goal-driven\r\n- Excellent presentation skills in order to deliver ideas to clients\r\n- Ability to think through and solve problems methodically\r\n- Team player\r\n- High degree of personal integrity and professionalism\r\n- University degree (preferred)\r\n\r\nWe offer: \r\n- Working in dynamic and collaborative environment\r\n- Opportunities for learning and development\r\n- Permanent employment', '2020-09-27', '', 'P'),
(24, 17, 'Magacioner', 'Osnovni uslovi:\r\n- SSS  (minimum III stepen)\r\n- Poželjno prethodno radno iskustvo u magacinskim poslovima\r\n- Brzina i tačnost\r\n- Profesionalnost i pedantnost u radu\r\n- Orijentacija ka timskom radu\r\n\r\nDodatni uslovi:\r\n- Poželjno iskustvo u upravljanju viljuškarom i posedovanje sertifikata za bezbedno upravljanje viljuškarom\r\n- Poželjno iskustvo u automobilskoj industriji \r\n\r\nOpis radnog mesta:\r\n- Prijem repromaterijala i gotovih proizvoda i provera tačnosti isporučenih količina\r\n- Raspoređivanje materijala u magacinu\r\n- Preuzimanje robe iz magacina i popunjavanje proizvodnih linija\r\n- Smeštanje gotovih proizvoda u otpremnu zonu\r\n- Pakovanje gotovih proizvoda prema utvrđenoj proceduri\r\n- Priprema isporuke gotovih proizvoda\r\n- Istovar i utovar robe\r\n\r\nNudimo:\r\n- Zaposlenje u renomiranoj svetskoj kompaniji;\r\n- Stalne obuke i treninge;\r\n- Mogućnost napredovanja', '2020-09-28', '', 'P'),
(25, 17, 'Majstor u održavanju', 'Uslovi:\r\n- SSS (III - IV stepen) – mehatroničar, elektroničar, precizni mehaničar, električar\r\n- Potrebno poznavanje elektro i pneumatskih šema\r\n- Poželjno poznavanje automatike i robotike\r\n\r\nProfil kandidata:\r\n- Samostalnost u radu (sposobnost rešavanja problema na opremi, od najjednostavnijih do najsloženijih)\r\n- Profesionalnost i pedantnost u radu\r\n- Orijentacija ka timskom radu\r\n\r\nOpis radnog mesta:\r\n- Izvršavanje aktivnosti preventivnog i korektivnog održavanja proizvodne opreme u proizvodnji\r\n- Kontrolisanje, održavanje i popravljanje mašina i opreme kako bi se uspešno održao kontinualni proces proizvodnje\r\n- Izvođenje popravki na opremi (demontaže, dijagnostike, montaže, inspekcije, testiranja...)\r\n- Vršenje hitnih intervencija na elektro, mehaničkim i pneumatskim sistemima\r\n- Podešavanje elektronskih, električnih i mehaničkih delova mašina i praćenje radnih parametara\r\n- Instaliranje i testiranje nove opreme u skladu sa instrukcijama\r\n\r\nNudimo:\r\n- Specijalističke obuke i treninge, (obavezno za sve novozaposlene)\r\n- Rad u timu sa iskusnim majstorima\r\n- Mogućnost napredovanja\r\n- Zaposlenje u renomiranoj svetskoj kompaniji', '2020-09-30', '', 'P'),
(26, 17, 'Radnik na dostavi materijala', 'Aptiv je globalni lider i jedna od najvećih svetskih kompanija u oblasti dobavljača automobilske industrije. Isporukom naprednih električnih i elektronskih delova, pogonskih sklopova i bezbedonosnih tehnologija čini da vozila budu bezbedna, ekološki ispravna i pametna.\r\n\r\nUslovi:\r\n- Minimum osnovno obrazovanje\r\n- Spremnost za rad u tri smene\r\n- Posvećenost i odgovornost u radu\r\n\r\nProfil kandidata:\r\n- Poseduje želju za radom u dinamičnom okruženju\r\n- Teži postizanju što boljih rezultata\r\n- Poseduje pozitivan i profesionalan stav\r\n- Želi da radi u timu\r\n\r\nOpis radnog mesta:\r\n- Snadbevanje proizvodne linije materijalom potrebnim za proizvodni proces – dostava provodnika\r\n- Obavljanje procesa dostave prema uputstvima i procedurama\r\n- Sortiranje žica prema standardu kompanije\r\n- Održavanje radnog mesta i radnog prostora\r\n\r\nMi Vam nudimo:\r\n- Dinamičan posao u vodećoj, svetski priznatoj kompaniji\r\n- Priliku za profesionalni i lični razvoj u internacionalnom okruženju', '2020-09-30', '', 'P'),
(27, 17, 'Radnik u proizvodnji', 'Osnovni uslovi:\r\n- Minimum osnovna škola \r\n- Preciznost i spretnost\r\n- Spremnost za rad u tri smene\r\n- Orijentacija ka timskom radu\r\n- Posvećenost i odgovornost u radu\r\n\r\nDodatni uslovi:\r\n- Iskustvo u industriji kablova je prednost\r\n\r\nOsnovna zaduženja:\r\n- Izvršavanje radnih operacija na liniji za proizvodnju električnih instalacija za automobile\r\n\r\nMi nudimo:\r\n- Zanimljiv posao u vodećoj, svetski priznatoj kompaniji\r\n- Priliku za profesionalni i lični razvoj u internacionalnom okruženju\r\n- Redovna i stabilna primanja', '2020-09-30', '', 'P'),
(28, 17, 'Viljuškarista', 'Osnovni uslovi:\r\n- SSS (minimum III stepen)\r\n- Poželjno prethodno radno iskustvo u magacinskim poslovima\r\n- Brzina i tačnost\r\n- Profesionalnost i pedantnost u radu\r\n- Timski rad\r\n- Dozvola/sertifikat za upravljanje viljuškarom\r\n- Iskustvo u upravljanju viljuškarom\r\n \r\nOpis radnog mesta:\r\n- Prijem repromaterijala i gotovih proizvoda i provera tačnosti isporučenih količina\r\n- Raspoređivanje materijala u magacinu\r\n- Preuzimanje robe iz magacina i popunjavanje proizvodnih linija\r\n- Smeštanje gotovih proizvoda u otpremnu zonu\r\n- Pakovanje gotovih proizvoda prema utvrđenoj proceduri\r\n- Priprema isporuke gotovih proizvoda\r\n- Istovar i utovar robe\r\n- Upravljanje viljuskarom\r\n- Odgovornost za viljuškar- urednost i ispravnost viljuškara', '2020-09-30', '', 'P'),
(29, 18, 'Ekspert koordinator za IT poslovne sisteme', 'Tražimo ljude u kojima se rađaju ideje i koji će zajedno sa nama uložiti energiju da se one zaista ostvare. Tvoje ideje i energija su pokretač naše zajedničke budućnosti.\r\n\r\nTI SADA\r\n\r\n- Poseduješ iskustvo u implementaciji i podršci IT sistemima\r\n- Poželjno je da imaš iskustvo u upravljanju IT projektima\r\n- Odlično poznaješ rad u MS Office-u\r\n- Govoriš engleski jezik\r\n- Imaš visoko obrazovanje tehničkog usmerenja\r\n\r\nTI U NIS-u\r\n\r\n- Prikupljaš i analiziraš biznis zahteve za unapređenje funkcionalnosti u IT sistemima, u okviru dela kompanije koji se bavi prodajom\r\n- Učestvuješ u implementaciji novih IT sistema\r\n- Izrađuješ tehničke zadatke u skladu sa tehničkim zahtevima i potrebama biznisa\r\n- Organizuješ i realizuješ testiranje izmena\r\n- Izrađuješ korisnička uputstva, organizuješ i učestvuješ u obukama za rad u IT sistemima\r\n- Pružaš podršku korisnicima IT sistema\r\n- Koordiniraš i pratiš rad dobavljača', '2020-09-26', '', 'P'),
(30, 18, 'Vodeći analitičar', 'Tražimo ljude u kojima se rađaju ideje i koji će zajedno sa nama uložiti energiju da se one zaista ostvare. Tvoje ideje i energija su pokretač naše zajedničke budućnosti.\r\n\r\nTI SADA\r\n\r\n- Poseduješ iskustvo u analizi strategije poslovanja\r\n- Govoriš engleski jezik\r\n- Poželjno je da govoriš ruski jezik\r\n- Aktivno radiš u MS Office-u\r\n- Imaš visoko obrazovanje ekonomskog usmerenja\r\n\r\nTI U NIS-u\r\n\r\n- Analiziraš, modeliraš i formiraš maloprodajne i veleprodajne strategije, strategije za proizvodne linije, strategije logistike i sistema skladištenja naftnih derivata na lokalnom i inostranom tržištu\r\n- Analiziraš ostvarenje strateških pokazatelja i vršiš monitoring cena naftnih derivata maloprodajnog i veleprodajnog kanala prodaje\r\n- Sastavljaš izveštaje i prezentacione materijale u cilju donošenja strateških odluka po oblastima biznis strategija', '2020-10-15', '', 'P'),
(31, 18, 'Poslovni asistent', 'Tražimo ljude u kojima se rađaju ideje i koji će zajedno sa nama uložiti energiju da se one zaista ostvare. Tvoje ideje i energija su pokretač naše zajedničke budućnosti.\r\n\r\nTI SADA\r\n\r\n- Odlično govoriš engleski jezik\r\n- Poželjno je da govoriš ruski jezik\r\n- Poznaješ rad u MS Office-u\r\n- Imaš diplomu visokog obrazovanja\r\n\r\nTI U NIS-u\r\n\r\n- Pružaš administrativnu i tehničku podršku u cilju bolje organizacije rada\r\n- Pružaš komunikacijsku podršku drugim organizacionim jedinicama\r\n- Organizuješ sastanke, prikupljaš i pripremaš materijale\r\n- Izrađuješ prezentacije i izveštaje i prevodiš po potrebi', '2020-09-30', '', 'P'),
(32, 18, 'Koordinator za analitiku i izveštavanje', 'Tražimo ljude u kojima se rađaju ideje i koji će zajedno sa nama uložiti energiju da se one zaista ostvare. Tvoje ideje i energija su pokretač naše zajedničke budućnosti.\r\n\r\nTI SADA\r\n\r\n- Odlično poznaješ rad u Excel-u i imaš iskustva u radu u Power Point-u.\r\n- Imaš izražene analitičke veštine i obračaš pažnju na detalje.\r\n- Proaktivan si i uspešno funkcionišeš u timu.\r\n- Govoriš engleski ili ruski jezik.\r\n- Poseduješ diplomu visokog obrazovanja.\r\n- Poželjno je da imaš iskustvo u izradi izveštaja i analiziranju podataka.\r\n- Poželjno je da imaš iskustvo rada u SAP-u.\r\n\r\nTI U NIS-u\r\n\r\n- Izrađuješ izveštaje funkcije za nabavku na nedeljnom i godišnjem nivou, kao i za potrebe drugih organizacionih delova.\r\n- Učestvuješ u kreiranju novih i razvoju i automatizaciji postojećih izveštaja.\r\n- Usaglašavaš nabavne parametre za nabavke u okviru investicionih projekta.\r\n- Utvrđuješ kritične tačke u postojećim procesima u oblasti nabavke i planiranja.\r\n- Predlažeš rešenja za unapređenja poslovanja.', '2020-09-30', '', 'Z'),
(33, 18, 'Marketing ekspert za razvoj prodajnih akcija', 'Tražimo ljude u kojima se rađaju ideje i koji će zajedno sa nama uložiti energiju da se one zaista ostvare. Tvoje ideje i energija su pokretač naše zajedničke budućnosti.\r\n\r\nTI SADA\r\n\r\n- Imaš višegodišnje radno iskustvo u oblasti marketinga u FMCG industriji i/ili marketinškim agencijama\r\n- Pratiš trendove u oblasti marketinga i primenjuješ ih u praksi\r\n- Odlično poznaješ rad u MS Excel-u i Power Point-u\r\n- Odlično govoriš engleski jezik\r\n- Imaš visoko obrazovanje\r\n\r\nTI U NIS-u\r\n\r\n- Učestvuješ u kreiranju marketing strategije i razvoju potrošačkih brendova kompanije\r\n- Organizuješ lokalne i nacionalne marketing kampanje potrošačkih brendova i evaluiraš njihovu efikasnost\r\n- Sarađuješ sa B2B departmanom, marketinškim, medijskim i istraživačkim agencijama i produkcijskim kućama\r\n- Upravljaš budžetom za marketing akcije potrošačkih brendova\r\n- Pokrećeš i realizuješ procese marketing nabavki\r\n- Izveštavaš o realizovanim marketing akcijama', '2020-09-30', '', 'Z'),
(34, 19, 'Digital marketing specialist', 'Digitalni specijalista kao sastavni deo marketinškog tima aktivno radi na unapredjenju digitalnog marketinga i komunikacija. \r\n\r\nUloga Digitalnog specijaliste:\r\n\r\n- razvija strategiju digitalnog marketinga i strategiju sadržaja/kao dela opšte strategije brenda,\r\nidentifikuje strateške programe, partnerstva i digitalne mogućnosti u podršci strategije digitalnog marketinga,\r\n- razvija, analizira, upravlja digitalnim ekosistemom brenda u smislu digitalnih platformi kao što su web portali, mobilne aplikacije, IOT uređaji,\r\n- Operativno sprovodi planove za podizanje svesti, kontinuiranog angažovanja i zalaganja, uključujući stvaranje jakih omni-kanalnih i besprekornih iskustava u kupovini,\r\n- razvija, socijalizuje i primenjuje digitalne marketinške strategije i planove putem e-maila, push notifikacija, SMS-a, društvenih i plaćenih digitalnih (SEM, display, itd.) platformi za pokretanje prodaje i povećanje lojalnosti kupaca,\r\n- sarađuje sa drugim timovima za razvoj planova komunikacije i kampanje,\r\nkoristi podatke i uvide kako bi ocenio efikasnost marketinga; nudi savete o budućim strategijama i kampanjama, a donesene zaključke prenosi svim zainteresovanim stranama,\r\nupravlja dodeljenim spoljnim dobavljačima/agencijama kako bi se osiguralo usklađivanje sa strateškom vizijom kompanije i sveobuhvatnom strategijom brenda,\r\n- Vodi saradnju između internih i eksternih radnih grupa za razvoj kreativnih koncepata električne pošte i dizajna,\r\n- iznalazi nove ideje koje pomažu da se preduzeća razvijaju u korak sa tržištem digitalnog marketinga koji je podložan brzim promenama,\r\n- ponosni je ambasador naših brendova koji uključuje našu publiku, stvara iskustvo i pokazuje ponašanje brendova, interno i eksterno.\r\n \r\nPravi kandidat:\r\n- Poseduje diplomu fakulteta marketinškog ili srodnog usmerenja;\r\n- Ima minimum 2 godine relevantnog radnog iskustva u digitalnom marketingu;\r\n- Poseduje znanje o digitalnim marketing strategijama, kanalima, kao i o građenju brenda;\r\n- Odlično govori engleski jezik;\r\n- Napredni je korisnik MS Office paketa;\r\n- Poseduje vozačku dozvolu B kategorije;', '2020-09-30', '', 'Z'),
(35, 19, 'Espreso asistent', 'Uloga Espreso asistenta:\r\n\r\n- Priprema, analiza i izrada izveštaja za prodaju i marketing;\r\n- Odgovoran za evidenciju i pracenje  i implementaciju POSM  i materijala za brendiranje objekata na tržištu;\r\n- Učestvovanje u kreiranju planova;\r\n- Vodjenje izdavanja, tabelarno praćenje i upis espresso opreme kao i izveštavanje;\r\n- Administrativna podrska prema distributeru  (otvaranje novih kupaca);\r\n- Kontrola i pracenje marketing investicija kod kupaca;\r\n- Kontrola ispravnosti ugovora i menica kao i arhiviranje;\r\n- Praćenje, kreiranje i izdavanja Knjižnih odobrenja;\r\n- Praćenje utuženo – naplaćeno;\r\n- Izveštavanje prema potrebi;\r\n \r\nPravi kandidat:\r\n\r\n- Ima prethodnog radnog iskustva u administraciji prodaje;\r\n- Poznaje rad u MS Office paketu;\r\n- Poželjno je poznavanje rada u SAP-u, MIS-u, COGNUS-u (Praćenje komadne prodaje i povezivanje prodaje sa prodavcima);\r\n- Analitičan je, proaktivan i komunikativan', '2020-09-30', '', 'P'),
(36, 19, 'Unapređivač prodaje', 'Uloga unapređivača prodaje:\r\n\r\n- Pozicionira, obeležava i postavlja reklamni materijal kod kupaca na dodeljenom terenu u skladu sa standardima izlaganja;\r\n- Posećuje postojeće kupce u skladu sa rut kartom i daje informacije za ažuriranje rut karte;\r\n- Brine o rokovima izloženih proizvoda kod kupaca na dodeljenom terenu iz portfolia Atlantic - Grupe i odgovara za primenu FIFO standarda izlaganja na definisanim primarnim i sekundarnim pozicijama;\r\n- Vodi računa o optimalnim zalihama proizvoda u objektima, sprovodi strategije cenovne politike i vodi računa da ispod svakog proizvoda bude adekvatna cena;\r\n- Brine o zastupljenosti artikala iz obaveznog asortimana i sprovodi planograme izlaganja u skladu sa tržišnim učešćem, izlaže proizvode na primarnim i sekundarnim mestima izlaganja;\r\n- Realizuje planirane marketinške aktivnosti i prati aktivnosti konkurencije o novim proizvodima, marketinškim akcijama, promenama cena i ostalim aktivnostima u prodajnim objektima;\r\n- Gradi pozitivne odnose sa kupcima koji će omogućiti bolju pozicioniranost i prodaju;\r\n- Uspešno izvršava potrebne administrativne zadatke u zadatom roku;\r\n- Izveštava o odrađenim aktivnostima i o svim bitnim informacijama svog neposrednog  rukovodioca;\r\n- Prijavljuje svom nadredjenom oštećene proizvode i brine o čistoći prodajne pozicije;\r\n- Obavlja i ostale nepomenute srodne poslove po nalogu neposrednog rukovodioca.\r\n\r\nPravi kandidat:\r\n\r\n- III/IV stepen stručne spreme opšteg usmerenja;\r\n- Ima prethodno relevantno iskustvo rada u FMCG industriji u minimalnom trajanju od dve godine;\r\n- Ljubaznost, komunikativnost i saradljivost;\r\n- Odgovornost, brzina i veština u obavaljanju dnevnih zadataka;\r\n- Samoinicijativnost i predlaganje novih rešenja;\r\n- Vozačka dozvola B kategorije, aktivan vozač;\r\n- Osnovi nivo poznavanja rada na računaru.', '2020-09-30', '', 'Z'),
(37, 19, 'Stariji kontroler poslovnog kontrolinga', 'Uloga Starijeg kontrolera poslovnog kontrolinga:\r\n- Samostalna analiza izvještaja o marketinškim ulaganjima te o prodaji na svim gross-to-net razinama i na svim relevantnim dimenzijama (kupac, kanal, brand)\r\n- Samostalna analiza izvještaja bruto marže Strateškog Poslovnog Područja Pića i njezinih pojedinih komponenti\r\n- Izrada ex-ante kalkulacija isplativosti te ex-post analiza uspješnosti marketinških ulaganja\r\n- Izrada potrebnih analiza i kalkulacija vezanih uz lansiranje novih proizvoda / dovođenje novih principala te sudjelovanje u projektnom timu prilikom lansiranja novih proizvoda\r\n- Sudjelovanje u pripremi kvartalne prezentacije SPP Pića u području prodaje, marketinških ulaganja i bruto marže\r\n- Sudjelovanje u planskim procesima (l/t plan, budget, forecast) SPP Pića u području planiranja prodaje i marketinških ulaganja\r\n- Samostalna izrada kalkulacija profitabilnosti po SKU, kupcu i tržištu u području bruto marže\r\n- Analiza izračuna i određivanje problematičnih područja\r\n- Samostalna izrada analiza tržišta i konkurenata te izrada benchmarking analiza\r\n- Samostalna izrada raznih ad hoc analiza prilikom pregovaranja s kupcima, kao podrška određenim projektima te po nalogu direktno nadređenog\r\n- Aktivno sudjelovanje u procesima odobrenja i revizije transfernih cijena\r\n- Sudjelovanje u projektima automatizacije izvještaja iz područja poslovnog kontrolinga\r\n \r\nProfil pravog kandidata:\r\n- Minimalno tri godine relevantnog radnog iskustva u kontrolingu/financijskoj analizi u trgovini\r\n- Napredno poznavanje MS Office paketa\r\n- Napredno znanje engleskog jezika u govoru i pismu\r\n- Posvećenost detaljima i analitičko razmišljanje\r\n- Timski duh i otvorenost prema suradnji s drugim odjelima (posebice marketinga i prodaje)\r\n- Jake komunikacijske vještine i asertivnost\r\n- Proaktivno razmišljanje u smjeru ostvarivanja rezultata\r\n- Otvorenost prema implementaciji novih pristupa', '2020-09-30', '', 'Z'),
(38, 6, 'Google PPC Specialist', 'Šta očekujemo od tebe?\r\n- Kreiranje novih kampanja i grupa oglasa u okviru Google ADS platforme.\r\n- Upravljanje i optimizacija Google ADS kampanja i svih njenih komponenti.\r\n- Kreiranje strategije za Google ADS kampanje uključujući analizu ključnih reči i tržišta.\r\n- Implementacija strategije nastupa i odabir odgovarajućih tipova kampanja, uvezivanje i analiza kroz Google Analitycs.\r\n- Praćenje i korekcija budžeta kako bi se ostvario što bolji ROI.\r\n- Kreiranje izveštaja zajedno sa predlozima za poboljšanje i unapređenje kampanja.\r\n- Napredno znanje u praćenju konverzija i analizi ključnih reči.\r\n- Kreiranje ciljeva i praćenje celog procesa kroz sales funnel-e.\r\n- Pisanje i kreiranje prodajno orjentisanih i interesantnih tekstova za oglase.\r\n- Napredno poznavanje u kreiranju dinamičkih i remarketing kampanja.\r\n- Poznavanje osnovnih pravila u kreiranju landing stranica.\r\n- Poznavanje Google TAG Managera.\r\n\r\nPoželjne sposobnosti i kvalifikacije:\r\n- Najmanje 2 godine iskustva u radu sa Google ADS kampanjama.\r\n- Poznavanje i praćenje digital marketing trendova, novih tehnologija i alata.\r\n- Samoinicijativa, preciznost i odgovornost.\r\n- Razvijene copywriting veštine\r\n- Poznaje koncept i važnost SEO\r\n- Google ADS sertifikat (search i display poželjno)\r\n\r\nŠta mi nudimo tebi?\r\n- Mlado i dinamično radno okruženje\r\n- Petodnevnu radnu nedelju\r\n- Mogućnost brzog usavršavanja, razvoja i napredovanja\r\n- Fleksibilnost u radu, međusobno uvažavanje i razumevanje', '2020-09-30', '', 'Z'),
(39, 6, 'Social Media Manager', 'Potrebne veštine:\r\n- Najmanje 2 godine iskustva na istoj ili sličnoj poziciji u agenciji ili in-house\r\n- Samostalan rad u Facebook Business Manager i Ads Manager, Linkedin Campaign Manager, Microsoft Office paketu (Excel, Power Point, Word) – Photoshop je veliki plus!\r\n- Potpuno poznavanje rada u digitalnom marketingu (targetiranje, kreiranje i setovanje kampanja, analitika, izveštavanje)\r\n- Near-native poznavanje engleskog jezika\r\n- Visok stepen kreativnosti u kompletnom osmišljavanju nastupa brenda na društvenim mrežama (copywriting, visual identity)\r\n- Prezentacione i komunikacione veštine\r\n- Fleksibilnost, nezavisnost i funkcionisanje u timu\r\n\r\nOpis posla:\r\n- Osmišljavanje i kreiranje sadržaja na društvenim mrežama za klijente različitih branši\r\n- Učestvovanje u realizaciji strategije i digitalnog nastupa, setovanje i praćenje kampanja na društvenim mrežama Facebook, Instagram i Linkedin\r\n- Operativno komuniciranje sa dodeljenim klijentima na dnevnom nivou\r\n- Evaluacija i analiza konkurencije i industrije na tržištu za klijentov brend\r\n- Proaktivnost u razvoju nastupa klijentovog biznisa na društvenim mrežama predlaganjem adekvatnih mesečnih i kvartalnih dodatnih aktivnosti\r\n- Izveštavanje i analiziranje postignutih rezultata na mesečnom, kvartalnom i godišnjem nivou\r\n- Analitičko rasuđivanje i izrada kreativna rešenja u saradnji sa dizajnerskim timom\r\n\r\nŠta mi nudimo:\r\n- Rad u timu od šestoro mladih, pasioniranih i vrednih marketara koji neprestano uče i trude se da naprave razliku u svetu digitalnog marketinga\r\n- Mogućnost stalnog zaposlenja uz napredovanje', '2019-09-30', '', 'Z'),
(40, 6, 'Team Manager', 'Ako posedujete osobine:\r\n- želju, motivisanost i sposobnost za rad u timu\r\n- sposobnost uspostavljanja kontakata i lakoću komunikacije\r\n- preduzetnički duh, samoinicijativu i posvećenost ostvarivanju ciljeva \r\n\r\nAko ste spremni da radite na način, što uključuje:\r\n- planiranje i realizaciju prodajnih aktivnosti\r\n- predstavljanje proizvoda potencijalnim kupcima\r\n- praćenje zadovoljstva postojećih kupaca\r\n- kreiranje izveštaja, analizu i iznošenje ideja i predloga\r\n- formiranje, koordinaciju i vođenje prodajnog tima\r\n\r\nMi vam nudimo:\r\n- ugovor o radu\r\n- fiksnu platu i bonuse za ostvarene rezultate\r\n- stručnu obuku i kontinuiranu edukaciju\r\n- slobodu u radu\r\n- profesionalnu podršku i sredstva za uspešan rad\r\n- mogućnost internacionalne karijere', '2019-09-30', '', 'Z');

-- --------------------------------------------------------

--
-- Table structure for table `konkurs_prijava`
--

DROP TABLE IF EXISTS `konkurs_prijava`;
CREATE TABLE IF NOT EXISTS `konkurs_prijava` (
  `idPrijava` int(11) NOT NULL AUTO_INCREMENT,
  `idKonkurs` int(11) NOT NULL,
  `idStudent` int(11) NOT NULL,
  `idCV` int(11) NOT NULL,
  `datumPrijave` date DEFAULT NULL,
  `ocenaPrijave` int(11) DEFAULT NULL,
  `izabran` char(1) CHARACTER SET utf8 DEFAULT NULL,
  `datumIzbora` date DEFAULT NULL,
  PRIMARY KEY (`idPrijava`),
  KEY `idKonkursPrijava_idx` (`idKonkurs`),
  KEY `idStudentPrijava_idx` (`idStudent`),
  KEY `idCVPrijava_idx` (`idCV`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `konkurs_prijava`
--

INSERT INTO `konkurs_prijava` (`idPrijava`, `idKonkurs`, `idStudent`, `idCV`, `datumPrijave`, `ocenaPrijave`, `izabran`, `datumIzbora`) VALUES
(8, 34, 1, 10, '2019-08-29', 0, 'C', NULL),
(9, 36, 1, 10, '2019-08-29', 0, 'C', NULL),
(10, 22, 5, 11, '2019-08-29', 4, 'N', '2019-08-29'),
(11, 36, 24, 12, '2019-08-29', 0, 'C', NULL),
(12, 32, 23, 13, '2019-08-29', 0, 'C', NULL),
(13, 30, 22, 14, '2019-08-29', 0, 'C', NULL),
(14, 31, 22, 14, '2019-08-29', 0, 'C', NULL),
(15, 23, 22, 14, '2019-08-29', 7, 'C', NULL),
(16, 22, 22, 14, '2019-08-29', 5, 'N', '2019-08-29'),
(17, 31, 5, 11, '2019-08-30', 0, 'C', NULL),
(18, 38, 1, 10, '2019-08-30', 0, 'C', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
CREATE TABLE IF NOT EXISTS `korisnik` (
  `idKorisnik` int(11) NOT NULL AUTO_INCREMENT,
  `korisnickoIme` varchar(45) CHARACTER SET utf8 NOT NULL,
  `lozinka` varchar(45) CHARACTER SET utf8 NOT NULL,
  `slika` varchar(45) CHARACTER SET utf8 NOT NULL,
  `tip` char(1) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`idKorisnik`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`idKorisnik`, `korisnickoIme`, `lozinka`, `slika`, `tip`) VALUES
(1, 'pera', 'bf676ed1364b5857fba69b5623c81b64', 'pera.png', 'S'),
(2, 'admin', '0192023a7bbd73250516f069df18b500', 'admin.jpg', 'A'),
(4, 'marko', '26c7c9089e23c14396410bbc6675dbdf', 'marko.png', 'S'),
(5, 'marija', '474f5d4cac3a8cef024c0b4bece7a592', 'marija.png', 'S'),
(6, 'netlogic', '63a9f0ea7bb98050796b649e85481845', 'netlogic.jpg', 'K'),
(10, 'telekom', '63a9f0ea7bb98050796b649e85481845', 'telekom.png', 'K'),
(11, 'telenor', '63a9f0ea7bb98050796b649e85481845', 'telenor.png', 'K'),
(12, 'jysk', '63a9f0ea7bb98050796b649e85481845', 'jysk.png', 'K'),
(13, 'formaideale', '63a9f0ea7bb98050796b649e85481845', 'formaideale.png', 'K'),
(14, 'jaffa', '63a9f0ea7bb98050796b649e85481845', 'jaffa.png', 'K'),
(15, 'kombanka', '63a9f0ea7bb98050796b649e85481845', 'komercijalnabanka.png', 'K'),
(16, 'ringier', '63a9f0ea7bb98050796b649e85481845', 'ringier.jpg', 'K'),
(17, 'Aptiv', '63a9f0ea7bb98050796b649e85481845', 'Aptiv.jpg', 'K'),
(18, 'nis', '63a9f0ea7bb98050796b649e85481845', 'nis.jpg', 'K'),
(19, 'atlantic', '63a9f0ea7bb98050796b649e85481845', 'atlantic.png', 'K'),
(20, 'nikola', 'a646e457db47ad218d6d9d3ce325878b', 'nikola.png', 'S'),
(21, 'sandra', '3fc5586bed4fb9f745500c0605197924', 'sandra.png', 'S'),
(22, 'filip', 'a8222b7d267fa9ecc019b9e90f3c2a22', 'filip.png', 'S'),
(23, 'ivana', '89c2bdabc6857e82f180e757b69a8d4b', 'ivana.png', 'S'),
(24, 'ivan', 'b7727d252be76bc6d142e19315cfc8fd', 'ivan.png', 'S'),
(25, 'bambi', '63a9f0ea7bb98050796b649e85481845', 'bambi.png', 'K'),
(26, 'marina', 'f8d9af6a12bc4131329b9455900b9396', 'marina.png', 'S');

-- --------------------------------------------------------

--
-- Table structure for table `obrazovanje`
--

DROP TABLE IF EXISTS `obrazovanje`;
CREATE TABLE IF NOT EXISTS `obrazovanje` (
  `idObrazovanje` int(11) NOT NULL AUTO_INCREMENT,
  `idCV` int(11) NOT NULL,
  `datumOd` date DEFAULT NULL,
  `datumDo` date DEFAULT NULL,
  `nazivSkole` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `smer` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `grad` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `zemlja` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idObrazovanje`),
  KEY `idCVorazovanje` (`idCV`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `obrazovanje`
--

INSERT INTO `obrazovanje` (`idObrazovanje`, `idCV`, `datumOd`, `datumDo`, `nazivSkole`, `smer`, `grad`, `zemlja`) VALUES
(7, 10, '2001-02-12', '2005-02-12', 'Ekonomska', 'Ekonomski tehnicar', 'Krusevac', 'Rs'),
(8, 10, '2005-02-12', '2010-09-12', 'Medicinska', 'Farmacija', 'Krusevac', 'Srbija'),
(9, 11, '2010-09-01', '2014-06-30', 'Gimnazija', 'Opšti', 'Beograd', 'Srbija'),
(10, 12, '2001-09-01', '2005-06-30', 'Medicinska', 'Farmacija', 'Beograd', 'Srbija'),
(11, 12, '2005-10-01', '2011-10-01', 'Medicinski', 'Opsta', 'Beograd', 'Srbija'),
(12, 13, '2001-07-01', '2005-07-01', 'Gimnazija', 'opsti', 'Beograd', 'Srbija'),
(13, 14, '2010-09-01', '2015-06-30', 'Ekonomska', 'Opsti', 'Beograd', 'Srbija'),
(14, 14, '2015-09-01', '2019-06-30', 'Ekonomski fakultet', 'Finasije', 'Beograd', 'Srbija');

-- --------------------------------------------------------

--
-- Table structure for table `ocena_kompanije`
--

DROP TABLE IF EXISTS `ocena_kompanije`;
CREATE TABLE IF NOT EXISTS `ocena_kompanije` (
  `idOcena` int(11) NOT NULL AUTO_INCREMENT,
  `idKompanija` int(11) NOT NULL,
  `idStudent` int(11) NOT NULL,
  `ocena` int(11) DEFAULT NULL,
  `datum` date DEFAULT NULL,
  PRIMARY KEY (`idOcena`),
  KEY `idKompanijaOcena_idx` (`idKompanija`),
  KEY `idStudentOcena_idx` (`idStudent`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `paket`
--

DROP TABLE IF EXISTS `paket`;
CREATE TABLE IF NOT EXISTS `paket` (
  `idPaket` int(11) NOT NULL AUTO_INCREMENT,
  `idSajam` int(11) NOT NULL,
  `naziv` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `sadrzaj` mediumtext CHARACTER SET utf8,
  `maxBroj` int(11) DEFAULT NULL,
  `cena` int(11) DEFAULT NULL,
  `brojRadionica` int(11) DEFAULT NULL,
  `brojPredavanja` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPaket`),
  KEY `idSajamPaket_idx` (`idSajam`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paket`
--

INSERT INTO `paket` (`idPaket`, `idSajam`, `naziv`, `sadrzaj`, `maxBroj`, `cena`, `brojRadionica`, `brojPredavanja`) VALUES
(1, 1, 'Generalni pokrovitelj', '- štand cetvorostruke veličine\r\n- logo i dve strane u boji u brosuri\r\n- logo na promo majicama trostruke velicine\r\n- video promocija\r\n- 2 predavanja i 1 radionica', 1, 30000, 1, 2),
(2, 1, 'Zlatni pokrovitelj', '- štand trostruke veličine\r\n- logo i dve strane u boji u brosuri\r\n- logo na promo majicama trostruke velicine\r\n- video promocija od 10 minuta\r\n- 1 predavanje i 1 radionica', 2, 25000, 1, 1),
(3, 1, 'Srebrni pokrovitelj', '- štand dvostruke veličine\r\n- logo i dve strane u boji u brosuri\r\n- logo na promo majicama trostruke velicine\r\n- video promocija od 10 minuta\r\n- 2 predavanja', 3, 20000, 0, 2),
(4, 1, 'Bronzani pokrovitelj', '- štand dvotruke veličine\r\n - logo i dve strane u boji u brosuri\r\n - logo na promo majicama sandardne velicine\r\n - video promocija od 3 minuta\r\n - 1 radionica\r\n', 4, 15000, 0, 1),
(5, 1, 'Standardni paket', '- štand jednostruke veličine\r\n- logo i dve strane u boji u brosuri', 0, 10000, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `prijava_dodatak`
--

DROP TABLE IF EXISTS `prijava_dodatak`;
CREATE TABLE IF NOT EXISTS `prijava_dodatak` (
  `idPrijava` int(11) NOT NULL,
  `idDodatak` int(11) NOT NULL,
  PRIMARY KEY (`idDodatak`,`idPrijava`),
  KEY `idPrijavaPD` (`idPrijava`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prijava_dodatak`
--

INSERT INTO `prijava_dodatak` (`idPrijava`, `idDodatak`) VALUES
(5, 3),
(7, 1),
(8, 2),
(8, 3),
(8, 4),
(9, 2),
(9, 3),
(9, 4),
(10, 2),
(10, 4),
(11, 1),
(12, 1),
(12, 2),
(13, 2),
(13, 3),
(14, 2),
(14, 5);

-- --------------------------------------------------------

--
-- Table structure for table `prijava_sajam`
--

DROP TABLE IF EXISTS `prijava_sajam`;
CREATE TABLE IF NOT EXISTS `prijava_sajam` (
  `idPrijava` int(11) NOT NULL AUTO_INCREMENT,
  `idSajam` int(11) NOT NULL,
  `idPaket` int(11) NOT NULL,
  `idKompanija` int(11) NOT NULL,
  `status` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `napomena` mediumtext CHARACTER SET utf8,
  PRIMARY KEY (`idPrijava`),
  KEY `idSajamPrijava_idx` (`idSajam`),
  KEY `idPaketPrijava_idx` (`idPaket`),
  KEY `idKompanijaPrijava_idx` (`idKompanija`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prijava_sajam`
--

INSERT INTO `prijava_sajam` (`idPrijava`, `idSajam`, `idPaket`, `idKompanija`, `status`, `napomena`) VALUES
(5, 1, 3, 10, 'Odobrena', ''),
(6, 1, 4, 11, 'Odobrena', NULL),
(7, 1, 5, 14, 'Odobrena', NULL),
(8, 1, 2, 15, 'Odobrena', NULL),
(9, 1, 5, 16, 'Odobrena', NULL),
(10, 1, 5, 17, 'Odobrena', NULL),
(11, 1, 1, 18, 'Odobrena', NULL),
(12, 1, 4, 19, 'Odobrena', NULL),
(13, 1, 4, 6, 'Cekanje', NULL),
(14, 1, 5, 25, 'Odobrena', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `propratno_pismo`
--

DROP TABLE IF EXISTS `propratno_pismo`;
CREATE TABLE IF NOT EXISTS `propratno_pismo` (
  `idPropratnoPismo` int(11) NOT NULL AUTO_INCREMENT,
  `idPrijava` int(11) NOT NULL,
  `propratnoPismo` mediumtext CHARACTER SET utf8,
  PRIMARY KEY (`idPropratnoPismo`),
  KEY `idPrijavaPropratno_idx` (`idPrijava`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `propratno_pismo`
--

INSERT INTO `propratno_pismo` (`idPropratnoPismo`, `idPrijava`, `propratnoPismo`) VALUES
(7, 8, 'Postovani,\r\n\r\nOvim putem zelim da se prijavim na konkurs.\r\n\r\nSrdacan pozdrav,\r\nPera'),
(8, 9, 'Postovani,\r\n\r\nZelim da se prijavim na konkurs.\r\n\r\nSrdacan pozdrav,\r\nPera'),
(9, 10, 'Postovani,\r\n\r\nZelim da se prijavim na konkurs,\r\n\r\nSrdacan pozdrav,\r\nMarija'),
(10, 11, 'Postovani,\r\n\r\nOvim putem zelim da konkurisem za posao.\r\n\r\nSrdacan pozdrav,\r\nIvan'),
(11, 12, 'Postovani,\r\n\r\nOvim putem zelim da konkursem na vas konkurs.\r\n\r\nSrdacan pozrav,\r\nIvana'),
(12, 13, 'Postovani,\r\n\r\nPrijavljujem se na konkurs.\r\n\r\nSrdacan pozdrav,\r\nFilip'),
(13, 14, 'Postovani,\r\n\r\nPrijavljujem se na konkurs.\r\n\r\nSrdacan pozdrav,\r\nFilip'),
(14, 15, 'Postovani,\r\n\r\nPrijavljujem se na konkurs.\r\n\r\nSrdacan pozdrav,\r\nFilip'),
(15, 16, 'Postovani,\r\n\r\nPrijavljujem se na konkurs.\r\n\r\nSrdacan pozdrav,'),
(16, 17, 'Postovani \r\nPrijavljujem se na konkurs.\r\nMarija'),
(17, 18, 'Postovani,\r\n\r\nPrijavljujem se na vas konkurs za Google PPC Specialistu.\r\n\r\nSrdacan pozdrav,\r\nPera');

-- --------------------------------------------------------

--
-- Table structure for table `radno_iskustvo`
--

DROP TABLE IF EXISTS `radno_iskustvo`;
CREATE TABLE IF NOT EXISTS `radno_iskustvo` (
  `idRI` int(11) NOT NULL AUTO_INCREMENT,
  `idCV` int(11) NOT NULL,
  `datumOd` date DEFAULT NULL,
  `datumDo` date DEFAULT NULL,
  `pozicija` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `kompanija` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `grad` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `zemlja` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `opisZaduzenja` mediumtext CHARACTER SET utf8,
  PRIMARY KEY (`idRI`),
  KEY `idCVradnoiskustvo_idx` (`idCV`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `radno_iskustvo`
--

INSERT INTO `radno_iskustvo` (`idRI`, `idCV`, `datumOd`, `datumDo`, `pozicija`, `kompanija`, `grad`, `zemlja`, `opisZaduzenja`) VALUES
(1, 10, '2005-04-29', '2008-08-28', 'Racunovodja', 'Stadion', 'Beograd', 'Srbija', '- Radno mesto\n- Utovar\n- Ustovar'),
(2, 12, '2011-01-01', NULL, 'Lekar', 'Dom Zdravlja', 'Beograd', 'Srbija', 'Lekar'),
(3, 13, '2007-07-01', '2010-07-01', 'Prodavac', 'Laguna', 'Beograd', 'Srbija', 'Prodaja knjiga\nVodjenje kase\nPredaj pazara');

-- --------------------------------------------------------

--
-- Table structure for table `sajam`
--

DROP TABLE IF EXISTS `sajam`;
CREATE TABLE IF NOT EXISTS `sajam` (
  `idSajam` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `datumOd` date DEFAULT NULL,
  `datumDo` date DEFAULT NULL,
  `osnovneInformacije` mediumtext CHARACTER SET utf8,
  `logo` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `datumStudent` datetime DEFAULT NULL,
  `datumKompanija` datetime DEFAULT NULL,
  PRIMARY KEY (`idSajam`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sajam`
--

INSERT INTO `sajam` (`idSajam`, `naziv`, `datumOd`, `datumDo`, `osnovneInformacije`, `logo`, `datumStudent`, `datumKompanija`) VALUES
(1, 'Sajam Zaposljavanja - 2019', '2020-10-25', '2020-10-27', 'Sajam poslova „JobFair 19 - Kreiraj svoju budućnost!” održaće se 25. i 27. novembra 2019. godine tradicionalno u zgradi Tehničkih fakulteta. Petnaesti put zaredom organizatori Sajma, studenti volonteri, radiće na ostvarivanju zajedničke misije – smanjenju nezaposlenosti među mladima, obezbeđivanju najkvalitetnijeg kadra poslodavcima i uspostavljanju bolje komunikacije između obrazovnog sistema, kompanija koje posluju u našoj zemlji i studenata.\r\n\r\nStudenti će imati priliku da se na jednom mestu upoznaju sa velikim brojem kompanija iz tehničko-tehnoloških i prirodno-matematičkih struka, saznaju više o poslovima i praksama u ponudi, kao i o tome koja znanja i veštine je potrebno da razvijaju kako bi se lakše zaposlili. Kao i proteklih godina, u planu su brojna predavanja, tribine i treninzi koji će studentima pomoći da efikasnije predstave svoje kvalitete poslodavcima. Interesovanje koje su studenti širom Srbije tokom prethodnih godina pokazali za JobFair doprinelo je jačanju nacionalnog karaktera Sajma, stoga ćemo se i ove godine potruditi da što više naših kolega sa svih univerziteta u Srbiji dobije priliku da se uživo predstavi poslodavcima i upozna sa aktuelnim trendovima u svojoj struci.\r\n\r\n„JobFair 19 - Kreiraj svoju budućnost!” vidimo kao izuzetnu priliku za nalaženje posla ili prakse, a ujedno i najbolje mesto za razmenu ideja i informacija o novinama na tržištu rada. Iz tog razloga ćemo se potruditi da ovog novembra što više predstavnika obrazovnih ustanova, kao i kompanija i studenata, doprinese svojim prisustvom i aktivnim učešćem na Sajmu.\r\n\r\nOhrabreni dosadašnjim uspesima, sa istim duhom i vizijom počeli smo da organizujemo i predstojeći „JobFair”. Potrudićemo se da nastavimo tradiciju uspešno organizovanih Sajmova i opravdamo kako Vaše poverenje tako i Vaša očekivanja.', 'logo-sajma.jpg', '2019-10-24 00:00:00', '2019-10-20 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `satnica_kompanija`
--

DROP TABLE IF EXISTS `satnica_kompanija`;
CREATE TABLE IF NOT EXISTS `satnica_kompanija` (
  `idSatnica` int(11) NOT NULL,
  `idKompanija` int(11) NOT NULL,
  PRIMARY KEY (`idSatnica`),
  KEY `idSatnica_idx` (`idSatnica`),
  KEY `idKompanija_idx` (`idKompanija`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `satnica_kompanija`
--

INSERT INTO `satnica_kompanija` (`idSatnica`, `idKompanija`) VALUES
(2, 10),
(13, 10),
(16, 11),
(10, 15),
(12, 15),
(3, 18),
(5, 18),
(15, 18),
(7, 19),
(9, 25);

-- --------------------------------------------------------

--
-- Table structure for table `satnica_sajam`
--

DROP TABLE IF EXISTS `satnica_sajam`;
CREATE TABLE IF NOT EXISTS `satnica_sajam` (
  `idSatnica` int(11) NOT NULL AUTO_INCREMENT,
  `idSajam` int(11) NOT NULL,
  `datum` date DEFAULT NULL,
  `vreme` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `tip` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `mestoOdrzavanja` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `status` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`idSatnica`),
  KEY `idSajamSatnica_idx` (`idSajam`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `satnica_sajam`
--

INSERT INTO `satnica_sajam` (`idSatnica`, `idSajam`, `datum`, `vreme`, `tip`, `mestoOdrzavanja`, `status`) VALUES
(1, 1, '2020-10-25', '10:00', 'Radionica', NULL, 'Slobodna'),
(2, 1, '2020-10-25', '11:00', 'Predavanje', 'Sala 1', 'Rezevisana'),
(3, 1, '2020-10-25', '12:00', 'Predavanje', 'Sala 2', 'Rezevisana'),
(4, 1, '2020-10-25', '13:00', 'Radionica', NULL, 'Slobodna'),
(5, 1, '2020-10-25', '14:00', 'Radionica', 'Sala 1', 'Rezevisana'),
(6, 1, '2020-10-25', '15:00', 'Predavanje', NULL, 'Slobodna'),
(7, 1, '2020-10-26', '10:00', 'Predavanje', 'Sala 3', 'Rezevisana'),
(8, 1, '2020-10-26', '11:00', 'Prezentacija', NULL, 'Slobodna'),
(9, 1, '2020-10-26', '12:00', 'Prezentacija', NULL, 'Rezevisana'),
(10, 1, '2020-10-26', '13:00', 'Radionica', 'Sala 1', 'Rezevisana'),
(11, 1, '2020-10-26', '14:00', 'Radionica', NULL, 'Slobodna'),
(12, 1, '2020-10-26', '15:00', 'Predavanje', 'Sala 1', 'Rezevisana'),
(13, 1, '2020-10-27', '10:00', 'Predavanje', 'Sala 2', 'Rezevisana'),
(14, 1, '2020-10-27', '11:00', 'Prezentacija', NULL, 'Slobodna'),
(15, 1, '2020-10-27', '12:00', 'Predavanje', 'Sala 2', 'Rezevisana'),
(16, 1, '2020-10-27', '13:00', 'Predavanje', 'Sala 3', 'Rezevisana'),
(17, 1, '2020-10-27', '14:00', 'Radionica', NULL, 'Slobodna'),
(18, 1, '2020-10-27', '15:00', 'Predavanje', NULL, 'Slobodna');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `idKorisnik` int(11) NOT NULL,
  `ime` varchar(45) CHARACTER SET utf8 NOT NULL,
  `prezime` varchar(45) CHARACTER SET utf8 NOT NULL,
  `telefon` varchar(45) CHARACTER SET utf8 NOT NULL,
  `email` varchar(45) CHARACTER SET utf8 NOT NULL,
  `godinaStudija` varchar(45) CHARACTER SET utf8 NOT NULL,
  `diplomirao` char(1) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`idKorisnik`),
  KEY `idKorisnik_idx` (`idKorisnik`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`idKorisnik`, `ime`, `prezime`, `telefon`, `email`, `godinaStudija`, `diplomirao`) VALUES
(1, 'Pera', 'Petrovic', '0651234567', 'petarpetrovic@yahoo.com', '2011', 'N'),
(4, 'Marko', 'Nikolic', '0661258988', 'marko@gmail.com', '2010', 'D'),
(5, 'Marija', 'Savic', '0662458997', 'marija@gmail.com', '2010', 'N'),
(20, 'Nikola', 'Petrovic', '0645125899', 'nikola@gmail.com', '2010', 'D'),
(21, 'Sandra', 'Marinkovic', '0651234569', 'sandra@gmail.com', '2011', 'D'),
(22, 'Filip', 'Tripkovic', '0662133699', 'filip@gmail.com', '2014', 'N'),
(23, 'Ivana', 'Simic', '0611234567', 'ivana@gmail.com', '2009', 'N'),
(24, 'Ivan', 'Ivanovic', '0691234567', 'ivan@gmail.com', '2017', 'N'),
(26, 'Marina', 'Savic', '06412345699', 'marina@gmail.com', '2010', 'D');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `administrator`
--
ALTER TABLE `administrator`
  ADD CONSTRAINT `idKorisnik` FOREIGN KEY (`idKorisnik`) REFERENCES `korisnik` (`idKorisnik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `cv`
--
ALTER TABLE `cv`
  ADD CONSTRAINT `idKorisnik_student` FOREIGN KEY (`idKorisnik`) REFERENCES `student` (`idKorisnik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `dodatne_usluge`
--
ALTER TABLE `dodatne_usluge`
  ADD CONSTRAINT `idSajamDodatak` FOREIGN KEY (`idSajam`) REFERENCES `sajam` (`idSajam`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `jezik`
--
ALTER TABLE `jezik`
  ADD CONSTRAINT `idCVjezik` FOREIGN KEY (`idCV`) REFERENCES `cv` (`idCV`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `kompanija`
--
ALTER TABLE `kompanija`
  ADD CONSTRAINT `idDelatnost` FOREIGN KEY (`delatnost`) REFERENCES `delatnost` (`idDelatnost`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idGrad` FOREIGN KEY (`grad`) REFERENCES `grad` (`idGrad`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idKompanije` FOREIGN KEY (`idKorisnik`) REFERENCES `korisnik` (`idKorisnik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `kompjuterske_vestine`
--
ALTER TABLE `kompjuterske_vestine`
  ADD CONSTRAINT `idCVKV` FOREIGN KEY (`idCV`) REFERENCES `cv` (`idCV`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `konkurs`
--
ALTER TABLE `konkurs`
  ADD CONSTRAINT `idKompanijaKonkurs` FOREIGN KEY (`idKompanija`) REFERENCES `kompanija` (`idKorisnik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `konkurs_prijava`
--
ALTER TABLE `konkurs_prijava`
  ADD CONSTRAINT `idCVPrijava` FOREIGN KEY (`idCV`) REFERENCES `cv` (`idCV`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idKonkursPrijava` FOREIGN KEY (`idKonkurs`) REFERENCES `konkurs` (`idKonkurs`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idStudentPrijava` FOREIGN KEY (`idStudent`) REFERENCES `student` (`idKorisnik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `obrazovanje`
--
ALTER TABLE `obrazovanje`
  ADD CONSTRAINT `idCVorazovanje` FOREIGN KEY (`idCV`) REFERENCES `cv` (`idCV`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ocena_kompanije`
--
ALTER TABLE `ocena_kompanije`
  ADD CONSTRAINT `idKompanijaOcena` FOREIGN KEY (`idKompanija`) REFERENCES `kompanija` (`idKorisnik`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idStudentOcena` FOREIGN KEY (`idStudent`) REFERENCES `student` (`idKorisnik`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `paket`
--
ALTER TABLE `paket`
  ADD CONSTRAINT `idSajamPaket` FOREIGN KEY (`idSajam`) REFERENCES `sajam` (`idSajam`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `prijava_dodatak`
--
ALTER TABLE `prijava_dodatak`
  ADD CONSTRAINT `idDodatakPD` FOREIGN KEY (`idDodatak`) REFERENCES `dodatne_usluge` (`idDodatak`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idPrijavaPD` FOREIGN KEY (`idPrijava`) REFERENCES `prijava_sajam` (`idPrijava`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `prijava_sajam`
--
ALTER TABLE `prijava_sajam`
  ADD CONSTRAINT `idKompanijaPrijava` FOREIGN KEY (`idKompanija`) REFERENCES `kompanija` (`idKorisnik`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idPaketPrijava` FOREIGN KEY (`idPaket`) REFERENCES `paket` (`idPaket`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idSajamPrijava` FOREIGN KEY (`idSajam`) REFERENCES `sajam` (`idSajam`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `propratno_pismo`
--
ALTER TABLE `propratno_pismo`
  ADD CONSTRAINT `idPrijavaPropratno` FOREIGN KEY (`idPrijava`) REFERENCES `konkurs_prijava` (`idPrijava`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `radno_iskustvo`
--
ALTER TABLE `radno_iskustvo`
  ADD CONSTRAINT `idCVradnoiskustvo` FOREIGN KEY (`idCV`) REFERENCES `cv` (`idCV`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `satnica_kompanija`
--
ALTER TABLE `satnica_kompanija`
  ADD CONSTRAINT `idKompanija` FOREIGN KEY (`idKompanija`) REFERENCES `kompanija` (`idKorisnik`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idSatnica` FOREIGN KEY (`idSatnica`) REFERENCES `satnica_sajam` (`idSatnica`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `satnica_sajam`
--
ALTER TABLE `satnica_sajam`
  ADD CONSTRAINT `idSajamSatnica` FOREIGN KEY (`idSajam`) REFERENCES `sajam` (`idSajam`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `idStudent` FOREIGN KEY (`idKorisnik`) REFERENCES `korisnik` (`idKorisnik`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
