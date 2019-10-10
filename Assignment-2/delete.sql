use `cs5200_fall2019_Mo`;

-- Delete Alice's primary address
DELETE FROM address WHERE `primary` = 1 AND
personId = (SELECT id FROM person WHERE username = 'alice');

-- Remove the last widget in the Contact page. The last widget is the one with the highest value in the order field
SELECT @TMP := MAX(`order`) FROM widget WHERE pageId = (SELECT id FROM pages WHERE title = 'Contact');

DELETE FROM widget WHERE `order` = @TMP AND pageId = (SELECT id FROM pages WHERE title = 'Contact');

-- Remove the last updated page in Wikipedia
SELECT @TMP1 := updated FROM pages ORDER BY updated DESC LIMIT 1;

DELETE FROM pages WHERE updated = @TMP1;

-- Remove the CNET web site, as well as all related roles and privileges relating developers to the Website and Pages
DELETE FROM website WHERE `name` = 'CNET';