package com.kalgookso.august.controller;

import com.kalgookso.august.command.ArticleCommand;
import com.kalgookso.august.command.CommentCommand;
import com.kalgookso.august.criteria.ArticleCriteria;
import com.kalgookso.august.entity.article.Article;
import com.kalgookso.august.service.ArticleService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/categories/{categoryId}/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public String createArticle(@PathVariable String categoryId, @Valid @ModelAttribute("command") Article article, BindingResult bindingResult, Model model) {
        // 게시글 생성 로직
        return "redirect:/categories/" + categoryId + "/articles/" + article.getId();
    }

    @GetMapping
    public String getArticlesByCategoryId(@PathVariable String categoryId, ArticleCriteria criteria, @PageableDefault Pageable pageable, Model model) {
        // 카테고리별 게시글 목록 조회 로직
        return "categories/articles/list";
    }

    @GetMapping("/{articleId}")
    public String getArticle(@PathVariable String categoryId, @PathVariable String articleId, Model model) {
        // 게시글 조회 로직
        return "categories/articles/view";
    }

    @PutMapping("/{articleId}")
    public String updateArticle(@PathVariable String categoryId, @PathVariable String articleId, @Valid @ModelAttribute("command") ArticleCommand command, BindingResult bindingResult, Model model) {
        // 게시글 수정 로직
        return "redirect:/categories/" + categoryId + "/articles/" + articleId;
    }

    @DeleteMapping("/{articleId}")
    public String deleteArticle(@PathVariable String categoryId, @PathVariable String articleId, Model model) {
        // 게시글 삭제 로직
        return "redirect:/categories/" + categoryId + "/articles";
    }

    @PostMapping("/{articleId}/comments")
    public String addComment(@PathVariable String categoryId, @PathVariable String articleId, @Valid @ModelAttribute("command") CommentCommand comment, BindingResult bindingResult, Model model) {
        // 댓글 추가 로직
        return "redirect:/categories/" + categoryId + "/articles/" + articleId;
    }

    @PutMapping("/{articleId}/comments/{commentId}")
    public String updateComment(@PathVariable String categoryId, @PathVariable String articleId, @PathVariable String commentId, @Valid @ModelAttribute("command") CommentCommand comment, BindingResult bindingResult, Model model) {
        // 댓글 수정 로직
        return "redirect:/categories/" + categoryId + "/articles/" + articleId;
    }

    @DeleteMapping("/{articleId}/comments/{commentId}")
    public String removeComment(@PathVariable String categoryId, @PathVariable String articleId, @PathVariable String commentId, Model model) {
        // 댓글 삭제 로직
        return "redirect:/categories/" + categoryId + "/articles/" + articleId;
    }

}