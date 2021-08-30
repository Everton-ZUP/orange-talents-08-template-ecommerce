package br.com.zupacademy.everton.ecommerce.categoria.cadastrocategoria;

import br.com.zupacademy.everton.ecommerce.categoria.Categoria;

public class CategoriaDto {

    private Long id;
    private String nome;
    private Long idCategoriaMae;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.idCategoriaMae = categoria.getIdCategoriaMae();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }
}
