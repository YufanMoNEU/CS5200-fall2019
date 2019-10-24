package edu.northeastern.cs5200.models;

public class HeadingWidget extends Widget {
    private int size;

    public HeadingWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text, int order) {
        super(id, name, width, height, cssClass, cssStyle, text, order);
        this.size = 0;
    }

    public HeadingWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text, int order, int size) {
        super(id, name, width, height, cssClass, cssStyle, text, order);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}