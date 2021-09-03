package br.com.zupacademy.everton.ecommerce.compra.finalizacao;

import br.com.zupacademy.everton.ecommerce.Validation.ErroRegraNegocio;
import br.com.zupacademy.everton.ecommerce.compra.Compra;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @ManyToOne
    private Compra compra;

    @NotBlank
    private String transacaoOrigem;

    @Enumerated(EnumType.STRING) @NotNull
    private StatusTransacao statusTransacao;

    private LocalDateTime instante = LocalDateTime.now();

    @Deprecated
    public Transacao() {
    }

    public Transacao(Compra compra, String transacaoOrigem, StatusTransacao statusTransacao) {
        this.compra = compra;
        this.transacaoOrigem = transacaoOrigem;
        this.statusTransacao = statusTransacao;
    }

    public Long getId() {
        return id;
    }

    public Compra getCompra() {
        return compra;
    }

    public String getTransacaoOrigem() {
        return transacaoOrigem;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public Transacao atualizaStatus(Compra compra, StatusTransacao novoStatus) {
        if (this.compra.getId() != compra.getId()){
            throw new ErroRegraNegocio("Esta transação já está vinculada com outra compra","idPagamento",this.transacaoOrigem);
        }
        if (this.statusTransacao == StatusTransacao.SUCESSO){
            throw new ErroRegraNegocio("Esta transação já está finalizada com sucesso","idPagamento",this.transacaoOrigem);
        }
        this.instante = LocalDateTime.now();
        this.statusTransacao = novoStatus;
        return this;
    }
}
