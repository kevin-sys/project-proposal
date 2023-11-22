package com.example.projectproposal.Service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.example.projectproposal.Entity.AnexoPdf;

public interface IUploadAnexoService {
 
    public AnexoPdf guardarArchivoPDF(AnexoPdf archivoPDF) ;
     
    public AnexoPdf Save(MultipartFile file) throws IOException;

}
