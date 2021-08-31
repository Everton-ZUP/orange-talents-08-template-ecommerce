package br.com.zupacademy.everton.ecommerce.produto.cadastrarproduto;

import br.com.zupacademy.everton.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.everton.ecommerce.produto.Produto;
import br.com.zupacademy.everton.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.everton.ecommerce.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    private final ProdutoRepository repositorio;
    private final CategoriaRepository categoriaRepositorio;

    public ProdutoController(ProdutoRepository repo, CategoriaRepository repositorioCategoria) {
        this.repositorio = repo;
        this.categoriaRepositorio = repositorioCategoria;
    }

    @PostMapping @Transactional @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDto adicionar(@RequestBody @Valid ProdutoForm formulario, @AuthenticationPrincipal Usuario usuario){
        Produto produto = formulario.paraProduto(categoriaRepositorio,usuario);
        repositorio.save(produto);
        return new ProdutoDto(produto);
    }

}
