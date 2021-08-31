package br.com.zupacademy.everton.ecommerce.produto.pergunta;

import br.com.zupacademy.everton.ecommerce.produto.Produto;
import br.com.zupacademy.everton.ecommerce.usuario.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaForm {

    @NotBlank
    private String titulo;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta paraPergunta(Produto produto, Usuario usuario) {
        return new Pergunta(titulo,usuario,produto);
    }
}
