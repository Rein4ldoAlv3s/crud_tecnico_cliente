package com.reinaldo.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reinaldo.os.domain.Cliente;
import com.reinaldo.os.domain.Tecnico;
import com.reinaldo.os.dtos.ClienteDTO;
import com.reinaldo.os.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findByID(@PathVariable Integer id){
		Cliente obj = service.findById(id);
		ClienteDTO objDTO = new ClienteDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<ClienteDTO> objDTO = service.findAll().stream().map(c -> new ClienteDTO(c)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDTO);
	}
	
		
	@PostMapping
	public ResponseEntity<ClienteDTO> created(@Valid @RequestBody ClienteDTO objDTO){
		Cliente newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDto){
		ClienteDTO newObjDto = new ClienteDTO(service.update(id, objDto));
		return ResponseEntity.ok().body(newObjDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
