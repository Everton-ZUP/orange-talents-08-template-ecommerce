package br.com.zupacademy.everton.ecommerce.categoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @ManyToOne
    private Categoria categoriaMae;

    @Deprecated
    public Categoria(){}

    public Categoria(String nome) {
        this.nome = nome;
    }

    @Deprecated
    public Categoria(Long id, String nome, Categoria categoriaMae) {
        this.id = id;
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }

    public Long getIdCategoriaMae() {
        if (categoriaMae != null){
            return categoriaMae.getId();
        }
        return null;
    }
}
