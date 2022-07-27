package com.reinaldo.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reinaldo.os.domain.Cliente;
import com.reinaldo.os.domain.OS;
import com.reinaldo.os.domain.Tecnico;
import com.reinaldo.os.domain.enums.Prioridade;
import com.reinaldo.os.domain.enums.Status;
import com.reinaldo.os.repositories.ClienteRepository;
import com.reinaldo.os.repositories.OSRepository;
import com.reinaldo.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Valdir Cezar", "973.791.690-50", "(62) 99306-1196");
		Tecnico t2 = new Tecnico(null, "Linus Torvalds", "641.760.040-88", "(62) 94545-4545");
		Cliente c1 = new Cliente(null, "Betina Campos", "885.702.810-05", "(62) 99404-7729");
		
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1, t2));  
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}
}
