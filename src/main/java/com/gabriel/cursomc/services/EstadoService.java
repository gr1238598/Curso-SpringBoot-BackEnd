package com.gabriel.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.cursomc.domain.Estado;
import com.gabriel.cursomc.dto.EstadoDTO;
import com.gabriel.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repo;
	
	
	
	public List<Estado> findAll() {
		
		return repo.findAllByOrderByNome();
		
	}
	
	
	
}
