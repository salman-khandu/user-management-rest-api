CREATE TABLE `t_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bkpm7njy2ort1yoiddc7jg8gj` (`name`)
);


CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `t_user_role_mapping` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKm0hqgaavbfu4lxdt2yp38rff3` (`role_id`),
  CONSTRAINT `FK3hwehux2h9a77wgjhml020p0d` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FKm0hqgaavbfu4lxdt2yp38rff3` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
);

