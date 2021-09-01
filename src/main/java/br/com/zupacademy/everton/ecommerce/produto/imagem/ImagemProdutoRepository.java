package br.com.zupacademy.everton.ecommerce.produto.imagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto,Long> {
    List<ImagemProduto> findByProdutoId(Long id);
}
