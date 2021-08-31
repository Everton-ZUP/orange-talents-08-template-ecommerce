package br.com.zupacademy.everton.ecommerce.produto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String link;

    @ManyToOne @NotNull
    private Produto produto;

    @Deprecated
    public ImagemProduto() {
    }

    public ImagemProduto(String link, Produto produto) {
        this.link = link;
        this.produto = produto;
    }
}
