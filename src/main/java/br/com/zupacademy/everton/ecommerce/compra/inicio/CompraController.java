package br.com.zupacademy.everton.ecommerce.compra.inicio;

import br.com.zupacademy.everton.ecommerce.compra.Compra;
import br.com.zupacademy.everton.ecommerce.compra.CompraRepository;
import br.com.zupacademy.everton.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.everton.ecommerce.usuario.Usuario;
import br.com.zupacademy.everton.ecommerce.utils.EnviadorDeEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {

    //1
    @Autowired
    private CompraRepository compraRepository;

    //1
    @Autowired
    private ProdutoRepository produtoRepository;

    //1
    @Autowired
    private EnviadorDeEmail enviadorDeEmail;

//2
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.FOUND)
    public String comprar(@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid CompraForm formulario){
 //1
        Compra compra = formulario.paraCompra(usuario,produtoRepository);
        compraRepository.save(compra);
        enviadorDeEmail.novaCompra(compra);
        return compra.getRetornoGatewayPagamento();
    }
}
