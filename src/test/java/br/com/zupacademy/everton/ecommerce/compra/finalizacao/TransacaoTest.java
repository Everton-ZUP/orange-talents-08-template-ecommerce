package br.com.zupacademy.everton.ecommerce.compra.finalizacao;

import br.com.zupacademy.everton.ecommerce.Validation.ErroRegraNegocio;
import br.com.zupacademy.everton.ecommerce.compra.Compra;
import br.com.zupacademy.everton.ecommerce.compra.GatewayPagamento;
import br.com.zupacademy.everton.ecommerce.produto.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class TransacaoTest {

    @Test
    public void aoCriarUmObjetoNovoDeveTerInstanteDeCriacao(){
        //cenário
        Produto produto = new Produto("Test",new BigDecimal(100),10,"Produto teste",null,null);
        Compra compra = new Compra(GatewayPagamento.PAGSEGURO,produto,1,null);

        // ação
        Transacao transacao = new Transacao(compra,"123456",StatusTransacao.SUCESSO);
        Integer localdate = LocalDateTime.now().getHour();

        // resultado
        Assertions.assertTrue(transacao.getInstante().getHour()==localdate);
    }

    @Test
    public void deveriaDarErroAoTentarAtualizarUmaCompraDiferenteDaCompraDaTransacao(){
        //cenário
        Produto produto = new Produto("Test",new BigDecimal(100),10,"Produto teste",null,null);

        Compra compra = new Compra(GatewayPagamento.PAGSEGURO,produto,1,null);
        ReflectionTestUtils.setField(compra,"id",1l);

        Compra compraParaAtualizar = new Compra(GatewayPagamento.PAGSEGURO,produto,1,null);
        ReflectionTestUtils.setField(compraParaAtualizar,"id",2l);

        Transacao transacao = new Transacao(compra,"123456",StatusTransacao.SUCESSO);

        // ação | resultado
        Assertions.assertThrows(ErroRegraNegocio.class, () -> transacao.atualizaStatus(compraParaAtualizar,StatusTransacao.FALHA));
    }

    @Test
    public void deveriaDarErroAoTentarModificarUmStatusQueJaEstaSucesso(){
        //cenário
        Produto produto = new Produto("Test",new BigDecimal(100),10,"Produto teste",null,null);

        Compra compra = new Compra(GatewayPagamento.PAGSEGURO,produto,1,null);
        ReflectionTestUtils.setField(compra,"id",1l);

        Compra compraParaAtualizar = new Compra(GatewayPagamento.PAGSEGURO,produto,1,null);
        ReflectionTestUtils.setField(compraParaAtualizar,"id",1l);

        Transacao transacao = new Transacao(compra,"123456",StatusTransacao.SUCESSO);

        // ação | resultado
        Assertions.assertThrows(ErroRegraNegocio.class, () -> transacao.atualizaStatus(compraParaAtualizar,StatusTransacao.FALHA));
    }

    @Test
    public void deveModificarStatus(){
        //cenário
        Produto produto = new Produto("Test",new BigDecimal(100),10,"Produto teste",null,null);

        Compra compra = new Compra(GatewayPagamento.PAGSEGURO,produto,1,null);
        ReflectionTestUtils.setField(compra,"id",1l);

        Compra compraParaAtualizar = new Compra(GatewayPagamento.PAGSEGURO,produto,1,null);
        ReflectionTestUtils.setField(compraParaAtualizar,"id",1l);

        Transacao transacao = new Transacao(compra,"123456",StatusTransacao.FALHA);

        // ação | resultado
        Assertions.assertDoesNotThrow(() -> transacao.atualizaStatus(compraParaAtualizar,StatusTransacao.SUCESSO));
        Assertions.assertTrue(transacao.getStatusTransacao().equals(StatusTransacao.SUCESSO));
    }

}