package br.com.zupacademy.everton.ecommerce.produto.cadastrarproduto;

import br.com.zupacademy.everton.ecommerce.Validation.ExisteEntidade;
import br.com.zupacademy.everton.ecommerce.categoria.Categoria;
import br.com.zupacademy.everton.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.everton.ecommerce.produto.CaracteristicaProduto;
import br.com.zupacademy.everton.ecommerce.produto.Produto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ProdutoForm {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull @PositiveOrZero
    private Integer quantidade;

    @NotBlank @Length(max = 1000)
    private String descricao;


    @NotNull @ExisteEntidade(fieldName = "id", domainClass = Categoria.class)
    private Long idCategoria;

    @Size(min = 3) @Valid
    private List<CaracteristicaProdutoForm> caracteristicas;

    public ProdutoForm(String nome, BigDecimal valor, Integer quantidade, String descricao, Long idCategoria, List<CaracteristicaProdutoForm> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
        this.caracteristicas = caracteristicas;
    }


    public Produto paraProduto(CategoriaRepository categoriaRepositorio) {
        Categoria categoria = categoriaRepositorio.findById(idCategoria).get();
        Produto produto = new Produto(nome,valor,quantidade,descricao,categoria);
        caracteristicas.forEach(car -> System.out.println(car));
        caracteristicas.forEach(caracteristica -> produto.adicionaCaracteristica(caracteristica.toCaracteristicaProduto()));
        return produto;
    }
}
