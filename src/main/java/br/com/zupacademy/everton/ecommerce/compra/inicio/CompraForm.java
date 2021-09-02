package br.com.zupacademy.everton.ecommerce.compra.inicio;

import br.com.zupacademy.everton.ecommerce.Validation.ErroRegraNegocio;
import br.com.zupacademy.everton.ecommerce.Validation.ExisteEntidade;
import br.com.zupacademy.everton.ecommerce.compra.Compra;
import br.com.zupacademy.everton.ecommerce.compra.GatewayPagamento;
import br.com.zupacademy.everton.ecommerce.produto.Produto;
import br.com.zupacademy.everton.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.everton.ecommerce.usuario.Usuario;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraForm {

    @NotNull
    private GatewayPagamento gatewayPagamento;

    @NotNull @ExisteEntidade(domainClass = Produto.class, fieldName = "id")
    private Long idProduto;

    @NotNull @Positive
    private Integer quantidade;

    public CompraForm(GatewayPagamento gatewayPagamento, Long idProduto, Integer quantidade) {
        this.gatewayPagamento = gatewayPagamento;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public Compra paraCompra(Usuario usuario, ProdutoRepository produtoRepository){
        Produto produto = produtoRepository.findById(this.idProduto).get();
        if (produto.abateEstoque(this.quantidade)) {
            return new Compra(this.gatewayPagamento, produto, this.quantidade, usuario);
        }
        throw new ErroRegraNegocio("Quantidade de estoque insuficiente: Quantidade atual: "+produto.getQuantidade(),
                                "quantidade",this.quantidade);
    }
}
