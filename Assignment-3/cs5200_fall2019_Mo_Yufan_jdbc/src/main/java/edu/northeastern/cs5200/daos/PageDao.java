package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Page;
import edu.northeastern.cs5200.models.Website;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PageDao implements PageImpl {
    private static PageDao instance = null;
    private PageDao() {}

    public static PageDao getInstance() {
        if (instance == null) {
            instance = new PageDao();
        }
        return instance;
    }

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement pStatement = null;

    private final String INSERT_PAGE = "INSERT INTO Page (id, title, description, created, updated, views, websiteId) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String FIND_ALL_PAGES = "SELECT * FROM Page";
    private final String FIND_PAGE_BY_ID = "SELECT * FROM Page WHERE id=?";
    private final String FIND_PAGE_FOR_WEBSITE = "SELECT * FROM Page WHERE websiteId=?";
    private final String UPDATE_PAGE = "UPDATE Page SET title=?, description=?, created=?, updated=?, views=?, websiteId=? WHERE id=?";
    private final String DELETE_PAGE = "DELETE FROM Page WHERE id=?";

    public void createPageForWebsite(int websiteId, Page page) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement(INSERT_PAGE);
            pStatement.setInt(1, page.getId());
            pStatement.setString(2, page.getTitle());
            pStatement.setString(3, page.getDescription());
            java.util.Date created = page.getCreated();
            java.sql.Date createdSql;
            if (created != null) {
                createdSql = new java.sql.Date(created.getTime());
            } else createdSql = null;
            pStatement.setDate(4, createdSql);
            java.util.Date updated = page.getUpdated();
            java.sql.Date updatedSql;
            if (updated != null) {
                updatedSql = new java.sql.Date(updated.getTime());
            } else updatedSql = null;
            pStatement.setDate(5, updatedSql);
            pStatement.setInt(6, page.getViews());
            pStatement.setInt(7, websiteId);
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Collection<Page> findAllPages() {
        Collection<Page> pages = new ArrayList<>();
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            statement = connection.createStatement();
            ResultSet results = statement.executeQuery(FIND_ALL_PAGES);
            loadPages(pages, results);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pages;
    }

    public Page findPageById(int pageId) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement(FIND_PAGE_BY_ID);
            pStatement.setInt(1, pageId);
            ResultSet results = pStatement.executeQuery();
            if (results.next()) {
                int id = results.getInt("id");
                String title = results.getString("title");
                String description = results.getString("description");
                Date created = results.getDate("created");
                Date updated = results.getDate("updated");
                int views = results.getInt("views");
                int websiteId = results.getInt("websiteId");
                Page page = new Page(id, title, description, created, updated, views);
                WebsiteDao dao = WebsiteDao.getInstance();
                Website website = dao.findWebsiteById(websiteId);
                page.setWebsite(website);
                return page;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<Page> findPagesForWebsite(int websiteId) {
        Collection<Page> pages = new ArrayList<>();
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement(FIND_PAGE_FOR_WEBSITE);
            pStatement.setInt(1, websiteId);
            ResultSet results = pStatement.executeQuery();
            loadPages(pages, results);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pages;
    }

    public int updatePage(int pageId, Page page) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement(UPDATE_PAGE);
            pStatement.setString(1, page.getTitle());
            pStatement.setString(2, page.getDescription());
            java.util.Date created = page.getCreated();
            java.sql.Date createdSql;
            if (created != null) {
                createdSql = new java.sql.Date(created.getTime());
            } else createdSql = null;
            pStatement.setDate(3, createdSql);
            java.util.Date updated = page.getUpdated();
            java.sql.Date updatedSql;
            if (updated != null) {
                updatedSql = new java.sql.Date(updated.getTime());
            } else updatedSql = null;
            pStatement.setDate(4, updatedSql);
            pStatement.setInt(5, page.getViews());
            pStatement.setInt(6, page.getWebsite().getId());
            pStatement.setInt(7, page.getId());
            int num = pStatement.executeUpdate();
            return num;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deletePage(int pageId) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement(DELETE_PAGE);
            pStatement.setInt(1, pageId);
            int num = pStatement.executeUpdate();
            return num;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void loadPages(Collection<Page> pages, ResultSet results) throws SQLException {
        while (results.next()) {
            int id = results.getInt("id");
            String title = results.getString("title");
            String description = results.getString("description");
            Date created = results.getDate("created");
            Date updated = results.getDate("updated");
            int views = results.getInt("views");
            int websiteId = results.getInt("websiteId");
            Page page = new Page(id, title, description, created, updated, views);
            WebsiteDao dao = WebsiteDao.getInstance();
            Website website = dao.findWebsiteById(websiteId);
            page.setWebsite(website);
            pages.add(page);
        }
    }

}