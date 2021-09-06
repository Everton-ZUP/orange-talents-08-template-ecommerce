package br.com.zupacademy.everton.ecommerce;

import br.com.zupacademy.everton.ecommerce.categoria.Categoria;
import br.com.zupacademy.everton.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.everton.ecommerce.compra.Compra;
import br.com.zupacademy.everton.ecommerce.compra.CompraRepository;
import br.com.zupacademy.everton.ecommerce.compra.GatewayPagamento;
import br.com.zupacademy.everton.ecommerce.compra.finalizacao.TransacaoRepository;
import br.com.zupacademy.everton.ecommerce.produto.CaracteristicaProduto;
import br.com.zupacademy.everton.ecommerce.produto.Produto;
import br.com.zupacademy.everton.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.everton.ecommerce.usuario.Usuario;
import br.com.zupacademy.everton.ecommerce.usuario.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext
public class UtilsTests {

    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    CompraRepository compraRepository;
    @Autowired
    TransacaoRepository transacaoRepository;

    @BeforeEach
    public void limpaBase(){
        transacaoRepository.deleteAll();
        compraRepository.deleteAll();
        produtoRepository.deleteAll();
        categoriaRepository.deleteAll();
        usuarioRepository.deleteAll();
    }

    public Categoria criaCategoria(){
        Categoria categoria = new Categoria("Categoria Teste");
        categoriaRepository.save(categoria);
        return  categoria;
    }

    public Produto criaProduto(){
        Produto produto = new Produto("Produto Teste",new BigDecimal(100),100,"Descricao Teste",criaCategoria(), criaUsuario());
        List<CaracteristicaProduto> caracteristicas =
                Arrays.asList(  new CaracteristicaProduto("01","01"),
                                new CaracteristicaProduto("02","02"),
                                new CaracteristicaProduto("03","03"));
        caracteristicas.forEach(caracteristicaProduto -> produto.adicionaCaracteristica(caracteristicaProduto));

        produtoRepository.save(produto);
        return produto;
    }

    public Usuario criaUsuario() {
        Usuario usuario = new Usuario("teste@teste.com","123456");
        usuarioRepository.save(usuario);
        return usuario;
    }

    public Compra criaCompra(GatewayPagamento gateway){
        Compra compra = new Compra(gateway,criaProduto(),1,criaUsuario());
        compraRepository.save(compra);
        return compra;
    }

}
