USE `cs5200_fall2019_Mo`;

-- Update Charlie's primary phone number to 333-444-5555
UPDATE phone SET phone = '333-444-5555' WHERE 
personId = (SELECT id FROM person WHERE username = 'charlie') AND `primary` = 1;

-- Update the relative order of widget head345 on the page so that it's new order is 3. 
-- Note that the other widget's order needs to update as well
UPDATE widget SET `order` = 3 WHERE `name` = 'head345';
SELECT @TMP := pageId FROM widget WHERE `name` = 'head345';
UPDATE widget SET `order` = `order` + 2 WHERE `name` != 'head345' AND pageId = @TMP;

-- Append 'CNET - ' to the beginning of all CNET's page titles
UPDATE pages SET title = CONCAT('CNET - ',title) WHERE websiteId IN (SELECT id FROM website WHERE `name` = 'CNET');

-- Update roles - Swap Charlie's and Bob's role in CNET's Home page
SELECT @TMP1 := rolechoice FROM pagerole WHERE
pageId IN (SELECT id FROM pages WHERE (title = 'Home' OR title = 'CNET - Home') AND
websiteId = (SELECT id FROM website WHERE `name` = 'CNET'))
AND developerId = (SELECT id FROM person WHERE username = 'charlie'); 

SELECT @TMP2 := rolechoice FROM pagerole WHERE
pageId IN (SELECT id FROM pages WHERE (title = 'Home' OR title = 'CNET - Home') AND
websiteId = (SELECT id FROM website WHERE `name` = 'CNET'))
AND developerId = (SELECT id FROM person WHERE username = 'bob');

UPDATE pagerole SET rolechoice = @TMP2 WHERE
pageId IN (SELECT id FROM pages WHERE (title = 'Home' OR title = 'CNET - Home') AND
websiteId = (SELECT id FROM website WHERE `name` = 'CNET'))
AND developerId = (SELECT id FROM person WHERE username = 'charlie');

UPDATE pagerole SET rolechoice = @TMP1 WHERE
pageId IN (SELECT id FROM pages WHERE (title = 'Home' OR title = 'CNET - Home') AND
websiteId = (SELECT id FROM website WHERE `name` = 'CNET'))
AND developerId = (SELECT id FROM person WHERE username = 'bob');