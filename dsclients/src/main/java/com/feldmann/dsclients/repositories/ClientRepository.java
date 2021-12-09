package com.feldmann.dsclients.repositories;

import com.feldmann.dsclients.entities.Client;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long>{
    
}