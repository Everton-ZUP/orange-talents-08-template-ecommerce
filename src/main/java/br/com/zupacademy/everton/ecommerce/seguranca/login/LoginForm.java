package br.com.zupacademy.everton.ecommerce.seguranca.login;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

public class LoginForm {

    @NotBlank
    private String email;
    @NotBlank
    private String senha;

    public LoginForm(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
