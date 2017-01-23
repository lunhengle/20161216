package com.lhl.lucene.bean;

/**
 * 文章表.
 * Created by lunhengle on 2017/1/22.
 */
public class Article {
    /**
     * id.
     */
    private int id;
    /**
     * 标题.
     */
    private String title;
    /**
     * 内容.
     */
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
