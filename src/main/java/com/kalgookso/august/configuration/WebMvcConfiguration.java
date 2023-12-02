package com.kalgookso.august.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

public class WebMvcConfiguration implements WebMvcConfigurer {

    /**
     * HiddenHttpMethodFilter는 HTML form에서 지원하지 않는 HTTP 메서드(PUT, DELETE 등)를 사용할 수 있게 해주는 필터입니다.
     * 이 필터는 _method라는 이름의 hidden input 필드를 찾아 그 값을 HTTP 메서드로 사용합니다.
     */
    @Bean
    public FilterRegistrationBean<Filter> hiddenHttpMethodFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HiddenHttpMethodFilter());
        return registrationBean;
    }

}