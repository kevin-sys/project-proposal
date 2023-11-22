package com.example.projectproposal.Repository.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projectproposal.Entity.AnexoPdf;

@Repository
public interface IUploadAnexoDao extends JpaRepository<AnexoPdf,Long>{
    

}
