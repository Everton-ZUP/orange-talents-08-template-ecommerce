package br.com.zupacademy.everton.ecommerce.produto.pergunta;

import br.com.zupacademy.everton.ecommerce.compra.Compra;
import org.springframework.stereotype.Component;

@Component
public class EnviadorDeEmail {

    public void enviar(Pergunta pergunta) {
        String remetente = "mercadolivre@mercadolivre.com";
        String destinatario = pergunta.getProduto().getUsuario().getLogin();
        String texto = pergunta.getTitulo();
        System.out.println(remetente);
        System.out.println(destinatario);
        System.out.println(texto);
    }

    public void novaCompra(Compra compra) {
        String remetente = "mercadolivre@mercadolivre.com";
        String destinatario = compra.getProduto().getUsuario().getLogin();
        String texto = "Uma nova compra foi realizada para o seu produto: "+compra.getProduto().getNome()+
                " na quantidade de: "+compra.getQuantidade().toString()+" pelo comprador: "+compra.getComprador().getLogin();
        System.out.println(remetente);
        System.out.println(destinatario);
        System.out.println(texto);
    }
}
