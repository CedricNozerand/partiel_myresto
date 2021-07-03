package com.ensup.myresto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ensup.myresto.domaine.Command;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long>{

	
}
