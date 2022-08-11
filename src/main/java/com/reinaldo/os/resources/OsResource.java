package com.reinaldo.os.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reinaldo.os.dtos.OSDTO;
import com.reinaldo.os.services.OsService;

@RestController
@RequestMapping("/os")
public class OsResource {

	@Autowired
	private OsService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<OSDTO> findById(@PathVariable Integer id){
		OSDTO obj = new OSDTO(service.findById(id));
		return ResponseEntity.ok().body(obj);
	}
	
	
	
	
}
