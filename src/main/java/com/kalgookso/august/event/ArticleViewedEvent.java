package com.kalgookso.august.event;

import com.kalgookso.august.entity.Article;
import org.springframework.context.ApplicationEvent;

public class ArticleViewedEvent extends ApplicationEvent {

    private final Article article;

    public ArticleViewedEvent(Article article) {
        super(article);
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }

}