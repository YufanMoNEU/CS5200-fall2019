USE `cs5200_fall2019_Mo_jdbc`;

DROP TABLE IF EXISTS `answer`;
DROP TABLE IF EXISTS `question`;
DROP TABLE IF EXISTS `Module`;

CREATE TABLE `Module`(
`id` varchar(20) NOT NULL DEFAULT '',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `Module` (`id`) VALUES ('Project1');
INSERT INTO `Module` (`id`) VALUES ('Project2');
INSERT INTO `Module` (`id`) VALUES ('Assignment1');
INSERT INTO `Module` (`id`) VALUES ('Assignment2');
INSERT INTO `Module` (`id`) VALUES ('Quiz1');
INSERT INTO `Module` (`id`) VALUES ('Exam');
INSERT INTO `Module` (`id`) VALUES ('Logistics');

CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(50) NOT NULL,
	`askedBy` int NOT NULL,
  `postedOn` date DEFAULT NULL,
	`length` int DEFAULT 0,
  `views` int DEFAULT 0,
	`endorsedByInstructor` tinyint(1) DEFAULT 0,
  `module` varchar(20) NOT NULL,
	PRIMARY KEY (`id`),
  CONSTRAINT `question_asked_by_user`
		FOREIGN KEY (`askedBy`)
		REFERENCES `User` (`personId`),
	CONSTRAINT `module_enum`
		FOREIGN KEY (`module`)
		REFERENCES `Module`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(20) NOT NULL,
	`postedBy` int NOT NULL,
	`postedOn` date DEFAULT NULL,
	`correctAnswer` tinyint(1) DEFAULT 0,
	`upVotes` int DEFAULT 0,
  `downVotes` int DEFAULT 0,
	`questionId` int NOT NULL,
  PRIMARY KEY (`id`),
	CONSTRAINT answer_posted_by_user
		FOREIGN KEY (`postedBy`)
		REFERENCES `User`(`personId`),
	CONSTRAINT answer_of_question
		FOREIGN KEY (questionId)
		REFERENCES question(`id`)
		ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;