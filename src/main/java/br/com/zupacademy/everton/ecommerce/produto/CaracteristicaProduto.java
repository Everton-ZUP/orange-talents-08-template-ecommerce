package br.com.zupacademy.everton.ecommerce.produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class CaracteristicaProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @Deprecated
    public CaracteristicaProduto() {
    }

    public CaracteristicaProduto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
