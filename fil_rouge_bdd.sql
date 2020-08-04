-- --------------------------------------------------------
-- Hôte :                        localhost
-- Version du serveur:           8.0.13 - MySQL Community Server - GPL
-- SE du serveur:                Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Listage des données de la table fil_rouge.address : ~0 rows (environ)
DELETE FROM `address`;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;

-- Listage des données de la table fil_rouge.contact : ~3 rows (environ)
DELETE FROM `contact`;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` (`id`, `mail`, `subject`, `message`, `date_send`) VALUES
	(1, 'jeej@hotmail.fr', 'test', 'de la date jeej', '2020-02-11 15:05:32'),
	(2, 'jeej@hotmail.fr', 'test', 'zertyuio', '2020-02-11 15:20:37'),
	(3, 'jeej@hotmail.fr', 'test', 'rt', '2020-02-11 15:22:53');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;

-- Listage des données de la table fil_rouge.newletters : ~0 rows (environ)
DELETE FROM `newletters`;
/*!40000 ALTER TABLE `newletters` DISABLE KEYS */;
/*!40000 ALTER TABLE `newletters` ENABLE KEYS */;

-- Listage des données de la table fil_rouge.orders : ~38 rows (environ)
DELETE FROM `orders`;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `date`, `status`, `bill`, `delivery_form`, `users_id_id`, `total`) VALUES
	(1, '2020-02-21 09:44:35', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(2, '2020-02-21 09:45:31', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(3, '2020-02-21 09:46:14', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(4, '2020-02-21 09:46:39', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(5, '2020-02-21 09:46:52', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(6, '2020-02-21 09:59:36', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(7, '2020-02-21 10:09:25', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(8, '2020-02-21 10:09:57', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(9, '2020-02-21 10:11:37', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(10, '2020-02-21 10:16:22', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(11, '2020-02-21 10:16:41', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(12, '2020-02-21 10:28:13', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(13, '2020-02-21 10:28:32', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(14, '2020-02-21 10:34:11', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(15, '2020-02-21 10:34:56', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(16, '2020-02-21 10:36:38', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(17, '2020-02-21 10:40:32', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(18, '2020-02-21 10:43:17', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(19, '2020-02-21 10:43:40', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(20, '2020-02-21 10:45:47', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(21, '2020-02-21 10:48:52', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(22, '2020-02-21 10:55:40', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(23, '2020-02-21 10:55:56', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(24, '2020-02-21 10:56:31', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(25, '2020-02-21 10:57:53', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(26, '2020-02-21 10:58:57', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(27, '2020-02-21 10:59:23', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(28, '2020-02-21 11:01:06', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(29, '2020-02-21 11:02:32', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(30, '2020-02-24 10:04:49', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(31, '2020-02-24 10:14:41', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(32, '2020-02-24 10:15:42', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(33, '2020-02-24 10:23:13', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(34, '2020-02-24 10:29:41', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(35, '2020-02-24 10:30:27', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(36, '2020-02-24 10:33:30', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(37, '2020-02-24 10:35:58', 'En cours de traitement', 'jeej', 'jeej', 1, 0),
	(38, '2020-02-24 10:37:01', 'En cours de traitement', 'jeej', 'jeej', 1, 0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Listage des données de la table fil_rouge.order_details : ~46 rows (environ)
DELETE FROM `order_details`;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` (`id`, `quantity`, `product_id`, `orders_id`) VALUES
	(1, 2, 4, 16),
	(2, 32, 5, 16),
	(3, 1, 6, 16),
	(4, 2, 4, 17),
	(5, 32, 5, 17),
	(6, 1, 6, 17),
	(7, 2, 4, 18),
	(8, 32, 5, 18),
	(9, 1, 6, 18),
	(10, 2, 4, 19),
	(11, 32, 5, 19),
	(12, 1, 6, 19),
	(13, 2, 4, 20),
	(14, 32, 5, 20),
	(15, 1, 6, 20),
	(16, 2, 4, 21),
	(17, 32, 5, 21),
	(18, 1, 6, 21),
	(19, 2, 4, 22),
	(20, 32, 5, 22),
	(21, 1, 6, 22),
	(22, 2, 4, 23),
	(23, 32, 5, 23),
	(24, 1, 6, 23),
	(25, 2, 4, 24),
	(26, 32, 5, 24),
	(27, 1, 6, 24),
	(28, 2, 4, 25),
	(29, 32, 5, 25),
	(30, 1, 6, 25),
	(31, 2, 4, 26),
	(32, 32, 5, 26),
	(33, 1, 6, 26),
	(34, 1, 5, 27),
	(35, 1, 5, 28),
	(36, 14, 5, 29),
	(37, 1, 4, 29),
	(38, 3, 5, 30),
	(39, 3, 5, 31),
	(40, 9, 4, 32),
	(41, 4, 7, 33),
	(42, 6, 4, 34),
	(43, 3, 5, 35),
	(44, 1, 4, 36),
	(45, 9, 6, 37),
	(46, 19, 4, 38);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;

-- Listage des données de la table fil_rouge.partner : ~0 rows (environ)
DELETE FROM `partner`;
/*!40000 ALTER TABLE `partner` DISABLE KEYS */;
/*!40000 ALTER TABLE `partner` ENABLE KEYS */;

-- Listage des données de la table fil_rouge.product : ~5 rows (environ)
DELETE FROM `product`;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `libelle`, `description`, `color`, `picture`, `price`, `stock`, `product_category_id`, `stars`, `created_at`) VALUES
	(4, 'jeej3', 'khtxr', 'Une couleur', 'https://i.ytimg.com/vi/nX_BVKgUChw/maxresdefault.jpg', 19.63, 100, 2, NULL, '2020-02-19 12:13:10'),
	(5, 'jeej', 'Orlane', 'une couleur', 'https://i.ytimg.com/vi/nX_BVKgUChw/maxresdefault.jpg', 19.63, 50, 3, NULL, '2020-02-19 12:16:16'),
	(6, 'jeej2', 'jyxf', 'Une couleur', 'https://i.ytimg.com/vi/nX_BVKgUChw/maxresdefault.jpg', 18.96, 9, 1, NULL, '2020-02-19 12:17:32'),
	(7, 'jeej4', 'tewt', 'tewt', 'https://i.ytimg.com/vi/nX_BVKgUChw/maxresdefault.jpg', 19.63, 12, 2, NULL, '2020-02-19 12:23:02'),
	(8, 'test', 'qsdtg', 'kdlm', 'https://i.ytimg.com/vi/nX_BVKgUChw/maxresdefault.jpg', 123, 21, 3, NULL, '2020-08-09 00:00:00');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- Listage des données de la table fil_rouge.product_category : ~3 rows (environ)
DELETE FROM `product_category`;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` (`id`, `name`, `picture`) VALUES
	(1, 'Origami en papier', 'https://i.ytimg.com/vi/rBv_brGcPmk/maxresdefault.jpg'),
	(2, 'Origami en résine', 'https://www.drawer.fr/47203-thickbox_default/trophee-origami-plastique-mat-present-time-lion.jpg'),
	(3, 'Origami en serviettes', 'https://lh3.googleusercontent.com/proxy/KmX_0KQl4Yy4Eu6PZR0i5u9S2Mys3u3gNJNiyV-kTKfxerthiKKX9ZU_ayzKebJkMRDXFgvwgYivSbTSRK2-1gPjirk5uD11NJzPgaSQfG1RW7PIK_lunHOSRpKd4-x0CK6t8Vy7wjR6aIGYivd3dE7Iu-sGiw');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;

-- Listage des données de la table fil_rouge.product_category_productcategory : ~0 rows (environ)
DELETE FROM `product_category_productcategory`;
/*!40000 ALTER TABLE `product_category_productcategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_category_productcategory` ENABLE KEYS */;

-- Listage des données de la table fil_rouge.users : ~6 rows (environ)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `mail`, `password`, `surname`, `first_name`, `phone_number`, `siret`, `address_fact`, `commercial_id`, `coefficient`, `type`, `role`) VALUES
	(1, 'jeej@hotmail.fr', '$2y$13$KGcZXtAqfjnneyFOnwQeZOpOhlYDgkHvGvcRVhFIxNsU1xeg8A0Wq', 'lomb', 'pelpel', '622726238', 'Avenue de la street perdu', NULL, NULL, 0, 2, 'parcom'),
	(2, 'jeejs@hotmail.fr', '$2y$13$W274zjSdEW0TNg4R6Pccl.94UNtI5cxeapHOX7od4Eedah6.Ly1NW', 'da', 'ad', '1000000000', NULL, NULL, 1, 0, 1, 'procom'),
	(3, 'jeej@jeej.fr', '$2a$10$ZwrnTXmLN4Tk7NlhN71WVuwMTC06HlsnFBNs7I5Js6UUMU0QXlOC.', 'jeej', 'jeej', '000000000', NULL, 'jeej', 1, 1, 1, 'client'),
	(4, 'jeejd@jeejd.frt', '$2a$10$YoIGvXxrxwuAE4.fsg7XluTU02N61AABog79IaMJ2Vs1o211cmnNy', 'jeejdf', 'jeejd', '0000000000', '', 'jeejd', 2, 2, 0, 'admin'),
	(5, 'azer@azer.azer', '$2a$10$Sn/42jySmO7fX/50GCxLOuOgOqbRhUAhh85WrRYgHek4MS4bwi586', 'test', 'testcothis', '012345678', '', 'azertyuiop', 2, 1, 0, 'client'),
	(7, 'outlook@outlook.dfr', '$2a$10$WIJgLqtocQDKSz/geafbIeE9HeHbqikKv17wIWt92rfgc4p6v/CMG', 'jyfgjyfjyf', 'hgdhgdf', '000000000', NULL, '222jyfhgdhgf', 1, 1, 1, 'client');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
