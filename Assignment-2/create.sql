use `cs5200_fall2019_Mo`;
drop table if exists `websitepriviledge`;
drop table if exists `pagepriviledge`;
drop table if exists `websiterole`;
drop table if exists `pagerole`;

drop table if exists `address`;
drop table if exists `phone`;

drop table if exists `widget`;
drop table if exists `pages`;
drop table if exists `website`;
drop table if exists `user`;
drop table if exists `developer`;
drop table if exists `person`;
drop table if exists `role`;
drop table if exists `priviledge`;

create table `person`(
	`id` int not null auto_increment,
	`firstName` varchar(45) not null,
    `lastName` varchar(45) not null,
    `username` varchar(45) not null,
    `password` varchar(45) not null,
    `email` varchar(45) not null,
    `dob` datetime default null,
    primary key(`id`)
);

create table `developer`(
	`personId` int not null,
    `developerKey` varchar(45) not null,
    primary key(`personId`),
    constraint `developer_person_generalization`
    foreign key(`personId`)
    references `person`(`id`)
);
    
create table `user`(
	`personId` int not null,
    `userAgreement` bool not null,
    primary key(`personId`),
    constraint `user_person_generalization`
    foreign key(`personId`)
    references `person`(`id`)
);

create table `website`(
	`id` int not null auto_increment,
	`name` varchar(45) not null,
    `description` varchar(255) not null,
    `created` datetime not null,
    `updated` datetime not null,
	`visits` int not null,
    primary key(`id`)
);

create table `pages`(
	`id` int not null auto_increment,
     `websiteId` int not null,
     `title` varchar(45) not null,
     `description` varchar(255) not null,
     `created` datetime not null,
     `updated` datetime not null,
     `views` int not null,
     primary key(`id`),
     constraint `pages_website_composition`
     foreign key(`websiteId`)
     references `website`(`id`)
     on delete cascade
);

create table `widget`(
	`id` int not null auto_increment,
    `name` varchar(45) not null,
    `width` int not null,
    `height` int not null,
    `cssClass` varchar(45) not null,
    `cssStyle` varchar(45) not null,
    `text` varchar(255) not null,
    `order` int not null,
    `dtype` varchar(45) not null comment 'YouTube/Image/Heading/Html',
    `url` varchar(255) DEFAULT NULL COMMENT 'src, html',
	`size` int DEFAULT 2 COMMENT 'headingSize',
	`shareble` tinyint(1) DEFAULT 0 COMMENT 'Youtube',
	`expandable` tinyint(1) DEFAULT 0 COMMENT 'Youtube',
    `pageId` int not null,
    primary key(`id`),
    constraint `widget_pages_composition`
    foreign key(`pageId`)
    references `pages`(`id`)
    on delete cascade
);

-- create table `youtube`(
-- 	`widgeId` int not null,
--     `url` varchar(255) not null,
--     `shareble` bool not null,
--     `expandable` bool not null,
--     constraint `youtube_widge_generation`
--     foreign key(`widgeId`)
--     references `widge`(`id`)
-- );

-- create table `image`(
-- 	`widgeId` int not null,
--     `src` varchar(255) not null,
--     constraint `image_widge_generation`
--     foreign key(`widgeId`)
--     references `widge`(`id`)
-- );

-- create table `heading`(
--     `widgeId` int not null,
--     `size` int not null,
--     constraint `heading_widge_generation`
--     foreign key(`widgeId`)
--     references `widge`(`id`)
-- );

-- create table `html`(
--     `widgeId` int not null,
--     `html` varchar(255) not null,
--     constraint `html_widge_generation`
--     foreign key(`widgeId`)
--     references `widge`(`id`)
-- );

create table `phone`(
	`id` int not null auto_increment,
    `phone` varchar(45) not null,
    `primary` bool not null,
    `personId` int not null,
    primary key (`id`),
    constraint `phone_person_composition`
    foreign key(`personId`)
    references `person`(`id`)
);

create table `address`(
	`id` int not null auto_increment,
    `street1` varchar(45) not null,
    `street2` varchar(45) default null,
    `city` varchar(20) not null,
    `state` varchar(20) default null,
    `zip` varchar(20) not null,
    `primary` bool not null,
    `personId` int not null,
    primary key (`id`),
    constraint `address_person_composition`
    foreign key(`personId`)
    references `person`(`id`)
);

create table `priviledge`(
	`id` varchar(45) not null default '',
    primary key(`id`)
);
insert into `priviledge` (`id`) values ('create');
insert into `priviledge` (`id`) values ('read');
insert into `priviledge` (`id`) values ('update');
insert into `priviledge` (`id`) values ('delete');
    
create table `role`(
	`id` varchar(45) not null default '',
    primary key(`id`)
);
insert into `role` (`id`) values ('owner');
insert into `role` (`id`) values ('admin');
insert into `role` (`id`) values ('writer');
insert into `role` (`id`) values ('editor');
insert into `role` (`id`) values ('reviewer');


create table `pagepriviledge`(
	`id` int not null auto_increment,
    `priviledgechoice` varchar(45) not null default '',
    `developerId` int not null,
    `pageId` int not null,
    primary key(`id`),
    constraint `pages_priviledge_enums` 
	foreign key (`priviledgechoice`) 
	references `priviledge` (`id`),
    constraint `pages_priviledge_developer_association`
	foreign key (`developerId`) 
	references `developer` (`personId`),
	constraint `pages_priviledge_pages_association` 
	foreign key (`pageId`) 
	references `pages` (`id`)
	on delete cascade
);

create table `pagerole`(
	`id` int not null auto_increment,
    `rolechoice` varchar(45) not null default '',
    `developerId` int not null,
    `pageId` int not null,
	primary key(`id`),
    constraint `pages_role_enums` 
	foreign key (`rolechoice`) 
	references `role` (`id`),
    constraint `pages_role_developer_association` 
	foreign key (`developerId`) 
	references `developer` (`personId`),
	constraint `pages_role_pages_association` 
	foreign key (`pageId`) 
	references `pages` (`id`)
	on delete cascade
);
    
create table `websitepriviledge`(
	`id` int not null auto_increment,
    `priviledgechoice` varchar(45) not null default '',
    `developerId` int not null,
    `websiteId` int not null,
    primary key(`id`),
    constraint `website_priviledge_enums` 
	foreign key (`priviledgechoice`) 
	references `priviledge` (`id`),
    constraint `website_priviledge_developer_association` 
	foreign key (`developerId`) 
	references `developer` (`personId`),
	constraint `website_priviledge_website_association` 
	foreign key (`websiteId`) 
	references `website` (`id`)
	on delete cascade
);

create table `websiterole`(
	`id` int not null auto_increment,
    `rolechoice` varchar(45) not null default '',
    `developerId` int not null,
    `websiteId` int not null,
    primary key(`id`),
    constraint `website_role_enums` 
	foreign key (`rolechoice`) 
	references `role` (`id`),
    constraint `website_role_developer_association` 
	foreign key (`developerId`) 
	references `developer` (`personId`),
	constraint `website_role_website_association` 
	foreign key (`websiteId`) 
	references `website` (`id`)
	on delete cascade
);


