package br.com.zupacademy.everton.ecommerce.compra.finalizacao;

import br.com.zupacademy.everton.ecommerce.Validation.ExisteEntidade;
import br.com.zupacademy.everton.ecommerce.compra.Compra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PayPalForm {

    @NotNull @ExisteEntidade(domainClass = Compra.class, fieldName = "id")
    private Long idCompra;
    @NotBlank
    private String idPagamento;
    @NotNull @Min(0) @Max(1)
    private Integer status;

    public PayPalForm(Long idCompra, String idPagamento, Integer status) {
        this.idCompra = idCompra;
        this.idPagamento = idPagamento;
        this.status = status;
    }

    public String chamaProcessamento(ProcessaTransacao processaTransacao) {
        if (this.status == 1){
            processaTransacao.processaSucesso(this.idCompra,this.idPagamento);
            return "Seu pagamento foi processado com sucesso";
        }
        processaTransacao.processaFalha(this.idCompra,this.idPagamento);
        return "Ocorreu uma falha com o pagamento";
    }
}
