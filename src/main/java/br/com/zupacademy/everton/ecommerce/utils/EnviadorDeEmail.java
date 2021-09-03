package br.com.zupacademy.everton.ecommerce.utils;

import br.com.zupacademy.everton.ecommerce.compra.Compra;
import br.com.zupacademy.everton.ecommerce.produto.pergunta.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnviadorDeEmail {

    @Autowired
    private Email email;

    public void enviar(Pergunta pergunta) {
        String remetente = "mercadolivre@mercadolivre.com";
        String destinatario = pergunta.getProduto().getUsuario().getLogin();
        String texto = pergunta.getTitulo();
        email.enviar(remetente,destinatario,"Titulo",texto);
    }

    public void novaCompra(Compra compra) {
        String remetente = "mercadolivre@mercadolivre.com";
        String destinatario = compra.getProduto().getUsuario().getLogin();
        String texto = "Uma nova compra foi realizada para o seu produto: "+compra.getProduto().getNome()+
                " na quantidade de: "+compra.getQuantidade().toString()+" pelo comprador: "+compra.getComprador().getLogin();
        email.enviar(remetente,destinatario,"Titulo",texto);
    }

    public void pagamentoSucesso(Compra compra) {
        String remetente = "mercadolivre@mercadolivre.com";
        String destinatario = compra.getComprador().getLogin();
        String texto = "Pagamento realizado com sucesso da sua compra "+compra.toString();
        email.enviar(remetente,destinatario,"Titulo",texto);
    }

    public void pagamentoFalhou(Compra compra) {
        String remetente = "mercadolivre@mercadolivre.com";
        String destinatario = compra.getComprador().getLogin();
        String texto = "Seu Pagamento falhou tente novamente em: "+compra.getRetornoGatewayPagamento();
        email.enviar(remetente,destinatario,"Titulo",texto);
    }
}
