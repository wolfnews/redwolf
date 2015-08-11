CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `truename` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `mobile` varchar(16) NOT NULL,
  `email` varchar(16) DEFAULT NULL,
  `icon_path` varchar(128),
  `level` int(2) NOT NULL DEFAULT 0,
  `identified` boolean NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `category` varchar(64) DEFAULT NULL,
  `rate` decimal(10,2)  NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time`   datetime NOT NULL,
  `valid`      boolean  NOT NULL DEFAULT FALSE,
  `status`     varchar(16) NOT NULL DEFAULT 'NORMAL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `summary` text DEFAULT NULL,
  `mobile` varchar(16) NOT NULL,
  `level` int(2) NOT NULL DEFAULT 0,
  `creator` varchar(64),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `user_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `grade` int(10) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `user_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `money` decimal(10,2) NOT NULL DEFAULT '0.00',
  `pay_way` varchar(16) DEFAULT NULL,
  `category` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `subscribe_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `professor_id` bigint(20) NOT NULL,
  `subscribe_time` int(10),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `user_subscribe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `professor_id` bigint(20) NOT NULL,
  `expire_time` datetime NOT NULL,
  `group_id` bigint(20) NOT NULL,
  `expired` boolean NOT NULL DEFAULT TRUE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `user_rss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `professor_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `subscribe_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `professor_id` bigint(20) NOT NULL,
  `name` varchar(32) NOT NULL,
  `level` varchar(32) DEFAULT NULL,
  `price` int(10) DEFAULT 10,
  `max_person` int(10) DEFAULT 500,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `professor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `truename` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `mobile` varchar(16) NOT NULL,
  `occupation` varchar(32) DEFAULT NULL,
  `summary` text DEFAULT NULL,
  `icon_path` varchar(128),
  `level` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `author` bigint(20) NOT NULL,
  `title` varchar(128) NOT NULL,
  `keyword` varchar(128) DEFAULT NULL,
  `public_content` longtext,
  `private_content` longtext,
  `category` varchar(16) NOT NULL,
  `favor_num` int(10) NOT NULL DEFAULT '0',
  `browse_num` int(11) NOT NULL DEFAULT '0',
  `status` varchar(16) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `notice_verify_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `notice_id` bigint(20) NOT NULL,
  `manager_id` bigint(20) NOT NULL,
  `result` varchar(16) NOT NULL,
  `reason` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `title` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `tag` varchar(128) DEFAULT NULL,
  `summary` varchar(128) DEFAULT NULL,
  `content` LongText DEFAULT NULL,
  `category` varchar(16) NOT NULL,
  `browse_times` int(10) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `notice_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modify` datetime NOT NULL,
  `notice_id` bigint(20) NOT NULL,
  `opinion` varchar(8) DEFAULT 0,
  `comment` text ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
select visit_date, count(source)as UV from visit_count group by visit_date;
select visit_date, sum(amount) as PV from visit_count group by visit_date;
