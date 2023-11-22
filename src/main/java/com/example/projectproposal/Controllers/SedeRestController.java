package com.example.projectproposal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectproposal.Entity.Sede;
import com.example.projectproposal.Service.ISedeService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin({ "http://localhost:4200" }) // cors origin perimitir concectar dos herrramientas en diferentes dominios ,
                                          // en este caso el frontend con el backend
@RestController
@RequestMapping("/proposal")
public class SedeRestController {

   @Autowired // va a buscar el primer candidato , una clase concreta que implementación esta
              // interface y la va a inyectar la cual es sedeserviceimpl
   private ISedeService sedeService;

   @GetMapping("/sedes")
   public List<Sede> index() {
      return sedeService.findAll();
   }

   @GetMapping("/findByIdSede/{id}")
   public ResponseEntity<?> GetById(@PathVariable("id") Long id) {

      Sede sede = null;
      Map<String, Object> response = new HashMap<>(); // instancia de hashmap

      try {
         sede = sedeService.findById(id); // consutar el objeto en la base de datos
      } catch (DataAccessException e) {
         // TODO: handle exception
         response.put("mensaje", "Error al realizar la consulta en la base de datos");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }
      // si no se encuentra el objecto que se busca en la base de datos
      if (sede == null) {
         response.put("mensaje", "El cliente ID:".concat(id.toString().concat(" - no existe en la base de datosQ")));
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<Sede>(sede, HttpStatus.OK);

   }

   @GetMapping("/findByNameSede/{nombreSede}")
   public ResponseEntity<?> buscarPorNombre(@PathVariable("nombreSede") String nombreSede) {

      List<Sede> sedes;
      Map<String, Object> response = new HashMap<>(); // instancia de hashmap

      try {
         sedes = sedeService.findByNombreSedes(nombreSede); // consutar el objeto en la base de datos
         // si no se encuentra el objecto que se busca en la base de datos
         if (sedes == null) {
            response.put("mensaje", "El cliente ID:".concat(nombreSede.concat(" - no existe en la base de datosQ")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
         }

         return new ResponseEntity<List<Sede>>(sedes, HttpStatus.OK);

      } catch (DataAccessException e) {
         // TODO: handle exception
         response.put("mensaje", "Error al realizar la consulta en la base de datos");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }

   }

   @PostMapping("/createSede") // crea el endpoint
   public ResponseEntity<?> create(@Valid @RequestBody Sede sede, BindingResult result) {

      Sede sedeNew = null;
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
         sedeNew = sedeService.save(sede);// metodo para guardar en la base datos
      } catch (DataAccessException e) {
         // TODO: handle exception
         response.put("mensaje", "Error al realizar la insersion en la base de datos");
         response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }
      //
      response.put("mensaje", "El cliente ha sido creado con exito!");
      response.put("cliente", sedeNew);

      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
   }

   @PutMapping("/updateSede/{id}")
   public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Sede sede, BindingResult result) {
      // Sede sedeActual = sedeService.findById(id); // se realiza la consulta para
      // encontrar la sede que esta en la base de datos

      Sede sedeUpdate = null;

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

         sedeUpdate = sedeService.updaSede(sede, id);

         // validacion para saber si encontro la sede en la base de datos

         if (sedeUpdate == null) {

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
      response.put("sede", sedeUpdate);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

      // el metodo sava de la clase crudRespository
      // se utiliza el mismo metodo que en
      // el crear para actualizar esto por
      // detra
      // se entiendo como merger , si el metodo trae un id este lo actuliza de lo
      // contraio crear el objeto en la base de datos
   }

   @DeleteMapping("/deleteSede/{id}")
   public ResponseEntity<?> delete(@PathVariable Long id) {
      Sede sedeActual = sedeService.findById(id);
      Map<String, Object> response = new HashMap<>();

      if (sedeActual == null) {
         response.put("mensaje", "El cliente ID:".concat(id.toString().concat(" - no existe en la base de datos")));
         return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
      }

      try {
         // metodo para eleminar una sede de la base de datos
         sedeService.delete(id);
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
