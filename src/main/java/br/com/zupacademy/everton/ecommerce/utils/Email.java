package br.com.zupacademy.everton.ecommerce.utils;

import org.springframework.stereotype.Component;

@Component
public interface Email {
    void enviar(String remetente,String destinatario, String titulo,String corpo);
}
