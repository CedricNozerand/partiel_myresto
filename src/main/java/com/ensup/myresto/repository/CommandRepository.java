package com.ensup.myresto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ensup.myresto.domaine.Command;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long>{

	@Query("SELECT c FROM Command c WHERE c.status = :status")
	List<Command> getAllCommandByStatut(String status);
}
