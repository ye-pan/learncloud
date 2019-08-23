package com.yp.learncloud.authentication.controller;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
public class UserController {

    @GetMapping("/user")
    public Map<String, Object> user(OAuth2Authentication user) {
        Object principal = user.getUserAuthentication().getPrincipal();
        Set<String> authorities = AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities());
        return Map.of("user", principal, "authorities", authorities);
    }
}
