use `cs5200_fall2019_Mo`;

drop view if exists `developer_roles_and_priviledges`;

CREATE VIEW developer_roles_and_priviledges AS
SELECT
pd.firstName, pd.lastName, pd.username, pd.email,
a.`name` websiteName, a.visits, a.websiteUpdated, a.websiteRole, a.websitePriviledge,
a.title pageTitle, a.views, a.pageUpdated, a.pageRole, a.pagePriviledge
FROM
(SELECT * FROM person pe JOIN developer de ON pe.id = de.personId) pd
LEFT JOIN
(SELECT 
wt.`name`, wt.visits, wt.updated websiteUpdated, wt.rolechoice websiteRole, wt.priviledgechoice websitePriviledge,
pt.title, pt.views, pt.updated pageUpdated, pt.rolechoice pageRole, pt.priviledgechoice pagePriviledge, wt.developerId
FROM
(SELECT
w.`name`, w.visits, w.updated, wtemp.rolechoice, wtemp.priviledgechoice, wtemp.developerId, wtemp.websiteId
FROM website w JOIN (SELECT 
wr.rolechoice, wp.priviledgechoice, wr.developerId, wr.websiteId
FROM websiterole wr INNER JOIN websitepriviledge wp ON wr.websiteId = wp.websiteId AND wr.developerId = wp.developerId) wtemp
ON w.id = wtemp.websiteId) wt
LEFT JOIN
(SELECT
p.title, p.views, p.updated, ptemp.rolechoice, ptemp.priviledgechoice, ptemp.developerId, p.websiteId
FROM pages p JOIN (SELECT 
pr.rolechoice, pp.priviledgechoice, pr.developerId, pr.pageId
FROM pagerole pr JOIN pagepriviledge pp ON pr.pageId = pp.pageId AND pr.developerId = pp.developerId) ptemp 
ON p.id = ptemp.pageId) pt
ON wt.websiteId = pt.websiteId AND wt.developerId = pt.developerId) a
ON pd.personId = a.developerId;