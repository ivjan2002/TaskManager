-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2025 at 09:11 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `taskmanager`
--

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `project_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`project_id`, `description`, `end_date`, `project_name`, `start_date`) VALUES
(2, 'This is a test project', '2025-12-31 00:00:00.000000', '0', '2025-01-01 00:00:00.000000'),
(3, 'This is a test project2', '2026-01-01 00:00:00.000000', '0', '2025-02-01 00:00:00.000000');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `task_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  `task_priority` enum('HIGH','LOW','MEDIUM') DEFAULT NULL,
  `task_status` enum('ARCHIVED','CANCELLED','COMPLETED','IN_PROGRESS','ON_HOLD','TODO') DEFAULT NULL,
  `project_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`task_id`, `description`, `end_date`, `start_date`, `task_name`, `task_priority`, `task_status`, `project_id`, `user_id`) VALUES
(1, 'Zavrsiti kontroler za Task menadzer', '2024-02-10 01:00:00.000000', '2024-02-02 01:00:00.000000', 'Implementacija kontrolera', NULL, NULL, 3, 5),
(2, 'Opis zadatka', '2025-12-31 00:00:00.000000', '2025-02-22 00:00:00.000000', 'Naziv zadatka', 'MEDIUM', 'ON_HOLD', 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','USER') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `email`, `password`, `user_name`, `role`) VALUES
(1, 'newemail@example.com', NULL, '0', NULL),
(2, 'ivana@example.com', 'ivana123', '0', 'USER'),
(3, 'kaca@example.com', 'kaca123', '0', 'USER'),
(4, 'test@example.com', '$2a$10$/O5Kc8Tmnudyc5I3OPo4FuaNgRUZrCO9llt5CHz7CLvFAX.OT17/.', 'testUser', 'USER'),
(5, 'test@example.com', '$2a$10$29ha.SLxntC3u1at0mkkEuLWP7ajJaW7ud6z1C.JoP/YdJWARl99C', 'mara', 'USER'),
(6, 'newuser@example.com', '$2a$10$9JFvKJTR22wrkr31CVlWwuhI6RiblmNV4XwACXKYE1AJVGsaDphcq', 'user', 'USER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`project_id`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`task_id`),
  ADD KEY `FKk8qrwowg31kx7hp93sru1pdqa` (`project_id`),
  ADD KEY `FK2hsytmxysatfvt0p1992cw449` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `project_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `FK2hsytmxysatfvt0p1992cw449` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKk8qrwowg31kx7hp93sru1pdqa` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
