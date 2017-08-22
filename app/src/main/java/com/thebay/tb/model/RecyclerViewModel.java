package com.thebay.tb.model;

/**
 * Created by kyoungae on 2017-08-21.
 */

public class RecyclerViewModel {

    private String title;
    private String content;

    public RecyclerViewModel(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RecyclerViewModel{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
