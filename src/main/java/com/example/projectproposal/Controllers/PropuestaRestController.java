package com.example.projectproposal.Controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.example.projectproposal.Entity.Propuesta;
import com.example.projectproposal.Service.IUploadAnexoService;
import com.example.projectproposal.Service.UploadAnexoServideImpl;
import com.example.projectproposal.Entity.AnexoPdf;

import jakarta.validation.Valid;

@Controller
@RestController
@RequestMapping("/proposal")
public class PropuestaRestController {

    @Autowired
    IUploadAnexoService uploadAnexoService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file)
           throws IOException  {
        AnexoPdf newAnexoPdf = null;
        Map<String, Object> response = new HashMap<>();

        try {

            // if (file.isEmpty()) {
            //     // String directorio = "C://temp//uploads";
            //     // byte[] bytes = file.getBytes();
            //     // Path rutaCompleta = Paths.get(directorio + "/" + file.getOriginalFilename());
            //     // Files.write(rutaCompleta, bytes);

            //     response.put("cliente", file.getOriginalFilename());
            //     response.put("mensaje", "El archivo ha sido creado con éxito!");

            //     return ResponseEntity.status(HttpStatus.CREATED).body(response);
            // }

            newAnexoPdf = uploadAnexoService.Save(file);
            response.put("archivo", file.getOriginalFilename());
            response.put("mensaje", "El archivo ha sido creado con éxito!");

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

        } catch (MultipartException e) {
             response.put("mensaje", "Error al realizar la insersion en la base de datos");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (DataAccessException e) {
         // TODO: handle exception
         response.put("mensaje", "Error al realizar la insersion en la base de datos");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

}
