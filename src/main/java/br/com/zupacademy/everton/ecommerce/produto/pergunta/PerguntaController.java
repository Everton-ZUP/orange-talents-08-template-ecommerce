package br.com.zupacademy.everton.ecommerce.produto.pergunta;

import br.com.zupacademy.everton.ecommerce.produto.Produto;
import br.com.zupacademy.everton.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.everton.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class PerguntaController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private EnviadorDeEmail enviadorDeEmail;

    @PostMapping("/produtos/{id}/pergunta")
    public PerguntaDto adicionarPergunta(@PathVariable("id") Long idProduto, @AuthenticationPrincipal Usuario usuario, @Valid @RequestBody PerguntaForm formulario){
        Produto produto = produtoRepository.findById(idProduto).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Produto selecionado não existe"));
        Pergunta pergunta = formulario.paraPergunta(produto,usuario);
        perguntaRepository.save(pergunta);
        enviadorDeEmail.enviar(pergunta);
        return new PerguntaDto(pergunta);
    }

}
