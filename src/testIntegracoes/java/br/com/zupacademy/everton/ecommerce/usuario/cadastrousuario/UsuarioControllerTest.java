package br.com.zupacademy.everton.ecommerce.usuario.cadastrousuario;

import br.com.zupacademy.everton.ecommerce.UtilsTests;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioControllerTest extends UtilsTests {

    @Autowired
    MockMvc mockMvc;

    Gson gson = new Gson();

    @Test
    void adicionar() throws Exception {
        UsuarioForm formulario = new UsuarioForm("everton@zup.com.br","123456");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/usuarios")
                        .content(gson.toJson(formulario))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}