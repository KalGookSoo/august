package com.kalgookso.august.cms.service;

import com.kalgookso.august.cms.command.ArticleCommand;
import com.kalgookso.august.cms.entity.Article;
import com.kalgookso.august.cms.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class ArticleServiceTest {

    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Value("${com.kalgookso.august.filepath}")
    private String uploadPath;

    private Article testArticle;

    @BeforeEach
    public void setup() {
        articleService = new DefaultArticleService(articleRepository, eventPublisher, uploadPath);
//        ArticleCommand command = new ArticleCommand("테스트 게시글", "테스트 게시글 내용");
//        testArticle = articleService.create(command);
    }

    @Test
    @DisplayName("제목과 본문을 가진 게시글을 생성합니다.")
    @WithMockUser(username = "tester", password = "1234", roles = "USER")
    public void createArticleTest() {
        ArticleCommand command = new ArticleCommand("테스트 게시글", "테스트 게시글 내용");
        Article article = articleService.create(command);
        assertNotNull(article.getId());
        assertEquals("테스트 게시글", article.getTitle());
        assertEquals("테스트 게시글 내용", article.getContent());
        assertEquals("tester", article.getCreatedBy());
    }

}