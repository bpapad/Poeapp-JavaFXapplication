-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Φιλοξενητής: 127.0.0.1
-- Χρόνος δημιουργίας: 11 Δεκ 2020 στις 11:38:31
-- Έκδοση διακομιστή: 10.4.11-MariaDB
-- Έκδοση PHP: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Βάση δεδομένων: `poeapp`
--
CREATE DATABASE IF NOT EXISTS `poeapp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `poeapp`;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `credentials`
--

CREATE TABLE `credentials` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `cred_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `credentials`
--

INSERT INTO `credentials` (`username`, `password`, `cred_id`) VALUES
('bill', 'pap', 1),
('maria', 'sapou', 6),
('tasos', 'pap', 9);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `users`
--

CREATE TABLE `users` (
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `users`
--

INSERT INTO `users` (`firstname`, `lastname`, `email`, `nickname`, `user_id`) VALUES
('basilhs', 'papadas', 'bpapadas@gmail.com', 'bill', 1),
('Maria', 'Sapouridou', 'msap@gmail.com', 'maria', 6),
('anastasios', 'papadas', 'an@gmail.com', 'tasos', 9);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `user_build_guides`
--

CREATE TABLE `user_build_guides` (
  `build_name` varchar(255) NOT NULL,
  `build_title` varchar(255) NOT NULL,
  `build_link` varchar(255) NOT NULL,
  `build_user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Άδειασμα δεδομένων του πίνακα `user_build_guides`
--

INSERT INTO `user_build_guides` (`build_name`, `build_title`, `build_link`, `build_user_id`) VALUES
('rtyr', '[3.12] Elemental Hit Ascendant [All Content]', 'https://www.pathofexile.com/forum/view-thread/2183938', 1),
('frosty', '[3.12] FROST BLADES GLADIATOR Build 2020 Edition (From level 1 to the END-GAME!) + VIDEO GUIDE!', 'https://www.pathofexile.com/forum/view-thread/2184129', 1);

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `credentials`
--
ALTER TABLE `credentials`
  ADD PRIMARY KEY (`cred_id`),
  ADD KEY `cred_id` (`cred_id`);

--
-- Ευρετήρια για πίνακα `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Ευρετήρια για πίνακα `user_build_guides`
--
ALTER TABLE `user_build_guides`
  ADD KEY `user_builds_ibfk_1` (`build_user_id`);

--
-- AUTO_INCREMENT για άχρηστους πίνακες
--

--
-- AUTO_INCREMENT για πίνακα `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Περιορισμοί για άχρηστους πίνακες
--

--
-- Περιορισμοί για πίνακα `credentials`
--
ALTER TABLE `credentials`
  ADD CONSTRAINT `credentials_ibfk_1` FOREIGN KEY (`cred_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Περιορισμοί για πίνακα `user_build_guides`
--
ALTER TABLE `user_build_guides`
  ADD CONSTRAINT `user_builds_ibfk_1` FOREIGN KEY (`build_user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
