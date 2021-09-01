package br.com.zupacademy.everton.ecommerce.produto;

import br.com.zupacademy.everton.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.everton.ecommerce.produto.cadastrarproduto.ImagemForm;
import br.com.zupacademy.everton.ecommerce.produto.cadastrarproduto.ProdutoDto;
import br.com.zupacademy.everton.ecommerce.produto.cadastrarproduto.ProdutoForm;
import br.com.zupacademy.everton.ecommerce.produto.imagem.ImagemProduto;
import br.com.zupacademy.everton.ecommerce.produto.imagem.ImagemProdutoRepository;
import br.com.zupacademy.everton.ecommerce.produto.imagem.Uploader;
import br.com.zupacademy.everton.ecommerce.produto.opiniao.Opiniao;
import br.com.zupacademy.everton.ecommerce.produto.opiniao.OpiniaoForm;
import br.com.zupacademy.everton.ecommerce.produto.opiniao.OpiniaoDto;
import br.com.zupacademy.everton.ecommerce.produto.opiniao.OpiniaoRepository;
import br.com.zupacademy.everton.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

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
    private OpiniaoRepository opiniaoRepository;
    @Autowired
    private Uploader uploader;

    @PostMapping @Transactional @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDto adicionar(@RequestBody @Valid ProdutoForm formulario, @AuthenticationPrincipal Usuario usuario){
        Produto produto = formulario.paraProduto(categoriaRepositorio,usuario);
        repositorio.save(produto);
        return new ProdutoDto(produto);
    }

    @PostMapping("/{id}/opiniao")
    @Transactional
    public OpiniaoDto adicionarOpiniao(@PathVariable("id") Long idProduto, @AuthenticationPrincipal Usuario usuarioLogado, @RequestBody @Valid OpiniaoForm formulario){
        Produto produto = repositorio.findById(idProduto).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Este produto não existe"));
        Opiniao opiniao = formulario.paraModelo(produto,usuarioLogado);
        opiniaoRepository.save(opiniao);
        return new OpiniaoDto(opiniao);
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public String adicionarImagens(@PathVariable("id") Long id, @AuthenticationPrincipal Usuario usuario, @Valid ImagemForm formulario ){

        Produto produto = repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Este produto não existe"));

        if (produto.pertenceAoUsuario(usuario.getId())){
            List<String> links = uploader.envia(formulario.getImagens());
            links.forEach(link -> imagemProdutoRepository.save(new ImagemProduto(link,produto)));
            return "Imagens cadastradas com sucesso para o Produto: "+produto.getNome();
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Somente o dono do produto pode adicionar imagens a ele");
    }

}
