package com.kalgookso.august.controller;

import com.kalgookso.august.command.ArticleCommand;
import com.kalgookso.august.command.CommentCommand;
import com.kalgookso.august.criteria.ArticleCriteria;
import com.kalgookso.august.entity.article.Article;
import com.kalgookso.august.entity.article.Comment;
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

    @GetMapping
    public String getArticlesByCategoryId(@PathVariable String categoryId, ArticleCriteria criteria, @PageableDefault Pageable pageable, Model model) {
        model.addAttribute("articles", articleService.findByCategoryId(categoryId, criteria, pageable));
        return "categories/articles/list";
    }

    @GetMapping("/new")
    public String getNew(@PathVariable String categoryId, Model model) {
        model.addAttribute("command", new ArticleCommand());
        return "categories/articles/new";
    }

    @PostMapping
    public String createArticle(@PathVariable String categoryId, @Valid @ModelAttribute("command") Article article, BindingResult bindingResult, Model model) {
        // 게시글 생성 로직
        return "redirect:/categories/" + categoryId + "/articles/" + article.getId();
    }

    @GetMapping("/{articleId}")
    public String getArticle(@PathVariable String categoryId, @PathVariable String articleId, Model model) {
        model.addAttribute("article", articleService.view(articleId));
        return "categories/articles/view";
    }

    @GetMapping("/{articleId}/edit")
    public String getEdit(@PathVariable String categoryId, @PathVariable String articleId, Model model) {
        // 게시글 수정 화면 로직
        return "categories/articles/edit";
    }

    @PutMapping("/{articleId}")
    public String updateArticle(@PathVariable String categoryId, @PathVariable String articleId, @Valid @ModelAttribute("command") ArticleCommand command, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "categories/articles/edit";
        }
        return "redirect:/categories/" + categoryId + "/articles/" + articleId;
    }

    @DeleteMapping("/{articleId}")
    public String deleteArticle(@PathVariable String categoryId, @PathVariable String articleId) {
        articleService.deleteById(articleId);
        return "redirect:/categories/" + categoryId + "/articles";
    }

    @PostMapping("/{articleId}/comments")
    public String addComment(@PathVariable String categoryId, @PathVariable String articleId, @Valid @ModelAttribute("command") CommentCommand comment, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "categories/articles/view";
        }
        articleService.addComment(articleId, new Comment(comment.getContent()));
        return "redirect:/categories/" + categoryId + "/articles/" + articleId;
    }

    @PutMapping("/{articleId}/comments/{commentId}")
    public String updateComment(@PathVariable String categoryId, @PathVariable String articleId, @PathVariable String commentId, @Valid @ModelAttribute("command") CommentCommand comment, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "categories/articles/view";
        }
        // 댓글 수정 로직
        return "redirect:/categories/" + categoryId + "/articles/" + articleId;
    }

    @DeleteMapping("/{articleId}/comments/{commentId}")
    public String removeComment(@PathVariable String categoryId, @PathVariable String articleId, @PathVariable String commentId) {
        articleService.removeAttachment(articleId, commentId);
        return "redirect:/categories/" + categoryId + "/articles/" + articleId;
    }

}