package com.gabriel.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.cursomc.domain.Estado;
import com.gabriel.cursomc.dto.EstadoDTO;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
	
	//@Transactional(readOnly=true)
	List<Estado> findAllByOrderByNome();
}
