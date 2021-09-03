package br.com.zupacademy.everton.ecommerce.compra;

import br.com.zupacademy.everton.ecommerce.produto.Produto;
import br.com.zupacademy.everton.ecommerce.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) @NotNull
    private StatusCompra status = StatusCompra.INICIADA;
    @Enumerated(EnumType.STRING) @NotNull
    private GatewayPagamento gatewayPagamento;
    @ManyToOne
    private Produto produto;
    @NotNull @Positive
    private Integer quantidade;
    @ManyToOne
    private Usuario comprador;
    @NotNull
    private BigDecimal valorProduto;

    @Deprecated
    public Compra() {
    }

    public Compra(GatewayPagamento gatewayPagamento, Produto produto, Integer quantidade, Usuario comprador) {
        this.gatewayPagamento = gatewayPagamento;
        this.produto = produto;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.valorProduto = produto.getValor();
    }

    public Long getId() {
        return id;
    }

    public StatusCompra getStatus() {
        return status;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public BigDecimal getValorProduto() {
        return valorProduto;
    }

    public String getRetornoGatewayPagamento() {
        return this.gatewayPagamento.urlRetorno(this.id);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", status=" + status +
                ", gatewayPagamento=" + gatewayPagamento +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                ", valorProduto=" + valorProduto +
                '}';
    }
}
