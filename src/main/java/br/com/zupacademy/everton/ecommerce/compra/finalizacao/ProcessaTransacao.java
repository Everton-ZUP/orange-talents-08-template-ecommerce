package br.com.zupacademy.everton.ecommerce.compra.finalizacao;

import br.com.zupacademy.everton.ecommerce.APIFAKE.FiscalForm;
import br.com.zupacademy.everton.ecommerce.APIFAKE.RankingForm;
import br.com.zupacademy.everton.ecommerce.Validation.ErroRegraNegocio;
import br.com.zupacademy.everton.ecommerce.compra.Compra;
import br.com.zupacademy.everton.ecommerce.compra.CompraRepository;
import br.com.zupacademy.everton.ecommerce.utils.EnviadorDeEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProcessaTransacao {

    private StatusTransacao statusSucesso = StatusTransacao.SUCESSO;
    private StatusTransacao statusFalha = StatusTransacao.FALHA;

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ConectorComApisExternas conectorComApisExternas;

    @Autowired
    private EnviadorDeEmail enviadorDeEmail;


    /**
     * Faz o processamento de uma transação que retornou com sucesso
     * @param idCompra id da compra nesta aplicação
     * @param idPagamento id da transação no gateway de pagamento
     */
    public void processaSucesso(Long idCompra, String idPagamento) {
        Compra compra = compraRepository.findById(idCompra).orElseThrow(() -> new ErroRegraNegocio("Esta compra não existe","idCompra",idCompra));

        List<Transacao> transacoesCompra = transacaoRepository.findAllByStatusTransacaoAndCompra_Id(statusSucesso,idCompra);
        if (transacoesCompra.size() == 2){
            throw new ErroRegraNegocio("Já existem duas transações de Sucesso para está compra","idCompra",idCompra);
        }

        Transacao transacao = retornaTransacaoAtualizada(compra, idPagamento,statusSucesso);
        transacaoRepository.save(transacao);

        conectorComApisExternas.notificaFiscal(new FiscalForm(compra.getComprador().getId(),compra.getId()));
        conectorComApisExternas.notificaRanking(new RankingForm(compra.getId(),compra.getProduto().getUsuario().getId()));

        enviadorDeEmail.pagamentoSucesso(compra);
    }

    public void processaFalha(Long idCompra, String idPagamento) {
        Compra compra = compraRepository.findById(idCompra).orElseThrow(() -> new ErroRegraNegocio("Esta compra não existe","idCompra",idCompra));

        Transacao transacao = retornaTransacaoAtualizada(compra, idPagamento,statusFalha);
        transacaoRepository.save(transacao);

        enviadorDeEmail.pagamentoFalhou(compra);

    }

    private Transacao retornaTransacaoAtualizada(Compra compra, String idPagamento, StatusTransacao status) {
        Transacao transacao = transacaoRepository.findByTransacaoOrigem(idPagamento);
        if (transacao == null){
            transacao = new Transacao(compra, idPagamento, status);
        }else{
            transacao = transacao.atualizaStatus(compra, status);
        }
        return transacao;
    }

}
