-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 03 juil. 2021 à 14:50
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `myresto`
--

-- --------------------------------------------------------

--
-- Structure de la table `command`
--

CREATE TABLE `command` (
  `id` bigint(20) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `command`
--

INSERT INTO `command` (`id`, `date`, `user_id`) VALUES
(1, '03-07-2021 14:35:25', 1),
(2, '03-07-2021 14:35:48', 1),
(3, '03-07-2021 14:35:49', 1);

-- --------------------------------------------------------

--
-- Structure de la table `command_products`
--

CREATE TABLE `command_products` (
  `command_id` bigint(20) NOT NULL,
  `products_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `command_products`
--

INSERT INTO `command_products` (`command_id`, `products_id`) VALUES
(1, 2),
(2, 2),
(3, 4);

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `product`
--

INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `type`) VALUES
(1, 'Sauce tomate à l\'origan ou crème fraîche légère, mozzarella, fromage de chèvre, emmental et Fourme d\'Ambert AOP', '/assets/4 Fromages.jpg', '4-Fromages', '12.0', 'PIZZA'),
(2, 'Sauce barbecue, mozzarella, haché au bœuf, filet de poulet rôti et merguez', '/assets/BPM.jpg', 'BPM', '10.0', 'PIZZA'),
(3, 'Sauce tomate à l\'origan, mozzarella et saucisse pepperoni', '/assets/Pepperoni Lovers.jpg', 'Pepperoni', '14.0', 'PIZZA'),
(4, 'Sauce tomate à l\'origan, mozzarella, jambon et champignons frais', '/assets/Queen.jpg', 'Queen', '12.0', 'PIZZA');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'MEMBER');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `adress` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `adress`, `email`, `first_name`, `last_name`, `password`, `phone_number`) VALUES
(1, '11 Rue Gérard De Nerval', 'cedric.nozerand@gmail.com', 'Cédric', 'Nozerand', '$2a$10$1hsy.yxdmkHMX9hwjMuiEeRMzTXZcp3NTT5RQUZQAEGF8kEPaM5l6', '0601293069');

-- --------------------------------------------------------

--
-- Structure de la table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `user_orders`
--

CREATE TABLE `user_orders` (
  `user_id` bigint(20) NOT NULL,
  `orders_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `command`
--
ALTER TABLE `command`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKevwao6cjfmid4vxo87687q80i` (`user_id`);

--
-- Index pour la table `command_products`
--
ALTER TABLE `command_products`
  ADD KEY `FK18m81slt86eikab9245keg19h` (`products_id`),
  ADD KEY `FKekmvnppf3fcg6ihksh3ee8e0m` (`command_id`);

--
-- Index pour la table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKjmivyxk9rmgysrmsqw15lqr5b` (`name`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- Index pour la table `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  ADD KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`);

--
-- Index pour la table `user_orders`
--
ALTER TABLE `user_orders`
  ADD UNIQUE KEY `UK_oqipapb9ox7fhui5n2ttp9ab4` (`orders_id`),
  ADD KEY `FKkuspr37yv513ga1okogyxrb7m` (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `command`
--
ALTER TABLE `command`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `command`
--
ALTER TABLE `command`
  ADD CONSTRAINT `FKevwao6cjfmid4vxo87687q80i` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `command_products`
--
ALTER TABLE `command_products`
  ADD CONSTRAINT `FK18m81slt86eikab9245keg19h` FOREIGN KEY (`products_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKekmvnppf3fcg6ihksh3ee8e0m` FOREIGN KEY (`command_id`) REFERENCES `command` (`id`);

--
-- Contraintes pour la table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Contraintes pour la table `user_orders`
--
ALTER TABLE `user_orders`
  ADD CONSTRAINT `FK1rvwqv9qxvmctturrtpjoug6` FOREIGN KEY (`orders_id`) REFERENCES `command` (`id`),
  ADD CONSTRAINT `FKkuspr37yv513ga1okogyxrb7m` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
