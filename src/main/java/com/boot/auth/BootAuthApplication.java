package com.boot.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableResourceServer
@EnableAuthorizationServer
public class BootAuthApplication {

    /**
     * 사용자 정보 조회 시 사용
     *      OAuth2 로 보호되는 서비스에 접근하려고 할 때 사용
     *      보호 서비스로 호출되어 OAuth2 액세스 토큰의 유효성을 검증하고 보호 서비스에 접근하는 사용자 역할 조회
     */
    @RequestMapping(value = "/user")     // /auth/user 로 매핑
    public Map<String, Object> user(OAuth2Authentication user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        return userInfo;
    }

    public static void main(String[] args) {
        SpringApplication.run(BootAuthApplication.class, args);
    }

}
