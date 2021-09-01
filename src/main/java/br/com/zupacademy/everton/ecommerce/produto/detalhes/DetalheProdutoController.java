package br.com.zupacademy.everton.ecommerce.produto.detalhes;

import br.com.zupacademy.everton.ecommerce.produto.Produto;
import br.com.zupacademy.everton.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.everton.ecommerce.produto.imagem.ImagemProdutoRepository;
import br.com.zupacademy.everton.ecommerce.produto.opiniao.OpiniaoRepository;
import br.com.zupacademy.everton.ecommerce.produto.pergunta.PerguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DetalheProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    ImagemProdutoRepository imagemProdutoRepository;
    @Autowired
    OpiniaoRepository opiniaoRepository;
    @Autowired
    PerguntaRepository perguntaRepository;

    @GetMapping("/produtos/{id}/detalhes")
    public RetornoDetalhesProduto retornar(@PathVariable("id") Long id){
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        return new RetornoDetalhesProduto(produto,imagemProdutoRepository,opiniaoRepository,perguntaRepository);
    }
}
