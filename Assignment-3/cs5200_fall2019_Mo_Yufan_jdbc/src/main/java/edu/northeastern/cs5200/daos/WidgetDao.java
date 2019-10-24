package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.*;

import java.sql.*;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.*;

public class WidgetDao implements WidgetImpl {
	private static WidgetDao instance = null;
	private WidgetDao() {};
	public static WidgetDao getInstance() {
		if (instance == null) {
			instance = new WidgetDao();
		}
		return instance;
	}
	
	private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement pStatement = null;
    
    public int createWidgetForPage(int pageId, Widget widget) {
    	try{
    		connection = edu.northeastern.cs5200.Connection.getConnection();
    		pStatement = connection.prepareStatement("INSERT INTO `Widget` (`id`, `name`, `width`, `height`, `cssClass`, `cssStyle`, `text`, `order`, `pageId`) VALUES (?,?,?,?,?,?,?,?,?)");
    		pStatement.setInt(1, widget.getId());
            pStatement.setString(2, widget.getName());
            pStatement.setInt(3, widget.getWidth());
            pStatement.setInt(4, widget.getHeight());
            pStatement.setString(5, widget.getCssClass());
            pStatement.setString(6, widget.getCssStyle());
            pStatement.setString(7, widget.getText());
            pStatement.setInt(8, widget.getOrder());
            pStatement.setInt(9, pageId);
            return pStatement.executeUpdate();
    	} catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    	
    	
    }
    
    public Collection<Widget> findAllWidgets(){
    	Collection<Widget> widgets = new ArrayList<>();
    	try {
    		connection = edu.northeastern.cs5200.Connection.getConnection();
    		statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM `Widget`");
    		while (results.next()) {
    			int id = results.getInt("id");
                String name = results.getString("name");
                int width = results.getInt("width");
                int height = results.getInt("height");
                String cssClass = results.getString("cssClass");
                String cssStyle = results.getString("cssStyle");
                String text = results.getString("text");
                int order = results.getInt("order");
                int pageId = results.getInt("pageId");
                Widget widget = new Widget(id, name, width, height, cssClass, cssStyle, text, order);
                PageDao dao = PageDao.getInstance();
                Page page = dao.findPageById(pageId);
                widget.setPage(page);
                widgets.add(widget);
    		}
    		
    		
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return widgets;
    }

    public Widget findWidgetById(int widgetId) {
    	try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement("SELECT * FROM `Widget` where id=?");
            pStatement.setInt(1, widgetId);
            ResultSet results = pStatement.executeQuery();
            if (results.next()) {
                int id = results.getInt("id");
                String name = results.getString("name");
                int width = results.getInt("width");
                int height = results.getInt("height");
                String cssClass = results.getString("cssClass");
                String cssStyle = results.getString("cssStyle");
                String text = results.getString("text");
                int order = results.getInt("order");
                int pageId = results.getInt("pageId");
                Widget widget = new Widget(id, name, width, height, cssClass, cssStyle, text, order);
                PageDao dao = PageDao.getInstance();
                Page page = dao.findPageById(pageId);
                widget.setPage(page);
                return widget;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Collection<Widget> findWidgetsForPage(int pageId){
    	Collection<Widget> widgets = new ArrayList<>();
    	try {
    		connection = edu.northeastern.cs5200.Connection.getConnection();
    		pStatement = connection.prepareStatement("SELECT * FROM `Widget` where pageId=?");
    		pStatement.setInt(1,pageId);
    		ResultSet results = pStatement.executeQuery();
    		while (results.next()) {
    			int id = results.getInt("id");
    			String name = results.getString("name");
                int width = results.getInt("width");
                int height = results.getInt("height");
                String cssClass = results.getString("cssClass");
                String cssStyle = results.getString("cssStyle");
                String text = results.getString("text");
                int order = results.getInt("order");
                Widget widget = new Widget(id, name, width, height, cssClass, cssStyle, text, order);
                PageDao dao = PageDao.getInstance();
                Page page = dao.findPageById(pageId);
                widget.setPage(page);
                widgets.add(widget);
    		}
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
    	return widgets;
    }
	
    public int updateWidget(int widgetId, Widget widget) {
    	try {
    		connection = edu.northeastern.cs5200.Connection.getConnection();
    		pStatement = connection.prepareStatement("UPDATE `Widget` SET `name`=?, `width`=?, `height`=?, `cssClass`=?, `cssStyle`=?, `text`=?, `order`=?, `pageId`=? WHERE `id` = ? )");
    		pStatement.setString(1,widget.getName());
    		pStatement.setInt(2, widget.getWidth());
    		pStatement.setInt(3, widget.getHeight());
            pStatement.setString(4, widget.getCssClass());
            pStatement.setString(5, widget.getCssStyle());
            pStatement.setString(6, widget.getText());
            pStatement.setInt(7, widget.getOrder());
            pStatement.setInt(8, widget.getPage().getId());
            pStatement.setInt(9, widgetId);
            return pStatement.executeUpdate();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
    	return 0;
    }
    
    public int deleteWidget(int widgetId) {
    	try {
            connection = edu.northeastern.cs5200.Connection.getConnection();
            pStatement = connection.prepareStatement("DELETE FROM Widget WHERE id=?");
            pStatement.setInt(1, widgetId);
            return pStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
