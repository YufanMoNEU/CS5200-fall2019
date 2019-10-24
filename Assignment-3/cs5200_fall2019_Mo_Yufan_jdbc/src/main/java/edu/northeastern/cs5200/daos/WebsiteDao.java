package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Role;
import edu.northeastern.cs5200.models.Website;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class WebsiteDao implements WebsiteImpl {
	private static WebsiteDao instance = null;

    private WebsiteDao() {

    }

    public static WebsiteDao getInstance() {
        if (instance == null) {
            instance = new WebsiteDao();
        }
        return instance;
    }

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement pStatement = null;
    
    public void createWebsiteForDeveloper(int developerId, Website website) {
    	try {
    		connection = edu.northeastern.cs5200.Connection.getConnection();
    		pStatement = connection.prepareStatement("INSERT INTO `Website` (id, `name`, description, created, updated, visits) VALUES (?, ?, ?, ?, ?, ?)");
    		pStatement.setInt(1, website.getId());
    		pStatement.setString(2, website.getName());
    		pStatement.setString(3, website.getDescription());
    		java.util.Date created = website.getCreated();
            java.sql.Date createdSql;
            if (created != null) {
                createdSql = new java.sql.Date(created.getTime());
            } else createdSql = null;
            pStatement.setDate(4, createdSql);
            java.util.Date updated = website.getUpdated();
            java.sql.Date updatedSql;
            if (updated != null) {
                updatedSql = new java.sql.Date(updated.getTime());
            } else updatedSql = null;
            
            pStatement.setDate(5, updatedSql);
            pStatement.setInt(6, website.getVisits());
            pStatement.executeUpdate();
            pStatement = connection.prepareStatement("INSERT INTO WebsiteRole (role, developerId, websiteId) VALUES (?, ?, ?)");
            pStatement.setString(1, Role.OWNER.toString().toLowerCase());
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, website.getId());
            pStatement.executeUpdate();
    	}catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public Collection<Website> findAllWebsites() {
        Collection<Website> websites = new ArrayList<>();
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM `Website`");
            loadWebsites(websites, results);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return websites;
    }
    
    public Collection<Website> findWebsitesForDeveloper(int developerId){
    	Collection<Website> websites = new ArrayList<>();
    	try {
    		connection = edu.northeastern.cs5200.Connection.getConnection();
    		pStatement = connection.prepareStatement("SELECT * FROM Website w JOIN WebsiteRole wr ON w.id = wr.websiteId WHERE developerId = ?");
    		pStatement.setInt(1, developerId);
            ResultSet results = pStatement.executeQuery();
            loadWebsites(websites, results);
;    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return websites;
    }
	
    public Website findWebsiteById(int websiteId) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement("SELECT * FROM Website WHERE id=?");
            pStatement.setInt(1, websiteId);
            ResultSet results = pStatement.executeQuery();
            if (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                String description = results.getString("description");
                Date created = results.getDate("created");
                Date updated = results.getDate("updated");
                int visits = results.getInt("visits");
                Website website = new Website(id, name, description, created, updated, visits);
                return website;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int updateWebsite(int websiteId, Website website) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement("UPDATE Website SET `name`=?, description=?, created=?, updated=?, visits=? WHERE id=?");
            pStatement.setString(1, website.getName());
            pStatement.setString(2, website.getDescription());
            java.util.Date created = website.getCreated();
            java.sql.Date createdSql;
            if (created != null) {
                createdSql = new java.sql.Date(created.getTime());
            } else createdSql = null;
            pStatement.setDate(3, createdSql);
            java.util.Date updated = website.getUpdated();
            java.sql.Date updatedSql;
            if (updated != null) {
                updatedSql = new java.sql.Date(updated.getTime());
            } else updatedSql = null;
            pStatement.setDate(4, updatedSql);
            pStatement.setInt(5, website.getVisits());
            pStatement.setInt(6, websiteId);
            int num = pStatement.executeUpdate();
            return num;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int deleteWebsite(int websiteId) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement("DELETE FROM WebsiteRole WHERE websiteId=?");
            pStatement.setInt(1, websiteId);
            pStatement.executeUpdate();
            pStatement = connection.prepareStatement("DELETE FROM Website WHERE id=?");
            pStatement.setInt(1, websiteId);
            int num = pStatement.executeUpdate();
            return num;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    private void loadWebsites(Collection<Website> websites, ResultSet results) throws SQLException {
        while (results.next()) {
            int id = results.getInt("id");
            String name = results.getString("name");
            String description = results.getString("description");
            Date created = results.getDate("created");
            Date updated = results.getDate("updated");
            int visits = results.getInt("visits");
            Website website = new Website(id, name, description, created, updated, visits);
            websites.add(website);
        }
    }
    
}
