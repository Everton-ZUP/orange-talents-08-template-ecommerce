package br.com.zupacademy.everton.ecommerce.compra.inicio;

import br.com.zupacademy.everton.ecommerce.compra.Compra;
import br.com.zupacademy.everton.ecommerce.compra.CompraRepository;
import br.com.zupacademy.everton.ecommerce.produto.Produto;
import br.com.zupacademy.everton.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.everton.ecommerce.produto.pergunta.EnviadorDeEmail;
import br.com.zupacademy.everton.ecommerce.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EnviadorDeEmail enviadorDeEmail;


    @PostMapping
    @ResponseStatus(HttpStatus.FOUND)
    public String comprar(@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid CompraForm formulario){
        Compra compra = formulario.paraCompra(usuario,produtoRepository);
        compraRepository.save(compra);
        enviadorDeEmail.novaCompra(compra);
        return compra.getRetornoGatewayPagamento();
    }
}
