package com.reinaldo.os.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reinaldo.os.domain.Cliente;
import com.reinaldo.os.repositories.ClienteRepository;
import com.reinaldo.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente findByID(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		//return obj.orElseThrow( () -> new ObjectNotFoundException(
			//"Objeto não encontrado! " + id + ", Tipo: " + Cliente.class.getName()));		
		
	}
	
}
