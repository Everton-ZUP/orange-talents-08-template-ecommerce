package br.com.zupacademy.everton.ecommerce.compra.finalizacao;

import br.com.zupacademy.everton.ecommerce.UtilsTests;
import br.com.zupacademy.everton.ecommerce.compra.Compra;
import br.com.zupacademy.everton.ecommerce.compra.CompraRepository;
import br.com.zupacademy.everton.ecommerce.compra.GatewayPagamento;
import br.com.zupacademy.everton.ecommerce.produto.Produto;
import br.com.zupacademy.everton.ecommerce.produto.ProdutoRepository;
import br.com.zupacademy.everton.ecommerce.utils.EnviadorDeEmail;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


//@WebMvcTest(controllers = FinalizaCompraController.class)
//@ExtendWith(SpringExtension.class)


class FinalizaCompraControllerTest extends UtilsTests {

    @Autowired
    MockMvc mockMvc;

    Gson gson = new Gson();

    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgZGUgVGVzdGUiLCJzdWIiOiI5IiwiaWF0IjoxNjMwNjc4ODIyLCJleHAiOjE2MzA3NjUyMjJ9.LrPcgZHw-QIvoJFl4EDGJynkuIFK8TRVlSaWFov7Sys";

    @Test
    void pagamentoPagSeguro() throws Exception {
        Compra compra = super.criaCompra(GatewayPagamento.PAGSEGURO);
        PagSeguroForm formulario = new PagSeguroForm(compra.getId(), "123245","SUCESSO");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/pag-seguro")
                        .content(gson.toJson(formulario))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("authorization", "Bearer " + token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void pagamentoPaypal() {
    }
}