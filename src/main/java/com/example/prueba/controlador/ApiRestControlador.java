package com.example.prueba.controlador;


import com.example.prueba.Negocio.EmpleadoNegocio;
import com.example.prueba.modelo.Empleado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Rest encargado de servir las solicitudes
 */
@RestController
@RequestMapping("api1")
public class ApiRestControlador {
    Logger logger = LoggerFactory.getLogger(ApiRestControlador.class);

    @Autowired
    private EmpleadoNegocio empleadoNegocio;

    /*
Crear Intereses Compuestos
* */
    @PostMapping(value = "/crearEmpleado", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity crearEmpleado(@RequestParam(value = "files") MultipartFile[] files) throws Exception {
        logger.info("Ingreso al método crearEmpleado");

        for (MultipartFile file : files) {
            empleadoNegocio.crearEmpleados(file);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


    @GetMapping(value = "allEmpleados", produces = "application/json")
    public CompletableFuture<ResponseEntity<List<Empleado>>> findAllEmpleados(){
        return empleadoNegocio.findAllEmpleados().thenApply(ResponseEntity::ok);
    }

}
