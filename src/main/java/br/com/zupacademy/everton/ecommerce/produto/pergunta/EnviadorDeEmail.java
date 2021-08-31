package br.com.zupacademy.everton.ecommerce.produto.pergunta;

import org.springframework.stereotype.Component;

@Component
public class EnviadorDeEmail {

    public void enviar(Pergunta pergunta) {
        String remetente = pergunta.getUsuario().getLogin();
        String destinatario = pergunta.getProduto().getUsuario().getLogin();
        String texto = pergunta.getTitulo();
        System.out.println(remetente);
        System.out.println(destinatario);
        System.out.println(texto);
    }
}
