CREATE TABLE IF NOT EXISTS `user_permission` (
  `id_user` bigint NOT NULL,
  `id_permission` bigint NOT NULL,
  PRIMARY KEY (`id_user`, `id_permission`),
  KEY `idx_user_permission_id_permission` (`id_permission`),
  CONSTRAINT `fk_user_permission_id_permission` FOREIGN KEY (`id_permission`) REFERENCES `permission` (`id`),
  CONSTRAINT `fk_user_permission_id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
);
