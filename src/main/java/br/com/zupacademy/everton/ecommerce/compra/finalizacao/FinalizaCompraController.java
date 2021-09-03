package br.com.zupacademy.everton.ecommerce.compra.finalizacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class FinalizaCompraController {

    @Autowired
    private ProcessaTransacao processaTransacao;

    @PostMapping("/pag-seguro")
    public String pagamentoPagSeguro(@RequestBody @Valid PagSeguroForm formulario){
        return formulario.chamaProcessamento(processaTransacao);
    }

    @PostMapping("/paypal")
    public String pagamentoPaypal(@RequestBody @Valid PayPalForm formulario){
        return formulario.chamaProcessamento(processaTransacao);
    }


}
