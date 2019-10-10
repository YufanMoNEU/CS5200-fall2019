use `cs5200_fall2019_Mo`;

-- rerieve all developers
select * from person 
where id in (select personId from developer);


-- Retrieve a developer with id equal to 34  
select * from person
where id in (select personId from developer where personId = 34);
-- select * from developer where personId = 34;

-- Retrieve all developers who have a role in Twitter other than owner 
-- charlie and alice
select * from person 
where id in (select a.personId from 
(select * from developer d join websiterole w on d.personId = w.developerId) a 
where a.websiteId in (select id from website where `name` = 'Twitter') and a.rolechoice != 'owner');

-- Retrieve all developers who are page reviewers of pages with less than 300000 visits
-- charlie
select * from person where
id in (select a.personId from
(select * from developer de join pagerole pa on de.personId = pa.developerId) a 
where a.pageId in (select id from pages where views < 300000) and a.rolechoice = 'reviewer' );

-- Retrieve the writer developer who added a heading widget to CNET's home page 
-- charlie
select * from person where 
id in (select a.personId 
from (select * from developer d join pagerole p on d.personId = p.developerId) a 
where
pageId in (select id from pages where
websiteId in (select id from website where `name` = 'CNET')
and id in (select pageId from widget where dtype = 'heading') and title = 'Home') and rolechoice = 'writer');

-- Retrieve the website with the least number of visits
-- should return facebook row
select * from website where visits = (select min(visits) from website);

-- Retrieve the name of a website whose id is 678 (
-- should return Gizmodo
select `name` from website where id = 678;

-- Retrieve all websites with videos reviewed by bob (CNN)
-- should return CNN row
select * from website where
id in (select a.websiteId from (select websiteId, pageId, developerId, rolechoice
from pagerole pr join pages pa on pr.pageId = pa.id) a where 
a.pageId in (select pageId from widget where dtype = 'youtube')
and a.developerId = (select id from person where username = 'bob') and a.rolechoice = 'reviewer');

-- Retrieve all websites where alice is an owner 
-- should (Facebook, CNN) rows
select * from website where
id in (select websiteId from websiterole where 
developerId = (select id from person where `username` = 'alice') and rolechoice = 'owner');

-- Retrieve all websites where charlie is an admin and get more than 6000000 visits
-- should return CNN
SELECT * FROM website WHERE
id IN (SELECT websiteId FROM websiterole WHERE 
developerId = (SELECT id FROM person WHERE `username` = 'charlie')
AND rolechoice = 'admin') AND visits > 6000000;

-- Retrieve the page with the most number of views
-- websiteID 567
SELECT * FROM pages WHERE views = (SELECT max(views) FROM pages);

-- Retrieve the title of a page whose id is 234
-- About
SELECT title FROM pages WHERE id = 234;

-- Retrieve all pages where alice is an editor
-- should be Home and Preferences
SELECT * FROM pages WHERE
id IN (SELECT pageId FROM pagerole WHERE 
developerId = (SELECT id FROM person WHERE username = 'alice')
AND rolechoice = 'editor');

-- Retrieve the total number of pageviews in CNET
-- 691312
SELECT SUM(views) FROM pages WHERE
websiteId = (SELECT id FROM website WHERE `name`='CNET');

-- Retrieve the average number of page views in the Web site Wikipedia
-- 345656
SELECT AVG(views) FROM pages WHERE
websiteId = (SELECT id FROM website WHERE `name`='Wikipedia');

-- Retrieve all widgets in CNET's Home page
-- head123
SELECT * FROM widget WHERE 
pageId IN (SELECT id FROM pages WHERE title = 'Home' 
AND websiteId = (SELECT id FROM website WHERE `name` = 'CNET'));

-- Retrieve all youtube widgets in CNN
-- video456
SELECT * FROM widget WHERE
pageId IN (SELECT a.id FROM 
(SELECT p.id, w.`name` FROM pages p JOIN website w ON w.id = p.websiteId) a
WHERE a.`name` = 'CNN')
AND dtype = 'youtube';

-- Retrieve all image widgets on pages reviewed by Alice
-- image345
SELECT * FROM widget WHERE
pageId IN (SELECT pageId FROM pagerole WHERE
developerId = (SELECT id FROM person WHERE username = 'alice') AND rolechoice = 'reviewer') AND dtype = 'image';

-- Retrieve how many widgets are in Wikipedia
-- 3
SELECT COUNT(id) FROM widget WHERE
pageId IN (SELECT a.id FROM 
(SELECT p.id, w.`name` FROM pages p JOIN website w ON w.id = p.websiteId) a
WHERE a.`name` = 'Wikipedia');

-- Retrieve the names of all the websites where Bob has DELETE privileges.
-- Twitter, Wikipedia, CNET, Gizmodo (where Bob has either owner or admin roles).
SELECT `name` FROM website WHERE
id IN (SELECT websiteId FROM websitepriviledge 
WHERE priviledgechoice = 'delete'
AND developerId = (SELECT id FROM person WHERE username = 'bob'));

-- Retrieve the names of all the pages where Charlie has CREATE privileges.
-- Home, Preferences (where Charlie has Writer role)
SELECT title FROM pages WHERE
id IN (SELECT pageId FROM pagepriviledge 
WHERE priviledgechoice = 'create'
AND developerId = (SELECT id FROM person WHERE username = 'charlie'));