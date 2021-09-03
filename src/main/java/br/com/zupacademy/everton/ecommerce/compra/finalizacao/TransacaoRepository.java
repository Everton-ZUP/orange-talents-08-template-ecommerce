package br.com.zupacademy.everton.ecommerce.compra.finalizacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository  extends JpaRepository<Transacao,Long> {


    List<Transacao> findAllByStatusTransacaoAndCompra_Id(StatusTransacao toString, Long idCompra);

    Transacao findByTransacaoOrigem(String idPagamento);
}
