CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `credentials_non_expired` bit(1) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_username` (`username`)
) ;