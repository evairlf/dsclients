package com.feldmann.dsclients.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.feldmann.dsclients.dto.ClientDTO;
import com.feldmann.dsclients.dto.ClientSaveDTO;
import com.feldmann.dsclients.entities.Client;
import com.feldmann.dsclients.repositories.ClientRepository;
import com.feldmann.dsclients.services.exceptions.DatabaseException;
import com.feldmann.dsclients.services.exceptions.ResourceNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    
    private ClientRepository repository;

    public ClientService(ClientRepository clientRepository){
        this.repository = clientRepository;
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest){
        Page<Client> list = repository.findAll(pageRequest);
        return list.map(x -> new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> obj = repository.findById(id);
        Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientSaveDTO insert(ClientSaveDTO dto) {
        Client entity = copySaveDtoToEntity(dto);
        entity = repository.save(entity);
        return new ClientSaveDTO(entity);
    }

    public ClientDTO update(Long id, ClientDTO dto) {
        try {
            Client entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("id not found " + id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Integrity Violation");
        }
    }

    private void copyDtoToEntity(ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
    }

    private Client copySaveDtoToEntity(ClientSaveDTO dto){
        Client entity = new Client();
        entity.setName(dto.getName());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        return entity;
    }

    public ClientDTO responseSaveDto(ClientSaveDTO dto){
        ClientDTO entity = new ClientDTO();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity.setIncome(dto.getIncome());
        return entity;
    }

}