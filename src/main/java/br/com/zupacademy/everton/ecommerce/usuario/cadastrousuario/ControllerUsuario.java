package br.com.zupacademy.everton.ecommerce.usuario.cadastrousuario;

import br.com.zupacademy.everton.ecommerce.usuario.Usuario;
import br.com.zupacademy.everton.ecommerce.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class ControllerUsuario {

    private final UsuarioRepository repositorio;

    @Autowired
    public ControllerUsuario(UsuarioRepository repo) {
        this.repositorio = repo;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto adicionar(@RequestBody @Valid UsuarioForm formulario){
        Usuario usuario = formulario.paraUsuario();
        repositorio.save(usuario);
        return new UsuarioDto(usuario);
    }

}
