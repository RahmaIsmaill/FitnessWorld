-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 24, 2025 at 02:40 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fitnessworld`
--

-- --------------------------------------------------------

--
-- Table structure for table `couches`
--

CREATE TABLE `couches` (
  `couch_id` int(11) NOT NULL,
  `couch_name` varchar(100) NOT NULL,
  `couch_age` int(11) NOT NULL,
  `programs` varchar(255) NOT NULL,
  `image_path` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `couches`
--

INSERT INTO `couches` (`couch_id`, `couch_name`, `couch_age`, `programs`, `image_path`) VALUES
(2, 'rahma', 23, 'yoga', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `enrollments`
--

CREATE TABLE `enrollments` (
  `EnrollmentID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `ProgramID` int(11) DEFAULT NULL,
  `EnrollmentDate` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `programs`
--

CREATE TABLE `programs` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `Date` varchar(50) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `trainer_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `programs`
--

INSERT INTO `programs` (`id`, `name`, `Date`, `price`, `trainer_name`) VALUES
(3, 'zommba', '2020-02-02', 10000.00, 'rahma'),
(4, 'Yoga', '2022-02-02', 120.00, 'Yonnis');

-- --------------------------------------------------------

--
-- Table structure for table `userdetails`
--

CREATE TABLE `userdetails` (
  `user_id` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `weight` decimal(5,2) DEFAULT NULL,
  `height` decimal(5,2) DEFAULT NULL,
  `goal` varchar(255) DEFAULT NULL,
  `membership_date` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `userdetails`
--

INSERT INTO `userdetails` (`user_id`, `age`, `weight`, `height`, `goal`, `membership_date`) VALUES
(1, 18, 55.00, 180.00, 'lose 10k', '2020-02-02'),
(2, 21, 56.00, 160.00, 'lose weight', '2020-02-02'),
(3, 18, 55.00, 160.00, 'lose weight', '2020-02-02'),
(4, 19, 60.00, 160.00, 'lose weight', '2020-02-02'),
(5, 20, 70.00, 170.00, 'lose weight', '2004-02-20'),
(6, 18, 55.00, 169.00, 'lose', '2022-02-02'),
(7, 18, 55.00, 169.00, 'lose 170', '2022-02-02'),
(8, 17, 55.00, 66.00, '77', '2022-02-02'),
(9, 18, 55.00, 5.00, '4', '2020-22-02'),
(10, 19, 8.00, 9.00, '89', '2022-02-02'),
(11, 89, 89.00, 90.00, '90', '2022-02-02'),
(12, 99, 99.00, 99.00, '90', '2222-22-22'),
(13, 19, 66.00, 66.00, '66', '2222-02-02'),
(14, 55, 55.00, 5.00, '5', '5555-55-55'),
(15, 44, 44.00, 44.00, '44', '2020-20-20'),
(16, 19, 55.00, 55.00, '5', '2020-02-02'),
(17, 20, 55.00, 160.00, 'lose', '2022-02-02'),
(18, 20, 40.00, 140.00, 'lose', '2022-02-02'),
(19, 55, 55.00, 555.00, '555', '5555-55-55'),
(20, 20, 55.00, 150.00, 'lost', '2020-02-02'),
(21, 20, 40.00, 150.00, 'lose', '2222-22-22'),
(22, 33, 33.00, 33.00, '33', '3333-33-33'),
(23, 30, 40.00, 140.00, 'lost', '2020-20-20'),
(24, 20, 30.00, 55.00, '55', '2020-20-20'),
(25, 33, 55.00, 180.00, 'lost', '1111-11-11'),
(26, 19, 30.00, 130.00, 'lose', '2020-20-20'),
(27, 20, 40.00, 160.00, 'lost', '2002-02-02'),
(28, 80, 55.00, 55.00, '55', '2222-22-22'),
(29, 20, 55.00, 160.00, 'lose weight', '2024-08-30'),
(30, 21, 55.00, 160.00, 'lose Weight', '2024-08-08'),
(31, 29, 80.00, 190.00, 'lose weight', '2020-02-02'),
(32, 55, 55.00, 55.00, '55', '5555-55-55'),
(33, 77, 66.00, 66.00, '66', '2222-22-22'),
(34, 66, 66.00, 66.00, '66', '6666-66-66'),
(35, 66, 66.00, 66.00, '666666', '6666-66-66'),
(36, 55, 55.00, 55.00, '55', '5555-55-55'),
(37, 55, 55.00, 55.00, '55', '5555-55-55'),
(38, 55, 55.00, 55.00, '55', '5555-55-55'),
(39, 55, 55.00, 55.00, '55', '5555-55-55'),
(40, 44, 44.00, 44.00, '44', '4444-44-44'),
(41, 88, 66.00, 66.00, '66', '6666-66-66'),
(42, 77, 67.00, 76.00, '444', '4444-44-44'),
(43, 44, 44.00, 44.00, '44', '4444-44-44'),
(44, 66, 44.00, 43.00, '343', '2020-09-02'),
(45, 60, 60.00, 160.00, 'Lose weight', '2020-02-02'),
(46, 55, 55.00, 155.00, 'lost', '2222-22-22'),
(47, 34, 55.00, 190.00, 'lost', '8888-88-88'),
(48, 45, 44.00, 180.00, 'lose', '7777-77-77'),
(49, 55, 45.00, 45.00, '45', '5555-55-55'),
(50, 55, 66.00, 66.00, '66', '6666-66-66'),
(51, 66, 66.00, 66.00, '65', '5666-66-66'),
(52, 19, 12.00, 11.00, '11', '1111-11-11'),
(53, 55, 55.00, 160.00, '66', '6666-66-66'),
(54, 66, 55.00, 56.00, '65', '6666-66-66'),
(55, 88, 88.00, 88.00, '88', '8888-88-88'),
(56, 19, 80.00, 190.00, 'lose weight', '2025-01-24'),
(57, 77, 89.00, 180.00, 'gain weight', '2020-02-02'),
(58, 20, 44.00, 170.00, 'lose', '2004-05-06'),
(59, 44, 44.00, 144.00, '44', '4444-44-44');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('Trainee','Admin','Coach') NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `email`, `password`, `role`, `first_name`, `last_name`, `token`) VALUES
(1, 'rahmaismail', 'rahma@gmail.com', '777777777', 'Trainee', 'rahma', 'ismail', '12'),
(4, 'mualy', 'mu@gmail.com', '222222222', 'Trainee', 'muhammed', 'aly', '3'),
(5, 'Younnisbaqdons', 'Younnis@gmail.com', 'Younnis222', 'Trainee', 'younnis', 's', '34'),
(6, 'rahmaismail', 'rahmaismail@gmail.com', '3333333333', 'Trainee', 'rahma', 'ismail', '33'),
(7, 'ahmed', 'ahm@gmail.com', '111111111', 'Trainee', 'ahmed', 'm', '123'),
(8, 'ww', 'ww@gm.com', '3333333333', 'Trainee', 'ww', 'ww', '45'),
(9, 'younnis', 'y@gmail.com', '88888888888', 'Trainee', 'younnis', 'y', '88'),
(10, 'nosa', 'nosa@gm.com', '9999999999', 'Trainee', 'younnis', 's', '00'),
(11, 'you', 'u@gmail.com', '7777777777', 'Trainee', 'younnis', 'n', '90'),
(12, 'r', 'r@gm.vom', '888888888888', 'Trainee', 'r', 'r', '55'),
(13, 'younniss', 'uou@gmil.com', '555555555', 'Trainee', 'you', 'nnis', '09'),
(14, 'rahmah', 'rahmah@gmail.com', '777666888', 'Trainee', 'rahma', 'aa', '99'),
(15, 'som3a', 'som3a@gmail.com', 'som3aaaaaaa', 'Trainee', 'ismail', 'l', '2'),
(16, 'tamer', 't@gmail.com', '999999999', 'Trainee', 'tamer', 'samir', '0'),
(17, 'kl', 'kl@gmail.com', '000000000', 'Trainee', 'kk', 'll', '90'),
(18, 'karma', 'k@gmail.com', '777777777', 'Trainee', 'kar', 'ma', '5'),
(19, 'nur', 'nur@gmail.com', 'nur456780', 'Trainee', 'nour', 'ali', '9'),
(20, 'ux', 'ux@gmail.com', '444444444444', 'Trainee', 'u', 'x', '4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `couches`
--
ALTER TABLE `couches`
  ADD PRIMARY KEY (`couch_id`);

--
-- Indexes for table `enrollments`
--
ALTER TABLE `enrollments`
  ADD PRIMARY KEY (`EnrollmentID`),
  ADD KEY `UserID` (`UserID`),
  ADD KEY `ProgramID` (`ProgramID`);

--
-- Indexes for table `programs`
--
ALTER TABLE `programs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userdetails`
--
ALTER TABLE `userdetails`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `unique_token` (`token`,`email`,`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `couches`
--
ALTER TABLE `couches`
  MODIFY `couch_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `enrollments`
--
ALTER TABLE `enrollments`
  MODIFY `EnrollmentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `programs`
--
ALTER TABLE `programs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `userdetails`
--
ALTER TABLE `userdetails`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `enrollments`
--
ALTER TABLE `enrollments`
  ADD CONSTRAINT `enrollments_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `enrollments_ibfk_2` FOREIGN KEY (`ProgramID`) REFERENCES `programs` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
