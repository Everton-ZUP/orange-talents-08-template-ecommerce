package br.com.zupacademy.everton.ecommerce.usuario.cadastrousuario;


import br.com.zupacademy.everton.ecommerce.Validation.ValorUnico;
import br.com.zupacademy.everton.ecommerce.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioForm {

    @NotBlank @Email @ValorUnico(fieldName = "login", domainClass = Usuario.class)
    private String login;

    @NotBlank @Length(min = 6)
    private String senha;

    public UsuarioForm(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario paraUsuario() {
        return new Usuario(this.login,this.senha);
    }
}
