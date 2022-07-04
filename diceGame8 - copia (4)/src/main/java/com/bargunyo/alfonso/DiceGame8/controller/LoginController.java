package com.bargunyo.alfonso.DiceGame8.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bargunyo.alfonso.DiceGame8.entity.LoginUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RequestMapping("/")
@RestController
public class LoginController {

	
	 //interceptará las peticiones y recibirá como parametro el usuario y contraseña
    @PostMapping("/login")
    public LoginUser login(@RequestParam("user") String username,
                             @RequestParam ("password") String pwd) {
        String token = getJWTToken(username);
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(username);
        loginUser.setToken(token);
        return loginUser;
    }

    //para construir el token, delegando en la clase de utilidad Jwts que incluye información sobre su
    // expiración y un objeto de GrantedAuthority de Spring que usaremos para autorizar las peticiones
    // a los recursos protegidos
    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAut = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("JWToken")
                .setSubject(username)
                .claim("authorities",
                        grantedAut.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 6000000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return  token;
    }
}
