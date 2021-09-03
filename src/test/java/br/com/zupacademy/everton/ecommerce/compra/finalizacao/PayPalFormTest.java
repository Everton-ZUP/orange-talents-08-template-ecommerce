package br.com.zupacademy.everton.ecommerce.compra.finalizacao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class PayPalFormTest {

    @Test
    public void deveriaExecutarProcessaSucesso(){
        // cenário
        PayPalForm form = new PayPalForm(1l,"qualquer",1);
        ProcessaTransacao processaTransacao = Mockito.mock(ProcessaTransacao.class);

        //ação
        form.chamaProcessamento(processaTransacao);

        //Resultado
        Mockito.verify(processaTransacao).processaSucesso(1l,"qualquer");
        Mockito.verify(processaTransacao, new Times(0)).processaFalha(1l,"qualquer");
    }

    @Test
    public void deveriaExecutarProcessaFalha(){        // cenário
        PayPalForm form = new PayPalForm(1l,"qualquer",0);
        ProcessaTransacao processaTransacao = Mockito.mock(ProcessaTransacao.class);

        //ação
        form.chamaProcessamento(processaTransacao);

        //Resultado
        Mockito.verify(processaTransacao, new Times(0)).processaSucesso(1l,"qualquer");
        Mockito.verify(processaTransacao).processaFalha(1l,"qualquer");
    }

}