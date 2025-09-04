package com.gabriel.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.gabriel.cursomc.domain.Cliente;
import com.gabriel.cursomc.services.validation.ClienteUpdate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@ClienteUpdate
public class ClienteDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Email(message = "Email inválido")
	private String email;
	
	public ClienteDTO() {
		
	}
	
	
	public ClienteDTO(Cliente x) {
		
		this.id = x.getId();
		this.nome = x.getNome();
		this.email = x.getEmail();
		
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
