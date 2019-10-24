package edu.northeastern.cs5200.models;

public class HtmlWidget extends Widget {
    private String html;

    public HtmlWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text, int order) {
        super(id, name, width, height, cssClass, cssStyle, text, order);
    }

    public HtmlWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text, int order, String html) {
        super(id, name, width, height, cssClass, cssStyle, text, order);
        this.html = html;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}