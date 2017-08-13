package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Integer autorId;
	private Autor autor = new Autor();
	private List<Autor> autores = new DAO<Autor>(Autor.class).listaTodos();

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Autor getAutor() {
		return autor;
	}

	public List<Autor> getAutores() {
		System.out.println("Chamou getAutores()");
		if (autores == null) {
			autores = new DAO<Autor>(Autor.class).listaTodos();
		}
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		if(this.autor.getId() == null){
			new DAO<Autor>(Autor.class).adiciona(this.autor);
			autores.add(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
			autores = null;
		}
		this.autor = new Autor();
	}

	public RedirectView vaiPraLivros() {
		return new RedirectView("livro");
	}

	public void deletaAutores(Autor a) {
		new DAO<Autor>(Autor.class).remove(a);
		System.out.println("Atribuiu nulo");
		autores = null;
	}
	
	
	public void carregar(Autor a){
		System.out.println("carregou");
		this.autor = a;
	}
	
	public void carregarAutorPeloId(){
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}
}
