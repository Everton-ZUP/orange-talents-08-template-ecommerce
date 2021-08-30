package br.com.zupacademy.everton.ecommerce.usuario.cadastro;


import br.com.zupacademy.everton.ecommerce.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioForm {

    @NotBlank @Email
    private String login;

    @NotBlank @Length(min = 6)
    private String senha;

    public UsuarioForm(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(this.login,this.senha);
    }
}
