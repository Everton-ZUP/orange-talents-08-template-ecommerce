package br.com.zupacademy.everton.ecommerce.compra.finalizacao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PagSeguroFormTest {
    @Test
    public void deveriaExecutarProcessaSucesso(){
        // cenário
        PagSeguroForm form = new PagSeguroForm(1l,"qualquer","SUCESSO");
        ProcessaTransacao processaTransacao = Mockito.mock(ProcessaTransacao.class);

        //ação
        form.chamaProcessamento(processaTransacao);

        //Resultado
        Mockito.verify(processaTransacao).processaSucesso(1l,"qualquer");
        Mockito.verify(processaTransacao, new Times(0)).processaFalha(1l,"qualquer");
    }

    @Test
    public void deveriaExecutarProcessaFalha(){        // cenário
        PagSeguroForm form = new PagSeguroForm(1l,"qualquer","FALHA");
        ProcessaTransacao processaTransacao = Mockito.mock(ProcessaTransacao.class);

        //ação
        form.chamaProcessamento(processaTransacao);

        //Resultado
        Mockito.verify(processaTransacao, new Times(0)).processaSucesso(1l,"qualquer");
        Mockito.verify(processaTransacao).processaFalha(1l,"qualquer");
    }

}