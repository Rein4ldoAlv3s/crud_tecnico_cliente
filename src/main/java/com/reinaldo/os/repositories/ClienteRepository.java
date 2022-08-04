package com.reinaldo.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reinaldo.os.domain.Cliente;
import com.reinaldo.os.domain.Pessoa;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Query("SELECT obj FROM Pessoa obj WHERE obj.cpf =:cpf")
	Pessoa findByCPF(@Param("cpf") String cpf);
}
