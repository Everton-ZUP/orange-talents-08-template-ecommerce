package br.com.zupacademy.everton.ecommerce.produto.detalhes;

import br.com.zupacademy.everton.ecommerce.produto.CaracteristicaProduto;
import br.com.zupacademy.everton.ecommerce.produto.Produto;
import br.com.zupacademy.everton.ecommerce.produto.imagem.ImagemProdutoRepository;
import br.com.zupacademy.everton.ecommerce.produto.opiniao.Opiniao;
import br.com.zupacademy.everton.ecommerce.produto.opiniao.OpiniaoDto;
import br.com.zupacademy.everton.ecommerce.produto.opiniao.OpiniaoRepository;
import br.com.zupacademy.everton.ecommerce.produto.opiniao.ProcessadorOpinioes;
import br.com.zupacademy.everton.ecommerce.produto.pergunta.Pergunta;
import br.com.zupacademy.everton.ecommerce.produto.pergunta.PerguntaDto;
import br.com.zupacademy.everton.ecommerce.produto.pergunta.PerguntaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class RetornoDetalhesProduto {

    private String nome;
    private BigDecimal preco;
    private String descricao;

    private List<String> imagens;
    private List<CaracteristicaProduto> caracteristicas;

    private List<OpiniaoDto> opinioes;
    private List<PerguntaDto> perguntas;

    private Double mediaDeNotas;
    private Integer totalDeNotas;


    public RetornoDetalhesProduto(Produto produto, ImagemProdutoRepository imagemProdutoRepository, OpiniaoRepository opiniaoRepository, PerguntaRepository perguntaRepository) {
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        this.imagens = produto.getLinkImagens(imagemProdutoRepository);
        this.caracteristicas = produto.getCaracteristicas();

        List<Opiniao> listaOpinioes = produto.getOpnioes(opiniaoRepository);
        ProcessadorOpinioes processadorOpinioes = new ProcessadorOpinioes(listaOpinioes);
        this.opinioes = processadorOpinioes.getOpinioesDto();
        this.mediaDeNotas = processadorOpinioes.getMediaDeNotas();
        this.totalDeNotas = processadorOpinioes.getTotalDeNotas();

        List<Pergunta> listaPerguntas = produto.getPerguntas(perguntaRepository);
        this.perguntas = listaPerguntas.stream().map(PerguntaDto::new).collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public List<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public List<OpiniaoDto> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaDto> getPerguntas() {
        return perguntas;
    }

    public Double getMediaDeNotas() {
        return mediaDeNotas;
    }

    public Integer getTotalDeNotas() {
        return totalDeNotas;
    }
}
