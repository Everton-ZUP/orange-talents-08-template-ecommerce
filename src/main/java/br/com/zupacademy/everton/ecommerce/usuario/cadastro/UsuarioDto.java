package br.com.zupacademy.everton.ecommerce.usuario.cadastro;

import br.com.zupacademy.everton.ecommerce.usuario.Usuario;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UsuarioDto {

    private Long id;
    private String login;
    private LocalDateTime instanteCriação;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.instanteCriação = usuario.getInstanteDeCriacao();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public LocalDateTime getInstanteCriação() {
        return instanteCriação;
    }
}
