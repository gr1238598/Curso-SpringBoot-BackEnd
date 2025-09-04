package com.gabriel.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gabriel.cursomc.domain.Categoria;
import com.gabriel.cursomc.domain.Cliente;
import com.gabriel.cursomc.dto.CategoriaDTO;
import com.gabriel.cursomc.repositories.CategoriaRepository;
import com.gabriel.cursomc.services.exceptions.DataIntegrityException;
import com.gabriel.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Optional<Categoria> find(Integer id) {
		Optional<Categoria> c = repo.findById(id);

		if (c.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado! " + id + ", Tipo " + Categoria.class.getName());

		}
		return c;

	}

	public Categoria insert(Categoria obj) {

		return repo.save(obj);

	}

	public Categoria update(Categoria obj) {

		Categoria newObj = repo.getReferenceById(obj.getId());

		newObj.setNome(obj.getNome());

		return repo.save(newObj);
	}

	public void delete(Categoria obj) {

		find(obj.getId());

		try {
			repo.delete(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
		}

	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}

	public Categoria fromDTO(CategoriaDTO objDto) {

		return new Categoria(objDto.getId(), objDto.getNome());

	}

}
