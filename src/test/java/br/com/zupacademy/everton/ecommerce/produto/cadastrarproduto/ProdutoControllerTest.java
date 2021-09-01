package br.com.zupacademy.everton.ecommerce.produto.cadastrarproduto;

import br.com.zupacademy.everton.ecommerce.categoria.Categoria;
import br.com.zupacademy.everton.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.everton.ecommerce.produto.CaracteristicaProduto;
import br.com.zupacademy.everton.ecommerce.produto.Produto;
import br.com.zupacademy.everton.ecommerce.produto.ProdutoController;
import br.com.zupacademy.everton.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.everton.ecommerce.produto.imagem.ImagemProdutoRepository;
import br.com.zupacademy.everton.ecommerce.usuario.Usuario;
import br.com.zupacademy.everton.ecommerce.usuario.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
class ProdutoControllerTest {

    @Autowired
    ProdutoController produtoController;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @BeforeEach
    public void init(){
        produtoRepository.deleteAll();
        categoriaRepository.deleteAll();
        usuarioRepository.deleteAll();
    }

    @Test
    public void AdicionarUmProdutoERetornarUmDto(){
        //Cenário
        Categoria categoria = categoriaRepository.save(new Categoria("Categoria Teste"));
        Usuario user = usuarioRepository.save(new Usuario("everton@zup.com","123456"));

        List<CaracteristicaProdutoForm> caracteristicas =
                Arrays.asList(  new CaracteristicaProdutoForm("01","01"),
                        new CaracteristicaProdutoForm("02","02"),
                        new CaracteristicaProdutoForm("03","03"));

        ProdutoForm form = new ProdutoForm("Celular",new BigDecimal(150),12,
                                        "TEste",categoria.getId(),caracteristicas);

        //Ação
        ProdutoDto dto = produtoController.adicionar(form,user);

        //Validação
        Assertions.assertEquals(dto.getNome(),"Celular");
    }

}