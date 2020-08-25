
TRUNCATE `contact`;
INSERT INTO `contact` (`mail`, `subject`, `message`, `date_send`) VALUES
	('jean@hotmail.fr', 'objet', 'contenu du message', '2020-02-11 15:05:32'),
	('dupont@hotmail.fr', 'un objet', 'un message', '2020-02-11 15:20:37');


TRUNCATE `users`;
INSERT INTO `users` (`mail`, `password`, `first_name`, `surname`, `phone_number`, `siret`, `address_fact`, `commercial_id`, `coefficient`, `type`, `role`) VALUES
	('jean@hotmail.fr', '$2y$13$KGcZXtAqfjnneyFOnwQeZOpOhlYDgkHvGvcRVhFIxNsU1xeg8A0Wq', 'Jean', 'pelpel', '622726238', '45545456485646', NULL, NULL, 0, 2, 'parcom'),
	('nom@hotmail.fr', '$2y$13$W274zjSdEW0TNg4R6Pccl.94UNtI5cxeapHOX7od4Eedah6.Ly1NW', 'Jean', 'Dupont', '0620235689', NULL, NULL, 1, 0, 1, 'procom'),
	('adresse@mail.fr', '$2a$10$ZwrnTXmLN4Tk7NlhN71WVuwMTC06HlsnFBNs7I5Js6UUMU0QXlOC.', 'Charles', 'jeej', '0764646253', NULL, NULL, 1, 1, 1, 'client'),
	('jean@gmail.fr', '$2a$10$YoIGvXxrxwuAE4.fsg7XluTU02N61AABog79IaMJ2Vs1o211cmnNy', 'Hans', 'Ridel', '0164685856', '65478215935452', NULL, 2, 2, 0, 'admin'),
	('azer@azer.azer', '$2a$10$Sn/42jySmO7fX/50GCxLOuOgOqbRhUAhh85WrRYgHek4MS4bwi586', 'Coco', 'Pops', '012345678', '54538463854635', '123 rue Charles de Gaulle', 2, 1, 0, 'client'),
	('dupont@outlook.fr', '$2a$10$WIJgLqtocQDKSz/geafbIeE9HeHbqikKv17wIWt92rfgc4p6v/CMG', 'Toto', 'Dupont', '0465259234', NULL, 'Avenue de l\'AFPA', 1, 1, 1, 'client'),
	('admin@hotmail.fr', '$2y$13$jhElVFVsO3TgkvdK210Uie6iBJXSAJLLXt2EDL.Vs93N4G6HFLuxy', 'admin', 'admin', '0464156545', NULL, 'Avenue de l\'AFPA', 1, 1, 1, 'admin');

TRUNCATE `product_category`;
INSERT INTO `product_category` (`id`, `name`, `picture`) VALUES
	(1, 'Origami en papier', 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/fd/Origami-crane.jpg/1280px-Origami-crane.jpg'),
	(2, 'Origami en résine', 'https://www.drawer.fr/47203-thickbox_default/trophee-origami-plastique-mat-present-time-lion.jpg'),
	(3, 'Lampe en origami', 'https://i.pinimg.com/736x/1b/8a/14/1b8a140fcbe991a862e53eb5927ecc23.jpg');

	
TRUNCATE `product`;
INSERT INTO `product` (`libelle`, `description`, `color`, `picture`, `price`, `stock`, `product_category_id`, `stars`, `created_at`) VALUES
	('Grue', 'C\'est une petite grue blanche de 36cm', 'Blanche', 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/fd/Origami-crane.jpg/1280px-Origami-crane.jpg', 19.63, 500, 1, 3, '2020-02-19 12:13:10'),
	('Chat', 'Chat en 3D Avec sa couleur très douce, il trouvera facilement sa place dans une chambre d\'enfant pas exemple.', 'Bleu clair', 'https://www.rose-bunker.fr/4381-large_default/chaton-en-papier-origami-agent-paper.jpg', 35, 50, 1, 5, '2020-02-19 12:16:16'),
	('Fleur', 'Petite fleur jaune pour poser sur son bureau', 'jaune', 'https://designmag.fr/wp-content/uploads/2017/11/origami-fleur-debutant-origami-facile.jpg', 18.96, 9, 1, 4, '2020-02-19 12:17:32'),
	('Bouquet', 'Bouquet de roses', 'rose', 'https://archzine.fr/wp-content/uploads/2019/03/mode%CC%80le-de-rose-kusudama-compose%CC%81e-de-petites-fleurs-en-papier-origami-fleur-rose-pliage-avance%CC%81-1-e1553787179867.jpg', 27.99, 25, 1, 5, '2020-02-19 12:23:02'),
	('Rhinocéroce', 'Stylisée façon "origami", cette statue bleu laquée sera un véritable objet d\'art à placer dans vos extérieurs comme en intérieur pour une décoration originale et très tendance. Le rhinogami est très résistant grâce à sa polyrésine peinte et laquée.', 'bleu', 'https://mobiliermoss.com/images/produits/statue-rhinoceros-origami-bleu-1-rhinogami-mobiliermoss-xl.jpg', 526, 123, 2, NULL, '2020-08-09 00:00:00'),
	('Panthère', 'Grande statue de panthère de 8kg', 'grise', 'https://d2ans0z9s1x1c.cloudfront.net/products/00163e6f-f177-1eea-a3d7-ea9a8e701b92.jpg', 299, 123, 2, NULL, '2020-08-09 00:00:00'),
	('Lampe de chevet', 'Lampe de chevet moderne design', 'blanche', 'https://i.pinimg.com/736x/1b/8a/14/1b8a140fcbe991a862e53eb5927ecc23.jpg', 27, 123, 1, NULL, '2020-08-09 00:00:00'),
	('Bateau', 'Nostalgique et poétique elle nous renvoie dans l\'enfance', 'jaune', 'https://media.larmoiredebebe.com/ab/products/00/05/55/18/AB0005551815_1.jpg', 59.93, 123, 1, NULL, '2020-08-09 00:00:00');


	
TRUNCATE `orders`;
INSERT INTO `orders` (`date`, `status`, `bill`, `delivery_form`, `users_id_id`, `total`) VALUES
	('2020-02-21 09:44:35', 'En cours de traitement', 'jeej', 'jeej', 1, 23),
	('2020-03-21 09:45:31', 'En cours de traitement', 'jeej', 'jeej', 1, 123),
	('2020-04-21 09:46:14', 'En cours de traitement', 'jeej', 'jeej', 1, 56),
	('2020-03-21 09:46:39', 'En cours de traitement', 'jeej', 'jeej', 1, 213),
	('2020-02-20 09:46:52', 'En cours de traitement', 'jeej', 'jeej', 2, 123),
	('2020-02-21 09:59:36', 'En cours de traitement', 'jeej', 'jeej', 3, 56),
	('2020-02-14 10:09:25', 'En cours de traitement', 'jeej', 'jeej', 2, 65),
	('2020-03-23 10:09:57', 'En cours de traitement', 'jeej', 'jeej', 4, 56),
	('2020-02-21 10:11:37', 'En cours de traitement', 'jeej', 'jeej', 5, 52),
	('2020-03-26 10:16:22', 'En cours de traitement', 'jeej', 'jeej', 3, 55);

TRUNCATE `order_details`;
INSERT INTO `order_details` (`quantity`, `product_id`, `orders_id`) VALUES
	(2, 1, 1),
	(32, 2, 1),
	(1, 1, 2),
	(2, 4, 3),
	(32, 5, 1),
	(1, 1, 2),
	(2, 3, 3),
	(32, 5, 4),
	(1, 6, 4),
	(2, 4, 1),
	(32, 5, 5),
	(1, 6, 5),
	(13, 2, 5),
	(14, 2, 2),
	(5, 1, 3),
	(16, 2, 3),
	(17, 3, 3),
	(18, 1, 6),
	(9, 2, 4),
	(20, 4,5),
	(21, 1, 3),
	(22, 2, 4),
	(3, 3, 5),
	(4, 1, 6),
	(15, 2, 4),
	(2, 5, 5),
	(27, 1, 6),
	(8, 2, 4),
	(9, 6, 5),
	(30, 1, 6),
	(3, 2, 4),
	(32, 3, 5),
	(33, 1, 6),
	(4, 1, 5),
	(5, 1, 5),
	(3, 4, 5),
	(3, 1, 4),
	(8, 3, 5),
	(9, 3, 5),
	(4, 6, 4),
	(4, 4, 7),
	(2, 6, 4),
	(4, 3, 5),
	(4, 1, 4),
	(5, 8, 6),
	(6, 4, 4);
	
TRUNCATE partner;
INSERT INTO partner (name, picture) VALUES 
('AFPA','https://upload.wikimedia.org/wikipedia/commons/a/ae/Logo_Afpa.jpg');