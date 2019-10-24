package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.HeadingWidget;
import edu.northeastern.cs5200.models.Widget;

import java.util.*;

public interface WidgetImpl {
    int createWidgetForPage(int pageId, Widget widget);
    Collection<Widget> findAllWidgets();
    Widget findWidgetById(int widgetId);
    Collection<Widget> findWidgetsForPage(int pageId);
    int updateWidget(int widgetId, Widget widget);
    int deleteWidget(int widgetId);
}