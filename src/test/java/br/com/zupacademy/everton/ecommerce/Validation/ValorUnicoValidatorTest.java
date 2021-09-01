package br.com.zupacademy.everton.ecommerce.Validation;

import br.com.zupacademy.everton.ecommerce.categoria.Categoria;
import br.com.zupacademy.everton.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.everton.ecommerce.produto.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ValorUnicoValidatorTest {

    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    Validator validator;

    @BeforeEach
    public void init(){
        produtoRepository.deleteAll();
        categoriaRepository.deleteAll();
    }

    @Test
    public void EntidadeJaExiste(){
        // Cenário
        categoriaRepository.save(new Categoria("Categoria"));

        // Ação
        Set<ConstraintViolation<Teste>> violations = validator.validate(new Teste("Categoria"));

        // Validação
        Assertions.assertEquals(1,violations.size());
    }
    @Test
    public void EntidadeNaoExiste(){
        // Cenário
        categoriaRepository.save(new Categoria("Categoria"));

        // Ação
        Set<ConstraintViolation<Teste>> violations = validator.validate(new Teste("Romario"));

        // Validação
        Assertions.assertTrue(violations.isEmpty());
    }
    public static class Teste{
        @ValorUnico(fieldName = "nome", domainClass = Categoria.class)
        private String nome;

        public Teste(String nome) {
            this.nome = nome;
        }
    }

}