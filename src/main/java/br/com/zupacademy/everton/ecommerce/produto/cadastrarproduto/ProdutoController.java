package br.com.zupacademy.everton.ecommerce.produto.cadastrarproduto;

import br.com.zupacademy.everton.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.everton.ecommerce.produto.*;
import br.com.zupacademy.everton.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repositorio;
    @Autowired
    private CategoriaRepository categoriaRepositorio;
    @Autowired
    private ImagemProdutoRepository imagemProdutoRepository;
    @Autowired
    private Uploader uploader;

    @PostMapping @Transactional @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDto adicionar(@RequestBody @Valid ProdutoForm formulario, @AuthenticationPrincipal Usuario usuario){
        Produto produto = formulario.paraProduto(categoriaRepositorio,usuario);
        repositorio.save(produto);
        return new ProdutoDto(produto);
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public String adicionarImagens(@PathVariable("id") Long id, @AuthenticationPrincipal Usuario usuario, @Valid ImagemForm formulario ){

        Optional<Produto> optionalProduto = repositorio.findById(id);

        if (optionalProduto.isPresent()){
            Produto produto = optionalProduto.get();
            if (produto.pertenceAoUsuario(usuario.getId())){
                List<String> links = uploader.envia(formulario.getImagens());
                links.forEach(link -> imagemProdutoRepository.save(new ImagemProduto(link,produto)));
                return "Imagens cadastradas com sucesso para o Produto: "+produto.getNome();
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Somente o dono do produto pode adicionar imagens a ele");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Este produto não existe");
    }

}
