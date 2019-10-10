CREATE DEFINER=`yufancs5200`@`%` TRIGGER `cs5200_fall2019_Mo`.`websiterole_AFTER_INSERT` AFTER INSERT ON `websiterole` FOR EACH ROW
BEGIN
	INSERT INTO websitepriviledge (priviledgechoice, developerId, websiteId) 
	VALUES ('read', new.developerId, new.websiteId);
	IF new.rolechoice = 'owner' OR new.rolechoice = 'admin' THEN
		INSERT INTO websitepriviledge (priviledgechoice, developerId, websiteId) 
		VALUES ('create', new.developerId, new.websiteId), ('update', new.developerId, new.websiteId), ('delete', new.developerId, new.websiteId);
	ELSEIF new.rolechoice = 'writer' THEN
		INSERT INTO websitepriviledge (priviledgechoice, developerId, websiteId) 
		VALUES ('create', new.developerId, new.websiteId), ('update', new.developerId, new.websiteId);
	ELSEIF new.rolechoice = 'editor' THEN
		INSERT INTO websitepriviledge (priviledgechoice, developerId, websiteId) 
		VALUES ('update', new.developerId, new.websiteId);
	END IF;
END

CREATE DEFINER=`yufancs5200`@`%` TRIGGER `cs5200_fall2019_Mo`.`websiterole_AFTER_UPDATE` AFTER UPDATE ON `websiterole` FOR EACH ROW
BEGIN
	IF new.rolechoice != old.rolechoice THEN
		DELETE FROM websitepriviledge WHERE developerId = old.developerId AND websiteId = old.websiteId;
		INSERT INTO websitepriviledge (priviledgechoice, developerId, websiteId) 
		VALUES ('read', new.developerId, new.websiteId);
		IF new.rolechoice = 'owner' OR 'admin' THEN
			INSERT INTO websitepriviledge (priviledgechoice, developerId, websiteId) 
			VALUES ('create', new.developerId, new.websiteId), ('update', new.developerId, new.websiteId), ('delete', new.developerId, new.websiteId);
		ELSEIF new.rolechoice = 'writer' THEN
			INSERT INTO websitepriviledge (priviledgechoice, developerId, websiteId) 
			VALUES ('create', new.developerId, new.websiteId), ('update', new.developerId, new.websiteId);
		ELSEIF new.rolechoice = 'editor' THEN
			INSERT INTO websitepriviledge (priviledgechoice, developerId, websiteId) 
			VALUES ('update', new.developerId, new.websiteId);
		END IF;
	ELSE
		UPDATE websitepriviledge SET developerId = new.developerId AND websiteId = new.websiteId;
	END IF;
END

CREATE DEFINER=`yufancs5200`@`%` TRIGGER `cs5200_fall2019_Mo`.`websiterole_AFTER_DELETE` AFTER DELETE ON `websiterole` FOR EACH ROW
BEGIN
	DELETE FROM websitepriviledge WHERE developerId = old.developerId AND websiteId = old.websiteId;
END

CREATE DEFINER=`yufancs5200`@`%` TRIGGER `cs5200_fall2019_Mo`.`pagerole_AFTER_INSERT` AFTER INSERT ON `pagerole` FOR EACH ROW
BEGIN
	INSERT INTO pagepriviledge (priviledgechoice, developerId, pageId) 
	VALUES ('read', new.developerId, new.pageId);
	IF new.rolechoice = 'owner' OR 'admin' THEN
		INSERT INTO pagepriviledge (priviledgechoice, developerId, pageId) 
		VALUES ('create', new.developerId, new.pageId), ('update', new.developerId, new.pageId), ('delete', new.developerId, new.pageId);
	ELSEIF new.rolechoice = 'writer' THEN
		INSERT INTO pagepriviledge (priviledgechoice, developerId, pageId) 
		VALUES ('create', new.developerId, new.pageId), ('update', new.developerId, new.pageId);
	ELSEIF new.rolechoice = 'editor' THEN
		INSERT INTO pagepriviledge (priviledgechoice, developerId, pageId) 
		VALUES ('update', new.developerId, new.pageId);
	END IF;
END

CREATE DEFINER=`yufancs5200`@`%` TRIGGER `cs5200_fall2019_Mo`.`pagerole_AFTER_UPDATE` AFTER UPDATE ON `pagerole` FOR EACH ROW
BEGIN
	IF new.rolechoice != old.rolechoice THEN
		DELETE FROM pagepriviledge WHERE developerId = old.developerId AND pageId = old.pageId;
		INSERT INTO pagepriviledge (priviledgechoice, developerId, pageId) 
		VALUES ('read', new.developerId, new.pageId);
		IF new.rolechoice = 'owner' OR 'admin' THEN
			INSERT INTO pagepriviledge (priviledgechoice, developerId, pageId) 
			VALUES ('create', new.developerId, new.pageId), ('update', new.developerId, new.pageId), ('delete', new.developerId, new.pageId);
		ELSEIF new.rolechoice = 'writer' THEN
			INSERT INTO pagepriviledge (priviledgechoice, developerId, pageId) 
			VALUES ('create', new.developerId, new.pageId), ('update', new.developerId, new.pageId);
		ELSEIF new.rolechoice = 'editor' THEN
			INSERT INTO pagepriviledge (priviledgechoice, developerId, pageId) 
			VALUES ('update', new.developerId, new.pageId);
		END IF;
	ELSE
		UPDATE pagepriviledge SET developerId = new.developerId AND pageId = new.pageId;
	END IF;
END

CREATE DEFINER=`yufancs5200`@`%` TRIGGER `cs5200_fall2019_Mo`.`pagerole_AFTER_DELETE` AFTER DELETE ON `pagerole` FOR EACH ROW
BEGIN
	DELETE FROM pagepriviledge WHERE developerId = old.developerId AND websiteId = old.pageId;
END









    
    