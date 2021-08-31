package br.com.zupacademy.everton.ecommerce.produto;

import br.com.zupacademy.everton.ecommerce.categoria.Categoria;
import br.com.zupacademy.everton.ecommerce.usuario.Usuario;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank private String nome;
    @NotNull @Positive private BigDecimal valor;
    @NotNull @PositiveOrZero private Integer quantidade;
    @NotBlank @Length(max = 1000) private String descricao;
    private LocalDateTime instanteDeCadastro = LocalDateTime.now();
    @ManyToOne @NotNull private Categoria categoria;
    @OneToMany(cascade = CascadeType.ALL) @Size(min = 3) private List<CaracteristicaProduto> caracteristicas = new ArrayList<>();
    @ManyToOne @NotNull private Usuario usuario;

    @Deprecated
    public Produto() {
    }

    /**
     *
     * @param nome
     * @param valor
     * @param quantidade
     * @param descricao
     * @param categoria
     */
    public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao, Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public void adicionaCaracteristica(CaracteristicaProduto caracteristica){
        caracteristicas.add(caracteristica);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getInstanteDeCadastro() {
        return instanteDeCadastro;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }
}
