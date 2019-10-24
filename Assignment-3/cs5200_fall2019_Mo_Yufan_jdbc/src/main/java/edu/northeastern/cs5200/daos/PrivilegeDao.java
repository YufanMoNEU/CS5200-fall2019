package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Privilege;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PrivilegeDao implements PrivilegeImpl {
    private static PrivilegeDao instance = null;

    public PrivilegeDao() {
    }

    public static PrivilegeDao getInstance() {
        if (instance == null) {
            instance = new PrivilegeDao();
        }
        return instance;
    }

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement pStatement = null;

    private final String INSERT_WEBSITE_PRIVILEGE = "INSERT INTO WebsitePrivilege (Privilege, developerId, websiteId) VALUES (?, ?, ?)";
    private final String INSERT_PAGE_PRIVILEGE = "INSERT INTO PagePrivilege (Privilege, developerId, pageId) VALUES (?, ?, ?)";
    private final String DELETE_WEBSITE_PRIVILEGE = "DELETE FROM WebsitePrivilege WHERE Privilege=? AND developerId=? AND websiteId=?";
    private final String DELETE_PAGE_PRIVILEGE = "DELETE FROM PagePrivilege WHERE Privilege=? AND developerId=? AND pageId=?";

    public void assignWebsitePrivilege(int developerId, int websiteId, String privilege) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement(INSERT_WEBSITE_PRIVILEGE);
            pStatement.setString(1, Privilege.fromString(privilege).toString().toLowerCase());
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, websiteId);
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void assignPagePrivilege(int developerId, int pageId, String privilege) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement(INSERT_PAGE_PRIVILEGE);
            pStatement.setString(1, Privilege.fromString(privilege).toString().toLowerCase());
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, pageId);
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWebsitePrivilege(int developerId, int websiteId, String privilege) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement(DELETE_WEBSITE_PRIVILEGE);
            pStatement.setString(1, Privilege.fromString(privilege).toString().toLowerCase());
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, websiteId);
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePagePrivilege(int developerId, int pageId, String privilege) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement(DELETE_PAGE_PRIVILEGE);
            pStatement.setString(1, Privilege.fromString(privilege).toString().toLowerCase());
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, pageId);
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
