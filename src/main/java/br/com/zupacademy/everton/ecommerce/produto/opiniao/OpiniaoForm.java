package br.com.zupacademy.everton.ecommerce.produto.opiniao;

import br.com.zupacademy.everton.ecommerce.produto.Produto;
import br.com.zupacademy.everton.ecommerce.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpiniaoForm {

    @NotNull
    @Min(1) @Max(5)
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank @Length(max = 500)
    private String descricao;

    public OpiniaoForm(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao paraModelo(Produto produto, Usuario usuario) {
        return new Opiniao(nota,titulo,descricao,usuario,produto);
    }
}
