package com.example.projectproposal.Repository.Dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.projectproposal.Entity.Sede;

public interface SedeRepository extends JpaRepository<Sede,Long> {

    //public List<Sede> finallsSedes(); esta no se ha tocado

    
}
