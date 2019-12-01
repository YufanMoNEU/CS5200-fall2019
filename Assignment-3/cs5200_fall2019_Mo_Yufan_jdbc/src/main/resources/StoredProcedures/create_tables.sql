USE `jdbc`;

DROP TABLE IF EXISTS `WebsitePrivilege`;
DROP TABLE IF EXISTS `PagePrivilege`;
DROP TABLE IF EXISTS `WebsiteRole`;
DROP TABLE IF EXISTS `PageRole`;
DROP TABLE IF EXISTS `Phone`;
DROP TABLE IF EXISTS `Address`;
DROP TABLE IF EXISTS `HeadingWidget`;
DROP TABLE IF EXISTS `HtmlWidget`;
DROP TABLE IF EXISTS `ImageWidget`;
DROP TABLE IF EXISTS `YouTubeWidget`;
DROP TABLE IF EXISTS `Widget`;
DROP TABLE IF EXISTS `Page`;
DROP TABLE IF EXISTS `Website`;
DROP TABLE IF EXISTS `Developer`;
DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS `Person`;
DROP TABLE IF EXISTS `Role`;
DROP TABLE IF EXISTS `Privilege`;

CREATE TABLE `Person` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `dob` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Developer` (
  `personId` int NOT NULL,
  `developerKey` varchar(20) NOT NULL,
  PRIMARY KEY (`personId`),
	CONSTRAINT developer_person_generalization
		FOREIGN KEY (`personId`)
		REFERENCES Person(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `User` (
  `personId` int NOT NULL,
  `approvedUser` tinyint(1) DEFAULT 0,
	`userAgreement` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`personId`),
	CONSTRAINT user_person_generalization
		FOREIGN KEY (`personId`)
		REFERENCES Person(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Website` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL,
  `visits` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Page` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `created` date DEFAULT NULL,
  `updated` date DEFAULT NULL,
  `views` int NOT NULL,
  `websiteId` int NOT NULL COMMENT 'has',
  PRIMARY KEY (`id`),
  CONSTRAINT `page_website_composition`
		FOREIGN KEY (`websiteId`)
		REFERENCES `Website` (`id`)
		ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Widget` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
	`width` int DEFAULT 0,
	`height` int DEFAULT 0,
	`cssClass` varchar(255) DEFAULT NULL,
	`cssStyle` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
	`order` int NOT NULL,
  `pageId` int NOT NULL COMMENT 'has',
  PRIMARY KEY (`id`),
  CONSTRAINT `widget_page_composition`
		FOREIGN KEY (`pageId`)
		REFERENCES `Page` (`id`)
		ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `HeadingWidget` (
  `widgetId` int NOT NULL,
  `size` int DEFAULT 0,
  PRIMARY KEY (`widgetId`),
	CONSTRAINT heading_widget_generalization
		FOREIGN KEY (`widgetId`)
		REFERENCES Widget(`id`)
		ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `HtmlWidget` (
  `widgetId` int NOT NULL,
  `html` varchar(255) DEFAULT	NULL,
  PRIMARY KEY (`widgetId`),
	CONSTRAINT html_widget_generalization
		FOREIGN KEY (`widgetId`)
		REFERENCES Widget(`id`)
		ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `ImageWidget` (
  `widgetId` int NOT NULL,
  `src` varchar(50) NOT	NULL,
  PRIMARY KEY (`widgetId`),
	CONSTRAINT image_widget_generalization
		FOREIGN KEY (`widgetId`)
		REFERENCES Widget(`id`)
		ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `YouTubeWidget` (
  `widgetId` int NOT NULL,
  `url` varchar(50) NOT NULL,
  `shareable` tinyint(1) DEFAULT 0,
	`expandable` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`widgetId`),
	CONSTRAINT youtube_widget_generalization
		FOREIGN KEY (`widgetId`)
		REFERENCES Widget(`id`)
		ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Phone` (
  `id` int NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) NOT NULL,
  `primary` tinyint(1) NOT NULL,
  `personId` int NOT NULL COMMENT 'has',
  PRIMARY KEY (`id`),
  CONSTRAINT `phone_person_composition`
		FOREIGN KEY (`personId`)
		REFERENCES `Person` (`id`)
		ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `street1` varchar(100) NOT NULL,
	`street2` varchar(100) DEFAULT NULL,
	`city` varchar(20) NOT NULL,
	`state` varchar(20) DEFAULT NULL,
	`zip` varchar(20) NOT NULL,
  `primary` tinyint(1) NOT NULL,
  `personId` int NOT NULL COMMENT 'has',
  PRIMARY KEY (`id`),
  CONSTRAINT `address_person_composition`
		FOREIGN KEY (`personId`)
		REFERENCES `Person` (`id`)
		ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Role`(
`id` varchar(20) NOT NULL DEFAULT '',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `Role` (`id`) VALUES ('owner');
INSERT INTO `Role` (`id`) VALUES ('admin');
INSERT INTO `Role` (`id`) VALUES ('writer');
INSERT INTO `Role` (`id`) VALUES ('editor');
INSERT INTO `Role` (`id`) VALUES ('reviewer');

CREATE TABLE `Privilege`(
`id` varchar(20) NOT NULL DEFAULT '',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `Privilege` (`id`) VALUES ('create');
INSERT INTO `Privilege` (`id`) VALUES ('read');
INSERT INTO `Privilege` (`id`) VALUES ('update');
INSERT INTO `Privilege` (`id`) VALUES ('delete');

CREATE TABLE `WebsiteRole`(
`id` int NOT NULL AUTO_INCREMENT,
`role` varchar(20) NOT NULL DEFAULT '',
`developerId` int NOT NULL,
`websiteId` int NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `website_role_enums`
	FOREIGN KEY (`role`)
	REFERENCES `Role` (`id`),
CONSTRAINT `website_role_developer_association`
	FOREIGN KEY (`developerId`)
	REFERENCES `Developer` (`personId`),
CONSTRAINT `website_role_website_association`
	FOREIGN KEY (`websiteId`)
	REFERENCES `Website` (`id`)
	ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `PageRole`(
`id` int NOT NULL AUTO_INCREMENT,
`role` varchar(20) NOT NULL DEFAULT '',
`developerId` int NOT NULL,
`pageId` int NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `page_role_enums`
	FOREIGN KEY (`role`)
	REFERENCES `Role` (`id`),
CONSTRAINT `page_role_developer_association`
	FOREIGN KEY (`developerId`)
	REFERENCES `Developer` (`personId`),
CONSTRAINT `page_role_page_association`
	FOREIGN KEY (`pageId`)
	REFERENCES `Page` (`id`)
	ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `WebsitePrivilege`(
`id` int NOT NULL AUTO_INCREMENT,
`privilege` varchar(20) NOT NULL DEFAULT '',
`developerId` int NOT NULL,
`websiteId` int NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `website_privilege_enums`
	FOREIGN KEY (`privilege`)
	REFERENCES `Privilege` (`id`),
CONSTRAINT `website_privilege_developer_association`
	FOREIGN KEY (`developerId`)
	REFERENCES `Developer` (`personId`),
CONSTRAINT `website_privilege_website_association`
	FOREIGN KEY (`websiteId`)
	REFERENCES `Website` (`id`)
	ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `PagePrivilege`(
`id` int NOT NULL AUTO_INCREMENT,
`privilege` varchar(20) NOT NULL DEFAULT '',
`developerId` int NOT NULL,
`pageId` int NOT NULL,
PRIMARY KEY (`id`),
CONSTRAINT `page_privilege_enums`
	FOREIGN KEY (`privilege`)
	REFERENCES `Privilege` (`id`),
CONSTRAINT `page_privilege_developer_association`
	FOREIGN KEY (`developerId`)
	REFERENCES `Developer` (`personId`),
CONSTRAINT `page_privilege_page_association`
	FOREIGN KEY (`pageId`)
	REFERENCES `Page` (`id`)
	ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
