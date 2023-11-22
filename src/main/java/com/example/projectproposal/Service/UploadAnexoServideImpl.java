package com.example.projectproposal.Service;

import java.io.IOException;
import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.projectproposal.Entity.AnexoPdf;
import com.example.projectproposal.Repository.Dao.IUploadAnexoDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UploadAnexoServideImpl implements IUploadAnexoService {

    @Autowired
    private IUploadAnexoDao uploadAnexoDao;

    private final static String UPLOADS_FOLDER = "uploads";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public AnexoPdf guardarArchivoPDF(AnexoPdf archivoPDF) {
        return uploadAnexoDao.save(archivoPDF);

    }

    // metodo para guardar un pdf en la base de datos
    @Override
    public AnexoPdf Save(MultipartFile file) throws IOException {

        AnexoPdf anexoPdf = new AnexoPdf();
   // generamos un valor rando y lo concatenamos con el nombre del archivo para asignarle el valor "unico" 
        String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path rootPath = getPath(uniqueFilename);

        log.info("rootPath: " + rootPath);
          // getIpuntStream obtenermos el archivo y lo copiamos al directorio de rootPatg
        Files.copy(file.getInputStream(), rootPath);//copiamos el objeto a un repostiorio local del projecto
//le damos los valores al objeto anexoPdf atraves de los settter
        anexoPdf.setNombreArchivo(file.getOriginalFilename());
        anexoPdf.setArchivoPDF(file.getBytes());
// guardamos el objeto  con los metodos de jpa 
        return uploadAnexoDao.save(anexoPdf);

    }

    public Path getPath(String filename) {
        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }

}
