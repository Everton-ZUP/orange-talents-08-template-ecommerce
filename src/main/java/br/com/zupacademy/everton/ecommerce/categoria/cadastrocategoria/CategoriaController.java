package br.com.zupacademy.everton.ecommerce.categoria.cadastrocategoria;

import br.com.zupacademy.everton.ecommerce.categoria.Categoria;
import br.com.zupacademy.everton.ecommerce.categoria.CategoriaRepository;
import br.com.zupacademy.everton.ecommerce.usuario.Usuario;
import br.com.zupacademy.everton.ecommerce.usuario.cadastrousuario.UsuarioForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repositorio;


    @PostMapping @Transactional @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDto adicionar(@RequestBody @Valid CategoriaForm formulario){
        Categoria categoria = formulario.toCategoria(repositorio);
        repositorio.save(categoria);
        return new CategoriaDto(categoria);
    }

}
