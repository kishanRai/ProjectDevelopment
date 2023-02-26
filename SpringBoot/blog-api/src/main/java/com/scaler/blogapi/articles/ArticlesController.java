package com.scaler.blogapi.articles;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/articles")
public class ArticlesController {

    private ArticlesService articlesService;

    public ArticlesController(ArticlesService articlesService) {
        this.articlesService = articlesService;
    }

    @GetMapping("")
    public String getArticles() {
        return "Articles";
    }

    //TODO : Currently need to work on expose endpoint without adding in ApplicationSecurityConfig.configure() to permit
    @GetMapping("/private")
    public String getPrivateArticles(@AuthenticationPrincipal Long userId) {
        log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "Private Articles fetched for :" + userId;
    }
}
