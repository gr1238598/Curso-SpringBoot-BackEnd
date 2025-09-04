package com.gabriel.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gabriel.cursomc.domain.Categoria;
import com.gabriel.cursomc.domain.Produto;
import com.gabriel.cursomc.repositories.CategoriaRepository;
import com.gabriel.cursomc.repositories.ProdutoRepository;
import com.gabriel.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;

	public Optional<Produto> buscar(Integer id) {
		Optional<Produto> p = repo.findById(id);

		if (p.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado! " + id + ", Tipo " + Produto.class.getName());
		}

		return p;

	}
	public Page<Produto> search (String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		
		
		return repo.search(nome,categorias,pageRequest);
		
	}
}
