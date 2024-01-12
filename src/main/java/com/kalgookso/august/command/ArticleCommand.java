package com.kalgookso.august.command;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class ArticleCommand {

    private final String title;

    private final String content;

    private final List<MultipartFile> multipartFiles;

    public ArticleCommand() {
        this.title = null;
        this.content = null;
        this.multipartFiles = new ArrayList<>();
    }

    public ArticleCommand(String title, String content) {
        this.title = title;
        this.content = content;
        this.multipartFiles = new ArrayList<>();
    }

    public ArticleCommand(String title, String content, List<MultipartFile> multipartFiles) {
        this.title = title;
        this.content = content;
        this.multipartFiles = multipartFiles;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<MultipartFile> getMultipartFiles() {
        return multipartFiles;
    }
}