package br.com.zupacademy.everton.ecommerce.produto.cadastrarproduto;

import br.com.zupacademy.everton.ecommerce.produto.CaracteristicaProduto;

import javax.validation.constraints.NotBlank;

public class CaracteristicaProdutoForm {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public CaracteristicaProdutoForm(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaProduto toCaracteristicaProduto() {
        return new CaracteristicaProduto(this.nome,this.descricao);
    }

    @Override
    public String toString() {
        return "CaracteristicaProdutoForm{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
