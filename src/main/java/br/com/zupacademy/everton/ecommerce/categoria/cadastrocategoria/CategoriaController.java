package br.com.zupacademy.everton.ecommerce.categoria.cadastrocategoria;

import br.com.zupacademy.everton.ecommerce.categoria.Categoria;
import br.com.zupacademy.everton.ecommerce.categoria.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository repositorio;

    public CategoriaController(CategoriaRepository repo) {
        this.repositorio = repo;
    }

    @PostMapping @Transactional @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDto adicionar(@RequestBody @Valid CategoriaForm formulario){
        Categoria categoria = formulario.toCategoria(repositorio);
        repositorio.save(categoria);
        return new CategoriaDto(categoria);
    }

}
