package com.kalgookso.august.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 홈 컨트롤러 클래스입니다.
 * 이 클래스는 루트 URL에 대한 요청을 처리합니다.
 */
@Controller
public class HomeController {

    /**
     * 루트 URL에 대한 GET 요청을 처리하는 메서드입니다.
     * 이 메서드는 "Hello" 문자열을 반환합니다.
     * @return "Hello" 문자열
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

}