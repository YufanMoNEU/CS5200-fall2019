package edu.northeastern.cs5200.models;

public class YouTubeWidget extends Widget {
    private String url;
    private Boolean isShareable = false;
    private Boolean isExpandable = false;

    public YouTubeWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text, int order, String url) {
        super(id, name, width, height, cssClass, cssStyle, text, order);
        this.url = url;
    }

    public YouTubeWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text, int order, String url, Boolean isShareable, Boolean isExpandable) {
        super(id, name, width, height, cssClass, cssStyle, text, order);
        this.url = url;
        this.isShareable = isShareable;
        this.isExpandable = isExpandable;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getShareble() {
        return isShareable;
    }

    public void setShareble(Boolean shareble) {
        isShareable = shareble;
    }

    public Boolean getExpandable() {
        return isExpandable;
    }

    public void setExpandable(Boolean expandable) {
        isExpandable = expandable;
    }
}