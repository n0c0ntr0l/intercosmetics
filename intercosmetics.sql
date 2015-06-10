-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 10, 2015 at 08:38 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `intercosmetics`
--

-- --------------------------------------------------------

--
-- Table structure for table `client_table`
--

CREATE TABLE IF NOT EXISTS `client_table` (
  `ID` int(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Address` text NOT NULL,
  `Zip Code` varchar(255) NOT NULL,
  `SalesmanID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `collection_table`
--

CREATE TABLE IF NOT EXISTS `collection_table` (
  `Order_ID` int(20) NOT NULL,
  `Salesman_ID` int(20) NOT NULL,
  `Collection_Date` date NOT NULL,
  `Payment_Type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `order_table`
--

CREATE TABLE IF NOT EXISTS `order_table` (
  `Order_ID` int(30) NOT NULL,
  `Family` varchar(255) NOT NULL,
  `Name` text NOT NULL,
  `Product_ID` int(20) NOT NULL,
  `Price` decimal(20,0) NOT NULL,
  `Quantity` int(10) NOT NULL,
  `Discount` decimal(4,0) NOT NULL,
  `Salesman_ID` int(10) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product_table`
--

CREATE TABLE IF NOT EXISTS `product_table` (
  `Code` varchar(255) NOT NULL,
  `Name` text NOT NULL,
  `Family` varchar(255) NOT NULL,
  `Price` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `salesmen`
--

CREATE TABLE IF NOT EXISTS `salesmen` (
  `Name` varchar(255) NOT NULL,
  `ID` int(15) NOT NULL,
  `Password` varchar(255) CHARACTER SET ascii COLLATE ascii_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `salesmen`
--

INSERT INTO `salesmen` (`Name`, `ID`, `Password`) VALUES
('root', 0, 'password');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
