-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 21, 2024 at 06:09 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbquanlyktx`
--

-- --------------------------------------------------------

--
-- Table structure for table `hoadonthuephong`
--

CREATE TABLE `hoadonthuephong` (
  `IdHoaDon` char(20) COLLATE utf8mb4_general_ci NOT NULL,
  `TongTienPhong` char(20) COLLATE utf8mb4_general_ci NOT NULL,
  `DaThanhToan` char(3) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hoadonthuephong`
--

INSERT INTO `hoadonthuephong` (`IdHoaDon`, `TongTienPhong`, `DaThanhToan`) VALUES
('2024KTX1188', '1720000.0', 'NO'),
('2024KTX1305', '1088000.0', 'YES'),
('2024KTX1553', '1320000.0', 'YES');

-- --------------------------------------------------------

--
-- Table structure for table `hoadontiendien`
--

CREATE TABLE `hoadontiendien` (
  `MaSoPhong` char(5) COLLATE utf8mb4_general_ci NOT NULL,
  `Day` char(3) COLLATE utf8mb4_general_ci NOT NULL,
  `Tang` char(5) COLLATE utf8mb4_general_ci NOT NULL,
  `IdHoaDon` char(20) COLLATE utf8mb4_general_ci NOT NULL,
  `Thang` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `TienDienTheoThang` int NOT NULL,
  `DaThanhToan` char(3) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'NO'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hoadontiennuoc`
--

CREATE TABLE `hoadontiennuoc` (
  `IdHoaDon` char(20) COLLATE utf8mb4_general_ci NOT NULL,
  `TongTienNuoc` int NOT NULL,
  `DaThanhToan` char(3) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'NO'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hoadontiennuoc`
--

INSERT INTO `hoadontiennuoc` (`IdHoaDon`, `TongTienNuoc`, `DaThanhToan`) VALUES
('2024KTX1188', 215000, 'NO'),
('2024KTX1305', 136000, 'NO'),
('2024KTX1553', 165000, 'NO');

-- --------------------------------------------------------

--
-- Table structure for table `phong`
--

CREATE TABLE `phong` (
  `MaSoPhong` char(5) COLLATE utf8mb4_general_ci NOT NULL,
  `Day` char(3) COLLATE utf8mb4_general_ci NOT NULL,
  `Tang` char(5) COLLATE utf8mb4_general_ci NOT NULL,
  `LoaiPhong` int NOT NULL,
  `GiaPhong` int NOT NULL,
  `TinhTrangSuDung` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'được thuê thì true',
  `SoNguoiTrongPhong` int NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phong`
--

INSERT INTO `phong` (`MaSoPhong`, `Day`, `Tang`, `LoaiPhong`, `GiaPhong`, `TinhTrangSuDung`, `SoNguoiTrongPhong`) VALUES
('101', '1', '1', 4, 240, 'true', 2),
('101', '2', '1', 4, 240, 'true', 0),
('101', '3', '1', 4, 240, 'true', 0),
('102', '1', '1', 4, 240, 'true', 0),
('102', '2', '1', 4, 240, 'true', 0),
('102', '3', '1', 4, 240, 'true', 0),
('103', '1', '1', 4, 240, 'true', 0),
('103', '2', '1', 4, 240, 'true', 0),
('103', '3', '1', 4, 240, 'true', 0),
('104', '1', '1', 4, 240, 'true', 0),
('104', '2', '1', 4, 240, 'true', 0),
('104', '3', '1', 4, 240, 'true', 0),
('105', '1', '1', 4, 240, 'true', 0),
('105', '2', '1', 4, 240, 'true', 0),
('105', '3', '1', 4, 240, 'true', 0),
('106', '1', '1', 4, 240, 'true', 0),
('106', '2', '1', 4, 240, 'true', 0),
('106', '3', '1', 4, 240, 'true', 0),
('107', '1', '1', 4, 240, 'true', 0),
('107', '2', '1', 4, 240, 'true', 0),
('107', '3', '1', 4, 240, 'true', 0),
('108', '1', '1', 4, 240, 'true', 0),
('108', '2', '1', 4, 240, 'true', 0),
('108', '3', '1', 4, 240, 'true', 0),
('109', '1', '1', 4, 240, 'true', 0),
('109', '2', '1', 4, 240, 'true', 0),
('109', '3', '1', 4, 240, 'true', 0),
('110', '1', '1', 4, 240, 'true', 0),
('110', '2', '1', 4, 240, 'true', 0),
('110', '3', '1', 4, 240, 'true', 0),
('111', '1', '1', 6, 160, 'true', 0),
('111', '2', '1', 6, 160, 'true', 0),
('111', '3', '1', 6, 160, 'true', 0),
('112', '1', '1', 6, 160, 'true', 0),
('112', '2', '1', 6, 160, 'true', 0),
('112', '3', '1', 6, 160, 'true', 0),
('113', '1', '1', 6, 160, 'true', 0),
('113', '2', '1', 6, 160, 'true', 0),
('113', '3', '1', 6, 160, 'true', 0),
('114', '1', '1', 6, 160, 'true', 0),
('114', '2', '1', 6, 160, 'true', 0),
('114', '3', '1', 6, 160, 'true', 0),
('115', '1', '1', 6, 160, 'true', 0),
('115', '2', '1', 6, 160, 'true', 0),
('115', '3', '1', 6, 160, 'true', 0),
('116', '1', '1', 8, 120, 'true', 0),
('116', '2', '1', 8, 120, 'true', 0),
('116', '3', '1', 8, 120, 'true', 0),
('117', '1', '1', 8, 120, 'true', 0),
('117', '2', '1', 8, 120, 'true', 0),
('117', '3', '1', 8, 120, 'true', 0),
('118', '1', '1', 8, 120, 'true', 0),
('118', '2', '1', 8, 120, 'true', 0),
('118', '3', '1', 8, 120, 'true', 0),
('119', '1', '1', 8, 120, 'true', 0),
('119', '2', '1', 8, 120, 'true', 0),
('119', '3', '1', 8, 120, 'true', 0),
('120', '1', '1', 8, 120, 'true', 0),
('120', '2', '1', 8, 120, 'true', 0),
('120', '3', '1', 8, 120, 'true', 0),
('201', '1', '2', 4, 240, 'true', 0),
('201', '2', '2', 4, 240, 'true', 1),
('201', '3', '2', 4, 240, 'true', 0),
('202', '1', '2', 4, 240, 'true', 0),
('202', '2', '2', 4, 240, 'true', 0),
('202', '3', '2', 4, 240, 'true', 0),
('203', '1', '2', 4, 240, 'true', 0),
('203', '2', '2', 4, 240, 'true', 0),
('203', '3', '2', 4, 240, 'true', 0),
('204', '1', '2', 4, 240, 'true', 0),
('204', '2', '2', 4, 240, 'true', 0),
('204', '3', '2', 4, 240, 'true', 0),
('205', '1', '2', 4, 240, 'true', 0),
('205', '2', '2', 4, 240, 'true', 0),
('205', '3', '2', 4, 240, 'true', 0),
('206', '1', '2', 4, 240, 'true', 0),
('206', '2', '2', 4, 240, 'true', 0),
('206', '3', '2', 4, 240, 'true', 0),
('207', '1', '2', 4, 240, 'true', 0),
('207', '2', '2', 4, 240, 'true', 0),
('207', '3', '2', 4, 240, 'true', 0),
('208', '1', '2', 4, 240, 'true', 0),
('208', '2', '2', 4, 240, 'true', 0),
('208', '3', '2', 4, 240, 'true', 0),
('209', '1', '2', 4, 240, 'true', 0),
('209', '2', '2', 4, 240, 'true', 0),
('209', '3', '2', 4, 240, 'true', 0),
('210', '1', '2', 4, 240, 'true', 0),
('210', '2', '2', 4, 240, 'true', 0),
('210', '3', '2', 4, 240, 'true', 0),
('211', '1', '2', 6, 160, 'true', 0),
('211', '2', '2', 6, 160, 'true', 0),
('211', '3', '2', 6, 160, 'true', 0),
('212', '1', '2', 6, 160, 'true', 0),
('212', '2', '2', 6, 160, 'true', 0),
('212', '3', '2', 6, 160, 'true', 0),
('213', '1', '2', 6, 160, 'true', 0),
('213', '2', '2', 6, 160, 'true', 0),
('213', '3', '2', 6, 160, 'true', 0),
('214', '1', '2', 6, 160, 'true', 0),
('214', '2', '2', 6, 160, 'true', 0),
('214', '3', '2', 6, 160, 'true', 0),
('215', '1', '2', 6, 160, 'true', 0),
('215', '2', '2', 6, 160, 'true', 0),
('215', '3', '2', 6, 160, 'true', 0),
('216', '1', '2', 8, 120, 'true', 0),
('216', '2', '2', 8, 120, 'true', 0),
('216', '3', '2', 8, 120, 'true', 0),
('217', '1', '2', 8, 120, 'true', 0),
('217', '2', '2', 8, 120, 'true', 0),
('217', '3', '2', 8, 120, 'true', 0),
('218', '1', '2', 8, 120, 'true', 0),
('218', '2', '2', 8, 120, 'true', 0),
('218', '3', '2', 8, 120, 'true', 0),
('219', '1', '2', 8, 120, 'true', 0),
('219', '2', '2', 8, 120, 'true', 0),
('219', '3', '2', 8, 120, 'true', 0),
('220', '1', '2', 8, 120, 'true', 0),
('220', '2', '2', 8, 120, 'true', 0),
('220', '3', '2', 8, 120, 'true', 0),
('301', '1', '3', 4, 240, 'true', 0),
('301', '2', '3', 4, 240, 'true', 0),
('301', '3', '3', 4, 240, 'true', 0),
('302', '1', '3', 4, 240, 'true', 0),
('302', '2', '3', 4, 240, 'true', 0),
('302', '3', '3', 4, 240, 'true', 0),
('303', '1', '3', 4, 240, 'true', 0),
('303', '2', '3', 4, 240, 'true', 0),
('303', '3', '3', 4, 240, 'true', 0),
('304', '1', '3', 4, 240, 'true', 0),
('304', '2', '3', 4, 240, 'true', 0),
('304', '3', '3', 4, 240, 'true', 0),
('305', '1', '3', 4, 240, 'true', 0),
('305', '2', '3', 4, 240, 'true', 0),
('305', '3', '3', 4, 240, 'true', 0),
('306', '1', '3', 4, 240, 'true', 0),
('306', '2', '3', 4, 240, 'true', 0),
('306', '3', '3', 4, 240, 'true', 0),
('307', '1', '3', 4, 240, 'true', 0),
('307', '2', '3', 4, 240, 'true', 0),
('307', '3', '3', 4, 240, 'true', 0),
('308', '1', '3', 4, 240, 'true', 0),
('308', '2', '3', 4, 240, 'true', 0),
('308', '3', '3', 4, 240, 'true', 0),
('309', '1', '3', 4, 240, 'true', 0),
('309', '2', '3', 4, 240, 'true', 0),
('309', '3', '3', 4, 240, 'true', 0),
('310', '1', '3', 4, 240, 'true', 0),
('310', '2', '3', 4, 240, 'true', 0),
('310', '3', '3', 4, 240, 'true', 0),
('311', '1', '3', 6, 160, 'true', 0),
('311', '2', '3', 6, 160, 'true', 0),
('311', '3', '3', 6, 160, 'true', 0),
('312', '1', '3', 6, 160, 'true', 0),
('312', '2', '3', 6, 160, 'true', 0),
('312', '3', '3', 6, 160, 'true', 0),
('313', '1', '3', 6, 160, 'true', 0),
('313', '2', '3', 6, 160, 'true', 0),
('313', '3', '3', 6, 160, 'true', 0),
('314', '1', '3', 6, 160, 'true', 0),
('314', '2', '3', 6, 160, 'true', 0),
('314', '3', '3', 6, 160, 'true', 0),
('315', '1', '3', 6, 160, 'true', 0),
('315', '2', '3', 6, 160, 'true', 0),
('315', '3', '3', 6, 160, 'true', 0),
('316', '1', '3', 8, 120, 'true', 0),
('316', '2', '3', 8, 120, 'true', 0),
('316', '3', '3', 8, 120, 'true', 0),
('317', '1', '3', 8, 120, 'true', 0),
('317', '2', '3', 8, 120, 'true', 0),
('317', '3', '3', 8, 120, 'true', 0),
('318', '1', '3', 8, 120, 'true', 0),
('318', '2', '3', 8, 120, 'true', 0),
('318', '3', '3', 8, 120, 'true', 0),
('319', '1', '3', 8, 120, 'true', 0),
('319', '2', '3', 8, 120, 'true', 0),
('319', '3', '3', 8, 120, 'true', 0),
('320', '1', '3', 8, 120, 'true', 0),
('320', '2', '3', 8, 120, 'true', 0),
('320', '3', '3', 8, 120, 'true', 0),
('401', '1', '4', 4, 240, 'true', 0),
('401', '2', '4', 4, 240, 'true', 0),
('401', '3', '4', 4, 240, 'true', 0),
('402', '1', '4', 4, 240, 'true', 0),
('402', '2', '4', 4, 240, 'true', 0),
('402', '3', '4', 4, 240, 'true', 0),
('403', '1', '4', 4, 240, 'true', 0),
('403', '2', '4', 4, 240, 'true', 0),
('403', '3', '4', 4, 240, 'true', 0),
('404', '1', '4', 4, 240, 'true', 0),
('404', '2', '4', 4, 240, 'true', 0),
('404', '3', '4', 4, 240, 'true', 0),
('405', '1', '4', 4, 240, 'true', 0),
('405', '2', '4', 4, 240, 'true', 0),
('405', '3', '4', 4, 240, 'true', 0),
('406', '1', '4', 4, 240, 'true', 0),
('406', '2', '4', 4, 240, 'true', 0),
('406', '3', '4', 4, 240, 'true', 0),
('407', '1', '4', 4, 240, 'true', 0),
('407', '2', '4', 4, 240, 'true', 0),
('407', '3', '4', 4, 240, 'true', 0),
('408', '1', '4', 4, 240, 'true', 0),
('408', '2', '4', 4, 240, 'true', 0),
('408', '3', '4', 4, 240, 'true', 0),
('409', '1', '4', 4, 240, 'true', 0),
('409', '2', '4', 4, 240, 'true', 0),
('409', '3', '4', 4, 240, 'true', 0),
('410', '1', '4', 4, 240, 'true', 0),
('410', '2', '4', 4, 240, 'true', 0),
('410', '3', '4', 4, 240, 'true', 0),
('411', '1', '4', 6, 160, 'true', 0),
('411', '2', '4', 6, 160, 'true', 0),
('411', '3', '4', 6, 160, 'true', 0),
('412', '1', '4', 6, 160, 'true', 0),
('412', '2', '4', 6, 160, 'true', 0),
('412', '3', '4', 6, 160, 'true', 0),
('413', '1', '4', 6, 160, 'true', 0),
('413', '2', '4', 6, 160, 'true', 0),
('413', '3', '4', 6, 160, 'true', 0),
('414', '1', '4', 6, 160, 'true', 0),
('414', '2', '4', 6, 160, 'true', 0),
('414', '3', '4', 6, 160, 'true', 0),
('415', '1', '4', 6, 160, 'true', 0),
('415', '2', '4', 6, 160, 'true', 0),
('415', '3', '4', 6, 160, 'true', 0),
('416', '1', '4', 8, 120, 'true', 0),
('416', '2', '4', 8, 120, 'true', 0),
('416', '3', '4', 8, 120, 'true', 0),
('417', '1', '4', 8, 120, 'true', 0),
('417', '2', '4', 8, 120, 'true', 0),
('417', '3', '4', 8, 120, 'true', 0),
('418', '1', '4', 8, 120, 'true', 0),
('418', '2', '4', 8, 120, 'true', 0),
('418', '3', '4', 8, 120, 'true', 0),
('419', '1', '4', 8, 120, 'true', 0),
('419', '2', '4', 8, 120, 'true', 0),
('419', '3', '4', 8, 120, 'true', 0),
('420', '1', '4', 8, 120, 'true', 0),
('420', '2', '4', 8, 120, 'true', 0),
('420', '3', '4', 8, 120, 'true', 0),
('501', '1', '5', 4, 240, 'true', 0),
('501', '2', '5', 4, 240, 'true', 0),
('501', '3', '5', 4, 240, 'true', 0),
('502', '1', '5', 4, 240, 'true', 0),
('502', '2', '5', 4, 240, 'true', 0),
('502', '3', '5', 4, 240, 'true', 0),
('503', '1', '5', 4, 240, 'true', 0),
('503', '2', '5', 4, 240, 'true', 0),
('503', '3', '5', 4, 240, 'true', 0),
('504', '1', '5', 4, 240, 'true', 0),
('504', '2', '5', 4, 240, 'true', 0),
('504', '3', '5', 4, 240, 'true', 0),
('505', '1', '5', 4, 240, 'true', 0),
('505', '2', '5', 4, 240, 'true', 0),
('505', '3', '5', 4, 240, 'true', 0),
('506', '1', '5', 4, 240, 'true', 0),
('506', '2', '5', 4, 240, 'true', 0),
('506', '3', '5', 4, 240, 'true', 0),
('507', '1', '5', 4, 240, 'true', 0),
('507', '2', '5', 4, 240, 'true', 0),
('507', '3', '5', 4, 240, 'true', 0),
('508', '1', '5', 4, 240, 'true', 0),
('508', '2', '5', 4, 240, 'true', 0),
('508', '3', '5', 4, 240, 'true', 0),
('509', '1', '5', 4, 240, 'true', 0),
('509', '2', '5', 4, 240, 'true', 0),
('509', '3', '5', 4, 240, 'true', 0),
('510', '1', '5', 4, 240, 'true', 0),
('510', '2', '5', 4, 240, 'true', 0),
('510', '3', '5', 4, 240, 'true', 0),
('511', '1', '5', 6, 160, 'true', 0),
('511', '2', '5', 6, 160, 'true', 0),
('511', '3', '5', 6, 160, 'true', 0),
('512', '1', '5', 6, 160, 'true', 0),
('512', '2', '5', 6, 160, 'true', 0),
('512', '3', '5', 6, 160, 'true', 0),
('513', '1', '5', 6, 160, 'true', 0),
('513', '2', '5', 6, 160, 'true', 0),
('513', '3', '5', 6, 160, 'true', 0),
('514', '1', '5', 6, 160, 'true', 0),
('514', '2', '5', 6, 160, 'true', 0),
('514', '3', '5', 6, 160, 'true', 0),
('515', '1', '5', 6, 160, 'true', 0),
('515', '2', '5', 6, 160, 'true', 0),
('515', '3', '5', 6, 160, 'true', 0),
('516', '1', '5', 8, 120, 'true', 0),
('516', '2', '5', 8, 120, 'true', 0),
('516', '3', '5', 8, 120, 'true', 0),
('517', '1', '5', 8, 120, 'true', 0),
('517', '2', '5', 8, 120, 'true', 0),
('517', '3', '5', 8, 120, 'true', 0),
('518', '1', '5', 8, 120, 'true', 0),
('518', '2', '5', 8, 120, 'true', 0),
('518', '3', '5', 8, 120, 'true', 0),
('519', '1', '5', 8, 120, 'true', 0),
('519', '2', '5', 8, 120, 'true', 0),
('519', '3', '5', 8, 120, 'true', 0),
('520', '1', '5', 8, 120, 'true', 0),
('520', '2', '5', 8, 120, 'true', 0),
('520', '3', '5', 8, 120, 'true', 0);

-- --------------------------------------------------------

--
-- Table structure for table `quanlyhoatdongthuephong`
--

CREATE TABLE `quanlyhoatdongthuephong` (
  `MaSoDinhDanh` char(20) COLLATE utf8mb4_general_ci NOT NULL,
  `MaSoPhong` char(5) COLLATE utf8mb4_general_ci NOT NULL,
  `Tang` char(5) COLLATE utf8mb4_general_ci NOT NULL,
  `Day` char(3) COLLATE utf8mb4_general_ci NOT NULL,
  `IdHoaDon` char(20) COLLATE utf8mb4_general_ci NOT NULL,
  `NgayNhanPhong` date NOT NULL,
  `NgayTraPhong` date NOT NULL,
  `TrangThaiSuDung` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'true' COMMENT 'true(còn hạn thuê)/false(hết hạn thuê)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `quanlyhoatdongthuephong`
--

INSERT INTO `quanlyhoatdongthuephong` (`MaSoDinhDanh`, `MaSoPhong`, `Tang`, `Day`, `IdHoaDon`, `NgayNhanPhong`, `NgayTraPhong`, `TrangThaiSuDung`) VALUES
('10003', '101', '1', '1', '2024KTX1188', '2024-01-19', '2024-08-24', 'true'),
('10001', '101', '1', '1', '2024KTX1305', '2024-01-10', '2024-05-26', 'true'),
('10002', '201', '2', '2', '2024KTX1553', '2024-01-11', '2024-06-26', 'true');

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `User` char(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `Password` char(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `Email` char(30) COLLATE utf8mb4_general_ci NOT NULL,
  `codeAccess` char(15) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`User`, `Password`, `Email`, `codeAccess`) VALUES
('admin', 'admin', 'nhatanh19a10@gmail.com', '474691'),
('nhatanh19vn', 'admin', 'anhdn1922@gmail.com', '');

-- --------------------------------------------------------

--
-- Table structure for table `thongtinsinhvien`
--

CREATE TABLE `thongtinsinhvien` (
  `HoVaTen` char(30) COLLATE utf8mb4_general_ci NOT NULL,
  `MaSoDinhDanh` char(20) COLLATE utf8mb4_general_ci NOT NULL,
  `SoDienThoai` char(10) COLLATE utf8mb4_general_ci NOT NULL,
  `NgaySinh` date NOT NULL,
  `GioiTinh` char(3) COLLATE utf8mb4_general_ci NOT NULL,
  `QueQuan` char(255) COLLATE utf8mb4_general_ci NOT NULL,
  `Email` char(35) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `thongtinsinhvien`
--

INSERT INTO `thongtinsinhvien` (`HoVaTen`, `MaSoDinhDanh`, `SoDienThoai`, `NgaySinh`, `GioiTinh`, `QueQuan`, `Email`) VALUES
('Nguyễn Duy Mạnh', '10001', '0388444555', '2001-03-01', 'Nam', 'Quảng Ngãi', 'duymanh@gmail.com'),
('Đoàn Nhật Ánh', '10002', '0388777999', '2005-04-16', 'Nam', 'Quảng Trị', 'nhatanh19a10@gmail.com'),
('Nguyễn Văn Vĩnh', '10003', '0388111555', '2004-01-10', 'Nam', 'Đà Nẵng', 'vanvinh@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hoadonthuephong`
--
ALTER TABLE `hoadonthuephong`
  ADD PRIMARY KEY (`IdHoaDon`);

--
-- Indexes for table `hoadontiendien`
--
ALTER TABLE `hoadontiendien`
  ADD PRIMARY KEY (`MaSoPhong`,`Day`,`Tang`,`Thang`);

--
-- Indexes for table `hoadontiennuoc`
--
ALTER TABLE `hoadontiennuoc`
  ADD PRIMARY KEY (`IdHoaDon`);

--
-- Indexes for table `phong`
--
ALTER TABLE `phong`
  ADD PRIMARY KEY (`MaSoPhong`,`Day`,`Tang`);

--
-- Indexes for table `quanlyhoatdongthuephong`
--
ALTER TABLE `quanlyhoatdongthuephong`
  ADD PRIMARY KEY (`IdHoaDon`),
  ADD KEY `FK_QuanLyHoaDonThuePhong_ThongTinSinhVien` (`MaSoDinhDanh`),
  ADD KEY `FK_QuanLyHoaDonThuePhong_Phong` (`MaSoPhong`,`Day`,`Tang`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`User`,`Password`,`Email`);

--
-- Indexes for table `thongtinsinhvien`
--
ALTER TABLE `thongtinsinhvien`
  ADD PRIMARY KEY (`MaSoDinhDanh`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `hoadonthuephong`
--
ALTER TABLE `hoadonthuephong`
  ADD CONSTRAINT `FK_QuanLyHoaDonThuePhong_HoaDonThuePhong` FOREIGN KEY (`IdHoaDon`) REFERENCES `quanlyhoatdongthuephong` (`IdHoaDon`);

--
-- Constraints for table `hoadontiennuoc`
--
ALTER TABLE `hoadontiennuoc`
  ADD CONSTRAINT `FK_QuanLyHoaDonThuePhong_HoaDonTienNuoc` FOREIGN KEY (`IdHoaDon`) REFERENCES `quanlyhoatdongthuephong` (`IdHoaDon`);

--
-- Constraints for table `quanlyhoatdongthuephong`
--
ALTER TABLE `quanlyhoatdongthuephong`
  ADD CONSTRAINT `FK_QuanLyHoaDonThuePhong_Phong` FOREIGN KEY (`MaSoPhong`,`Day`,`Tang`) REFERENCES `phong` (`MaSoPhong`, `Day`, `Tang`),
  ADD CONSTRAINT `FK_QuanLyHoaDonThuePhong_ThongTinSinhVien` FOREIGN KEY (`MaSoDinhDanh`) REFERENCES `thongtinsinhvien` (`MaSoDinhDanh`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
