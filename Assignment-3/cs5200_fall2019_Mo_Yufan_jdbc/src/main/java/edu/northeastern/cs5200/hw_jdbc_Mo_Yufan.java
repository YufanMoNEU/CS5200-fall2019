package edu.northeastern.cs5200;

import edu.northeastern.cs5200.daos.*;
import edu.northeastern.cs5200.models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class hw_jdbc_Mo_Yufan {

    public void create() {
        //insert Person
    	//date is null
        Developer alice = new Developer("4321rewq", 12, "Alice", "Wonder", "alice", "alice", "alice@wonder.com", null);
        createDeveloper(alice);
        Developer bob = new Developer("5432trew", 23, "Bob", "Marley", "bob", "bob", "bob@marley.com", null);
        createDeveloper(bob);
        Developer charlie = new Developer("6543erty", 34, "Charlie", "Garcia", "charlie", "charlie", "chuch@garcia.com", null);
        createDeveloper(charlie);
        User dan = new User(45, "Dan", "Martin", "dan", "dan",
                "dan@martin.com", null);
        createUser(dan);
        User ed = new User(56, "Ed", "Karaz", "ed", "ed",
                "ed@kar.com", null);
        createUser(ed);

        //insert website
        Date today = new Date();
        Website facebook = new Website(123, "Facebook", "an online social media and social networking service",
                today, today, 1234234);
        createWebsite(12, facebook);
        Website twitter = new Website(234, "Twitter", "an online news and social networking service",
                today, today, 4321543);
        createWebsite(23, twitter);
        Website wikipedia = new Website(345, "Wikipedia", "a free online encyclopedia",
                today, today, 3456654);
        createWebsite(34, wikipedia);
        Website cnn = new Website(456, "CNN", "an American basic cable and satellite television news channel",
                today, today, 6543345);
        createWebsite(12, cnn);
        Website cnet = new Website(567, "CNET", "an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics",
                today, today, 5433455);
        createWebsite(23, cnet);
        Website gizmodo = new Website(678, "Gizmodo", "a design, technology, science and science fiction website that also writes articles on polit",
                today, today, 4322345);
        createWebsite(34, gizmodo);

        //insert website role
        RoleDao roleDao = RoleDao.getInstance();
        roleDao.assignWebsiteRole(23, 123, 4);
        roleDao.assignWebsiteRole(34, 123, 2);
        roleDao.assignWebsiteRole(34, 234, 4);
        roleDao.assignWebsiteRole(12, 234, 2);
        roleDao.assignWebsiteRole(12, 345, 4);
        roleDao.assignWebsiteRole(23, 345, 2);
        roleDao.assignWebsiteRole(23, 456, 4);
        roleDao.assignWebsiteRole(34, 456, 2);
        roleDao.assignWebsiteRole(34, 567, 4);
        roleDao.assignWebsiteRole(12, 567, 2);
        roleDao.assignWebsiteRole(12, 678, 4);
        roleDao.assignWebsiteRole(23, 678, 2);

        //insert page
        PageDao pageDao = PageDao.getInstance();
        java.sql.Date created = java.sql.Date.valueOf("2018-09-05");
        java.sql.Date updated = java.sql.Date.valueOf("2018-10-24");
        Page home = new Page(123, "Home", "Landing page", created, updated, 123434);
        pageDao.createPageForWebsite(567, home);
        Page about = new Page(234, "About", "Website description", created, updated, 234545);
        pageDao.createPageForWebsite(678, about);
        Page contact = new Page(345, "Contact", "Addresses, phones, and contact info", created, updated, 345656);
        pageDao.createPageForWebsite(345, contact);
        Page preferences = new Page(456, "Preferences", "Where users can configure their preferences", created, updated, 456776);
        pageDao.createPageForWebsite(456, preferences);
        Page profile = new Page(567, "Profile", "Users can configure their personal infomation", created, updated, 567878);
        pageDao.createPageForWebsite(567, profile);

        //insert page role
        roleDao.assignPageRole(12, 123, 4);
        roleDao.assignPageRole(23, 123, 5);
        roleDao.assignPageRole(34, 123, 3);
        roleDao.assignPageRole(23, 234, 4);
        roleDao.assignPageRole(34, 234, 5);
        roleDao.assignPageRole(12, 234, 3);
        roleDao.assignPageRole(34, 345, 4);
        roleDao.assignPageRole(12, 345, 5);
        roleDao.assignPageRole(23, 345, 3);
        roleDao.assignPageRole(12, 456, 4);
        roleDao.assignPageRole(23, 456, 5);
        roleDao.assignPageRole(34, 456, 3);
        roleDao.assignPageRole(23, 567, 4);
        roleDao.assignPageRole(34, 567, 5);
        roleDao.assignPageRole(12, 567, 3);

        //insert widget
        HeadingWidget head123 = new HeadingWidget(1, "head123", 0, 0, null, null, "Welcome", 0, 0);
        createHeadingForPage(123, head123);
        HtmlWidget post234 = new HtmlWidget(2, "post234", 0, 0, null, null, "<p>Lorem</p>", 0);
        createHtmlForPage(234, post234);
        HeadingWidget head345 = new HeadingWidget(3, "head345", 0, 0, null, null, "Hi", 1);
        createHeadingForPage(345, head345);
        HtmlWidget intro345 = new HtmlWidget(4, "intro345", 0, 0, null, null, "<h1>Hi</h1>", 2);
        createHtmlForPage(345, intro345);
        ImageWidget image345 = new ImageWidget(5, "image345", 50, 100, null, null, null, 3, "/img/567.png");
        createImageForPage(345, image345);
        YouTubeWidget video456 = new YouTubeWidget(6, "video456", 400, 300, null, null, null, 0, "https://youtu.be/h67VX51QXiQ");
        createYouTubeForPage(456, video456);
    }

    public void update() {

        // Update developer - Update Charlie's primary phone number to 333-444-5555
        updateDeveloper();

        // Update widget - Update the relative order of widget head345 on the page so that it's new order is 3. Note that the other widget's order needs to update as well
        updateWidget();

        // Update page - Append 'CNET - ' to the beginning of all CNET's page titles
        updatePage();

        // Update roles - Swap Charlie's and Bob's role in CNET's Home page
        updateRoles();
    }

    public void delete() {

        //Delete developer - Delete Alice's primary address
        deleteDeveloperAddress();

        // Delete widget - Remove the last widget in the Contact page. The last widget is the one with the highest value in the order field
        deleteLastWidget();

        // Delete page - Remove the last updated page in Wikipedia
        deleteLastPage();

        // Delete website - Remove the CNET web site, as well as all related roles and privileges relating developers to the Website and Pages
        WebsiteDao websiteDao = WebsiteDao.getInstance();
        websiteDao.deleteWebsite(567);
    }

    private java.sql.Connection connection = null;
    private Statement statement = null;
    private PreparedStatement pStatement = null;

    private final String INSERT_HEADING = "INSERT INTO HeadingWidget (widgetId, `size`) VALUES (?, ?)";
    private final String INSERT_HTML = "INSERT INTO HtmlWidget (widgetId, html) VALUES (?, ?)";
    private final String INSERT_IMAGE= "INSERT INTO ImageWidget (widgetId, src) VALUES (?, ?)";
    private final String INSERT_YOUTUBE = "INSERT INTO YouTubeWidget (widgetId, url, shareable, expandable) VALUES (?, ?, ?, ?)";
    private final String FIND_PAGE_ROLE_FOR_DEVELOPER = "SELECT role FROM PageRole WHERE pageId=? AND developerId=?";
    private final String FIND_WIDGET_BY_NAME = "SELECT * FROM Widget WHERE `name`=?";
    private final String FIND_MAX_ORDER_FOR_WIDGET = "SELECT MAX(`order`) AS `last` FROM Widget WHERE pageId = (SELECT id FROM Page WHERE title=?)";
    private final String DELETE_LAST_WIDGET = "DELETE FROM Widget WHERE `order`=? AND pageId = (SELECT id FROM Page WHERE title=?)";
    private final String FIND_LAST_UPDATED_PAGE_FOR_WEBSITE = "SELECT updated FROM Page WHERE websiteId=(SELECT id FROM Website WHERE name=?) ORDER BY updated DESC LIMIT 1";
    private final String DELETE_LAST_PAGE = "DELETE FROM Page WHERE updated=? AND websiteId=(SELECT id FROM Website WHERE name=?)";
    private final String FIND_UNANSWERED_QUESTIONS = "SELECT q.text AS qText, COUNT(a.id) AS aNum FROM " +
            "question q, `User` u, answer a WHERE u.personId = q.askedBy AND a.postedBy = u.personId AND a.correctAnswer = 0 GROUP BY q.module HAVING MAX(aNum)";
    private final String ENDORSED_USERS_FOR_WEEK = "SELECT u.personId, CONCAT(u.firstName, \" \", u.lastName) AS fullName, approved " +
            "FROM (" +
            "SELECT u.personId, u.firstName, u.lastName, u.approvedUser AS approved, " +
            "FROM question q, `User` u, answer a " +
            "WHERE q.askedBy = u.personId AND u.personId = a.postedBy AND a.postedOn BETWEEN ? AND ? " +
            "ORDER BY COUNT(q.endorsedByInstructor) DESC LIMIT 5" +
            ") ORDER BY u.firstName";

    // Stored Procedures
    public void getUnansweredQuestions() {
        try {
            connection = Connection.getConnection();
            statement = connection.createStatement();
            ResultSet results = statement.executeQuery(FIND_UNANSWERED_QUESTIONS);
            while (results.next()) {
                String text = results.getString("qText");
                int count = results.getInt("aNum");
                System.out.println(text + " " + count);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void endorsedUsersForWeek(Date start, Date end) {
        try {
            connection = Connection.getConnection();
            pStatement = connection.prepareStatement(ENDORSED_USERS_FOR_WEEK);
            pStatement.setDate(1, new java.sql.Date(start.getTime()));
            pStatement.setDate(2, new java.sql.Date(end.getTime()));
            ResultSet results = pStatement.executeQuery();
            while (results.next()) {
                String id = results.getString("u.personId");
                String name = results.getString("fullName");
                Boolean approved = results.getBoolean("u.approvedUser");
                System.out.println(id + " " + name + " " + approved);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createDeveloper(Developer developer) {
        DeveloperDao dao = DeveloperDao.getInstance();
        dao.createDeveloper(developer);
    }

    private void createUser(User user) {
        UserDao dao = UserDao.getInstance();
        dao.createUser(user);
    }

    private void createWebsite(int developId, Website website) {
        WebsiteDao dao = WebsiteDao.getInstance();
        dao.createWebsiteForDeveloper(developId, website);
    }

    //createWidgets
    private void createHeadingForPage(int pageId, HeadingWidget headingWidget) {
        try {
            WidgetDao widgetDao = WidgetDao.getInstance();
            widgetDao.createWidgetForPage(pageId, headingWidget);
            connection = Connection.getConnection();
            pStatement = connection.prepareStatement(INSERT_HEADING);
            pStatement.setInt(1, headingWidget.getId());
            pStatement.setInt(2, headingWidget.getSize());
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createHtmlForPage(int pageId, HtmlWidget htmlWidget) {
        try {
            WidgetDao widgetDao = WidgetDao.getInstance();
            widgetDao.createWidgetForPage(pageId, htmlWidget);
            connection = Connection.getConnection();
            pStatement = connection.prepareStatement(INSERT_HTML);
            pStatement.setInt(1, htmlWidget.getId());
            pStatement.setString(2, htmlWidget.getHtml());
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createImageForPage(int pageId, ImageWidget imageWidget) {
        try {
            WidgetDao widgetDao = WidgetDao.getInstance();
            widgetDao.createWidgetForPage(pageId, imageWidget);
            connection = Connection.getConnection();
            pStatement = connection.prepareStatement(INSERT_IMAGE);
            pStatement.setInt(1, imageWidget.getId());
            pStatement.setString(2, imageWidget.getSrc());
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createYouTubeForPage(int pageId, YouTubeWidget youTubeWidget) {
        try {
            WidgetDao widgetDao = WidgetDao.getInstance();
            widgetDao.createWidgetForPage(pageId, youTubeWidget);
            connection = Connection.getConnection();
            pStatement = connection.prepareStatement(INSERT_YOUTUBE);
            pStatement.setInt(1, youTubeWidget.getId());
            pStatement.setString(2, youTubeWidget.getUrl());
            pStatement.setInt(3, youTubeWidget.getShareble() ? 1 : 0);
            pStatement.setInt(4, youTubeWidget.getExpandable() ? 1 : 0);
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update
    private void updateDeveloper() {
        DeveloperDao developerDao = DeveloperDao.getInstance();
        Developer charlie = developerDao.findDeveloperByUsername("charlie");
        Collection<Phone> phones = charlie.getPhones();
        charlie.setPhones(new ArrayList<>());
        if (phones.isEmpty()) {
            //Insert primary phone
            Phone charliePhone = new Phone(true, "333-444-5555", charlie);
            charlie.addPhone(charliePhone);
        } else {
            for (Phone phone : phones) {
                if (phone.getPrimary()) {
                    phone.setPhone("333-444-5555");
                }
                charlie.addPhone(phone);
            }
        }
        developerDao.updateDeveloper(charlie.getId(), charlie);
    }

    private void updateWidget() {
        WidgetDao widgetDao = WidgetDao.getInstance();
        int pageId = findPageIdForWidgetByName("head345");
        Collection<Widget> widgets = widgetDao.findWidgetsForPage(pageId);
        for (Widget widget : widgets) {
            widget.setOrder(widget.getOrder() + 2);
            widgetDao.updateWidget(widget.getId(), widget);
        }
    }

    private void updatePage() {
        PageDao pageDao = PageDao.getInstance();
        Collection<Page> pages = pageDao.findPagesForWebsite(567);
        for (Page page : pages) {
            page.setTitle("CNET - " + page.getTitle());
            pageDao.updatePage(page.getId(), page);
        }
    }

    private void updateRoles() {
        RoleDao roleDao = RoleDao.getInstance();
        Role bobRole = findPageRoleForDeveloper(123, 23);
        Role charlieRole = findPageRoleForDeveloper(123, 34);
        roleDao.deletePageRole(23, 123, bobRole.getRoleId());
        roleDao.assignPageRole(23, 123, charlieRole.getRoleId());
        roleDao.deletePageRole(34, 123, charlieRole.getRoleId());
        roleDao.assignPageRole(34, 123, bobRole.getRoleId());
    }

    //delete
    private void deleteDeveloperAddress() {
        try {
            connection = Connection.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM Address WHERE personId=12 AND `primary`=1");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteLastWidget() {
        try {
            connection = Connection.getConnection();
            pStatement = connection.prepareStatement(FIND_MAX_ORDER_FOR_WIDGET);
            pStatement.setString(1, "Contact");
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                int last = resultSet.getInt("last");
                pStatement = connection.prepareStatement(DELETE_LAST_WIDGET);
                pStatement.setInt(1, last);
                pStatement.setString(2, "Contact");
                pStatement.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteLastPage() {
        try {
            connection = Connection.getConnection();
            pStatement = connection.prepareStatement(FIND_LAST_UPDATED_PAGE_FOR_WEBSITE);
            pStatement.setString(1, "Wikipedia");
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                Date updated = resultSet.getDate("updated");
                pStatement = connection.prepareStatement(DELETE_LAST_PAGE);
                pStatement.setDate(1, new java.sql.Date(updated.getTime()));
                pStatement.setString(2, "Wikipedia");
                pStatement.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //helper functions
    private Role findPageRoleForDeveloper(int pageId, int developerId) {
        try {
            connection = Connection.getConnection();
            pStatement = connection.prepareStatement(FIND_PAGE_ROLE_FOR_DEVELOPER);
            pStatement.setInt(1, pageId);
            pStatement.setInt(2, developerId);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                String role = resultSet.getString("role");
                int roleId;
                switch (role) {
                    case "owner":
                        roleId = 1;
                        break;
                    case "admin":
                        roleId = 2;
                        break;
                    case "writer":
                        roleId = 3;
                        break;
                    case "editor":
                        roleId = 4;
                        break;
                    case "reviewer":
                        roleId = 5;
                        break;
                    default:
                        roleId = 0;
                }
                return Role.fromInt(roleId);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int findPageIdForWidgetByName(String name) {
        try {
            connection = Connection.getConnection();
            pStatement = connection.prepareStatement(FIND_WIDGET_BY_NAME);
            pStatement.setString(1, name);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("pageId");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
