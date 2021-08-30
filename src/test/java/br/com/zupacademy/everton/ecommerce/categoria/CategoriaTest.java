package br.com.zupacademy.everton.ecommerce.categoria;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {

    @Test
    @DisplayName("Deveria retornar nulo")
    void getIdCategoriaMae() {
        Categoria categoria = new Categoria("Amador");
        Assert.isTrue(categoria.getIdCategoriaMae() == null, "Deveria retornar nulo no Id da categoria Mãe");
    }
}