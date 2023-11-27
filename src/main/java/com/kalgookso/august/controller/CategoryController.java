package com.kalgookso.august.controller;

import com.kalgookso.august.command.CategoryCommand;
import com.kalgookso.august.entity.Category;
import com.kalgookso.august.mapper.CategoryMapper;
import com.kalgookso.august.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAll(@PageableDefault Pageable pageable, Model model) {
        final Page<Category> page = categoryService.findAll(pageable);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageable.getPageSize());
        return "categories/list";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable String id, Model model) {
        final Optional<Category> category = categoryService.findById(id);
        model.addAttribute("category", category.orElseThrow());
        return "categories/view";
    }

    @GetMapping("/new")
    public String getNew(@ModelAttribute("command") CategoryCommand command) {
        return "categories/new";
    }

    @PostMapping
    public String create(@ModelAttribute("command") @Valid CategoryCommand command, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "categories/new";
        }
        final Category category = CategoryMapper.INSTANCE.toEntity(command);
        final Category savedCategory = categoryService.save(category);
        model.addAttribute("category", savedCategory);
        return "redirect:/categories/" + savedCategory.getId();
    }

    @GetMapping("/{id}/edit")
    public String getEdit(@PathVariable String id, @ModelAttribute("command") CategoryCommand command, Model model) {
        final Optional<Category> category = categoryService.findById(id);
        model.addAttribute("category", category.orElseThrow());
        return "categories/edit";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute("command") @Valid CategoryCommand command, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "categories/edit";
        }
        final Optional<Category> foundCategory = categoryService.findById(id);
        if (foundCategory.isEmpty()) {
            throw new IllegalArgumentException("카테고리를 찾을 수 없습니다.");
        }
        final Category category = foundCategory.get();
        CategoryMapper.INSTANCE.updateEntityFromCommand(command, category);
        final Category savedCategory = categoryService.save(category);
        model.addAttribute("category", savedCategory);
        return "redirect:/categories/" + savedCategory.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }

}