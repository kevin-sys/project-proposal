package com.example.projectproposal.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectproposal.Entity.Programa;
import com.example.projectproposal.Service.IProgramaService;
import com.example.projectproposal.Service.ProgramaServiceImpl;

import jakarta.validation.Valid;


@Controller
@RestController
@RequestMapping("/proposal")
public class ProgramaRestController {
     
    @Autowired
    private IProgramaService programaService;


    @GetMapping("/programas")
   public List<Programa> index() {
      return programaService.findAll();
   }

   @GetMapping("/findByIdPrograma/{codigo}")
   public ResponseEntity<?> GetById(@PathVariable("id") Long id) {

      Programa programa = null;
      Map<String, Object> response = new HashMap<>(); // instancia de hashmap

      try {
         programa = programaService.findById(id); // consutar el objeto en la base de datos
      } catch (DataAccessException e) {
         // TODO: handle exception
         response.put("mensaje", "Error al realizar la consulta en la base de datos");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }
      // si no se encuentra el objecto que se busca en la base de datos
      if (programa == null) {
         response.put("mensaje", "El cliente ID:".concat(id.toString().concat(" - no existe en la base de datosQ")));
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<Programa>(programa, HttpStatus.OK);

   }

   @GetMapping("/findByNamePrograma/{nombreprograma}")
   public ResponseEntity<?> buscarPorNombre(@PathVariable("nombreprograma") String nombrePrograma) {

      List<Programa> programa;
      Map<String, Object> response = new HashMap<>(); // instancia de hashmap

      try {
         programa = programaService.findByNombreProgramas(nombrePrograma); // consutar el objeto en la base de datos
         // si no se encuentra el objecto que se busca en la base de datos
         if (programa.isEmpty()) {
            response.put("mensaje", "El cliente ID:".concat(nombrePrograma.concat(" - no existe en la base de datosQ")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
         }

         return new ResponseEntity<List<Programa>>(programa, HttpStatus.OK);

      } catch (DataAccessException e) {
         // TODO: handle exception
         response.put("mensaje", "Error al realizar la consulta en la base de datos");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }

   }

   @PostMapping("/createPrograma") // crea el endpoint
   public ResponseEntity<?> create(@Valid @RequestBody Programa programa, BindingResult result) {

      Programa programaNew = null;
      Map<String, Object> response = new HashMap<>(); // instancia de hashmap para crear un map para el manejo de
                                                      // errores

      if (result.hasErrors()) {

         // Se crea una lista de errores (errores que vienen del front en formato json)
         List<String> errors = result.getFieldErrors().stream().map(
               fieldError -> "El campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage())
               .collect(Collectors.toList());

         response.put("errors", errors);
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

      }
      // manejo de errorees en la consulta en la base de datos
      try {
         programaNew = programaService.save(programa);// metodo para guardar en la base datos
      } catch (DataAccessException e) {
         // TODO: handle exception
         response.put("mensaje", "Error al realizar la insersion en la base de datos");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }
      //
      response.put("mensaje", "El cliente ha sido creado con exito!");
      response.put("cliente", programaNew);

      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
   }

   @PutMapping("/updatePrograma/{id}")
   public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Programa programa, BindingResult result) {
      // Sede sedeActual = sedeService.findById(id); // se realiza la consulta para
      // encontrar la sede que esta en la base de datos

      Programa programaUpdate = null;

      Map<String, Object> response = new HashMap<>(); // instancia de hashmap para crear un map para el manejo de
                                                      // errores
      // valida si encontro algun error de las validaciones que anotamos en la clase
      // entity
      if (result.hasErrors()) {

         // Se crea una lista de errores (errores que vienen del front en formato json)
         List<String> errors = result.getFieldErrors().stream().map(
               fieldError -> "El campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage())
               .collect(Collectors.toList());

         response.put("errors", errors);
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

      }

      try {
         // se agrega los nuevos datos ala sede que se trajo de la base de datos
         // sedeActual.setNombreSede(sede.getNombreSede());
         // sedeActual.setDireccion(sede.getDireccion());
         // sedeActual.setTelefono(sede.getTelefono());
         // sedeActual.setEmail(sede.getEmail());
         // metodo para guardar en la base datos
         // sedeUpdate = sedeService.save(sedeActual);

         programaUpdate = programaService.updatePrograma(programa, id);

         // validacion para saber si encontro la sede en la base de datos

         if (programaUpdate == null) {

            response.put("mensaje", "El cliente ID:".concat(id.toString().concat(" - no existe en la base de datosQ")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
         }
      } catch (DataAccessException e) {
         // TODO: handle exception
         // manejo de exception de la base de datos

         response.put("mensaje", "Error al realizar la actualizacion en la base de datos");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }

      response.put("mensaje", "El cliente ha sido actualizado con exito!");
      response.put("sede", programaUpdate);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

      // el metodo sava de la clase crudRespository
      // se utiliza el mismo metodo que en
      // el crear para actualizar esto por
      // detra
      // se entiendo como merger , si el metodo trae un id este lo actuliza de lo
      // contraio crear el objeto en la base de datos
   }

   @DeleteMapping("/deletePrograma/{id}")
   public ResponseEntity<?> delete(@PathVariable Long id) {
      Programa sedeActual = programaService.findById(id);
      Map<String, Object> response = new HashMap<>();

      if (sedeActual == null) {
         response.put("mensaje", "El cliente ID:".concat(id.toString().concat(" - no existe en la base de datos")));
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
      }

      try {
         // metodo para eleminar una sede de la base de datos
         programaService.delete(id);
      } catch (EmptyResultDataAccessException e) {
         response.put("mensaje", "La sede con el ID " + id + " no existe");
         return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
      } catch (DataAccessException e) {
         // TODO: handle exception
         response.put("mensaje", "Error al realizar el eliminar en la base de datos");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }

      response.put("mensaje", "El cliente ha sido eliminadoo con exito!");
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
   }

    
}
