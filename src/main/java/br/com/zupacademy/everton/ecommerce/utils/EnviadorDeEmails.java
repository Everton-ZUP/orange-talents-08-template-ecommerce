package br.com.zupacademy.everton.ecommerce.utils;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EnviadorDeEmails implements Email{
    @Override
    public void enviar(String remetente, String destinatario, String titulo, String corpo) {
        System.out.println(remetente);
        System.out.println(destinatario);
        System.out.println(titulo);
        System.out.println(corpo);
    }
}
