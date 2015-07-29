package controller;

import java.util.List;

import javax.inject.Inject;

import model.Exame;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import dao.UsuarioRepository;

@Controller
@Path("/usuarios")
public class UsuariosController {
	
	@Inject private Result result;
	@Inject private UsuarioRepository repository;

	@Get("/form")
	public List<Exame> form() {
		List<Exame> list = (List<Exame>) repository.list();
		result.include("exames", list);
		return list;
		
	}
	
	@Get("")
	public List<Exame> listar() {
		List<Exame> list = (List<Exame>) repository.list();
		result.include("exames", list);
		return list;
	}
	
	@Post("/save")
	public void save(Exame usuario) {
		repository.save(usuario);
		result.redirectTo(this).form();
	}
	
	@Path("/editar/{id}")
	public void editar(Long id) {
		Exame u = null;
		if (id != null) {
			u = repository.find(id);
			result.include("exame", u);
		}
	}
	
	@Get("/remover/{id}")
	public void remover(Long id) {
		if (id != null) {
			repository.delete(id);
		}
		result.redirectTo(this).form();
	}
	
}