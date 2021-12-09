package com.feldmann.dsclients.dto;

import java.io.Serializable;
import java.time.Instant;

import com.feldmann.dsclients.entities.Client;

public class ClientDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
  
    private String name;
    private String cpf;
    private String income;
    private Instant birthDate;
    private Integer children;
    
    public ClientDTO() {
    }

    public ClientDTO(Long id, String name, String cpf, String income, Instant birthDate, Integer children) {
       
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }


    public ClientDTO(String name, String cpf, String income, Instant birthDate, Integer children) {
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client entity) {
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.income = entity.getIncome();
        this.birthDate = entity.getBirthDate();
        this.children = entity.getChildren();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }

    
}