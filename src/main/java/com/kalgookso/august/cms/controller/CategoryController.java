package com.kalgookso.august.cms.controller;

import com.kalgookso.august.cms.command.CategoryCommand;
import com.kalgookso.august.cms.entity.Category;
import com.kalgookso.august.cms.mapper.CategoryMapper;
import com.kalgookso.august.cms.service.CategoryService;
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
        Page<Category> page = categoryService.findAll(pageable);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageable.getPageSize());
        return "categories/list";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable String id, Model model) {
        Optional<Category> category = categoryService.findById(id);
        model.addAttribute("category", category.orElseThrow());
        return "categories/view";
    }

    @GetMapping("/new")
    public String getNew(@ModelAttribute("command") CategoryCommand command) {
        return "categories/new";
    }

    @PostMapping
    public String create(@ModelAttribute("command") @Valid CategoryCommand command, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "categories/new";
        }
        Category category = CategoryMapper.INSTANCE.toEntity(command);
        Category savedCategory = categoryService.create(category);
        return "redirect:/categories/" + savedCategory.getId();
    }

    @GetMapping("/{id}/edit")
    public String getEdit(@PathVariable String id, @ModelAttribute("command") CategoryCommand command, Model model) {
        Optional<Category> category = categoryService.findById(id);
        model.addAttribute("category", category.orElseThrow());
        return "categories/edit";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, @ModelAttribute("command") @Valid CategoryCommand command, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "categories/edit";
        }
        Category savedCategory = categoryService.update(id, command);
        model.addAttribute("category", savedCategory);
        return "redirect:/categories/" + savedCategory.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }

}