package br.com.zupacademy.everton.ecommerce.compra.finalizacao;

import br.com.zupacademy.everton.ecommerce.Validation.ExisteEntidade;
import br.com.zupacademy.everton.ecommerce.compra.Compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PagSeguroForm {

    @NotNull @ExisteEntidade(domainClass = Compra.class, fieldName = "id")
    private Long idCompra;
    @NotBlank
    private String idPagamento;
    @NotBlank @Pattern(regexp = "(SUCESSO|FALHA)")
    private String status;

    public PagSeguroForm(Long idCompra, String idPagamento, String status) {
        this.idCompra = idCompra;
        this.idPagamento = idPagamento;
        this.status = status;
    }

    public String chamaProcessamento(ProcessaTransacao processaTransacao) {
        if (this.status.equals("SUCESSO")){
            processaTransacao.processaSucesso(this.idCompra,this.idPagamento);
            return "Seu pagamento foi processado com sucesso";
        }
        processaTransacao.processaFalha(this.idCompra,this.idPagamento);
        return "Ocorreu uma falha com o pagamento";
    }
}
