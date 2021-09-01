package br.com.zupacademy.everton.ecommerce.produto.cadastrarproduto;

import br.com.zupacademy.everton.ecommerce.categoria.Categoria;
import br.com.zupacademy.everton.ecommerce.produto.CaracteristicaProduto;
import br.com.zupacademy.everton.ecommerce.produto.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProdutoDto {

    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private String descricao;
    private LocalDateTime instanteDeCadastro;
    private Long idCategoria;
    private List<CaracteristicaProduto> caracteristicas;

    public ProdutoDto(Produto prod) {
        this.id = prod.getId();
        this.nome = prod.getNome();
        this.valor = prod.getValor();
        this.quantidade = prod.getQuantidade();
        this.descricao = prod.getDescricao();
        this.instanteDeCadastro = prod.getInstanteDeCadastro();
        this.idCategoria = prod.getCategoria().getId();
        this.caracteristicas = prod.getCaracteristicas();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInstanteDeCadastro() {
        return instanteDeCadastro;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public List<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }
}
