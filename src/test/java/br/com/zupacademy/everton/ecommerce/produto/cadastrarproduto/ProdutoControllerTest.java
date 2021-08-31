package br.com.zupacademy.everton.ecommerce.produto.cadastrarproduto;

import br.com.zupacademy.everton.ecommerce.categoria.Categoria;
import br.com.zupacademy.everton.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.everton.ecommerce.produto.CaracteristicaProduto;
import br.com.zupacademy.everton.ecommerce.produto.Produto;
import br.com.zupacademy.everton.ecommerce.produto.ProdutoController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ProdutoControllerTest {

    @InjectMocks
    ProdutoController produtoController;

    @Mock
    CategoriaRepository categoriaRepository;

    @Test
    public void deveriaTerUmaListaComNoMinimo3CaracteristicasDoTipoCaracteristicaProduto(){
        Categoria categoria = new Categoria(1l, "Categoria Teste", null);
        List<CaracteristicaProdutoForm> caracteristicas = new ArrayList<>();
        caracteristicas.add(new CaracteristicaProdutoForm("01","01"));
        caracteristicas.add(new CaracteristicaProdutoForm("02","02"));
        caracteristicas.add(new CaracteristicaProdutoForm("03","03"));
        ProdutoForm produtoForm = new ProdutoForm("Celular",new BigDecimal(150),12,"Teste01",1l,caracteristicas);

        Mockito.when(categoriaRepository.findById(1l)).thenReturn(Optional.of(categoria));

        Produto produto = produtoForm.paraProduto(categoriaRepository,null);

        Assert.isTrue(produto.getCaracteristicas().size() == 3,"Deveria ter 3 características");
        Assert.isTrue(produto.getCaracteristicas().get(0).getClass() == CaracteristicaProduto.class, "As características deveriam ter sido convertidas");
    }

}