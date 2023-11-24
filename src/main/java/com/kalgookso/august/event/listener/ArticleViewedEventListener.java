package com.kalgookso.august.event.listener;

import com.kalgookso.august.entity.Article;
import com.kalgookso.august.event.ArticleViewedEvent;
import com.kalgookso.august.service.ArticleService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 게시글 조회 이벤트 리스너 클래스입니다.
 * 이 클래스는 게시글 조회 이벤트를 처리하며, 이벤트가 발생하면 게시글의 조회수를 증가시킵니다.
 */
@Component
public class ArticleViewedEventListener {

    private final ArticleService articleService;  // ArticleService 인스턴스

    /**
     * ArticleViewedEventListener 생성자입니다.
     * @param articleService ArticleService 인스턴스
     */
    public ArticleViewedEventListener(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * 게시글 조회 이벤트를 처리하는 메서드입니다.
     * 이 메서드는 게시글 조회 이벤트가 발생하면 게시글의 조회수를 증가시킵니다.
     * @param event 게시글 조회 이벤트
     */
    @Async
    @EventListener
    public void handleArticleViewedEvent(ArticleViewedEvent event) {
        Article article = event.getArticle();

        // 게시글의 조회수를 증가시킵니다.
        article.increaseViewCount();

        // 변경된 게시글 정보를 데이터베이스에 저장합니다.
        articleService.save(article);
    }

}