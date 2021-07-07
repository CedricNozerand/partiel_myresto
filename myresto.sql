-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 07 juil. 2021 à 15:23
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
  `date` datetime DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `command`
--

INSERT INTO `command` (`id`, `date`, `status`, `user_id`) VALUES
(1, '2021-01-03 16:30:58', 'Closed', 1),
(2, '2021-01-03 17:05:52', 'Paid', 1),
(3, '2021-02-03 18:25:00', 'Paid', 1),
(4, '2021-02-03 18:42:58', 'Paid', 1),
(5, '2021-02-06 07:52:14', 'Paid', 1),
(6, '2021-03-06 14:00:47', 'Active', 1),
(7, '2021-03-07 12:50:27', 'Paid', 2),
(8, '2021-04-07 13:15:04', 'Paid', 2),
(9, '2021-04-07 13:15:17', 'Paid', 2),
(10, '2021-04-07 13:15:25', 'Paid', 2),
(11, '2021-04-07 13:15:37', 'Paid', 2),
(12, '2021-04-07 13:17:58', 'Paid', 2),
(13, '2021-05-07 13:19:04', 'Paid', 2),
(14, '2021-05-07 13:19:13', 'Paid', 2),
(15, '2021-06-07 13:19:19', 'Paid', 2),
(16, '2021-06-07 13:19:23', 'Paid', 2),
(17, '2021-06-07 13:19:29', 'Paid', 2),
(18, '2021-06-07 13:19:35', 'Paid', 2),
(19, '2021-06-07 13:19:44', 'Paid', 2),
(20, '2021-06-07 13:19:52', 'Paid', 2),
(21, '2021-07-07 13:20:02', 'Paid', 2);

-- --------------------------------------------------------

--
-- Structure de la table `command_products`
--

CREATE TABLE `command_products` (
  `command_id` bigint(20) NOT NULL,
  `products_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `command_products`
--

INSERT INTO `command_products` (`command_id`, `products_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 3),
(3, 1),
(4, 4),
(4, 4),
(5, 1),
(5, 2),
(6, 2),
(6, 8),
(6, 9),
(6, 3),
(7, 2),
(7, 3),
(8, 2),
(8, 4),
(8, 6),
(8, 14),
(9, 2),
(9, 6),
(10, 18),
(10, 9),
(11, 2),
(11, 16),
(11, 7),
(11, 10),
(11, 10),
(12, 2),
(12, 6),
(12, 11),
(13, 17),
(13, 7),
(14, 1),
(14, 5),
(14, 9),
(15, 16),
(15, 8),
(16, 2),
(17, 15),
(17, 10),
(18, 16),
(19, 1),
(19, 2),
(19, 3),
(20, 2),
(20, 5),
(21, 1),
(21, 17),
(21, 8),
(21, 13);

-- --------------------------------------------------------

--
-- Structure de la table `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `price` double NOT NULL,
  `type` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `product`
--

INSERT INTO `product` (`id`, `description`, `image`, `name`, `price`, `type`) VALUES
(1, 'Sauce tomate à l\'origan ou crème fraîche légère, mozzarella, fromage de chèvre, emmental et Fourme d\'Ambert AOP', '/assets/4 Fromages.jpg', '4-Fromages', 12, 'PIZZA'),
(2, 'Sauce barbecue, mozzarella, haché au bœuf, filet de poulet rôti et merguez', '/assets/BPM.jpg', 'BPM', 10, 'PIZZA'),
(3, 'Sauce tomate à l\'origan, mozzarella et saucisse pepperoni', '/assets/Pepperoni Lovers.jpg', 'Pepperoni', 14, 'PIZZA'),
(4, 'Sauce tomate à l\'origan, mozzarella, jambon et champignons frais', '/assets/Queen.jpg', 'Queen', 12, 'PIZZA'),
(5, 'Délicieux Cookie aux pépites de chocolat noir servi chaud. Idéal à partager!', '/assets/coockie.jpg', 'Coockie', 5, 'DESSERT'),
(6, '8 Bâtonnets de pâte à pizza garnis de véritable Nutella', '/assets/breadsticks.jpg', 'Breadsticks', 6, 'DESSERT'),
(7, '4 Mini Beignets fourrés au véritable Nutella', '/assets/beignets.jpg', 'Beignets', 4, 'DESSERT'),
(8, 'Coeur Fondant au chocolat', '/assets/brownie.jpg', 'Brownie', 5, 'DESSERT'),
(9, 'Coca-Cola Original 33cl', '/assets/coca.jpg', 'Coca Cola', 3, 'BOISSON'),
(10, 'Oasis Tropical 33cl', '/assets/tropical.jpg', 'Oasis', 3, 'BOISSON'),
(11, 'Orangina 33cl', '/assets/orangina.jpg', 'Orangina', 4, 'BOISSON'),
(12, 'Fuze Tea 33cl', '/assets/fuzetea.jpg', 'Fuze Tea', 4, 'BOISSON'),
(13, 'Sprite 33cl', '/assets/sprite.jpg', 'Sprite', 3, 'BOISSON'),
(14, 'Vittel 50cl', '/assets/eau.jpg', 'Vittel', 2, 'BOISSON'),
(15, 'Crème fraîche légère, mozzarella, jambon cru, fromage à raclette et champignons frais', '/assets/montagnarde.jpg', 'Montagnarde', 13, 'PIZZA'),
(16, 'Sauce tomate à l\'origan, mozzarella, haché au bœuf, saucisse pepperoni, champignons frais, oignons rouges frais et poivrons verts frais', '/assets/supreme.jpg', 'Suprême', 15, 'PIZZA'),
(17, 'Crème fraîche légère, mozzarella, pommes de terre, lardons et fromage à raclette', '/assets/raclette.jpg', 'Raclette', 12, 'PIZZA'),
(18, 'Sauce tomate à l\'origan, mozzarella, merguez et champignons frais', '/assets/orientale.jpg', 'Orientale', 13, 'PIZZA');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'MEMBER'),
(2, 'ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `adress` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `first_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone_number` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `adress`, `email`, `first_name`, `last_name`, `password`, `phone_number`) VALUES
(1, '11 Rue Gérard De Nerval', 'cedric.nozerand@gmail.com', 'Cédric', 'Nozerand', '$2y$07$EFdcjXaO6FVga8dB9nQlLehmVmyZ2WzLCw5lNsA2RMLh6I0jhTNL6', '0601293069'),
(2, 'voisins', 'admin@myResto.com', 'admin', 'admin', '$2y$07$EFdcjXaO6FVga8dB9nQlLehmVmyZ2WzLCw5lNsA2RMLh6I0jhTNL6', '0123589012');

-- --------------------------------------------------------

--
-- Structure de la table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2);

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
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `command`
--
ALTER TABLE `command`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
