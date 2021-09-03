package br.com.zupacademy.everton.ecommerce.produto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.security.access.method.P;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @ParameterizedTest
    @CsvSource({"1,1,true","1,2,false","4,2,true"})
    void PodeAbaterEstoque(int estoque, int qtd, boolean esperado) {
        // Cenário
        Produto produto = new Produto("Everton",new BigDecimal(185),estoque,"teste",null,null);

        //Ação
        boolean resultado = produto.abateEstoque(qtd);

        //Resultado
        Assertions.assertTrue(resultado == esperado);
    }
}