package com.reinaldo.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reinaldo.os.domain.Cliente;
import com.reinaldo.os.domain.Pessoa;
import com.reinaldo.os.domain.Tecnico;
import com.reinaldo.os.dtos.ClienteDTO;
import com.reinaldo.os.dtos.TecnicoDTO;
import com.reinaldo.os.repositories.ClienteRepository;
import com.reinaldo.os.services.exceptions.DataIntegrityViolationException;
import com.reinaldo.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! " + id + ", Tipo: " + Tecnico.class.getName()));
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Cliente create(ClienteDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegrityViolationException("CPF já cadastrado na base de dados");
		}
		Cliente newObj = new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
	}
	
	

	public Cliente update(Integer id, @Valid ClienteDTO objDto) {
		Cliente oldObj = findById(id);
		
		if(findByCPF(objDto) != null && findByCPF(objDto).getId() != id ) {
			throw new DataIntegrityViolationException("CPF já está cadastrado no sistema. Informe outro!");
		}
		
		oldObj.setCpf(objDto.getCpf());
		oldObj.setNome(objDto.getNome());
		oldObj.setTelefone(objDto.getTelefone());
		oldObj.setList(null);
		
		return oldObj;
	}



	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = repository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

	
	
}
