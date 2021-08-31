package br.com.zupacademy.everton.ecommerce.categoria.cadastrocategoria;

import br.com.zupacademy.everton.ecommerce.categoria.Categoria;
import br.com.zupacademy.everton.ecommerce.categoria.CategoriaRepository;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CategoriaControllerTest {

    @InjectMocks
    CategoriaController categoriaController;

    @Mock
    CategoriaRepository categoriaRepository;

    @Test
    public void DeveriaRetornarUmCategoriaDtoQuandoAdicionaUmCategoriaFormEidDaCategoriaMaeNulo(){
        CategoriaForm categoriaForm = new CategoriaForm();
        categoriaForm.setNome("categoria1");

        Categoria categoria = categoriaForm.toCategoria(categoriaRepository);

        Assert.isTrue(categoria.getClass()==Categoria.class,"Deveria ter retornado uma categoria");
        Assert.isTrue(categoria.getIdCategoriaMae()==null,"Deveria ter retornado nulo");
    }

    @Test
    public void DeveriaRetornarOidDaCategoriaMae(){
        Categoria categoriaMae = new Categoria(1l, "Categoria Teste", null);

        CategoriaForm categoriaForm = new CategoriaForm();
        categoriaForm.setNome("categoria1");
        categoriaForm.setIdCategoriaMae(1l);

        Mockito.when(categoriaRepository.findById(1l)).thenReturn(Optional.of(categoriaMae));

        Categoria categoria = categoriaForm.toCategoria(categoriaRepository);

        Assert.isTrue(categoria.getIdCategoriaMae()==1l,"Deveria ter retornado o Id Da Categoria Mae");

    }
}