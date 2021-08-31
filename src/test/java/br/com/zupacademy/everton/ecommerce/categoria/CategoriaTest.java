package br.com.zupacademy.everton.ecommerce.categoria;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CategoriaTest {

    @Test
    public void retornarNulloNoIdCategoriaMaeQuandoCriaNovoObjeto() {
        Categoria categoria = new Categoria("Amador");
        Assert.isTrue(categoria.getIdCategoriaMae() == null, "Deveria retornar nulo no Id da categoria Mãe");
    }

    @Test
    public void retornaIdDaCategoriaMaeQuandoTemCategoriaMaeAssociada(){
        Categoria categoriaMae = new Categoria(1l,"Categoria Teste",null);
        Categoria categoria = new Categoria(1l,"Categoria Teste",categoriaMae);
        Assert.isTrue(categoria.getIdCategoriaMae() == 1l,"Deveria retornar o ID da categoria Mãe");
    }
}