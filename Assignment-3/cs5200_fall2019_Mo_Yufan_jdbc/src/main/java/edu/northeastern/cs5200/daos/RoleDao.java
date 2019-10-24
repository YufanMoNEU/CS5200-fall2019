package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RoleDao implements RoleImpl {

    private static RoleDao instance = null;

    public RoleDao() {
    }

    public static RoleDao getInstance() {
        if (instance == null) {
            instance = new RoleDao();
        }
        return instance;
    }

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement pStatement = null;

    private final String INSERT_WEBSITE_ROLE = "INSERT INTO WebsiteRole (role, developerId, websiteId) VALUES (?, ?, ?)";
    private final String INSERT_PAGE_ROLE = "INSERT INTO PageRole (role, developerId, pageId) VALUES (?, ?, ?)";
    private final String DELETE_WEBSITE_ROLE = "DELETE FROM WebsiteRole WHERE role=? AND developerId=? AND websiteId=?";
    private final String DELETE_PAGE_ROLE = "DELETE FROM PageRole WHERE role=? AND developerId=? AND pageId=?";

    public void assignWebsiteRole(int developerId, int websiteId, int roleId) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement(INSERT_WEBSITE_ROLE);
            pStatement.setString(1, Role.fromInt(roleId).toString().toLowerCase());
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, websiteId);
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void assignPageRole(int developerId, int pageId, int roleId) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement(INSERT_PAGE_ROLE);
            pStatement.setString(1, Role.fromInt(roleId).toString().toLowerCase());
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, pageId);
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteWebsiteRole(int developerId, int websiteId, int roleId) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement(DELETE_WEBSITE_ROLE);
            pStatement.setString(1, Role.fromInt(roleId).toString().toLowerCase());
            pStatement.setInt(2, developerId);
            pStatement.setInt(3, websiteId);
            pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePageRole(int developerId, int pageId, int roleId) {
        try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement(DELETE_PAGE_ROLE);
            pStatement.setString(1, Role.fromInt(roleId).toString().toLowerCase());
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
