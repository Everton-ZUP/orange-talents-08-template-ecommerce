package br.com.zupacademy.everton.ecommerce.categoria.cadastrocategoria;

import br.com.zupacademy.everton.ecommerce.Validation.ExisteEntidade;
import br.com.zupacademy.everton.ecommerce.Validation.ValorUnico;
import br.com.zupacademy.everton.ecommerce.categoria.Categoria;
import br.com.zupacademy.everton.ecommerce.categoria.CategoriaRepository;

import javax.validation.constraints.NotBlank;

public class CategoriaForm {

    @NotBlank @ValorUnico(fieldName = "nome", domainClass = Categoria.class)
    private String nome;

    @ExisteEntidade(fieldName = "id", domainClass = Categoria.class)
    private Long idCategoriaMae;

    public Categoria toCategoria(CategoriaRepository repositorio) {
        Categoria categoria = new Categoria(this.nome);
        if (idCategoriaMae != null){
            categoria.setCategoriaMae(repositorio.findById(idCategoriaMae).get());
        }
        return categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }
}
